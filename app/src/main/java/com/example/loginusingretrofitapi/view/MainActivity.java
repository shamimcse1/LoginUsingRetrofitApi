package com.example.loginusingretrofitapi.view;

import static com.example.loginusingretrofitapi.helpers.Constant.USER_INFO;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginusingretrofitapi.R;
import com.example.loginusingretrofitapi.databinding.ActivityMainBinding;
import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.helpers.SharedPreference;
import com.example.loginusingretrofitapi.model.Profile;
import com.example.loginusingretrofitapi.viewModel.ProfileViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    ProgressDialog progressDialog;
    SharedPreference sharedPreference;
    ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        sharedPreference = new SharedPreference(this);
        String token = sharedPreference.getToken(USER_INFO);
        String mainToken = "Bearer " + token;
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.getProfileLiveData(mainToken);


        profileViewModel.liveData.observe(this, new Observer<DataResource<Profile>>() {
            @Override
            public void onChanged(DataResource<Profile> profileDataResource) {
                mainBinding.id.setText(String.valueOf(profileDataResource.getActualData().getId()));
                mainBinding.userName.setText(profileDataResource.getActualData().getUserName());
                mainBinding.designation.setText(profileDataResource.getActualData().getDesignation());
                mainBinding.phone.setText(profileDataResource.getActualData().getPhone());


            }
        });


    }

}