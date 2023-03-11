package com.example.triolingo_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.triolingo_mobile.Model.UserModel;
import com.google.gson.Gson;


public class AccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        SharedPreferences sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String json = sharedPref.getString("user", null);
        if (json != null) {
            Gson gson = new Gson();
            UserModel userLogin = gson.fromJson(json, UserModel.class);
            TextView username = findViewById(R.id.tv_name);
            username.setText(userLogin.getUsername());
        }

        (findViewById(R.id.btnSetting)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingProfile = new  Intent(AccountActivity.this, SettingProfileActivity.class);
                startActivity(settingProfile);
            }
        });
    }
}