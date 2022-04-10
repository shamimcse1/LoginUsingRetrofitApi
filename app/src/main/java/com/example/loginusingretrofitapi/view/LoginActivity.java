package com.example.loginusingretrofitapi.view;

import static com.example.loginusingretrofitapi.helpers.Constant.USER_INFO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginusingretrofitapi.R;
import com.example.loginusingretrofitapi.databinding.ActivityLoginBinding;
import com.example.loginusingretrofitapi.databinding.ActivityLoginBindingImpl;
import com.example.loginusingretrofitapi.databinding.ActivityMainBinding;
import com.example.loginusingretrofitapi.helpers.Constant;
import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.helpers.SharedPreference;
import com.example.loginusingretrofitapi.model.LoginAuth;
import com.example.loginusingretrofitapi.model.UserAuth;
import com.example.loginusingretrofitapi.viewModel.UserAuthViewModel;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding mainBinding;
    ProgressDialog progressDialog;
    UserAuthViewModel userViewModel;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        sharedPreference = new SharedPreference(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");

        userViewModel = new ViewModelProvider(this).get(UserAuthViewModel.class);

        userViewModel.liveData.observe(this, new Observer<DataResource<LoginAuth>>() {
            @Override
            public void onChanged(DataResource<LoginAuth> loginAuth) {
                switch (loginAuth.getStatus()) {
                    case SUCCESS:

                        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        String token = "Bearer " + loginAuth.getActualData().getToken();
                        sharedPreference.setToken(USER_INFO,token);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case ERROR:
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        break;

                    case LOADING:
                        progressDialog.show();
                        break;

                }
            }
        });

        mainBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mainBinding.edtUserId.getText().toString();
                String password = mainBinding.edtPassword.getText().toString();

                if (!isEmpty(id, password)) {
                    UserAuth userAuth = new UserAuth(Constant.CLIENT_ID, Constant.CLIENT_SECRET, id, password);
                    userViewModel.authLogin(userAuth);
                }

            }
        });


    }

    public boolean isEmpty(String UserID, String Password) {
        boolean isEmptyResult = false;
        if (UserID.length() == 0) {
            mainBinding.edtUserId.setError("Enter User ID");
            isEmptyResult = true;
        }
        if (Password.length() == 0) {
            mainBinding.edtPassword.setError("Enter Password");
            isEmptyResult = true;
        }
        return isEmptyResult;
    }
}