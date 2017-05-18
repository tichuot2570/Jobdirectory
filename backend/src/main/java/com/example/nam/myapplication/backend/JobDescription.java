package com.example.nam.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by nam on 23-Apr-17.
 */
@Entity
public class JobDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJobDescription;
    private long fk_LocationId;
    private long fk_SpecializationId;
    private long fk_CompanyId;
    private String jobName;
    private String jobNameFR;
    private String jobDescription;
    private String jobDescriptionFR;

    public JobDescription() {
    }

    public JobDescription(int idJobDescription, int fk_LocationId, int fk_SpecializationId, int fk_CompanyId, String jobName, String jobNameFR, String jobDescription, String jobDescriptionFR) {
        this.idJobDescription = idJobDescription;
        this.fk_LocationId = fk_LocationId;
        this.fk_SpecializationId = fk_SpecializationId;
        this.fk_CompanyId = fk_CompanyId;
        this.jobName = jobName;
        this.jobNameFR = jobNameFR;
        this.jobDescription = jobDescription;
        this.jobDescriptionFR = jobDescriptionFR;
    }

    //for creating job with fr as language
    public JobDescription(int idJobDescription, int fk_LocationId, int fk_SpecializationId, int fk_CompanyId, String jobNameFR, String jobDescriptionFR) {
        this.idJobDescription = idJobDescription;
        this.fk_LocationId = fk_LocationId;
        this.fk_SpecializationId = fk_SpecializationId;
        this.fk_CompanyId = fk_CompanyId;
        this.jobNameFR = jobNameFR;
        this.jobDescriptionFR = jobDescriptionFR;
    }

    public long getIdJobDescription() {
        return idJobDescription;
    }

    public void setIdJobDescription(int idJobDescription) {
        this.idJobDescription = idJobDescription;
    }

    public long getFk_LocationId() {
        return fk_LocationId;
    }

    public void setFk_LocationId(int fk_LocationId) {
        this.fk_LocationId = fk_LocationId;
    }

    public long getFk_SpecializationId() {
        return fk_SpecializationId;
    }

    public void setFk_SpecializationId(int fk_SpecializationId) {
        this.fk_SpecializationId = fk_SpecializationId;
    }

    public long getFk_CompanyId() {
        return fk_CompanyId;
    }

    public void setFk_CompanyId(int fk_CompanyId) {
        this.fk_CompanyId = fk_CompanyId;
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

    @Override
    public String toString() {
        return "JobDescription{" +
                "idJobDescription=" + idJobDescription +
                ", fk_LocationId=" + fk_LocationId +
                ", fk_CompanyId=" + fk_CompanyId +
                ", fk_SpecializationId=" + fk_SpecializationId +
                ", jobName='" + jobName + '\'' +
                ", jobNameFR='" + jobNameFR + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobDescriptionFR='" + jobDescriptionFR + '\'' +
                '}';
    }
}