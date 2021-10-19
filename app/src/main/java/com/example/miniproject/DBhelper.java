package com.example.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
//import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context,"Userdata.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table adminLogin(userid TEXT primary key,password TEXT)");
        DB.execSQL("create Table workerLogin(userid TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists adminLogin");
        DB.execSQL("drop table if exists workerLogin");
    }
    public void insertAdmin(String userid,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid",userid);
        contentValues.put("password",password);
        long result = DB.insert("adminLogin",null,contentValues);
        /*if(result == -1)
            return false;
        else
            return true;*/
    }
    public void insertWorker(String userid,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid",userid);
        contentValues.put("password",password);
        long result = DB.insert("workerLogin",null,contentValues);
        /*if(result == -1)
            return false;
        else
            return true;*/
    }
    public Boolean updateAdmin(String userid,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid",userid);
        contentValues.put("password",password);
        Cursor cursor = DB.rawQuery("Select * from adminLogin where userid = ?", new String[]{userid});
        if (cursor.getCount() > 0) {
            long result = DB.update("adminLogin", contentValues, "userid=?", new String[]{userid});
            if (result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    public Boolean deletetuserdata(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        //ContentValues contentValues = new ContentValues();
        // contentValues.put("name",name);
        //contentValues.put("contact", contact);
        //contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name=?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    public Boolean validateAdmin(String userid,String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from adminLogin", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(userid ) ){
                if(cursor.getString(1).equals(password)) {
                    return true;
                }
            }
        }
        return  false;
    }
    public Boolean validateWorker(String userid,String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from workerLogin", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(userid ) ){
                if(cursor.getString(1).equals(password)) {
                    return true;
                }
            }
        }
        return  false;
    }
}