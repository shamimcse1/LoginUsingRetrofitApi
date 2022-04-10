package com.example.loginusingretrofitapi.model.district;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_name_bng")
    @Expose
    private String districtNameBng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictNameBng() {
        return districtNameBng;
    }

    public void setDistrictNameBng(String districtNameBng) {
        this.districtNameBng = districtNameBng;
    }
}