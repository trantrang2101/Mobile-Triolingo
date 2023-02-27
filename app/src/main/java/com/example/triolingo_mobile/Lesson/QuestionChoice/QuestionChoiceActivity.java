package com.example.triolingo_mobile.Lesson.QuestionChoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.triolingo_mobile.Model.AnswerModel;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Util.LessonUtil;

import java.util.ArrayList;
import java.util.List;



public class QuestionChoiceActivity extends AppCompatActivity {

    public int currentAnswer = -1;
    public int currentClick = -1;
    public int point = 10;
    public Button crt_btn;

    public String ques = "Choose correct answer";
    public String description = "The meaning of Potato in VN";
    public List<AnswerModel> ansList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_question_choice);
        FrameLayout main = findViewById(R.id.main_layout);
        main.getForeground().setAlpha(0);

        ConstraintLayout header = (ConstraintLayout) findViewById(R.id.include);
        ProgressBar progressBar = header.findViewById(R.id.lesson_progressBar);

//        only for clone data
//    list of ques/excercise for a lesson
        ArrayList<String> quesList = new ArrayList<>();
//    current ques/ex
        int quesNo = -1;
        quesList.add("7");
        quesList.add("10");
        quesList.add("8");
        ansList.add(new AnswerModel(1, 1, "Khoai", 1, true));
        ansList.add(new AnswerModel(2, 1, "Gà", 1, false));
        ansList.add(new AnswerModel(3, 1, "Cá", 1, false));
        ansList.add(new AnswerModel(4, 1, "Heo", 1, false));
//        add null answer to fullfil 4 button
        while (ansList.size()<4){
            ansList.add(new AnswerModel(-1, -1, "", 1, false));
        }

        TextView textQues = findViewById(R.id.lesson_text_ques);
        String displayAns = ques+":\n \""+ description + "\"";
        textQues.setText(displayAns);

        AnswerModel ans = new AnswerModel();
        for (AnswerModel a: ansList){
            if (a.isCorrect()){
                ans = a;
                break;
            }
        }
        String answer = ans.getAnswer();
        ArrayList<Button> listButton = new ArrayList<>();

        ImageView closeLesson = findViewById(R.id.lesson_close);
        closeLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LessonUtil.closeLesson(QuestionChoiceActivity.this, main);
            }
        });

        int i = 1;
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
                        btnOld.setBackgroundTintList(ContextCompat.getColorStateList(QuestionChoiceActivity.this, R.color.white));
                    }
                    currentClick = id;
                    //set color for choose answer
                    Button btn = (Button) view;
                    btn.setBackgroundTintList(ContextCompat.getColorStateList(QuestionChoiceActivity.this, R.color.btn_ans_choice));
                    if (btn.getText().equals(answer)) {
                        currentAnswer = 1;
                    } else {
                        currentAnswer = 0;
                    }
                    Log.i("logQC", currentAnswer + "");

                    // check answer button
                    Button btnCheck = (Button) findViewById(R.id.button_check);
                    btnCheck.setClickable(true);
                    btnCheck.setTextColor(ContextCompat.getColorStateList(QuestionChoiceActivity.this, R.color.white));
                    btnCheck.setBackgroundTintList(ContextCompat.getColorStateList(QuestionChoiceActivity.this, R.color.correct_text));
                    btnCheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // lock view after check answer
                            lockButtons(listButton);
                            ConstraintLayout footer = (ConstraintLayout) findViewById(R.id.include2);
                            footer.setVisibility(View.GONE);
                            Button continueBtn;
//                            Toast.makeText(QuestionChoiceActivity.this, "click checks", Toast.LENGTH_SHORT).show();
                            if (currentAnswer == 1) {
                                ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.answer_correct);
                                cl.setVisibility(View.VISIBLE);
                                continueBtn = findViewById(R.id.lesson_btn_continue1);
                                int curProgress = progressBar.getProgress();
                                progressBar.setProgress(curProgress + point);
                            } else {
                                ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.answer_incorrect);
                                cl.setVisibility(View.VISIBLE);
                                continueBtn = findViewById(R.id.lesson_btn_continue0);
                                crt_btn.setBackgroundTintList(ContextCompat.getColorStateList(QuestionChoiceActivity.this, R.color.correct_ans));
                                btn.setBackgroundTintList(ContextCompat.getColorStateList(QuestionChoiceActivity.this, R.color.incorrect_ans));
                            }
                            continueBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LessonUtil.nextQuestion(quesList,quesNo+1, 0, QuestionChoiceActivity.this);
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