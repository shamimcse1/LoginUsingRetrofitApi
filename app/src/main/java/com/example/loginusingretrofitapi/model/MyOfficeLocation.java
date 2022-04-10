package com.example.loginusingretrofitapi.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class MyOfficeLocation {
    @SerializedName("lat")
    @Expose
    private String latitude;

    @SerializedName("lon")
    @Expose
    private String longitudes;

    public MyOfficeLocation(String latitude, String longitudes) {
        this.latitude = latitude;
        this.longitudes = longitudes;
    }

    public MyOfficeLocation() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitudes() {
        return longitudes;
    }

    public void setLongitudes(String longitudes) {
        this.longitudes = longitudes;
    }
}
