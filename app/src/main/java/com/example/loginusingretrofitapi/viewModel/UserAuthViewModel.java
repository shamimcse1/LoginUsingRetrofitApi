package com.example.loginusingretrofitapi.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.model.LoginAuth;
import com.example.loginusingretrofitapi.model.UserAuth;
import com.example.loginusingretrofitapi.repository.UserAuthRepository;

public class UserAuthViewModel extends ViewModel {

    UserAuthRepository repository  = new UserAuthRepository();
    public LiveData<DataResource<LoginAuth>> liveData = repository.resourceLiveData;


    public void authLogin(UserAuth userAuth){
        repository.userAuthLogin(userAuth);
    }
}
