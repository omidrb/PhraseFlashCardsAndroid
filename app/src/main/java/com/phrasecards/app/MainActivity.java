package com.phrasecards.app;
import android.app.*; import android.os.*; import android.content.*; import android.view.*; import android.widget.*; import androidx.appcompat.app.AppCompatActivity; import org.json.*; import java.util.*;
public class MainActivity extends AppCompatActivity{
 static class Card{String id,lang,p,t,n,e;Card(String a,String b,String c,String d,String f,String g){id=a;lang=b;p=c;t=d;n=f;e=g;}}
 ArrayList<Card> cards=new ArrayList<>(); SharedPreferences sp; Card cur; String lang="English"; TextView phrase,tr,note,ex,stats; LinearLayout ans,rating; Button show,hard,good,add,manage,en,no;
 public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);sp=getSharedPreferences("leitner_v2",MODE_PRIVATE);bind();load();
  en.setOnClickListener(v->{lang="English";next();});no.setOnClickListener(v->{lang="Norwegian";next();});
  show.setOnClickListener(v->{ans.setVisibility(View.VISIBLE);show.setVisibility(View.GONE);rating.setVisibility(View.VISIBLE);});
  hard.setOnClickListener(v->rate(false));good.setOnClickListener(v->rate(true));add.setOnClickListener(v->addDialog());manage.setOnClickListener(v->manageDialog());next();}
 void bind(){phrase=findViewById(R.id.phrase);tr=findViewById(R.id.translation);note=findViewById(R.id.note);ex=findViewById(R.id.example);stats=findViewById(R.id.stats);ans=findViewById(R.id.answerBox);rating=findViewById(R.id.rating);show=findViewById(R.id.show);hard=findViewById(R.id.hard);good=findViewById(R.id.good);add=findViewById(R.id.add);manage=findViewById(R.id.manage);en=findViewById(R.id.english);no=findViewById(R.id.norwegian);}
 void load(){try{
  java.io.InputStream in=getAssets().open("seed_cards.json");
  java.io.ByteArrayOutputStream out=new java.io.ByteArrayOutputStream();
  byte[] buf=new byte[8192]; int n;
  while((n=in.read(buf))!=-1) out.write(buf,0,n);
  in.close();
  JSONArray a=new JSONArray(out.toString("UTF-8"));
  for(int i=0;i<a.length();i++){
    JSONObject o=a.getJSONObject(i);
    cards.add(new Card(o.getString("id"),o.getString("lang"),o.getString("phrase"),o.getString("tr"),o.getString("note"),o.getString("ex")));
  }
 }catch(Exception e){e.printStackTrace();}}
 int box(Card c){return sp.getInt(c.id+"_box",1);} long due(Card c){return sp.getLong(c.id+"_due",0);}
 long interval(int box){long day=86400000L; switch(box){case 1:return 0;case 2:return day;case 3:return 3*day;case 4:return 7*day;default:return 14*day;}}
 void next(){ArrayList<Card> pool=new ArrayList<>(),all=new ArrayList<>();long now=System.currentTimeMillis();for(Card c:cards)if(c.lang.equals(lang)){all.add(c);if(due(c)<=now)pool.add(c);}if(pool.isEmpty())pool=all;if(pool.isEmpty()){phrase.setText("No cards");return;}
  Collections.sort(pool,(a,b)->{int x=Integer.compare(box(a),box(b));if(x!=0)return x;return Long.compare(due(a),due(b));});cur=pool.get(0);
  phrase.setText(cur.p);tr.setText(cur.t);note.setText(cur.n);ex.setText(cur.e.isEmpty()?"":"Example: "+cur.e);ans.setVisibility(View.GONE);rating.setVisibility(View.GONE);show.setVisibility(View.VISIBLE);update();}
 void rate(boolean good){int b=box(cur);b=good?Math.min(5,b+1):1;sp.edit().putInt(cur.id+"_box",b).putLong(cur.id+"_due",System.currentTimeMillis()+interval(b)).putInt("reviews",sp.getInt("reviews",0)+1).apply();next();}
 void update(){int[] n=new int[6];int count=0;for(Card c:cards)if(c.lang.equals(lang)){n[box(c)]++;count++;}stats.setText(lang+" · "+count+" cards · Box 1:"+n[1]+"  2:"+n[2]+"  3:"+n[3]+"  4:"+n[4]+"  5:"+n[5]);}
 void addDialog(){LinearLayout l=new LinearLayout(this);l.setPadding(30,10,30,0);l.setOrientation(LinearLayout.VERTICAL);EditText p=new EditText(this);p.setHint(lang+" phrase");EditText t=new EditText(this);t.setHint("Translation");EditText n=new EditText(this);n.setHint("Usage");EditText e=new EditText(this);e.setHint("Example");l.addView(p);l.addView(t);l.addView(n);l.addView(e);new AlertDialog.Builder(this).setTitle("Add to "+lang).setView(l).setNegativeButton("Cancel",null).setPositiveButton("Save",(d,x)->{if(!p.getText().toString().trim().isEmpty()){cards.add(new Card("c"+System.currentTimeMillis(),lang,p.getText().toString(),t.getText().toString(),n.getText().toString(),e.getText().toString()));next();}}).show();}
 void manageDialog(){ArrayList<Card> list=new ArrayList<>();for(Card c:cards)if(c.lang.equals(lang))list.add(c);String[] a=new String[list.size()];for(int i=0;i<a.length;i++)a[i]="Box "+box(list.get(i))+" — "+list.get(i).p;new AlertDialog.Builder(this).setTitle(lang+" cards").setItems(a,(d,i)->Toast.makeText(this,list.get(i).p,Toast.LENGTH_SHORT).show()).setNegativeButton("Close",null).show();}
}