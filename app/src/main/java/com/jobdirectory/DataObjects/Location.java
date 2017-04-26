package com.jobdirectory.DataObjects;

/**
 * Created by nam on 22-Apr-17.
 */

public class Location {
    private int idLocation;
    private String name_location;
    private int zipCode;

    public Location(int idLocation, String name_location, int zipCode) {
        this.idLocation = idLocation;
        this.name_location = name_location;
        this.zipCode = zipCode;
    }

    public int getIdLocation() {
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
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
