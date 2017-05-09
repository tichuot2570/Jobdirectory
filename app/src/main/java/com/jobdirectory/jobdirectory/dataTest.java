package com.jobdirectory.jobdirectory;

import com.jobdirectory.DataObjects.Category;
import com.jobdirectory.DataObjects.Company;
import com.jobdirectory.DataObjects.JobDescription;
import com.jobdirectory.DataObjects.Location;
import com.jobdirectory.DataObjects.Specialization;
import com.jobdirectory.DataObjects.User;

import java.util.ArrayList;

/**
 * Created by Vincent_2 on 04.05.2017.
 */


public class dataTest {

    public static ArrayList<Category> categories;
    public static ArrayList<Location> locations;
    public static ArrayList<JobDescription> jobDescriptions;
    public static ArrayList<Company> companies;
    public static ArrayList<Specialization> specializations;

    dataTest() {

        Category category1 = new Category(1, "Information technology", "Tchnologies de l'information");
        Category category2 = new Category(2, "Bank", "Banque");
        Category category3 = new Category(3, "Health", "Santé");
        categories = new ArrayList();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        Specialization specialization1 = new Specialization(10, 1, "Web development", "Développement web");
        Specialization specialization2 = new Specialization(11, 1, "Network security", "Sécurité réseau");
        specializations = new ArrayList();
        specializations.add(specialization1);
        specializations.add(specialization2);

        Company company1 = new Company(1, "E-Rabbit", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        Company company2 = new Company(2, "NewLine Media", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

        Location location1 = new Location(1, "Sierre", "3960");
        Location location2 = new Location(2, "Sion", "1950");
        locations = new ArrayList();
        locations.add(location1);
        locations.add(location2);

        JobDescription jobDescription1 = new JobDescription(1, 1, 10, 1, "Web Designer", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae gravida sem. Etiam et tincidunt arcu. Proin fermentum mattis egestas.", "Lorem ipsum");
        JobDescription jobDescription2 = new JobDescription(2, 2, 11, 2, "IT security manager", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae gravida sem. Etiam et tincidunt arcu. Proin fermentum mattis egestas.", "Lorem ipsum");
        JobDescription jobDescription3 = new JobDescription(3, 1, 10, 1, "Front-end Developer", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae gravida sem. Etiam et tincidunt arcu. Proin fermentum mattis egestas.", "Lorem ipsum");
        JobDescription jobDescription4 = new JobDescription(4, 2, 10, 1, "E-commerce specialist", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae gravida sem. Etiam et tincidunt arcu. Proin fermentum mattis egestas.", "Lorem ipsum");
        JobDescription jobDescription5 = new JobDescription(5, 1, 11, 1, "Network engineer", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae gravida sem. Etiam et tincidunt arcu. Proin fermentum mattis egestas.", "Lorem ipsum");
        jobDescriptions = new ArrayList();
        jobDescriptions.add(jobDescription1);
        jobDescriptions.add(jobDescription2);
        jobDescriptions.add(jobDescription3);
        jobDescriptions.add(jobDescription4);
        jobDescriptions.add(jobDescription5);

        User user1 = new User(100, "john123", "John", "Doe", "john123", "john@gmail.com", 1, "Recruiter");
        User user2 = new User(101, "lisa123", "Lisa", "Gold", "lisa123", "lisa@gmail.com", 2, "Human ressources");
        ArrayList<User> users = new ArrayList();
        users.add(user1);
        users.add(user2);

    }


}
