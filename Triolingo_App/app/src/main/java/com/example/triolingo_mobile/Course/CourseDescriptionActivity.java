package com.example.triolingo_mobile.Course;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Units.ListUnits;

public class CourseDescriptionActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_description);
        Intent unit = getIntent();
        int id = unit.getIntExtra("id",0);
        boolean isAssigned = unit.getBooleanExtra("isAssigned",false);
        ((TextView)findViewById(R.id.totalCourse)).setText(unit.getStringExtra("name"));
        String description = unit.getStringExtra("description");
        ((TextView)findViewById(R.id.unitDesc)).setText(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N?
                Html.fromHtml(description,Html.FROM_HTML_MODE_COMPACT):
                Html.fromHtml(description));
        ((ImageView)findViewById(R.id.course_desc)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(CourseDescriptionActivity.this, ListCoursesActivity.class);
                startActivity(intent);
            }
        });
        ((ImageView)findViewById(R.id.course_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(CourseDescriptionActivity.this, ListUnits.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}