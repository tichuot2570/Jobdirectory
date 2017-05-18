package com.example.nam.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by nam on 22-Apr-17.
 */
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLocation;
    private String name_location;
    private String zipCode;

    public Location() {
    }

    public Location(int idLocation, String name_location, String zipCode) {
        this.idLocation = idLocation;
        this.name_location = name_location;
        this.zipCode = zipCode;
    }

    public long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getName_location() {
        return name_location;
    }

    public void setName_location(String name_location) {
        this.name_location = name_location;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", name_location='" + name_location + '\'' +
                ", zipcode='" + zipCode + '\'' +
                '}';
    }
}
