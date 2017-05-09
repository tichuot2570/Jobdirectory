package com.jobdirectory.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jobdirectory.DataObjects.JobDescription;
import com.jobdirectory.db.Contract;
import com.jobdirectory.db.DBHelper;

import java.util.ArrayList;

/**
 * Created by Vincent_2 on 29.04.2017.
 */

public class JobDescriptionDataSource {

    private SQLiteDatabase db;
    private Context context;

    public JobDescriptionDataSource(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }


    /**
     * Delete a Job announcement
     */

    public void deleteJob(long id) {
        //delete the person
        this.db.delete(Contract.JobDescription.TABLE_JobDescription, Contract.JobDescription.COLUMN_JobDescription_NAME_ENTRY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    /**
     * Update a Job announcement
     */

    public long updateJob(JobDescription job) {

        ContentValues values = new ContentValues();
        values.put(Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_EN, job.getJobName());
        values.put(Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_EN, job.getJobDescription());
        /*
        values.put(Contract.JobDescription.COLUMN_JobDescription_ID_COMPANY, job.getFk_CompanyId());
        values.put(Contract.JobDescription.COLUMN_JobDescription_ID_LOCATION, job.getFk_LocationId());
        values.put(Contract.JobDescription.COLUMN_JobDescription_ID_SPECIALIZATION, job.getFk_SpecializationId());
        values.put(Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_FR, job.getJobNameFR());
        values.put(Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_FR, job.getJobDescriptionFR());
        */

        return this.db.update(Contract.JobDescription.TABLE_JobDescription, values, Contract.JobDescription.COLUMN_JobDescription_NAME_ENTRY_ID + " = ?",
                new String[]{String.valueOf(job.getIdJobDescription())});

    }


    /**
     * Insert a new Job announcement
     */

    public long createJob(JobDescription newJob) {
        long id;
        ContentValues values = new ContentValues();
        values.put(Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_EN, newJob.getJobName());
        values.put(Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_EN, newJob.getJobDescription());
        values.put(Contract.JobDescription.COLUMN_JobDescription_ID_COMPANY, newJob.getFk_CompanyId());
        values.put(Contract.JobDescription.COLUMN_JobDescription_ID_LOCATION, newJob.getFk_LocationId());
        values.put(Contract.JobDescription.COLUMN_JobDescription_ID_SPECIALIZATION, newJob.getFk_SpecializationId());
        values.put(Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_FR, newJob.getJobNameFR());
        values.put(Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_FR, newJob.getJobDescriptionFR());
        id = this.db.insert(Contract.JobDescription.TABLE_JobDescription, null, values);
        return id;
    }


    /**
     * Get jobs by the company ID and logged user
     */

    public ArrayList<JobDescription> getJobDescriptionFromCompany(long loggedUserId) {
        ArrayList<JobDescription> jobDescriptions = new ArrayList<JobDescription>();
        String sql = "SELECT * FROM " + Contract.JobDescription.TABLE_JobDescription + " jd , " +
                Contract.Company.TABLE_Company + " cp , " +
                Contract.User.TABLE_User + " ur , " +
                Contract.Location.TABLE_Location + " lc " +
                " WHERE jd." + Contract.JobDescription.COLUMN_JobDescription_ID_COMPANY + " = cp." + Contract.Company.COLUMN_Company_NAME_ENTRY_ID +
                " AND cp." + Contract.Company.COLUMN_Company_NAME_ENTRY_ID + " = ur." + Contract.User.COLUMN_User_ID_COMPANY +
                " AND jd." + Contract.JobDescription.COLUMN_JobDescription_ID_LOCATION + " = lc." + Contract.Location.COLUMN_Location_NAME_ENTRY_ID +
                " AND ur." + Contract.User.COLUMN_User_ID_COMPANY + " = " + loggedUserId;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                JobDescription jobDescription = new JobDescription();
                jobDescription.setIdJobDescription(cursor.getInt(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_NAME_ENTRY_ID)));
                jobDescription.setJobName(cursor.getString(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_EN)));
                jobDescription.setJobDescription(cursor.getString(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_EN)));
                jobDescription.setCompanyName(cursor.getString(cursor.getColumnIndex(Contract.Company.COLUMN_Company_COMPANY_NAME)));
                jobDescription.setlocationName(cursor.getString(cursor.getColumnIndex(Contract.Location.COLUMN_Location_CITY)));
                jobDescriptions.add(jobDescription);
            } while (cursor.moveToNext());
        }
        return jobDescriptions;
    }


    /**
     * Get jobs from a specific specialization and location
     */

    public ArrayList<JobDescription> getJobDescriptionFrom_Specialization_Location(long idS, long idL) {
        ArrayList<JobDescription> jobDescriptions = new ArrayList<JobDescription>();
        String sql = "SELECT * FROM " + Contract.JobDescription.TABLE_JobDescription + " jd , " +
                Contract.Company.TABLE_Company + " cp , " +
                Contract.Location.TABLE_Location + " lc " +
                " WHERE jd." + Contract.JobDescription.COLUMN_JobDescription_ID_COMPANY + " = cp." + Contract.Company.COLUMN_Company_NAME_ENTRY_ID +
                " AND jd." + Contract.JobDescription.COLUMN_JobDescription_ID_LOCATION + " = lc." + Contract.Location.COLUMN_Location_NAME_ENTRY_ID +
                " AND jd." + Contract.JobDescription.COLUMN_JobDescription_ID_SPECIALIZATION + " = " + idS +
                " AND jd." + Contract.JobDescription.COLUMN_JobDescription_ID_LOCATION + " = " + idL;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                JobDescription jobDescription = new JobDescription();
                jobDescription.setIdJobDescription(cursor.getInt(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_NAME_ENTRY_ID)));
                jobDescription.setJobName(cursor.getString(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_EN)));
                jobDescription.setJobDescription(cursor.getString(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_EN)));
                jobDescription.setCompanyName(cursor.getString(cursor.getColumnIndex(Contract.Company.COLUMN_Company_COMPANY_NAME)));
                jobDescription.setlocationName(cursor.getString(cursor.getColumnIndex(Contract.Location.COLUMN_Location_CITY)));
                jobDescriptions.add(jobDescription);
            } while (cursor.moveToNext());
        }

        return jobDescriptions;
    }


    /**
     * Get a specific job by id. All informations through company and location table also
     */

    public JobDescription getJobById(long id) {
        String sql = "SELECT * FROM " + Contract.JobDescription.TABLE_JobDescription + " jd , " +
                Contract.Company.TABLE_Company + " cp , " +
                Contract.Location.TABLE_Location + " lc " +
                " WHERE jd." + Contract.JobDescription.COLUMN_JobDescription_ID_COMPANY + " = cp." + Contract.Company.COLUMN_Company_NAME_ENTRY_ID +
                " AND jd." + Contract.JobDescription.COLUMN_JobDescription_ID_LOCATION + " = lc." + Contract.Location.COLUMN_Location_NAME_ENTRY_ID +
                " AND " + Contract.JobDescription.COLUMN_JobDescription_NAME_ENTRY_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        JobDescription jobDescription = new JobDescription();
        jobDescription.setIdJobDescription(cursor.getInt(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_NAME_ENTRY_ID)));
        jobDescription.setJobName(cursor.getString(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_EN)));
        jobDescription.setJobDescription(cursor.getString(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_EN)));
        jobDescription.setCompanyName(cursor.getString(cursor.getColumnIndex(Contract.Company.COLUMN_Company_COMPANY_NAME)));
        jobDescription.setlocationName(cursor.getString(cursor.getColumnIndex(Contract.Location.COLUMN_Location_CITY)));
        jobDescription.setFk_SpecializationId(cursor.getInt(cursor.getColumnIndex(Contract.JobDescription.COLUMN_JobDescription_ID_SPECIALIZATION)));

        return jobDescription;
    }

}
