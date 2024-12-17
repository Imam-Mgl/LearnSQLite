package com.imam.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Contacts.db";
    public static final String TABLE_NAME = "contacts";



    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,PHONE_NUMBER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    public boolean createContacts(String name, String phoneNumber) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("PHONE_NUMBER", phoneNumber);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor readAllContacts(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;
        if (sqLiteDatabase != null) { cursor = sqLiteDatabase.rawQuery(query, null);}
        return cursor;
    }

    public void  updateContact(String id, String name, String phoneNumber) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NAME", name);
        contentValues.put("PHONE_NUMBER", phoneNumber);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
    }

    public void deleteContact(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, "ID=?", new String[]{id});
    }
}
