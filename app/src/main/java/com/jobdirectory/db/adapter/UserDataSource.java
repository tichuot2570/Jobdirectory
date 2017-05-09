package com.jobdirectory.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jobdirectory.DataObjects.User;
import com.jobdirectory.db.Contract;
import com.jobdirectory.db.DBHelper;

import java.util.ArrayList;

/**
 * Created by Vincent_2 on 01.05.2017.
 */

public class UserDataSource {


    private SQLiteDatabase db;
    private Context context;

    public UserDataSource(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new User
     */
    public long createUser(User user) {
        long id;
        ContentValues values = new ContentValues();
        values.put(Contract.User.COLUMN_User_USERNAME, user.getUsername());
        values.put(Contract.User.COLUMN_User_FIRSTNAME, user.getFirstname());
        values.put(Contract.User.COLUMN_User_LASTNAME, user.getLastname());
        values.put(Contract.User.COLUMN_User_PASSWORD, user.getPassword());
        values.put(Contract.User.COLUMN_User_ROLE, user.getRole());
        values.put(Contract.User.COLUMN_User_EMAIL, user.getEmail());
        values.put(Contract.User.COLUMN_User_ID_COMPANY, user.getFk_CompanyId());
        id = this.db.insert(Contract.User.TABLE_User, null, values);
        return id;
    }


    /**
     * Get user by a userName
     */

    public User getUser(String name) {
        String sql = "SELECT * FROM " + Contract.User.TABLE_User +
                " WHERE " + Contract.User.COLUMN_User_USERNAME + " = '" + name + "'";

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User();
        user.setUserId(cursor.getInt(cursor.getColumnIndex(Contract.User.COLUMN_User_NAME_ENTRY_ID)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(Contract.User.COLUMN_User_USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(Contract.User.COLUMN_User_PASSWORD)));
        user.setFk_CompanyId(cursor.getInt(cursor.getColumnIndex(Contract.User.COLUMN_User_ID_COMPANY)));

        return user;

    }


    /**
     * Get all users
     */

    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT * FROM " + Contract.User.TABLE_User;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserId(cursor.getInt(cursor.getColumnIndex(Contract.User.COLUMN_User_NAME_ENTRY_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(Contract.User.COLUMN_User_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Contract.User.COLUMN_User_PASSWORD)));
                user.setFk_CompanyId(cursor.getInt(cursor.getColumnIndex(Contract.User.COLUMN_User_ID_COMPANY)));
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;

    }

}
