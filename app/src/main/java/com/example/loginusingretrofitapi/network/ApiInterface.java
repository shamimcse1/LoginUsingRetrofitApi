package com.example.loginusingretrofitapi.network;

import com.example.loginusingretrofitapi.model.district.District;
import com.example.loginusingretrofitapi.model.LoginAuth;
import com.example.loginusingretrofitapi.model.Profile;
import com.example.loginusingretrofitapi.model.ResponseModel;
import com.example.loginusingretrofitapi.model.up_zila.Upozila;
import com.example.loginusingretrofitapi.model.UserAuth;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @POST("api/login")
    Call<LoginAuth> apiAuthCall(@Body UserAuth userAuth);

    @GET("api/staff")
    Call<Profile> getProfile(@Header("Authorization") String token);

    @Multipart
    @POST("api/add_client")
    Call<ResponseModel> addPotentialClient(@Header("Authorization") String token,
                                           @Part MultipartBody.Part moneyReceiptFilePart,
                                           @Part("data") RequestBody submitVoucher
    );

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("api/districts")
    Call<District> requestAllDistrict(@Header("Authorization") String token);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("api/upazillas/{id}")
    Call<Upozila> requestAllUpozilla(@Header("Authorization") String token, @Path("id") String date);

}
