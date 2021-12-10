package com.onurcansever.lambtonfinal;

public class Country {
    private String countryName;
    private String countryCapital;
    private int countryFlag;
    private Place[] places;

    public Country(String countryName, String countryCapital, int countryFlag, Place[] places) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
        this.countryFlag = countryFlag;
        this.places = places;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public Place[] getPlaces() {
        return places;
    }
}
