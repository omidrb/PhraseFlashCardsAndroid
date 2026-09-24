package com.phrasecards.app;
import android.app.*;import android.content.*;import java.util.*;
public class NotificationScheduler{
 static final String PREF="zope_flashcards_v81";
 public static void ensureScheduled(Context c){if(c.getSharedPreferences(PREF,Context.MODE_PRIVATE).getBoolean("notif_enabled",false))schedule(c);}
 public static void schedule(Context c){
  android.content.SharedPreferences sp=c.getSharedPreferences(PREF,Context.MODE_PRIVATE);int h=sp.getInt("notif_hour",18),m=sp.getInt("notif_minute",0),n=Math.max(1,Math.min(5,sp.getInt("notif_count",1)));AlarmManager am=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);if(am==null)return;
  for(int i=0;i<5;i++){Intent old=new Intent(c,NotificationReceiver.class);old.putExtra("slot",i);PendingIntent pi=PendingIntent.getBroadcast(c,7100+i,old,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);am.cancel(pi);}
  long gap=24L*60L/n;for(int i=0;i<n;i++){Calendar cal=Calendar.getInstance();cal.set(Calendar.HOUR_OF_DAY,h);cal.set(Calendar.MINUTE,m);cal.set(Calendar.SECOND,0);cal.set(Calendar.MILLISECOND,0);cal.add(Calendar.MINUTE,(int)(i*gap));while(cal.getTimeInMillis()<=System.currentTimeMillis())cal.add(Calendar.DAY_OF_YEAR,1);Intent in=new Intent(c,NotificationReceiver.class);in.putExtra("slot",i);PendingIntent pi=PendingIntent.getBroadcast(c,7100+i,in,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);am.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);}
 }
}
