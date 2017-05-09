package com.jobdirectory.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jobdirectory.DataObjects.Category;
import com.jobdirectory.db.Contract;
import com.jobdirectory.db.DBHelper;

import java.util.ArrayList;

/**
 * Created by Vincent_2 on 29.04.2017.
 */

public class CategoryDataSource {

    private SQLiteDatabase db;
    private Context context;

    public CategoryDataSource(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }


    /**
     * Insert a new Category
     */

    public long createCategory(Category newCateory) {
        long id;
        ContentValues values = new ContentValues();
        values.put(Contract.Category.COLUMN_Category_CATEGORY_NAME_EN, newCateory.getName_category());
        values.put(Contract.Category.COLUMN_Category_CATEGORY_NAME_FR, newCateory.getName_categoryFR());
        id = this.db.insert(Contract.Category.TABLE_Category, null, values);
        return id;
    }


    /**
     * Get all Categories
     */
    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> categories = new ArrayList<Category>();
        String sql = "SELECT * FROM " + Contract.Category.TABLE_Category + " ORDER BY " + Contract.Category.COLUMN_Category_CATEGORY_NAME_EN;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setIdCategory(cursor.getInt(cursor.getColumnIndex(Contract.Category.COLUMN_Category_NAME_ENTRY_ID)));
                category.setName_category(cursor.getString(cursor.getColumnIndex(Contract.Category.COLUMN_Category_CATEGORY_NAME_EN)));
                category.setName_categoryFR(cursor.getString(cursor.getColumnIndex(Contract.Category.COLUMN_Category_CATEGORY_NAME_FR)));
                categories.add(category);
            } while (cursor.moveToNext());
        }

        return categories;
    }

}
