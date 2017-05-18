package com.jobdirectory.db;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by nam on 16-Apr-17.
 */


public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public static final int DATABASE_VERSION = 23;
    public static final String DATABASE_NAME = "Job.db";
    private static DBHelper instance;
    Context context;
    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        //add ***
        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Location and Category
        Log.i("", SQLCommands.SQL_CREATE_COMPANY);
        Log.i("", SQLCommands.SQL_CREATE_LOCATION);
        Log.i("", SQLCommands.SQL_CREATE_CATEGORY);
        Log.i("", SQLCommands.SQL_CREATE_SPECIALIZATION);
        Log.i("", SQLCommands.SQL_CREATE_USER);
        Log.i("", SQLCommands.SQL_CREATE_JOBDESCRIPTION);


        db.execSQL(SQLCommands.SQL_CREATE_COMPANY);
        db.execSQL(SQLCommands.SQL_CREATE_LOCATION);
        db.execSQL(SQLCommands.SQL_CREATE_CATEGORY);
        db.execSQL(SQLCommands.SQL_CREATE_SPECIALIZATION);
        db.execSQL(SQLCommands.SQL_CREATE_USER);
        db.execSQL(SQLCommands.SQL_CREATE_JOBDESCRIPTION);


        Log.i("", "db created");

    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
            //Enable foreign key support
            instance.db.execSQL("PRAGMA foreign_keys = ON;");
        }

        return instance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQLCommands.SQL_DELETE_CATEGORY);
        db.execSQL(SQLCommands.SQL_DELETE_COMPANY);
        db.execSQL(SQLCommands.SQL_DELETE_LOCATION);
        db.execSQL(SQLCommands.SQL_DELETE_USER);
        db.execSQL(SQLCommands.SQL_DELETE_SPECIALIZATION);
        db.execSQL(SQLCommands.SQL_DELETE_JOBDESCRIPTION);

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


    //DB Manager tool, found on the web

    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }



}