package com.jobdirectory.DataObjects;

/**
 * Created by nam on 23-Apr-17.
 */

public class JobDescription {
    private int idJobDescription;
    private int fk_LocationId;
    private int fk_SpecializationId;
    private int fk_CompanyId;
    private String jobName;
    private String jobNameFR;
    private String jobDescription;
    private String jobDescriptionFR;
    private String companyName;
    private String locationName;

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

    public int getIdJobDescription() {
        return idJobDescription;
    }

    public void setIdJobDescription(int idJobDescription) {
        this.idJobDescription = idJobDescription;
    }

    public int getFk_LocationId() {
        return fk_LocationId;
    }

    public void setFk_LocationId(int fk_LocationId) {
        this.fk_LocationId = fk_LocationId;
    }

    public int getFk_SpecializationId() {
        return fk_SpecializationId;
    }

    public void setFk_SpecializationId(int fk_SpecializationId) {
        this.fk_SpecializationId = fk_SpecializationId;
    }

    public int getFk_CompanyId() {
        return fk_CompanyId;
    }

    public void setFk_CompanyId(int fk_CompanyId) {
        this.fk_CompanyId = fk_CompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getlocationName() {
        return locationName;
    }

    public void setlocationName(String locationName) {
        this.locationName = locationName;
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