package com.jobdirectory.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jobdirectory.DataObjects.Company;
import com.jobdirectory.db.Contract;
import com.jobdirectory.db.DBHelper;

/**
 * Created by Vincent_2 on 02.05.2017.
 */

public class CompanyDataSource {

    private SQLiteDatabase db;
    private Context context;

    public CompanyDataSource(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }


    /**
     * Insert a new Company
     */

    public long createCompany(Company newCompany) {
        long id;
        ContentValues values = new ContentValues();
        values.put(Contract.Company.COLUMN_Company_COMPANY_NAME, newCompany.getName_company());
        values.put(Contract.Company.COLUMN_Company_COMPANY_DESCRIPTION_EN, newCompany.getDescription_company());
        values.put(Contract.Company.COLUMN_Company_COMPANY_DESCRIPTION_FR, newCompany.getDescription_companyFR());
        id = this.db.insert(Contract.Company.TABLE_Company, null, values);
        return id;
    }

}
