package com.example.triolingo_mobile.Units;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.triolingo_mobile.Lesson.ListenChoice.ListenChoiceActivity;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Util.LessonUtil;

public class ListUnits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_units);
        ImageButton bt = findViewById(R.id.bt_Unit);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListUnits.this, LessonProgress.class);
                startActivity(intent);
            }
        });
    }

    public void MoveToLesson(View view) {

    }
}