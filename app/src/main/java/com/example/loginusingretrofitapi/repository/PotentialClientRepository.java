package com.example.loginusingretrofitapi.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginusingretrofitapi.helpers.Constant;
import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.helpers.SharedPreference;
import com.example.loginusingretrofitapi.model.PotentialClientPost;
import com.example.loginusingretrofitapi.model.district.District;
import com.example.loginusingretrofitapi.model.ResponseModel;
import com.example.loginusingretrofitapi.model.up_zila.Upozila;
import com.example.loginusingretrofitapi.network.ApiInterface;
import com.example.loginusingretrofitapi.network.RetrofitApiClient;
import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PotentialClientRepository {

    ApiInterface apiInterface;

    //Add Potentials Client
    private final MutableLiveData<DataResource<ResponseModel>> potentialClientMutableLiveData = new MutableLiveData<>();
    public LiveData<DataResource<ResponseModel>> submitResponse = potentialClientMutableLiveData;

    //Get District data
    private final MutableLiveData<DataResource<District>> districtMutableLiveData = new MutableLiveData<>();
    public LiveData<DataResource<District>> districtLiveData = districtMutableLiveData;

    //Get Upozila Data
    private final MutableLiveData<DataResource<Upozila>> upozilaMutableLiveData = new MutableLiveData<>();
    public LiveData<DataResource<Upozila>> upozilaLiveData = upozilaMutableLiveData;



    public void getDistrict() {

        String token = SharedPreference.getToken(Constant.USER_INFO);
        districtMutableLiveData.setValue(DataResource.loading());

        apiInterface = RetrofitApiClient.getRetrofit().create(ApiInterface.class);
        Call<District> call = apiInterface.requestAllDistrict(token);

        call.enqueue(new Callback<District>() {
            @Override
            public void onResponse(Call<District> call, Response<District> response) {

                if (response.code() == 200 && response.body() != null) {
                    districtMutableLiveData.setValue(DataResource.success(response.body()));
                } else {
                    districtMutableLiveData.setValue(DataResource.error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<District> call, Throwable t) {
                districtMutableLiveData.setValue(DataResource.error(t.getMessage()));
            }
        });

    }

    public void getUpozila(String id) {


        String token = SharedPreference.getToken(Constant.USER_INFO);

        upozilaMutableLiveData.setValue(DataResource.loading());

        apiInterface = RetrofitApiClient.getRetrofit().create(ApiInterface.class);
        Call<Upozila> call = apiInterface.requestAllUpozilla(token, id);

        call.enqueue(new Callback<Upozila>() {
            @Override
            public void onResponse(Call<Upozila> call, Response<Upozila> response) {
                if (response.code() == 200 && response.body() != null) {
                    upozilaMutableLiveData.setValue(DataResource.success(response.body()));
                } else {
                    upozilaMutableLiveData.setValue(DataResource.error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<Upozila> call, Throwable t) {
                upozilaMutableLiveData.setValue(DataResource.error(t.getMessage()));
            }
        });

    }

    public void addPotentialClient(PotentialClientPost potentialClientPost, File imageFile) {

        String token = SharedPreference.getToken(Constant.USER_INFO);
        potentialClientMutableLiveData.postValue(DataResource.loading());


        MultipartBody.Part filePart = null;
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), new Gson().toJson(potentialClientPost));

        if (imageFile != null) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), imageFile);

            filePart = MultipartBody.Part.createFormData("attachments", imageFile.getName(), fileBody);
        }

        apiInterface = RetrofitApiClient.getRetrofit().create(ApiInterface.class);

        Call<ResponseModel> call = apiInterface.addPotentialClient(token, filePart, description);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.code() == 201 && response.body() != null) {
                    potentialClientMutableLiveData.postValue(DataResource.success(response.body()));
                } else {
                    potentialClientMutableLiveData.postValue(DataResource.error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                potentialClientMutableLiveData.postValue(DataResource.error(t.getMessage()));
            }
        });


    }
}
