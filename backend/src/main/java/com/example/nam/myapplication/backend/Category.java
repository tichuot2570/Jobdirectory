package com.example.nam.myapplication.backend;

/**
 * Created by nam on 22-Apr-17.
 */

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private String name_category;
    private String name_categoryFR;


    private List<Specialization> specializations = new ArrayList<Specialization>();

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }


    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public String getName_categoryFR() {
        return name_categoryFR;
    }

    public void setName_categoryFR(String name_categoryFR) {
        this.name_categoryFR = name_categoryFR;
    }
}
