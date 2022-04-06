package com.example.loginusingretrofitapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("user_name")
@Expose
private String userName;
@SerializedName("userid")
@Expose
private String userid;
@SerializedName("designation")
@Expose
private String designation;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("appointment_type")
@Expose
private Object appointmentType;
@SerializedName("joining_date")
@Expose
private Object joiningDate;
@SerializedName("business_program_id")
@Expose
private Integer businessProgramId;
@SerializedName("last_login")
@Expose
private Object lastLogin;
@SerializedName("status")
@Expose
private Integer status;
@SerializedName("change_passwd_status")
@Expose
private Integer changePasswdStatus;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("level")
@Expose
private Integer level;
@SerializedName("is_accountant")
@Expose
private Integer isAccountant;
@SerializedName("role_id")
@Expose
private Integer roleId;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getUserid() {
return userid;
}

public void setUserid(String userid) {
this.userid = userid;
}

public String getDesignation() {
return designation;
}

public void setDesignation(String designation) {
this.designation = designation;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public Object getAppointmentType() {
return appointmentType;
}

public void setAppointmentType(Object appointmentType) {
this.appointmentType = appointmentType;
}

public Object getJoiningDate() {
return joiningDate;
}

public void setJoiningDate(Object joiningDate) {
this.joiningDate = joiningDate;
}

public Integer getBusinessProgramId() {
return businessProgramId;
}

public void setBusinessProgramId(Integer businessProgramId) {
this.businessProgramId = businessProgramId;
}

public Object getLastLogin() {
return lastLogin;
}

public void setLastLogin(Object lastLogin) {
this.lastLogin = lastLogin;
}

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public Integer getChangePasswdStatus() {
return changePasswdStatus;
}

public void setChangePasswdStatus(Integer changePasswdStatus) {
this.changePasswdStatus = changePasswdStatus;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public Integer getLevel() {
return level;
}

public void setLevel(Integer level) {
this.level = level;
}

public Integer getIsAccountant() {
return isAccountant;
}

public void setIsAccountant(Integer isAccountant) {
this.isAccountant = isAccountant;
}

public Integer getRoleId() {
return roleId;
}

public void setRoleId(Integer roleId) {
this.roleId = roleId;
}

}