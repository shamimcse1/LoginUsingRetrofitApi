package com.example.loginusingretrofitapi.model.up_zila;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("upazilla_name")
@Expose
private String upazillaName;
@SerializedName("upazila_name_bng")
@Expose
private String upazilaNameBng;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getUpazillaName() {
return upazillaName;
}

public void setUpazillaName(String upazillaName) {
this.upazillaName = upazillaName;
}

public String getUpazilaNameBng() {
return upazilaNameBng;
}

public void setUpazilaNameBng(String upazilaNameBng) {
this.upazilaNameBng = upazilaNameBng;
}

}