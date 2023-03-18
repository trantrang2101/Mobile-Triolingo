package com.example.triolingo_mobile.Lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Units.ListUnits;

public class LessonFinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lession_finish);

        Intent intent = getIntent();
        int curPoint = intent.getIntExtra("curPoint", -1);
        int totalPoint = intent.getIntExtra("totalPoint", -1);

        TextView score = (TextView) findViewById(R.id.ScoreTxt);
        score.setText(curPoint+"/"+totalPoint);

        Button btn = findViewById(R.id.continueBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LessonFinishActivity.this, ListUnits.class);

                startActivity(i);
            }
        });
    }
}