package com.example.triolingo_mobile.Lesson.Reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.triolingo_mobile.DAO.ExerciseDAO;
import com.example.triolingo_mobile.Lesson.ListenChoice.ListenChoiceActivity;
import com.example.triolingo_mobile.Model.Exercise;
import com.example.triolingo_mobile.Model.Question;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Util.LessonUtil;

import java.util.ArrayList;

public class ReadingActivity extends AppCompatActivity {

    public int curPoint = 0;
    public int totalPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_reading);
        FrameLayout main = findViewById(R.id.main_layout);
        main.getForeground().setAlpha(0);

        ConstraintLayout header = (ConstraintLayout) findViewById(R.id.include);
        ProgressBar progressBar = header.findViewById(R.id.lesson_progressBar);

        Intent intent = getIntent();
        ExerciseDAO exDao = ExerciseDAO.getInstance();

//        ArrayList<Exercise> listExercise = (ArrayList<Exercise>) intent.getSerializableExtra("listExercise");
        int quesNo = intent.getIntExtra("quesNo", -1);
        int progressPercent = intent.getIntExtra("progressPercent", -1);
        curPoint += intent.getIntExtra("curPoint", -1);
        totalPoint += intent.getIntExtra("totalPoint", -1);
        Log.i("next", "ques No:" + quesNo);
        int curProgress = intent.getIntExtra("curProgress", -1);

        progressBar.setProgress(curProgress);

//        Exercise exercise = listExercise.get(quesNo);
//        Question question = exDao.getQuesOfExercise(exercise.getId());
        Question question = LessonUtil.getListQuestion().get(quesNo);
//        String text = exercise.getTitle();
        String ques = question.getQuestion1();
        totalPoint += question.getMark();

        TextView textView = (TextView) findViewById(R.id.lesson_reading_text);
        textView.setText(ques);
        TextView quesView = findViewById(R.id.lesson_text_ques);
//        quesView.setText(text);
        ImageView closeLesson = findViewById(R.id.lesson_close);
        closeLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LessonUtil.closeLesson(ReadingActivity.this, main);
            }
        });

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                long future_time = System.currentTimeMillis() + 5*1000;
//                long cur_time = System.currentTimeMillis();
//                while (cur_time < future_time){
//                    cur_time += 1*1000;
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.answer_correct);
//                        cl.setVisibility(View.VISIBLE);
//                        curPoint += question.getMark();
//                        progressBar.setProgress(curProgress + progressPercent);
//                        Button continueBtn = findViewById(R.id.lesson_btn_continue1);
//                        continueBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                LessonUtil.nextExercise(listExercise, quesNo+1, curPoint,
//                                        totalPoint,curProgress + progressPercent,
//                                        ReadingActivity.this);
//                            }
//                        });
//                    }
//                });
//            }
//        });
//        t.start();

        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_reading);
        if (canScroll(scrollView)) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                boolean first_time = true;

                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    int diff = (textView.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

                    if (diff == 0 && first_time) {
//                    lockScroll(scrollView);
                        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.answer_correct);
                        cl.setVisibility(View.VISIBLE);
                        curPoint += question.getMark();
                        progressBar.setProgress(curProgress + progressPercent);
                        first_time = false;
                        Button continueBtn = findViewById(R.id.lesson_btn_continue1);
                        continueBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                LessonUtil.nextQuestion(quesNo + 1, curPoint,
                                        totalPoint, curProgress + progressPercent,progressPercent,
                                        ReadingActivity.this);
                            }
                        });
                    }
                }
            });
        } else {
            ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.answer_correct);
            cl.setVisibility(View.VISIBLE);
            curPoint += question.getMark();
            progressBar.setProgress(curProgress + progressPercent);
            Button continueBtn = findViewById(R.id.lesson_btn_continue1);
            continueBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LessonUtil.nextQuestion( quesNo + 1, curPoint,
                            totalPoint, curProgress + progressPercent,progressPercent,
                            ReadingActivity.this);
                }
            });
        }

    }

    public void lockScroll(ScrollView scrollView) {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    private boolean canScroll(ScrollView scrollView) {
        View child = (View) scrollView.getChildAt(0);
        if (child != null) {
            int childHeight = (child).getHeight();
            return scrollView.getHeight() < childHeight + scrollView.getPaddingTop() + scrollView.getPaddingBottom();
        }
        return false;
    }
}