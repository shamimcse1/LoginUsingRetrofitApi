package com.example.loginusingretrofitapi.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.model.Profile;
import com.example.loginusingretrofitapi.repository.ProfileRepository;

public class ProfileViewModel extends ViewModel {

    ProfileRepository profileRepository = new ProfileRepository();

    public LiveData<DataResource<Profile>> liveData = profileRepository.profileLiveData;


    public void getProfileLiveData(String token) {
        profileRepository.getProfileData(token);

    }
}
