package com.example.triolingo_mobile.Lesson.WordMatching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.triolingo_mobile.DAO.LessonDAO;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Util.LessonUtil;

import java.util.HashMap;

public class WordMatchingActivity extends AppCompatActivity {
    private HashMap<String, String> Question_Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_matching_execise);
    }

    void GetQuestionData() {
//        var LessonDAO
        Intent intent = getIntent();
        int exerciseID = intent.getIntExtra("exerciseId", -1);
        if (exerciseID < 0) {
            // next question
        }
    }

    void finishExecercise() {

    }
}