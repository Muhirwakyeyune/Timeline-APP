package com.example.timeline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.metrics.Event;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper
{
    public static final String  DBName="Timelines";
    public static final int DBVersion= 1;
    public DbHelper(Context context){
        super(context,DBName, null, DBVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("create table Timelines(Event TEXT primary key, Date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
     Db.execSQL("drop table if exists Timelines");
    }
    public boolean InsertTimeline(String Event, String Date){
     SQLiteDatabase Db =this.getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put("Event", Event);
        CV.put("Date", Date);
        long result=Db.insert("Timelines", null, CV);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean UpdateEvent(String Event, String Date){
        SQLiteDatabase Db =this.getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put("Event", Event);
        CV.put("Date", Event);
        Cursor cursor= Db.rawQuery( "select * from Timelines where Headlline=?",new String[]{Event});
        if(cursor.getCount()>0){
            long result= Db.update("Timelines", CV, "Headline=?",new String[]{Event});
                    if(result==-1){
                        return false;
                    }
                    else{
                        return true;
                    }

        }
        else{
            return false;
        }
    }
    public boolean deleteEvent(String Event, String Date){
        SQLiteDatabase Db =this.getWritableDatabase();

        Cursor cursor= Db.rawQuery( "select * from Timelines where Headlline=?",new String[]{Event});
        if(cursor.getCount()>0){
            long result= Db.delete("Timelines", "Headline=?",new String[]{Event});
            if(result==-1){
                return false;
            }
            else{
                return true;
            }

        }
        else{
            return false;
        }
    }
    public ArrayList<String> geData() {
        ArrayList<String> Timeline = new ArrayList<>();
        SQLiteDatabase Db = this.getReadableDatabase();
        Cursor cursor = Db.rawQuery("select * from Timelines", null);
        Timeline timelines = null;
        while (cursor.moveToNext()) {
            String Headlines = cursor.getString(0);
            String MAGAZINE = cursor.getString(1);
        Timeline   TLines = new Timeline(Headlines, MAGAZINE);
        TLines.add(Timeline);
        }
        return Timeline;
    }
}