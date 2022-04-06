package com.example.loginusingretrofitapi.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.model.LoginAuth;
import com.example.loginusingretrofitapi.model.UserAuth;
import com.example.loginusingretrofitapi.network.ApiInterface;
import com.example.loginusingretrofitapi.network.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAuthRepository {

    ApiInterface apiInterface;

    private final MutableLiveData<DataResource<LoginAuth>> mutableLiveData = new MutableLiveData<DataResource<LoginAuth>>();
    public LiveData<DataResource<LoginAuth>> resourceLiveData = mutableLiveData;

    public void userAuthLogin(UserAuth userAuth) {
        apiInterface = RetrofitApiClient.getRetrofit().create(ApiInterface.class);

        Call<LoginAuth> call = apiInterface.apiAuthCall(userAuth);

        mutableLiveData.postValue(DataResource.loading());
        call.enqueue(new Callback<LoginAuth>() {
            @Override
            public void onResponse(@NonNull Call<LoginAuth> call, @NonNull Response<LoginAuth> response) {

                if (response.code() == 200 && response.body() != null) {
                    mutableLiveData.postValue(DataResource.success(response.body()));
                }
                else {
                    mutableLiveData.postValue(DataResource.error(response.message()));
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginAuth> call, @NonNull Throwable t) {
                mutableLiveData.postValue(DataResource.error(t.getMessage()));
            }
        });

    }


}
