package com.example.nam.myapplication.backend;

/**
 * Created by nam on 22-Apr-17.
 */

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompany")
    private Long idCompany;
    private String name_company;
    private String description_company;
    private String description_companyFR;
    private JobDescription jobDescription;
    //1 company can have 1-many users
    private List<User> users = new ArrayList<User>();


    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }

    public String getName_company() {
        return name_company;
    }

    public void setName_company(String name_company) {
        this.name_company = name_company;
    }

    public String getDescription_company() {
        return description_company;
    }

    public void setDescription_company(String description_company) {
        this.description_company = description_company;
    }

    public String getDescription_companyFR() {
        return description_companyFR;
    }

    public void setDescription_companyFR(String description_companyFR) {
        this.description_companyFR = description_companyFR;
    }

    @OneToOne(mappedBy = "company")
    public JobDescription getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
