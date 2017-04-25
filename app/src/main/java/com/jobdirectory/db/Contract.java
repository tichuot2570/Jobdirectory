package com.jobdirectory.db;

import android.provider.BaseColumns;

/**
 * Created by nam on 16-Apr-17.
 */

public class Contract {

    public Contract() {
    }

    /*
    //table Job Description
    public static abstract class JobDescription implements BaseColumns {
        public static final String TABLE_JobDescription = "JobDescription";

        public static final String COLUMN_JobDescription_NAME_ENTRY_ID = "idJobDescription";
        public static final String COLUMN_JobDescription_NAME_JOB_EN = "Name_job_EN";
        public static final String COLUMN_JobDescription_NAME_JOB_FR = "Name_job_FR";
        public static final String COLUMN_JobDescription_JOB_DESCRIPTION_EN = "Job_description_EN";
        public static final String COLUMN_JobDescription_JOB_DESCRIPTION_FN = "Job_description_FR";


        // foreign key! idSpecialization, idLocation, idCompany
        public static final String COLUMN_JobDescription_ID_SPECIALIZATION = "idSpecialization";
        public static final String COLUMN_JobDescription_ID_LOCATION = "idLocation";
        public static final String COLUMN_JobDescription_ID_COMPANY = "idCompany";

    }
    */


    //table Location
    public static abstract class Location implements BaseColumns {
        public static final String TABLE_Location = "Location";

        public static final String COLUMN_Location_NAME_ENTRY_ID = "idLocation";
        public static final String COLUMN_Location_CITY = "City";
        public static final String COLUMN_Location_ZIP_CODE = "ZipCode";
    }

    //table Company
    public static abstract class Company implements BaseColumns {
        public static final String TABLE_Company = "Company";

        public static final String COLUMN_Company_NAME_ENTRY_ID = "idCompany";
        public static final String COLUMN_Company_COMPANY_NAME = "Company_Name";
        public static final String COLUMN_Company_COMPANY_DESCRIPTION_EN = "Company_Description_EN";
        public static final String COLUMN_Company_COMPANY_DESCRIPTION_FR = "Company_Description_FR";
    }

    //table Specialization
    public static abstract class Specialization implements BaseColumns {
        public static final String TABLE_Specialization = "Specialization";

        public static final String COLUMN_Specialization_NAME_ENTRY_ID = "idSpecialization";
        public static final String COLUMN_Specialization_SPECIALIZATION_TITLE_EN = "Specialization_Title_EN";
        public static final String COLUMN_Specialization_SPECIALIZATION_TITLE_FR = "Specialization_Title_FR";

        public static final String COLUMN_Specialization_SPECIALIZATION_DESCRIPTION_EN = "Specialization_Description_EN";
        public static final String COLUMN_Specialization_SPECIALIZATION_DESCRIPTION_FR = "Specialization_Description_FR";


        // foreign key! idCategory
        public static final String COLUMN_Specialization_ID_CATEGORY = "idCategory";
    }

    //table Category
    public static abstract class Category implements BaseColumns {
        public static final String TABLE_Category = "Category";

        public static final String COLUMN_Category_NAME_ENTRY_ID = "idCategory";
        public static final String COLUMN_Category_CATEGORY_NAME_EN = "Category_Name_EN";
        public static final String COLUMN_Category_CATEGORY_NAME_FR = "Category_Name_FR";

    }

    //table User
    public static abstract class User implements BaseColumns {
        public static final String TABLE_User = "User";

        public static final String COLUMN_User_NAME_ENTRY_ID = "idUser";
        public static final String COLUMN_User_FIRSTNAME = "Firstname";
        public static final String COLUMN_User_LASTNAME = "Lastname";
        public static final String COLUMN_User_USERNAME = "Username";
        public static final String COLUMN_User_PASSWORD = "Password";
        public static final String COLUMN_User_EMAIL = "Email";
        public static final String COLUMN_User_ROLE = "Role";

        // foreign key! idCompany
        public static final String COLUMN_User_ID_COMPANY = "idCompany";

    }
}
