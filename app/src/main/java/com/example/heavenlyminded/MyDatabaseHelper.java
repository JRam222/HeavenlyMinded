package com.example.heavenlyminded;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SharedEvents.db";
    private static final String TABLE_NAME = "SHARED_EVENTS";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " +
                TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TYPE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addEvent(String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TYPE, type);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            Toast.makeText(context, "Add Event Failed.", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Event Added Successfully.", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return  cursor;
    }
}
