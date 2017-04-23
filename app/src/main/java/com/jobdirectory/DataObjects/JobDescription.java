package com.jobdirectory.DataObjects;

/**
 * Created by nam on 23-Apr-17.
 */

public class JobDescription {
    private int idJobDescription;
    private int fk_LocationId;
    private int fk_SpecializationId;
    private int fk_CompanyId;
    private String jobDescription;
    private String jobDescriptionFR;

    public JobDescription(int idJobDescription, int fk_LocationId, int fk_SpecializationId, int fk_CompanyId, String jobDescription, String jobDescriptionFR) {
        this.idJobDescription = idJobDescription;
        this.fk_LocationId = fk_LocationId;
        this.fk_SpecializationId = fk_SpecializationId;
        this.fk_CompanyId = fk_CompanyId;
        this.jobDescription = jobDescription;
        this.jobDescriptionFR = jobDescriptionFR;
    }

    //for creating job with fr as language
    public JobDescription(int idJobDescription, int fk_LocationId, int fk_SpecializationId, int fk_CompanyId, String jobDescriptionFR) {
        this.idJobDescription = idJobDescription;
        this.fk_LocationId = fk_LocationId;
        this.fk_SpecializationId = fk_SpecializationId;
        this.fk_CompanyId = fk_CompanyId;
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

    @Override
    public String toString() {
        return "JobDescription{" +
                "idJobDescription=" + idJobDescription +
                ", fk_LocationId=" + fk_LocationId +
                ", fk_CompanyId=" + fk_CompanyId +
                ", fk_SpecializationId=" + fk_SpecializationId +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobDescriptionFR='" + jobDescriptionFR + '\'' +
                '}';
    }
}
