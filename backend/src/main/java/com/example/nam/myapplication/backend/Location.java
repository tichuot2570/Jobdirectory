package com.example.nam.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

/**
 * Created by nam on 22-Apr-17.
 */
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLocation")
    private Long idLocation;
    private String name_locationFR;
    private String name_locationGE;
    private String zipCode;

    private JobDescription jobDescription;

    public Long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    public String getName_locationFR() {
        return name_locationFR;
    }

    public void setName_locationFR(String name_locationFR) {
        this.name_locationFR = name_locationFR;
    }

    public String getName_locationGE() {
        return name_locationGE;
    }

    public void setName_locationGE(String name_locationGE) {
        this.name_locationGE = name_locationGE;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @OneToOne(mappedBy = "location")
    public JobDescription getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }
}
