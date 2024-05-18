package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "Student_Table";
    public static final String COL1 = "ID";
    public static final String COL2 = "Name";
    public static final String COL3 = "Marks";
    public static final String COL4 = "Remarks";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT, MARKS INTEGER, REMARKS TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    }
    public boolean insertData(String name, String marks, String remarks){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,marks);
        contentValues.put(COL4,remarks);
        Long success = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (success==-1){
            return false;
        }else {
            return true;
        }
    }
public Cursor getALlData(){
    SQLiteDatabase myDb = this.getWritableDatabase();
    Cursor cur = myDb.rawQuery("select * from " + TABLE_NAME, null);
    return cur;
}
public boolean updateData(String id, String name, String marks, String remarks){
    SQLiteDatabase updateData;
    updateData = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COL1, id);
    contentValues.put(COL2, name);
    contentValues.put(COL3, marks);
    contentValues.put(COL4, remarks);
    updateData.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
    return true;
}
public Integer deleteData(String id){
    SQLiteDatabase deledata = this.getWritableDatabase();
    return deledata.delete(TABLE_NAME, "ID=?", new String[]{id});
    }
}
