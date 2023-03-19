package com.example.triolingo_mobile.Lesson.WordMatching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.triolingo_mobile.DAO.ExerciseDAO;
import com.example.triolingo_mobile.DAO.LessonDAO;
import com.example.triolingo_mobile.DAO.QuestionDAO;
import com.example.triolingo_mobile.Model.AnswerModel;
import com.example.triolingo_mobile.Model.Question;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Util.LessonUtil;

import java.util.HashMap;
import java.util.List;

public class WordMatchingActivity extends AppCompatActivity {
    private HashMap<String, Integer> QuestionColumn;
    private HashMap<String, Integer> AnswerColumn;
    private RecyclerView questionColumn;
    private RecyclerView answerColumn;
    private Button continueBtn;
    private int curPoint;
    private String selectedQuestion;
    private String selectedAnswer;
    private WordListAdapter questionAdapter;
    private WordListAdapter answerAdapter;
    private List<Question> questions;
    private ProgressBar progressBar;

    private int exerciseNo;
    private int totalPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_matching_execise);
        questionColumn = findViewById(R.id.question_recyclerview);
        answerColumn = findViewById(R.id.answer_recyclerview);
        continueBtn = findViewById(R.id.word_match_continue);
        progressBar = findViewById(R.id.include4).findViewById(R.id.lesson_progressBar);

        GetQuestionData();
    }

    void GetQuestionData() {
        Intent intent = getIntent();
        int exerciseId = intent.getIntExtra("exerciseId", -1);
        exerciseNo = intent.getIntExtra("exerciseNo", -1);
        questions = QuestionDAO.getInstance().getQuestionsByExId(exerciseId,"STATUS>0");
        int progressNum = LessonUtil.getLoadedExerciseCount() > 0 ? 100*exerciseNo/LessonUtil.getLoadedExerciseCount() : 0;
        progressBar.setProgress(progressNum);
        curPoint = 0;
        QuestionColumn = new HashMap<>();
        AnswerColumn = new HashMap<>();
        for (Question question : questions) {
            List<AnswerModel> answers = ExerciseDAO.getInstance().getAnswerOfQuestion(question.getId(), "STATUS > 0 AND IsCorrect > 0");
            if (question.getQuestion1() != null && answers.size() > 0) {
                QuestionColumn.put(question.getQuestion1(), question.getId());
                AnswerColumn.put(answers.get(0).getAnswer(), question.getId());
                curPoint += question.getMark();
            }
        }

        totalPoint = curPoint;
        questionAdapter = new WordListAdapter(QuestionColumn, this::OnQuestionClick, false);
        questionColumn.setAdapter(questionAdapter);
        questionColumn.setLayoutManager(new LinearLayoutManager(this));
        answerAdapter = new WordListAdapter(AnswerColumn, this::OnAnswerClick, true);
        answerColumn.setAdapter(answerAdapter);
        answerColumn.setLayoutManager(new LinearLayoutManager(this));
        continueBtn.setOnClickListener(this::OnClickContinue);

        if (!IsFinished()) {
            continueBtn.setEnabled(false);
        }
    }

    int OnAnswerClick(WordItemHolder holder) {
        selectedAnswer = holder.getWord();
        if (selectedQuestion == null) {
            // no question is selected yet
            return 0; // 0 mean selected
        }
        else {
            // selected an answer, then a question
            boolean isCorrect = QuestionColumn.get(selectedQuestion) == AnswerColumn.get(selectedAnswer);
            questionAdapter.FinalizeLastSelected(isCorrect);
            RemoveWord(isCorrect);
            DeselectAll();
            return isCorrect ? 1 : QuestionColumn.size() > 0 ? -1 : -2;
        }
    }

    void RemoveWord(boolean isCorrect) {
        if (selectedQuestion == null)
            return;

        int id = QuestionColumn.containsKey(selectedQuestion) ? QuestionColumn.get(selectedQuestion) : -1;
        if (!isCorrect) {
            for (Question q : questions) {
                if (q.getId() == id) {
                    curPoint -= q.getMark();
                    break;
                }
            }
        }
        QuestionColumn.remove(selectedQuestion);
        if (IsFinished()) {
            // next exercise
            for (int qId : QuestionColumn.values()) {
                for (Question question : questions) {
                    if (qId == question.getId()) {
                        curPoint -= question.getMark();
                        break;
                    }
                }
            }
            questionAdapter.MarkAllIncorrect();
            answerAdapter.MarkAllIncorrect();
            continueBtn.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.progressbar_process));
            continueBtn.setEnabled(true);
        }
    }

    boolean IsFinished() {
        for (int question : QuestionColumn.values()) {
            for (int answer : AnswerColumn.values()) {
                if (answer == question) {
                    // there's still answer for the question
                    return false;
                }
            }
        }
        return true;
    }

    int OnQuestionClick(WordItemHolder holder) {
        selectedQuestion = holder.getWord();
        if (selectedAnswer == null) {
            // no answer is selected yet
            return 0; // 0 mean selected
        }
        else {
            // selected a question, then an answer
            boolean isCorrect = QuestionColumn.get(selectedQuestion) == AnswerColumn.get(selectedAnswer);
            answerAdapter.FinalizeLastSelected(isCorrect);
            questionAdapter.SetLastQuestionWrong();
            RemoveWord(isCorrect);
            DeselectAll();
            return isCorrect ? 1 : -1;
        }
    }

    void DeselectAll() {
        selectedQuestion = null;
        selectedAnswer = null;
    }

    void OnClickContinue(View v) {
        Intent intent = getIntent();
        int nowProgress = LessonUtil.getLoadedExerciseCount() > 0 ? 100*(exerciseNo + 1)/LessonUtil.getLoadedExerciseCount() : 100;
        progressBar.setProgress(nowProgress);
        LessonUtil.nextExercise(
                exerciseNo + 1,
                intent.getIntExtra("curPoint", 0) + curPoint,
                intent.getIntExtra("totalPoint", 0) + totalPoint,
                nowProgress,
                WordMatchingActivity.this);
    }
}