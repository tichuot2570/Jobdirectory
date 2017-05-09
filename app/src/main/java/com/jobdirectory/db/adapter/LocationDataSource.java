package com.jobdirectory.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jobdirectory.DataObjects.Location;
import com.jobdirectory.db.Contract;
import com.jobdirectory.db.DBHelper;

import java.util.ArrayList;

/**
 * Created by nam on 28-Apr-17.
 */

public class LocationDataSource {
    private SQLiteDatabase db;
    private Context context;

    public LocationDataSource(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new Location
     */
    public long createLocation(Location location) {
        long id;
        ContentValues values = new ContentValues();
        values.put(Contract.Location.COLUMN_Location_CITY, location.getName_location());
        values.put(Contract.Location.COLUMN_Location_ZIP_CODE, location.getZipCode());
        id = this.db.insert(Contract.Location.TABLE_Location, null, values);
        return id;
    }


    /**
     * Get all Locations
     */
    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> locations = new ArrayList<Location>();
        String sql = "SELECT * FROM " + Contract.Location.TABLE_Location + " ORDER BY " + Contract.Location.COLUMN_Location_CITY;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setIdLocation(cursor.getInt(cursor.getColumnIndex(Contract.Location.COLUMN_Location_NAME_ENTRY_ID)));
                location.setName_location(cursor.getString(cursor.getColumnIndex(Contract.Location.COLUMN_Location_CITY)));
                location.setZipCode(cursor.getString(cursor.getColumnIndex(Contract.Location.COLUMN_Location_ZIP_CODE)));
                locations.add(location);
            } while (cursor.moveToNext());
        }
        return locations;
    }

}
