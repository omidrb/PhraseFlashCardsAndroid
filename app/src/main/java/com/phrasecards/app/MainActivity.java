package com.phrasecards.app;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
 static class Card {
  String id,lang,p,t,n,e;
  Card(String a,String b,String c,String d,String f,String g){id=a;lang=b;p=c;t=d;n=f;e=g;}
 }
 ArrayList<Card> cards=new ArrayList<>();
 ArrayList<String> decks=new ArrayList<>();
 SharedPreferences sp;
 Card cur;
 String lang="English";
 int currentPos=0;
 TextView phrase,tr,note,ex,stats,cardNumber;
 LinearLayout ans,rating,deckButtons,studyArea;
 View goodBar,hardBar,unseenBar;
 Button show,hard,good,add,manage,addDeck;
 float downX,downY;

 @Override public void onCreate(Bundle b){
  super.onCreate(b); setContentView(R.layout.activity_main);
  sp=getSharedPreferences("leitner_v3",MODE_PRIVATE); bind(); load(); loadCustomCards(); loadDecks(); renderDeckButtons();
  show.setOnClickListener(v->{ans.setVisibility(View.VISIBLE);show.setVisibility(View.GONE);rating.setVisibility(View.VISIBLE);});
  hard.setOnClickListener(v->rate(false)); good.setOnClickListener(v->rate(true));
  add.setOnClickListener(v->addDialog()); manage.setOnClickListener(v->manageDialog()); addDeck.setOnClickListener(v->addDeckDialog());
  studyArea.setOnTouchListener((v,event)->{
   if(event.getAction()==MotionEvent.ACTION_DOWN){downX=event.getX();downY=event.getY();return true;}
   if(event.getAction()==MotionEvent.ACTION_UP){
    float dx=event.getX()-downX, dy=event.getY()-downY;
    if(Math.abs(dx)>120 && Math.abs(dx)>Math.abs(dy)){skip(dx>0?-1:1);return true;}
   } return true;
  });
  next();
 }
 void bind(){
  phrase=findViewById(R.id.phrase);tr=findViewById(R.id.translation);note=findViewById(R.id.note);ex=findViewById(R.id.example);
  stats=findViewById(R.id.stats);cardNumber=findViewById(R.id.cardNumber);ans=findViewById(R.id.answerBox);rating=findViewById(R.id.rating);
  show=findViewById(R.id.show);hard=findViewById(R.id.hard);good=findViewById(R.id.good);add=findViewById(R.id.add);
  manage=findViewById(R.id.manage);addDeck=findViewById(R.id.addDeck);deckButtons=findViewById(R.id.deckButtons);
  studyArea=findViewById(R.id.studyArea);goodBar=findViewById(R.id.goodProgress);hardBar=findViewById(R.id.hardProgress);unseenBar=findViewById(R.id.unseenProgress);
 }
 void load(){
  try{
   java.io.InputStream in=getAssets().open("seed_cards.json");java.io.ByteArrayOutputStream out=new java.io.ByteArrayOutputStream();
   byte[] buf=new byte[8192];int n;while((n=in.read(buf))!=-1)out.write(buf,0,n);in.close();
   JSONArray a=new JSONArray(out.toString("UTF-8"));
   for(int i=0;i<a.length();i++){JSONObject o=a.getJSONObject(i);String id=o.getString("id");if(!sp.getBoolean(id+"_deleted",false))cards.add(new Card(id,o.getString("lang"),o.getString("phrase"),o.getString("tr"),o.getString("note"),o.getString("ex")));}
  }catch(Exception e){e.printStackTrace();}
 }
 void loadCustomCards(){
  try{JSONArray a=new JSONArray(sp.getString("customCards","[]"));for(int i=0;i<a.length();i++){JSONObject o=a.getJSONObject(i);cards.add(new Card(o.getString("id"),o.getString("lang"),o.getString("p"),o.optString("t"),o.optString("n"),o.optString("e")));}}catch(Exception ignored){}
 }
 void saveCustomCards(){
  JSONArray a=new JSONArray();try{for(Card c:cards)if(c.id.startsWith("c")){JSONObject o=new JSONObject();o.put("id",c.id);o.put("lang",c.lang);o.put("p",c.p);o.put("t",c.t);o.put("n",c.n);o.put("e",c.e);a.put(o);}}catch(Exception ignored){}
  sp.edit().putString("customCards",a.toString()).apply();
 }
 void loadDecks(){
  decks.add("English");decks.add("Norwegian");
  try{JSONArray a=new JSONArray(sp.getString("decks","[]"));for(int i=0;i<a.length();i++){String d=a.getString(i);if(!decks.contains(d))decks.add(d);}}catch(Exception ignored){}
 }
 void saveDecks(){JSONArray a=new JSONArray();for(String d:decks)if(!d.equals("English")&&!d.equals("Norwegian"))a.put(d);sp.edit().putString("decks",a.toString()).apply();}
 void renderDeckButtons(){
  deckButtons.removeAllViews();
  for(String d:decks){
   Button b=new Button(this);b.setText(d);b.setAllCaps(false);b.setTextSize(13);
   b.setTextColor(Color.parseColor(d.equals(lang)?"#FFFFFF":"#374151"));
   b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor(d.equals(lang)?"#2563EB":"#E5E7EB")));
   b.setOnClickListener(v->{lang=d;currentPos=0;renderDeckButtons();next();});
   LinearLayout.LayoutParams bp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,44);
   bp.setMargins(2,1,2,1); deckButtons.addView(b,bp);
  }
 }
 int box(Card c){return sp.getInt(c.id+"_box",1);}
 long due(Card c){return sp.getLong(c.id+"_due",0);}
 int result(Card c){return sp.getInt(c.id+"_result",0);} // 1 good, -1 hard, 0 unseen
 long interval(int b){long d=86400000L;return b<=1?0:b==2?d:b==3?3*d:b==4?7*d:14*d;}
 ArrayList<Card> ordered(){
  ArrayList<Card> dueList=new ArrayList<>(),future=new ArrayList<>();long now=System.currentTimeMillis();
  for(Card c:cards)if(c.lang.equals(lang)){if(due(c)<=now)dueList.add(c);else future.add(c);}
  Comparator<Card> cmp=(a,b)->{int x=Integer.compare(box(a),box(b));return x!=0?x:Long.compare(due(a),due(b));};
  Collections.sort(dueList,cmp);Collections.sort(future,cmp);dueList.addAll(future);return dueList;
 }
 void next(){
  ArrayList<Card> list=ordered();if(list.isEmpty()){cur=null;phrase.setText("No cards yet — tap + Card");cardNumber.setText(lang);update();return;}
  if(currentPos>=list.size())currentPos=0;if(currentPos<0)currentPos=list.size()-1;cur=list.get(currentPos);showCard(list.size());
 }
 void showCard(int total){
  phrase.setText(cur.p);tr.setText(cur.t);note.setText(cur.n);ex.setText(cur.e.isEmpty()?"":"Example: "+cur.e);
  cardNumber.setText("Card "+(currentPos+1)+" of "+total+"  •  Leitner Box "+box(cur));
  ans.setVisibility(View.GONE);rating.setVisibility(View.GONE);show.setVisibility(View.VISIBLE);update();
 }
 void skip(int delta){currentPos+=delta;next();}
 void rate(boolean isGood){
  if(cur==null)return;int b=box(cur);b=isGood?Math.min(5,b+1):1;
  sp.edit().putInt(cur.id+"_box",b).putLong(cur.id+"_due",System.currentTimeMillis()+interval(b))
   .putInt(cur.id+"_result",isGood?1:-1).putInt("reviews",sp.getInt("reviews",0)+1).apply();
  currentPos++;next();
 }
 void update(){
  int total=0,g=0,h=0;for(Card c:cards)if(c.lang.equals(lang)){total++;if(result(c)==1)g++;else if(result(c)==-1)h++;}
  int u=Math.max(0,total-g-h);setWeight(goodBar,g);setWeight(hardBar,h);setWeight(unseenBar,u);
  stats.setText("Good "+g+"   •   Hard "+h+"   •   Unreviewed "+u+"   •   Total "+total);
 }
 void setWeight(View v,int w){LinearLayout.LayoutParams p=(LinearLayout.LayoutParams)v.getLayoutParams();p.weight=Math.max(0,w);p.width=0;v.setLayoutParams(p);}

 void addDialog(){
  LinearLayout l=form();EditText p=(EditText)l.getChildAt(0),t=(EditText)l.getChildAt(1),n=(EditText)l.getChildAt(2),e=(EditText)l.getChildAt(3);
  new AlertDialog.Builder(this).setTitle("Add card to "+lang).setView(l).setNegativeButton("Cancel",null).setPositiveButton("Save",(d,x)->{
   if(!p.getText().toString().trim().isEmpty()){cards.add(new Card("c"+System.currentTimeMillis(),lang,p.getText().toString().trim(),t.getText().toString(),n.getText().toString(),e.getText().toString()));saveCustomCards();next();}
  }).show();
 }
 LinearLayout form(){
  LinearLayout l=new LinearLayout(this);l.setPadding(35,5,35,0);l.setOrientation(LinearLayout.VERTICAL);
  String[] hints={"Phrase","Meaning / translation","Usage note","Example"};for(String h:hints){EditText x=new EditText(this);x.setHint(h);l.addView(x);}return l;
 }
 void manageDialog(){
  ArrayList<Card> list=new ArrayList<>();for(Card c:cards)if(c.lang.equals(lang))list.add(c);
  String[] names=new String[list.size()];for(int i=0;i<names.length;i++)names[i]="Box "+box(list.get(i))+"  •  "+list.get(i).p;
  new AlertDialog.Builder(this).setTitle(lang+" — "+list.size()+" cards").setItems(names,(d,i)->cardActions(list.get(i))).setNegativeButton("Close",null).show();
 }
 void cardActions(Card c){
  new AlertDialog.Builder(this).setTitle(c.p).setItems(new String[]{"Delete card","Reset Leitner progress"},(d,which)->{
   if(which==0)new AlertDialog.Builder(this).setTitle("Delete card?").setMessage(c.p).setNegativeButton("Cancel",null).setPositiveButton("Delete",(q,x)->{
    cards.remove(c);sp.edit().putBoolean(c.id+"_deleted",true).remove(c.id+"_box").remove(c.id+"_due").remove(c.id+"_result").apply();saveCustomCards();next();
   }).show();
   else {sp.edit().remove(c.id+"_box").remove(c.id+"_due").remove(c.id+"_result").apply();next();}
  }).show();
 }
 void addDeckDialog(){
  EditText input=new EditText(this);input.setHint("e.g. German, Spanish, French");
  new AlertDialog.Builder(this).setTitle("Add language / deck").setView(input).setNegativeButton("Cancel",null).setPositiveButton("Add",(d,x)->{
   String name=input.getText().toString().trim();if(!name.isEmpty()&&!decks.contains(name)){decks.add(name);saveDecks();lang=name;renderDeckButtons();next();}
  }).show();
 }
}