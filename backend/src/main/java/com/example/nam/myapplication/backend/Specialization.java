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
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSpecialization")
    private Long idSpecialization;
    private String Spec_Title;
    private String Spec_Description;
    private String Spec_TitleFR;
    private String Spec_DescriptionFR;
    private JobDescription jobDescription;


    public Long getIdSpecialization() {
        return idSpecialization;
    }

    public void setIdSpecialization(Long idSpecialization) {
        this.idSpecialization = idSpecialization;
    }

    public String getSpec_Title() {
        return Spec_Title;
    }

    public void setSpec_Title(String spec_Title) {
        Spec_Title = spec_Title;
    }

    public String getSpec_Description() {
        return Spec_Description;
    }

    public void setSpec_Description(String spec_Description) {
        Spec_Description = spec_Description;
    }

    public String getSpec_TitleFR() {
        return Spec_TitleFR;
    }

    public void setSpec_TitleFR(String spec_TitleFR) {
        Spec_TitleFR = spec_TitleFR;
    }

    public String getSpec_DescriptionFR() {
        return Spec_DescriptionFR;
    }

    public void setSpec_DescriptionFR(String spec_DescriptionFR) {
        Spec_DescriptionFR = spec_DescriptionFR;
    }

    @OneToOne(mappedBy = "specialization")
    public JobDescription getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }


}
