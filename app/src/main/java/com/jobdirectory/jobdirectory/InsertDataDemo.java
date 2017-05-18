package com.jobdirectory.jobdirectory;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.jobdirectory.DataObjects.Category;
import com.jobdirectory.DataObjects.Company;
import com.jobdirectory.DataObjects.JobDescription;
import com.jobdirectory.DataObjects.Location;
import com.jobdirectory.DataObjects.Specialization;
import com.jobdirectory.DataObjects.User;
import com.jobdirectory.db.adapter.CategoryDataSource;
import com.jobdirectory.db.adapter.CompanyDataSource;
import com.jobdirectory.db.adapter.JobDescriptionDataSource;
import com.jobdirectory.db.adapter.LocationDataSource;
import com.jobdirectory.db.adapter.SpecializationDataSource;
import com.jobdirectory.db.adapter.UserDataSource;

/**
 * Created by Vincent_2 on 03.05.2017.
 */

public class InsertDataDemo extends AppCompatActivity {


    public void InsertDataDemo()

    {

    }


    public void insertDataDemo(Context context) {


        //************* Category

        CategoryDataSource categoryDs = new CategoryDataSource(context);
        Category newCategory = new Category();

        newCategory.setName_category("Building");
        newCategory.setName_categoryFR("Construction");
        newCategory.setIdCategory((int) categoryDs.createCategory(newCategory));

        newCategory.setName_category("Information technology");
        newCategory.setName_categoryFR("Technologie de l'information");
        newCategory.setIdCategory((int) categoryDs.createCategory(newCategory));

        newCategory.setName_category("Health");
        newCategory.setName_categoryFR("Santé");
        newCategory.setIdCategory((int) categoryDs.createCategory(newCategory));

        newCategory.setName_category("Bank");
        newCategory.setName_categoryFR("Banque");
        newCategory.setIdCategory((int) categoryDs.createCategory(newCategory));


        //************* Specialization

        SpecializationDataSource specializationDs = new SpecializationDataSource(context);
        Specialization newSpecialization = new Specialization();

        newSpecialization.setSpec_Title("Internet, digital medias");
        newSpecialization.setSpec_TitleFR("Internet, digital medias");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setFk_CategoryId(2);
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));

        newSpecialization.setSpec_Title("Network security");
        newSpecialization.setSpec_TitleFR("Network security");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setFk_CategoryId(2);
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));

        newSpecialization.setSpec_Title("Electrician");
        newSpecialization.setSpec_TitleFR("Electrician");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setFk_CategoryId(1);
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));

        newSpecialization.setSpec_Title("Masonry");
        newSpecialization.setSpec_TitleFR("Masonry");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setFk_CategoryId(1);
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));

        newSpecialization.setSpec_Title("Care staff");
        newSpecialization.setSpec_TitleFR("Personnel de soins");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setFk_CategoryId(3);
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));

        newSpecialization.setSpec_Title("Physiotherapist");
        newSpecialization.setSpec_TitleFR("Physiothérapeute");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setFk_CategoryId(3);
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));

        newSpecialization.setSpec_Title("Private banking");
        newSpecialization.setSpec_TitleFR("Conseil en placement");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setFk_CategoryId(4);
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));

        newSpecialization.setSpec_Title("Front office");
        newSpecialization.setSpec_TitleFR("Conseiller de clientèle");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setFk_CategoryId(4);
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));


        //************* Location

        LocationDataSource locationDs = new LocationDataSource(context);
        Location newLocation = new Location();

        newLocation.setName_location("Sierre");
        newLocation.setZipCode("3960");
        newLocation.setIdLocation((int) locationDs.createLocation(newLocation));

        /*
        com.example.nam.myapplication.backend.locationApi.model.Location locationCloud = new com.example.nam.myapplication.backend.locationApi.model.Location();
        locationCloud.setIdLocation((long)newLocation.getIdLocation());
        locationCloud.setNameLocation(newLocation.getName_location());
        locationCloud.setZipCode(newLocation.getZipCode());
        new EndpointsLocationAsyncTaskInsert(locationCloud).execute();
        */

        newLocation.setName_location("Sion");
        newLocation.setZipCode("1950");
        newLocation.setIdLocation((int) locationDs.createLocation(newLocation));

        newLocation.setName_location("Lausanne");
        newLocation.setZipCode("1001");
        newLocation.setIdLocation((int) locationDs.createLocation(newLocation));


        //************* Company

        CompanyDataSource companyDs = new CompanyDataSource(context);
        Company newCompany = new Company();

        newCompany.setName_company("E-Media Sàrl");
        newCompany.setDescription_company("");
        newCompany.setDescription_companyFR("");
        newCompany.setIdCompany((int) companyDs.createCompany(newCompany));

        newCompany.setName_company("Red Fox SA");
        newCompany.setDescription_company("");
        newCompany.setDescription_companyFR("");
        newCompany.setIdCompany((int) companyDs.createCompany(newCompany));

        newCompany.setName_company("Wallis Hospital");
        newCompany.setDescription_company("");
        newCompany.setDescription_companyFR("");
        newCompany.setIdCompany((int) companyDs.createCompany(newCompany));

        newCompany.setName_company("BCVs");
        newCompany.setDescription_company("");
        newCompany.setDescription_companyFR("");
        newCompany.setIdCompany((int) companyDs.createCompany(newCompany));

        newCompany.setName_company("Sunny Bar Sàrl");
        newCompany.setDescription_company("");
        newCompany.setDescription_companyFR("");
        newCompany.setIdCompany((int) companyDs.createCompany(newCompany));

        newCompany.setName_company("Pro Builder SA");
        newCompany.setDescription_company("");
        newCompany.setDescription_companyFR("");
        newCompany.setIdCompany((int) companyDs.createCompany(newCompany));


        //************* JobDescription

        JobDescriptionDataSource jobDs = new JobDescriptionDataSource(context);
        JobDescription newJob = new JobDescription();

        newJob.setJobName("Front-end developer");
        newJob.setJobNameFR("Développeur front-end");
        newJob.setJobDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setJobDescriptionFR("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setFk_CompanyId(1);
        newJob.setFk_LocationId(1);
        newJob.setFk_SpecializationId(1);
        newJob.setIdJobDescription((int) jobDs.createJob(newJob));

        newJob.setJobName("Cisco engineer");
        newJob.setJobNameFR("Ingénieur Cisco");
        newJob.setJobDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setJobDescriptionFR("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setFk_CompanyId(2);
        newJob.setFk_LocationId(2);
        newJob.setFk_SpecializationId(2);
        newJob.setIdJobDescription((int) jobDs.createJob(newJob));

        newJob.setJobName("Nurse");
        newJob.setJobNameFR("Infirmier(-ière)");
        newJob.setJobDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setJobDescriptionFR("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setFk_CompanyId(3);
        newJob.setFk_LocationId(2);
        newJob.setFk_SpecializationId(5);
        newJob.setIdJobDescription((int) jobDs.createJob(newJob));

        newJob.setJobName("Electrician apprentice");
        newJob.setJobNameFR("Electrician apprentice");
        newJob.setJobDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setJobDescriptionFR("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setFk_CompanyId(6);
        newJob.setFk_LocationId(3);
        newJob.setFk_SpecializationId(3);
        newJob.setIdJobDescription((int) jobDs.createJob(newJob));

        newJob.setJobName("Java programmer");
        newJob.setJobNameFR("Java programmer");
        newJob.setJobDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setJobDescriptionFR("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setFk_CompanyId(2);
        newJob.setFk_LocationId(1);
        newJob.setFk_SpecializationId(1);
        newJob.setIdJobDescription((int) jobDs.createJob(newJob));

        newJob.setJobName("SAP specialist");
        newJob.setJobNameFR("SAP specialist");
        newJob.setJobDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setJobDescriptionFR("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setFk_CompanyId(3);
        newJob.setFk_LocationId(1);
        newJob.setFk_SpecializationId(1);
        newJob.setIdJobDescription((int) jobDs.createJob(newJob));

        newJob.setJobName("Mobile developer");
        newJob.setJobNameFR("Mobile developer");
        newJob.setJobDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setJobDescriptionFR("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setFk_CompanyId(1);
        newJob.setFk_LocationId(1);
        newJob.setFk_SpecializationId(1);
        newJob.setIdJobDescription((int) jobDs.createJob(newJob));

        newJob.setJobName("Builder");
        newJob.setJobNameFR("Maçon");
        newJob.setJobDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setJobDescriptionFR("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel porttitor dui. Curabitur finibus orci a consectetur iaculis.");
        newJob.setFk_CompanyId(6);
        newJob.setFk_LocationId(2);
        newJob.setFk_SpecializationId(4);
        newJob.setIdJobDescription((int) jobDs.createJob(newJob));


        //************* User

        UserDataSource userDs = new UserDataSource(context);
        User newUser = new User();

        newUser.setUsername("john");
        newUser.setFirstname("John");
        newUser.setLastname("Doe");
        newUser.setEmail("john@mail.com");
        newUser.setPassword("john1234");
        newUser.setRole("Manager");
        newUser.setFk_CompanyId(1);
        newUser.setFk_CompanyId((int) userDs.createUser(newUser));

        newUser.setUsername("bart");
        newUser.setFirstname("Bart");
        newUser.setLastname("Simpson");
        newUser.setEmail("bart@mail.com");
        newUser.setPassword("bart1234");
        newUser.setRole("Engineer");
        newUser.setFk_CompanyId(2);
        newUser.setFk_CompanyId((int) userDs.createUser(newUser));

        newUser.setUsername("lisa");
        newUser.setFirstname("Lisa");
        newUser.setLastname("Smith");
        newUser.setEmail("lisa@mail.com");
        newUser.setPassword("lisa1234");
        newUser.setRole("Recruiter");
        newUser.setFk_CompanyId(6);
        newUser.setFk_CompanyId((int) userDs.createUser(newUser));


    }


}
