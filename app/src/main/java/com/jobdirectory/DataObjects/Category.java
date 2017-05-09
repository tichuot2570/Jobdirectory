package com.jobdirectory.DataObjects;

/**
 * Created by nam on 22-Apr-17.
 */

public class Category {

    private int idCategory;
    private String name_category;
    private String name_categoryFR;

    public Category() {
    }

    //for category with fr as language
    public Category(int idCategory, String name_categoryFR) {
        this.idCategory = idCategory;
        this.name_categoryFR = name_categoryFR;
    }

    public Category(int idCategory, String name_category, String name_categoryFR) {
        this.idCategory = idCategory;
        this.name_category = name_category;
        this.name_categoryFR = name_categoryFR;
    }

    public Category(String name_category) {
        this.name_category = name_category;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
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

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                " , name_category='" + name_category + '\'' +
                " , name_categoryFR='" + name_categoryFR + '\'' +

                '}';
    }
}
