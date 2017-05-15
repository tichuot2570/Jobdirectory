package com.example.nam.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by nam on 23-Apr-17.
 */
@Entity
public class JobDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJobDescription;
    private String jobName;
    private String jobNameFR;
    private String jobDescription;
    private String jobDescriptionFR;

    //relationships between Job Description with Location, Specialization, Company
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idSpecialization")
    private Specialization specialization;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idLocation")
    private Location location;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idCompany")
    private Company company;


    public Long getIdJobDescription() {
        return idJobDescription;
    }

    public void setIdJobDescription(Long idJobDescription) {
        this.idJobDescription = idJobDescription;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDescriptionFR() {
        return jobDescriptionFR;
    }

    public void setJobDescriptionFR(String jobDescriptionFR) {
        this.jobDescriptionFR = jobDescriptionFR;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobNameFR() {
        return jobNameFR;
    }

    public void setJobNameFR(String jobNameFR) {
        this.jobNameFR = jobNameFR;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}