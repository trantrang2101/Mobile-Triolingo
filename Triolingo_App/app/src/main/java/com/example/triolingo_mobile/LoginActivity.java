package com.example.triolingo_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triolingo_mobile.Lesson.QuestionChoice.QuestionChoiceActivity;

public class LoginActivity extends AppCompatActivity {
    EditText InputUsername;
    EditText InputPassword;
    Button LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InputUsername = findViewById(R.id.userNameTxt);
        InputPassword = findViewById(R.id.passwordTxt);
        LoginBtn = findViewById(R.id.loginBtn);

        InputUsername.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String UserName = InputUsername.getText().toString().trim();
                String Password = InputPassword.getText().toString().trim();
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (!UserName.isEmpty() && !Password.isEmpty()) {
                        LoginBtn.setEnabled(true);
                        LoginBtn.setBackgroundColor(Color.BLUE);
                        LoginBtn.setTextColor(Color.parseColor("#FFFFFF"));

                    } else {
                        LoginBtn.setEnabled(false);
                        LoginBtn.setBackgroundColor(Color.parseColor(("#5E756969")));
                        LoginBtn.setTextColor(Color.parseColor("#BAEFE2E2"));
                    }
                    return true;
                }
                return false;
            }
        });
        InputPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String UserName = InputUsername.getText().toString().trim();
                String Password = InputPassword.getText().toString().trim();
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (!UserName.isEmpty() && !Password.isEmpty()) {
                        LoginBtn.setEnabled(true);
                        LoginBtn.setBackgroundColor(Color.BLUE);
                        LoginBtn.setTextColor(Color.parseColor("#FFFFFF"));

                    } else {
                        LoginBtn.setEnabled(false);
                        LoginBtn.setBackgroundColor(Color.parseColor(("#5E756969")));
                        LoginBtn.setTextColor(Color.parseColor("#BAEFE2E2"));
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void LoginBtn(View v) {
        String UserName = InputUsername.getText().toString().trim();
        String Password = InputPassword.getText().toString().trim();
        if (!UserName.equals("admin") && !Password.equals("admin")) {
            InputUsername.setError("Username or Password not correct");
        } else {
            Intent switchActivityIntent = new Intent(this, QuestionChoiceActivity.class);
            startActivity(switchActivityIntent);
            Toast.makeText(this, "Login successfully...", Toast.LENGTH_SHORT).show();
        }
    }
}