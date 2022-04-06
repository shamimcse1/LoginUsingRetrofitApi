package com.example.loginusingretrofitapi.helpers;


import static android.content.Context.MODE_PRIVATE;
import static com.example.loginusingretrofitapi.helpers.Constant.USER_INFO;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {


    private Context context;

    public SharedPreference(Context context) {
        this.context = context;
    }

    public void setToken(String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(USER_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getToken(String key) {
        SharedPreferences prefs = context.getSharedPreferences(USER_INFO, MODE_PRIVATE);
        return prefs.getString(key, null);
    }


}
