package com.example.loginusingretrofitapi.model;

public class UserAuth {
    private  String client_id;
    private String client_secret;
    private  String userid;
    private  String password;


    public UserAuth(String client_id, String client_secret, String userid, String password) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.userid = userid;
        this.password = password;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
