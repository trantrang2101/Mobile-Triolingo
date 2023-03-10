package com.example.triolingo_mobile.Lesson.ListenChoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.triolingo_mobile.Lesson.QuestionChoice.QuestionChoiceActivity;
import com.example.triolingo_mobile.Model.AnswerModel;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Util.LessonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListenChoiceActivity extends AppCompatActivity {

    public int currentAnswer = -1;
    public int currentClick = -1;
    public int point = 10;
    TextToSpeech textToSpeech;
    public Button crt_btn;

    public String ques = "Listen and choose correct answer";
    public String descript = "Potato";
    public List<AnswerModel> ansList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_listen_choice);
        FrameLayout main = findViewById(R.id.main_layout);
        main.getForeground().setAlpha(0);

        ConstraintLayout header = (ConstraintLayout) findViewById(R.id.include);
        ProgressBar progressBar = header.findViewById(R.id.lesson_progressBar);

        //        clone data
        Intent intent = getIntent();
        ArrayList<String> quesList = intent.getStringArrayListExtra("quesList");
        int quesNo = intent.getIntExtra("quesNo", -1);
        Log.i("next", "ques Reading:"+quesNo);
        int curProgress = intent.getIntExtra("curPoint", -1);
        progressBar.setProgress(curProgress);

        ansList.add(new AnswerModel(1, 1, "Khoai", 1, true));
        ansList.add(new AnswerModel(2, 1, "G??", 1, false));
        ansList.add(new AnswerModel(3, 1, "C??", 1, false));
        ansList.add(new AnswerModel(4, 1, "Heo", 1, false));
        while (ansList.size()<4){
            ansList.add(new AnswerModel(-1, -1, "", 1, false));
        }

        TextView quesView = findViewById(R.id.lesson_text_listen);
        quesView.setText(ques);

        ImageView closeLesson = findViewById(R.id.lesson_close);
        closeLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LessonUtil.closeLesson(ListenChoiceActivity.this, main);
            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        Button buttonListen = findViewById(R.id.lesson_listen_btn);
        buttonListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale current = getResources().getConfiguration().getLocales().get(0);
                Log.i("speech", "click +" + current.toString());
                textToSpeech.speak(descript, TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        AnswerModel ans = new AnswerModel();
        for (AnswerModel a: ansList){
            if (a.isCorrect()){
                ans = a;
                break;
            }
        }
        String answer = ans.getAnswer();
        int i = 1;
        ArrayList<Button> listButton = new ArrayList<>();

        for (AnswerModel choice : ansList) {
            String idName = "lesson_ans_" + i;
            int id = getResources().getIdentifier(idName, "id", getPackageName());
            Button btn = (Button) findViewById(id);
            listButton.add(btn);
            btn.setText(choice.getAnswer());
            if (choice.getId() == ans.getId()) crt_btn = btn;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // switch choice and change background
                    if (currentClick != -1 && currentClick != id) {
                        Button btnOld = (Button) findViewById(currentClick);
                        btnOld.setBackgroundTintList(ContextCompat.getColorStateList(ListenChoiceActivity.this, R.color.white));
                    }
                    currentClick = id;
                    //set color for choose answer
                    Button btn = (Button) view;
                    btn.setBackgroundTintList(ContextCompat.getColorStateList(ListenChoiceActivity.this, R.color.btn_ans_choice));
                    if (btn.getText().equals(answer)) {
                        currentAnswer = 1;
                    } else {
                        currentAnswer = 0;
                    }
                    Log.i("logQC", currentAnswer + "");

                    // check answer button
                    Button btnCheck = (Button) findViewById(R.id.button_check);
                    btnCheck.setClickable(true);
                    btnCheck.setTextColor(ContextCompat.getColorStateList(ListenChoiceActivity.this,R.color.white));
                    btnCheck.setBackgroundTintList(ContextCompat.getColorStateList(ListenChoiceActivity.this,R.color.correct_text));
                    btnCheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // lock view after check answer
                            listButton.add(buttonListen);
                            lockButtons(listButton);
                            ConstraintLayout footer = (ConstraintLayout) findViewById(R.id.include2);
                            footer.setVisibility(View.GONE);
                            Button continueBtn;
//                            Toast.makeText(QuestionChoiceActivity.this, "click checks", Toast.LENGTH_SHORT).show();
                            if (currentAnswer == 1) {
                                ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.answer_correct);
                                cl.setVisibility(View.VISIBLE);
                                continueBtn = findViewById(R.id.lesson_btn_continue1);
                                progressBar.setProgress(curProgress+point);
                            } else {
                                ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.answer_incorrect);
                                cl.setVisibility(View.VISIBLE);
                                continueBtn = findViewById(R.id.lesson_btn_continue0);
                                crt_btn.setBackgroundTintList(ContextCompat.getColorStateList(ListenChoiceActivity.this,R.color.correct_ans));
                                btn.setBackgroundTintList(ContextCompat.getColorStateList(ListenChoiceActivity.this,R.color.incorrect_ans));
                            }
                            continueBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int curPoint= -1;
                                    if (currentAnswer == 1){
                                        curPoint = intent.getIntExtra("curPoint", -1) + point;
                                    } else{
                                        curPoint = intent.getIntExtra("curPoint", -1);
                                    }
                                    LessonUtil.nextQuestion(quesList, quesNo+1, curPoint, ListenChoiceActivity.this);
                                }
                            });
                        }
                    });
                }
            });
            i++;
        }
    }

    public void lockButtons(@NonNull ArrayList<Button> listButton) {
        for (Button btn : listButton) {
            btn.setClickable(false);
        }
    }
}