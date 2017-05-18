package com.example.nam.myapplication.backend;

/**
 * Created by nam on 22-Apr-17.
 */

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCompany;
    private String name_company;
    private String description_company;
    private String description_companyFR;

    public Company() {
    }

    //for creating the general specialization with fr as language
    public Company(int idCompany, String name_company, String description_companyFR) {
        this.idCompany = idCompany;
        this.name_company = name_company;
        this.description_companyFR = description_companyFR;
    }

    //for creating the general specialization with fr and en as language
    public Company(long idCompany, String name_company, String description_company, String description_companyFR) {
        this.idCompany = idCompany;
        this.description_company = description_company;
        this.name_company = name_company;
        this.description_companyFR = description_companyFR;
    }


    //for creating the general specialization with en as language
    public Company(String name_company, String description_company) {
        this.name_company = name_company;
        this.description_company = description_company;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
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

    @Override
    public String toString() {
        return "Company{" +
                "idCompany=" + idCompany +
                ", name_company='" + name_company + '\'' +
                ", description_company='" + description_company + '\'' +
                ", description_companyFR='" + description_companyFR + '\'' +
                '}';
    }
}
