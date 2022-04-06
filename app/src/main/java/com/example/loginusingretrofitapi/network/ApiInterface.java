package com.example.loginusingretrofitapi.network;

import com.example.loginusingretrofitapi.model.LoginAuth;
import com.example.loginusingretrofitapi.model.Profile;
import com.example.loginusingretrofitapi.model.UserAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
   @POST("api/login")
    Call<LoginAuth> apiAuthCall(@Body UserAuth userAuth);

    @GET("api/staff")
    Call<Profile> getProfile(@Header("Authorization") String token);

}
