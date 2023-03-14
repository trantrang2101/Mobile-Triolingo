package com.example.triolingo_mobile.Units;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.triolingo_mobile.DataAccess.DbContext;
import com.example.triolingo_mobile.R;

import java.sql.Connection;

public class ListUnits extends AppCompatActivity {

    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_units);
    }

    public void MoveToLesson(View view) {

    }

    public void GetText(View view) {
        DbContext db = new DbContext();
        connect = db.ConnectionClass();
        if (connect != null) {
            TextView tv = findViewById(R.id.textView5);
            tv.setText("Con me may");
        }
    }
}