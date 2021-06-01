package com.example.todo_25;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="SURNAME";
    public static final String COL_4="MARKS";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+"(ID INTERGER PRIMARY KEY,"+"NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String ID,String NAME,String SURNAME,String MARKS ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contextValues=new ContentValues();
        contextValues.put(COL_1,ID);
        contextValues.put(COL_2,NAME);
        contextValues.put(COL_3,SURNAME);
        contextValues.put(COL_4,MARKS);
        long result=db.insert(TABLE_NAME,null,contextValues);
        if(result==-1) {
            return false;
        }
            else{
                return true;
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
