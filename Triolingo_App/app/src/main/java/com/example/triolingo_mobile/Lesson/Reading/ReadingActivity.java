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

import com.example.triolingo_mobile.Lesson.QuestionChoice.QuestionChoiceActivity;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Util.LessonUtil;

import java.util.ArrayList;

public class ReadingActivity extends AppCompatActivity {

    public int point = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_reading);
        FrameLayout main = findViewById(R.id.main_layout);
        main.getForeground().setAlpha(0);

        ConstraintLayout header = (ConstraintLayout) findViewById(R.id.include);
        ProgressBar progressBar = header.findViewById(R.id.lesson_progressBar);

        Intent intent = getIntent();
        ArrayList<String> quesList = intent.getStringArrayListExtra("quesList");
        int quesNo = intent.getIntExtra("quesNo", -1);
        Log.i("next", "ques Reading:"+quesNo);
        int curProgress = intent.getIntExtra("curPoint", -1) ;
        progressBar.setProgress(curProgress);

        String ques = "Reading this paragraph below";
        String text = "Hắn vừa đi vừa chửi. Bao giờ cũng thế, cứ rượu xong là hắn chửi. Bắt đầu chửi trời, có hề gì?\nTrời có của riêng nhà nào? Rồi hắn chửi đời. Thế cũng chẳng sao: Đời là tất cả nhưng cũng chẳng là ai. Tức mình hắn chửi ngay tất cả làng Vũ Đại. Nhưng cả làng Vũ Đại ai cũng nhủ: “Chắc nó trừ mình ra!”. Không ai lên tiếng cả. Tức thật! Ồ thế này thì tức thật! Tức chết đi được mất! Đã thế, hắn phải chửi cha đứa nào không chửi nhau với hắn. Nhưng cũng không ai ra điều. Mẹ kiếp! Thế thì có phí rượu không? Thế thì có khổ hắn không? Không biết đứa chết mẹ nào đẻ ra thân hắn cho hắn khổ đến nông nỗi này! A ha! Phải đấy hắn cứ thế mà chửi, hắn chửi đứa chết mẹ nào đẻ ra thân hắn, đẻ ra cái thằng Chí Phèo? Mà có trời biết! Hắn không biết, cả làng Vũ Đại cũng không ai biết.";

        TextView textView = (TextView) findViewById(R.id.lesson_reading_text);
        textView.setText(text);
        TextView quesView = findViewById(R.id.lesson_text_ques);
        quesView.setText(ques);

        ImageView closeLesson = findViewById(R.id.lesson_close);
        closeLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LessonUtil.closeLesson(ReadingActivity.this, main);
            }
        });

        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_reading);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            boolean first_time = true;
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int diff = (textView.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

                if (diff==0 && first_time){
//                    lockScroll(scrollView);
                    ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.answer_correct);
                    cl.setVisibility(View.VISIBLE);
                    progressBar.setProgress(curProgress + point);
                    first_time = false;
                    Button continueBtn = findViewById(R.id.lesson_btn_continue1);
                    continueBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LessonUtil.nextQuestion(quesList ,quesNo+1, curProgress + point, ReadingActivity.this);
                        }
                    });
                }
            }
        });
    }

    public void lockScroll(ScrollView scrollView){
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }
}