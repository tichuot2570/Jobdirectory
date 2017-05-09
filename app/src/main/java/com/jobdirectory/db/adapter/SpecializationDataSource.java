package com.jobdirectory.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jobdirectory.DataObjects.Specialization;
import com.jobdirectory.db.Contract;
import com.jobdirectory.db.DBHelper;

import java.util.ArrayList;

/**
 * Created by Vincent_2 on 29.04.2017.
 */

public class SpecializationDataSource {

    private SQLiteDatabase db;
    private Context context;

    public SpecializationDataSource(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }


    /**
     * Insert a new Specialization
     */

    public long createSpecialization(Specialization newSpecialization) {
        long id;
        ContentValues values = new ContentValues();
        values.put(Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_EN, newSpecialization.getSpec_Title());
        values.put(Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_FR, newSpecialization.getSpec_TitleFR());
        values.put(Contract.Specialization.COLUMN_Specialization_ID_CATEGORY, newSpecialization.getFk_CategoryId());
        values.put(Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_DESCRIPTION_EN, newSpecialization.getSpec_Description());
        values.put(Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_DESCRIPTION_FR, newSpecialization.getSpec_DescriptionFR());
        id = this.db.insert(Contract.Specialization.TABLE_Specialization, null, values);
        return id;
    }


    /**
     * Get specializations from a specific category
     */

    public ArrayList<Specialization> getSpecializationsFromCategoryId(long id) {
        ArrayList<Specialization> specializations = new ArrayList<Specialization>();
        String sql = "SELECT * FROM " + Contract.Specialization.TABLE_Specialization +
                " WHERE " + Contract.Specialization.COLUMN_Specialization_ID_CATEGORY + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Specialization specialization = new Specialization();
                specialization.setIdSpecialization(cursor.getInt(cursor.getColumnIndex(Contract.Specialization.COLUMN_Specialization_NAME_ENTRY_ID)));
                specialization.setSpec_Title(cursor.getString(cursor.getColumnIndex(Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_EN)));
                specialization.setSpec_TitleFR(cursor.getString(cursor.getColumnIndex(Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_FR)));
                specializations.add(specialization);
            } while (cursor.moveToNext());
        }

        return specializations;
    }


    /**
     * Get all specializations
     */

    public ArrayList<Specialization> getAllSpecializations() {
        ArrayList<Specialization> specializations = new ArrayList<Specialization>();
        String sql = "SELECT * FROM " + Contract.Specialization.TABLE_Specialization + " ORDER BY " + Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_EN;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Specialization specialization = new Specialization();
                specialization.setIdSpecialization(cursor.getInt(cursor.getColumnIndex(Contract.Specialization.COLUMN_Specialization_NAME_ENTRY_ID)));
                specialization.setSpec_Title(cursor.getString(cursor.getColumnIndex(Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_EN)));
                specialization.setSpec_TitleFR(cursor.getString(cursor.getColumnIndex(Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_FR)));
                specializations.add(specialization);
            } while (cursor.moveToNext());
        }

        return specializations;
    }


}
