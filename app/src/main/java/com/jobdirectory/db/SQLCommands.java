package com.jobdirectory.db;

/**
 * Created by nam on 16-Apr-17.
 */

public class SQLCommands {

    private static final String NOTNULL = " TEXT NOT NULL";
    private static final String NULL = " TEXT NULL";
    private static final String COMMA = ", ";

    /******************* CREATING TABLES *******************/

    //Create Company table
    public static final String SQL_CREATE_COMPANY =
            "CREATE TABLE " + Contract.Company.TABLE_Company + " (" +
                    Contract.Company.COLUMN_Company_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Contract.Company.COLUMN_Company_COMPANY_NAME + NOTNULL + COMMA +
                    Contract.Company.COLUMN_Company_COMPANY_DESCRIPTION_EN + NOTNULL + COMMA +
                    Contract.Company.COLUMN_Company_COMPANY_DESCRIPTION_FR + NOTNULL +
                    ");";

    //Create Location table
    public static final String SQL_CREATE_LOCATION =
            "CREATE TABLE " + Contract.Location.TABLE_Location + " (" +
                    Contract.Location.COLUMN_Location_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Contract.Location.COLUMN_Location_CITY + NOTNULL + COMMA +
                    Contract.Location.COLUMN_Location_ZIP_CODE + NOTNULL + ");";



    //Create Category
    public static final String SQL_CREATE_CATEGORY =
            "CREATE TABLE " + Contract.Category.TABLE_Category + " (" +
                    Contract.Category.COLUMN_Category_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Contract.Category.COLUMN_Category_CATEGORY_NAME_EN + NOTNULL + COMMA +
                    Contract.Category.COLUMN_Category_CATEGORY_NAME_FR + NOTNULL + ");";

    //Create specialization
    public static final String SQL_CREATE_SPECIALIZATION =
            "CREATE TABLE " + Contract.Specialization.TABLE_Specialization + " (" +
                    Contract.Specialization.COLUMN_Specialization_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_EN + NOTNULL + COMMA +
                    Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_TITLE_FR + NOTNULL + COMMA +
                    Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_DESCRIPTION_EN + NOTNULL + COMMA +
                    Contract.Specialization.COLUMN_Specialization_SPECIALIZATION_DESCRIPTION_FR + NOTNULL + COMMA +
                    Contract.Specialization.COLUMN_Specialization_ID_CATEGORY + " INTEGER" + COMMA +
                    "FOREIGN KEY (" + Contract.Specialization.COLUMN_Specialization_ID_CATEGORY + ") REFERENCES " + Contract.Category.TABLE_Category +
                    "(" + Contract.Category.COLUMN_Category_NAME_ENTRY_ID + ") ON DELETE CASCADE);";


    //Create Job_Description
    public static final String SQL_CREATE_JOBDESCRIPTION =
            "CREATE TABLE " + Contract.JobDescription.TABLE_JobDescription + " (" +
                    Contract.JobDescription.COLUMN_JobDescription_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_EN + NOTNULL + COMMA +
                    Contract.JobDescription.COLUMN_JobDescription_NAME_JOB_FR + NOTNULL + COMMA +
                    Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_EN + NOTNULL + COMMA +
                    Contract.JobDescription.COLUMN_JobDescription_JOBDESCRIPTION_FR + NOTNULL + COMMA +
                    Contract.JobDescription.COLUMN_JobDescription_ID_COMPANY + " INTEGER" + COMMA +
                    Contract.JobDescription.COLUMN_JobDescription_ID_LOCATION + " INTEGER" + COMMA +
                    Contract.JobDescription.COLUMN_JobDescription_ID_SPECIALIZATION + " INTEGER" + COMMA +
                    " FOREIGN KEY (" + Contract.JobDescription.COLUMN_JobDescription_ID_COMPANY + ") REFERENCES " + Contract.Company.TABLE_Company +
                    "(" + Contract.Company.COLUMN_Company_NAME_ENTRY_ID + ") ON DELETE CASCADE , " +
                    //FK Location
                    " FOREIGN KEY (" + Contract.JobDescription.COLUMN_JobDescription_ID_LOCATION + ") REFERENCES " + Contract.Location.TABLE_Location +
                    "(" + Contract.Location.COLUMN_Location_NAME_ENTRY_ID + ") ON DELETE CASCADE , " +
                    //FK Specialization
                    " FOREIGN KEY (" + Contract.JobDescription.COLUMN_JobDescription_ID_SPECIALIZATION + ") REFERENCES " + Contract.Specialization.TABLE_Specialization +
                    "(" + Contract.Specialization.COLUMN_Specialization_NAME_ENTRY_ID + ") ON DELETE CASCADE);";

    //Create User
    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + Contract.User.TABLE_User + " (" +
                    Contract.User.COLUMN_User_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    Contract.User.COLUMN_User_USERNAME + NOTNULL + COMMA +
                    Contract.User.COLUMN_User_FIRSTNAME + NOTNULL + COMMA +
                    Contract.User.COLUMN_User_LASTNAME + NOTNULL + COMMA +
                    Contract.User.COLUMN_User_EMAIL + NOTNULL + COMMA +
                    Contract.User.COLUMN_User_PASSWORD + NOTNULL + COMMA +
                    Contract.User.COLUMN_User_ROLE + NOTNULL + COMMA +
                    Contract.User.COLUMN_User_ID_COMPANY + " INTEGER" + COMMA +
                    " FOREIGN KEY (" + Contract.User.COLUMN_User_ID_COMPANY + ") REFERENCES " + Contract.Company.TABLE_Company +
                    "(" + Contract.Company.COLUMN_Company_NAME_ENTRY_ID + ") ON DELETE CASCADE);";


    /******************* DROPPING TABLES *******************/

    public static final String SQL_DELETE_COMPANY =
            "DROP TABLE IF EXISTS " + Contract.Company.TABLE_Company;

    public static final String SQL_DELETE_LOCATION =
            "DROP TABLE IF EXISTS " + Contract.Location.TABLE_Location;

    public static final String SQL_DELETE_CATEGORY =
            "DROP TABLE IF EXISTS " + Contract.Category.TABLE_Category;
    public static final String SQL_DELETE_SPECIALIZATION =
            "DROP TABLE IF EXISTS " + Contract.Specialization.TABLE_Specialization;

    public static final String SQL_DELETE_JOBDESCRIPTION =
            "DROP TABLE IF EXISTS " + Contract.JobDescription.TABLE_JobDescription;

    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + Contract.User.TABLE_User;

}