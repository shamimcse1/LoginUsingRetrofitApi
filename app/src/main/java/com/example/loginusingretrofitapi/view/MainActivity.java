package com.example.loginusingretrofitapi.view;

import static com.example.loginusingretrofitapi.helpers.Constant.USER_INFO;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginusingretrofitapi.R;
import com.example.loginusingretrofitapi.databinding.ActivityMainBinding;
import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.helpers.SharedPreference;
import com.example.loginusingretrofitapi.model.Profile;
import com.example.loginusingretrofitapi.repository.PotentialClientRepository;
import com.example.loginusingretrofitapi.viewModel.PotentialClientViewModel;
import com.example.loginusingretrofitapi.viewModel.ProfileViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    ProgressDialog progressDialog;
    ProfileViewModel profileViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        String token = SharedPreference.getToken(USER_INFO);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.getProfileLiveData(token);

        mainBinding.addClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddPotentialClientActivity.class));
            }
        });



        profileViewModel.liveData.observe(this, new Observer<DataResource<Profile>>() {
            @Override
            public void onChanged(DataResource<Profile> profileDataResource) {
                mainBinding.id.setText(String.valueOf("ID : "+ profileDataResource.getActualData().getId()));
                mainBinding.userName.setText("Name : "+profileDataResource.getActualData().getUserName());
                mainBinding.designation.setText("Designation : "+profileDataResource.getActualData().getDesignation());
                mainBinding.phone.setText("Phone : "+profileDataResource.getActualData().getPhone());


            }
        });


    }

}