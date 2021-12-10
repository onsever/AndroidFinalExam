package com.onurcansever.lambtonfinal;

public class Place {
    private String placeName;
    private double visitPrice;
    private int placeImage;

    public Place(String placeName, double visitPrice, int placeImage) {
        this.placeName = placeName;
        this.visitPrice = visitPrice;
        this.placeImage = placeImage;
    }

    public String getPlaceName() {
        return placeName;
    }

    public double getVisitPrice() {
        return visitPrice;
    }

    public int getPlaceImage() {
        return placeImage;
    }
}
