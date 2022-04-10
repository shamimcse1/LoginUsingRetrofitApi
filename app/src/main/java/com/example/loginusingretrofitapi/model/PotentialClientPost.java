package com.example.loginusingretrofitapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PotentialClientPost {

@SerializedName("name")
@Expose
private String name="";
@SerializedName("phone_no")
@Expose
private String phoneNo="";
@SerializedName("district_id")
@Expose
private String districtId="";
@SerializedName("upazilla_id")
@Expose
private String upazillaId="";
@SerializedName("thana")
@Expose
private String thana="";
@SerializedName("post")
@Expose
private String post="";
@SerializedName("address")
@Expose
private String address="";
@SerializedName("project_name")
@Expose
private String projectName="";
@SerializedName("note")
@Expose
private String note="";

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPhoneNo() {
return phoneNo;
}

public void setPhoneNo(String phoneNo) {
this.phoneNo = phoneNo;
}

public String getDistrictId() {
return districtId;
}

public void setDistrictId(String districtId) {
this.districtId = districtId;
}

public String getUpazillaId() {
return upazillaId;
}

public void setUpazillaId(String upazillaId) {
this.upazillaId = upazillaId;
}

public String getThana() {
return thana;
}

public void setThana(String thana) {
this.thana = thana;
}

public String getPost() {
return post;
}

public void setPost(String post) {
this.post = post;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getProjectName() {
return projectName;
}

public void setProjectName(String projectName) {
this.projectName = projectName;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

}