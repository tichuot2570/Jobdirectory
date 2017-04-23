package com.jobdirectory.DataObjects;

/**
 * Created by nam on 22-Apr-17.
 */

public class Company {
    private int idCompany;
    private String name_company;
    private String description_company;
    private String description_companyFR;

    //for creating the general specialization with fr as language
    public Company(int idCompany, String name_company, String description_companyFR) {
        this.idCompany = idCompany;
        this.name_company = name_company;
        this.description_companyFR = description_companyFR;
    }

    //for creating the general specialization with fr and en as language
    public Company(int idCompany, String name_company, String description_company, String description_companyFR) {
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

    public int getIdCompany() {
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
