package com.example.loginusingretrofitapi.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.model.Profile;
import com.example.loginusingretrofitapi.network.ApiInterface;
import com.example.loginusingretrofitapi.network.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    ApiInterface apiInterface;

    private final MutableLiveData<DataResource<Profile>> mutableLiveData = new MutableLiveData<DataResource<Profile>>();
    public LiveData<DataResource<Profile>> profileLiveData = mutableLiveData;

    public void getProfileData(String token) {

        apiInterface = RetrofitApiClient.getRetrofit().create(ApiInterface.class);

        Call<Profile> call = apiInterface.getProfile(token);

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.code() == 200 && response.body() != null) {
                    mutableLiveData.postValue(DataResource.success(response.body()));
                }
                else {
                    mutableLiveData.postValue(DataResource.error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                mutableLiveData.postValue(DataResource.error(t.getMessage()));
            }
        });


    }
}
