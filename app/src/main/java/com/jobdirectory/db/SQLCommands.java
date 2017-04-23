package com.jobdirectory.db;

import static com.jobdirectory.db.Contract.*;

/**
 * Created by nam on 16-Apr-17.
 */

public class SQLCommands {

    private static final String NOTNULL = " TEXT NOT NULL";
    private static final String NULL = " TEXT NULL";
    private static final String COMMA = ", ";

    /******************* CREATING TABLES *******************/

    //Create Location table
    public static final String SQL_CREATE_LOCATION =
            "CREATE TABLE " + Location.TABLE_Location + " (" +
                    Location.COLUMN_Location_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Location.COLUMN_Location_CITY + NOTNULL + COMMA +
                    Location.COLUMN_Location_ZIP_CODE + NOTNULL + ");";

    //Create Company table
    public static final String SQL_CREATE_COMPANY =
            "CREATE TABLE " + Company.TABLE_Company + " (" +
                    Company.COLUMN_Company_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Company.COLUMN_Company_COMPANY_NAME + NOTNULL + COMMA +
                    Company.COLUMN_Company_COMPANY_DESCRIPTION_EN + NOTNULL +
                    Company.COLUMN_Company_COMPANY_DESCRIPTION_FR + NOTNULL +
                    ");";
    //Create Category
    public static final String SQL_CREATE_CATEGORY =
            "CREATE TABLE " + Category.TABLE_Category + " (" +
                    Category.COLUMN_Category_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Category.COLUMN_Category_CATEGORY_NAME_EN + NOTNULL + COMMA +
                    Category.COLUMN_Category_CATEGORY_NAME_FR + NOTNULL + ");";

    //Create Category
    public static final String SQL_CREATE_SPECIALIZATION =
            "CREATE TABLE " + Specialization.TABLE_Specialization + " (" +
                    Specialization.COLUMN_Specialization_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_EN + NOTNULL + COMMA + Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_EN + NOTNULL + COMMA +
                    Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_FR + NOTNULL + COMMA +
                    Specialization.COLUMN_Specialization_SPECIALIZATION_DESCRIPTION_EN + NOTNULL + COMMA +
                    Specialization.COLUMN_Specialization_SPECIALIZATION_DESCRIPTION_FR + NOTNULL + COMMA +
                    Specialization.COLUMN_Specialization_ID_CATEGORY + "INTEGER" + COMMA +
                    "FOREIGN KEY (" + Specialization.COLUMN_Specialization_ID_CATEGORY + ") REFERENCES " + Category.TABLE_Category +
                    "(" + Category.COLUMN_Category_NAME_ENTRY_ID + ") ON DELETE CASCADE);";

    //Create User
    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + User.TABLE_User + " (" +
                    User.COLUMN_User_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    User.COLUMN_User_USERNAME + NOTNULL + COMMA +
                    User.COLUMN_User_FIRSTNAME + NOTNULL + COMMA +
                    User.COLUMN_User_LASTNAME + NOTNULL + COMMA +
                    User.COLUMN_User_EMAIL + NOTNULL + COMMA +
                    User.COLUMN_User_PASSWORD + NOTNULL + COMMA +
                    User.COLUMN_User_ROLE + NOTNULL + COMMA +
                    User.COLUMN_User_ID_COMPANY + "INTEGER" + COMMA +
                    "FOREIGN KEY (" + User.COLUMN_User_ID_COMPANY + ") REFERENCES " + Company.TABLE_Company +
                    "(" + Company.COLUMN_Company_NAME_ENTRY_ID + ") ON DELETE CASCADE);";

    //Create Job_Description
    public static final String SQL_CREATE_JOB_DESCRIPTION =
            "CREATE TABLE " + JobDescription.TABLE_JobDescription + " (" +
                    JobDescription.COLUMN_JobDescription_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    JobDescription.COLUMN_JobDescription_NAME_JOB_EN + NOTNULL + COMMA +
                    JobDescription.COLUMN_JobDescription_NAME_JOB_FR + NOTNULL + COMMA +
                    JobDescription.COLUMN_JobDescription_JOB_DESCRIPTION_EN + NOTNULL + COMMA +
                    JobDescription.COLUMN_JobDescription_JOB_DESCRIPTION_FN + NOTNULL + COMMA +
                    //FK Company
                    JobDescription.COLUMN_JobDescription_ID_COMPANY + "INTEGER" + COMMA +
                    "FOREIGN KEY (" + JobDescription.COLUMN_JobDescription_ID_COMPANY + ") REFERENCES " + Company.TABLE_Company +
                    "(" + Company.COLUMN_Company_NAME_ENTRY_ID + ") ON DELETE CASCADE , " +
                    //FK Location
                    JobDescription.COLUMN_JobDescription_ID_LOCATION + "INTEGER" + COMMA +
                    "FOREIGN KEY (" + JobDescription.COLUMN_JobDescription_ID_LOCATION + ") REFERENCES " + Location.TABLE_Location +
                    "(" + Location.COLUMN_Location_NAME_ENTRY_ID + ") ON DELETE CASCADE , " +
                    //FK Specialization
                    JobDescription.COLUMN_JobDescription_ID_SPECIALIZATION + "INTEGER" + COMMA +
                    "FOREIGN KEY (" + JobDescription.COLUMN_JobDescription_ID_SPECIALIZATION + ") REFERENCES " + Specialization.TABLE_Specialization +
                    "(" + Specialization.COLUMN_Specialization_NAME_ENTRY_ID + ") ON DELETE CASCADE);";


    /******************* DROPPING TABLES *******************/

    public static final String SQL_DELETE_LOCATION =
            "DROP TABLE IF EXISTS " + Location.TABLE_Location;
    public static final String SQL_DELETE_COMPANY =
            "DROP TABLE IF EXISTS " + Company.TABLE_Company;
    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + User.TABLE_User;
    public static final String SQL_DELETE_CATEGORY =
            "DROP TABLE IF EXISTS " + Category.TABLE_Category;
    public static final String SQL_DELETE_SPECIALIZATION =
            "DROP TABLE IF EXISTS " + Specialization.TABLE_Specialization;
    public static final String SQL_DELETE_JOB_DESCRIPTION =
            "DROP TABLE IF EXISTS " + JobDescription.TABLE_JobDescription;
}
