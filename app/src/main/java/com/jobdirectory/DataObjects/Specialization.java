package com.jobdirectory.DataObjects;

import java.io.Serializable;
import java.io.StringReader;

/**
 * Created by nam on 22-Apr-17.
 */

public class Specialization {

    private int idSpecialization;
    private int fk_CategoryId;
    private String Spec_Title;
    private String Spec_Description;
    private String Spec_TitleFR;
    private String Spec_DescriptionFR;

    //for creating the general specialization with FR as language
    public Specialization(int idSpecialization, int fk_CategoryId, String Spec_TitleFR, String Spec_DescriptionFR) {
        this.idSpecialization = idSpecialization;
        this.fk_CategoryId = fk_CategoryId;
        this.Spec_Title = Spec_TitleFR;
        this.Spec_Description = Spec_DescriptionFR;
    }

    //for creating the general specialization with en and fr as language
    public Specialization(int idSpecialization, int fk_CategoryId, String Spec_Title, String Spec_Description, String Spec_TitleFR, String Spec_DescriptionFR) {
        this.idSpecialization = idSpecialization;
        this.fk_CategoryId = fk_CategoryId;
        this.Spec_Title = Spec_Title;
        this.Spec_Description = Spec_Description;
        this.Spec_TitleFR = Spec_TitleFR;
        this.Spec_DescriptionFR = Spec_DescriptionFR;
    }

    //for creating the general specialization with EN as language
    public Specialization(String Spec_Title, String Spec_Description) {
        this.Spec_Title = Spec_Title;
        this.Spec_Description = Spec_Description;
    }


    public int getIdSpecialization() {
        return idSpecialization;
    }

    public void setIdSpecialization(int idSpecialization) {
        this.idSpecialization = idSpecialization;
    }

    public int getFk_CategoryId() {
        return fk_CategoryId;
    }

    public void setFk_CategoryId(int fk_CategoryId) {
        this.fk_CategoryId = fk_CategoryId;
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

    @Override
    public String toString() {
        return "Specialization{" +
                "idSpecialization=" + idSpecialization +
                ", fk_CategoryId =" + fk_CategoryId +
                ", specialization_title='" + Spec_Title + '\'' +
                ", specialization_description='" + Spec_Description + '\'' +
                ", specialization_titleFR='" + Spec_TitleFR + '\'' +
                ", specialization_descriptionRR='" + Spec_DescriptionFR + '\'' +
                '}';
    }
}
