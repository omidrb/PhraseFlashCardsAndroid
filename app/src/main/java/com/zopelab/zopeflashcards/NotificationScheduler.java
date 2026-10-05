package com.zopelab.zopeflashcards;
import android.app.*;import android.content.*;import java.util.*;
public class NotificationScheduler{
 static final String PREF="zope_flashcards_v81";
 public static void ensureScheduled(Context c){if(c.getSharedPreferences(PREF,Context.MODE_PRIVATE).getBoolean("notif_enabled",false))schedule(c);}
 public static void schedule(Context c){
  android.content.SharedPreferences sp=c.getSharedPreferences(PREF,Context.MODE_PRIVATE);int n=Math.max(1,Math.min(5,sp.getInt("notif_count",1)));AlarmManager am=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);if(am==null)return;
  for(int i=0;i<5;i++)cancelSlot(c,am,i);for(int i=0;i<n;i++)scheduleSlot(c,i,false);
 }
 public static void scheduleNextDay(Context c,int slot){scheduleSlot(c,slot,true);}
 static void cancelSlot(Context c,AlarmManager am,int slot){Intent in=new Intent(c,NotificationReceiver.class).putExtra("slot",slot);PendingIntent pi=PendingIntent.getBroadcast(c,7100+slot,in,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);am.cancel(pi);}
 static void scheduleSlot(Context c,int slot,boolean forceTomorrow){
  android.content.SharedPreferences sp=c.getSharedPreferences(PREF,Context.MODE_PRIVATE);if(!sp.getBoolean("notif_enabled",false))return;int n=Math.max(1,Math.min(5,sp.getInt("notif_count",1)));if(slot<0||slot>=n)return;int h=sp.getInt("notif_hour",18),m=sp.getInt("notif_minute",0);AlarmManager am=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);if(am==null)return;Calendar cal=Calendar.getInstance();cal.set(Calendar.HOUR_OF_DAY,h);cal.set(Calendar.MINUTE,m);cal.set(Calendar.SECOND,0);cal.set(Calendar.MILLISECOND,0);cal.add(Calendar.HOUR_OF_DAY,slot);if(forceTomorrow)cal.add(Calendar.DAY_OF_YEAR,1);else while(cal.getTimeInMillis()<=System.currentTimeMillis())cal.add(Calendar.DAY_OF_YEAR,1);Intent in=new Intent(c,NotificationReceiver.class).putExtra("slot",slot);PendingIntent pi=PendingIntent.getBroadcast(c,7100+slot,in,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);if(android.os.Build.VERSION.SDK_INT>=23)am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pi);else am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pi);
 }
}
