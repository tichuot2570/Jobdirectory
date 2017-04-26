package com.jobdirectory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nam on 16-Apr-17.
 */


public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Job.db";

    public DBHelper(Context context) {
        //super(context, name, factory, version);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        //add ***
        //db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Location and Category
        Log.i("", SQLCommands.SQL_CREATE_LOCATION);
        Log.i("", SQLCommands.SQL_CREATE_CATEGORY);
        Log.i("", SQLCommands.SQL_CREATE_COMPANY);
        Log.i("", SQLCommands.SQL_CREATE_SPECIALIZATION);
        Log.i("", SQLCommands.SQL_CREATE_USER);
        //Log.i("", SQLCommands.SQL_CREATE_JOB_DESCRIPTION);

        db.execSQL(SQLCommands.SQL_CREATE_LOCATION);
        db.execSQL(SQLCommands.SQL_CREATE_CATEGORY);
        db.execSQL(SQLCommands.SQL_CREATE_COMPANY);
        db.execSQL(SQLCommands.SQL_CREATE_SPECIALIZATION);
        db.execSQL(SQLCommands.SQL_CREATE_USER);
        //db.execSQL(SQLCommands.SQL_CREATE_JOB_DESCRIPTION);
        Log.i("", "db created");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLCommands.SQL_DELETE_CATEGORY);
        db.execSQL(SQLCommands.SQL_DELETE_COMPANY);
        db.execSQL(SQLCommands.SQL_DELETE_LOCATION);
        db.execSQL(SQLCommands.SQL_DELETE_USER);
        db.execSQL(SQLCommands.SQL_DELETE_SPECIALIZATION);
        //db.execSQL(SQLCommands.SQL_DELETE_JOB_DESCRIPTION);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}