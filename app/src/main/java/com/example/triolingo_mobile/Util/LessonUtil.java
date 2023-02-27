package com.example.triolingo_mobile.Util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.triolingo_mobile.Lesson.ListenChoice.ListenChoiceActivity;
import com.example.triolingo_mobile.Lesson.QuestionChoice.QuestionChoiceActivity;
import com.example.triolingo_mobile.Lesson.Reading.ReadingActivity;
import com.example.triolingo_mobile.R;

import java.util.ArrayList;

public class LessonUtil {

    public static void nextQuestion(ArrayList<String> quesList, int quesNo, int curPoint, Context c){
        if (quesNo == quesList.size()){
            quesNo = 0;
        }
        String nextQuesType = quesList.get(quesNo);
        Log.i("next", "next:" + nextQuesType);
        Intent intent;
        switch (Integer.parseInt(nextQuesType)){
            case 7:
                intent = new Intent(c, ReadingActivity.class);
                break;
            case 8:
                intent = new Intent(c, QuestionChoiceActivity.class);
                break;
            case 10:
                intent = new Intent(c, ListenChoiceActivity.class);
                break;
            default:
                intent = new Intent();
                break;
        }
        intent.putStringArrayListExtra("quesList", quesList);
        Log.i("next", "quesL:"+quesList.size());
        intent.putExtra("quesNo", quesNo);
        intent.putExtra("curPoint", curPoint);
        c.startActivity(intent);
    }

    // when click close icon or click go back in control
    public static void closeLesson(Context c, FrameLayout main){
        View view = View.inflate(c, R.layout.lesson_close, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        PopupWindow popupWindow = new PopupWindow(view, width, height, false);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//        blur background layout
        main.getForeground().setAlpha(100);
        popupWindow.setOutsideTouchable(true);


        main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupWindow.dismiss();
                main.getForeground().setAlpha(0);
                return true;
            }
        });

        Button cancelBtn = view.findViewById(R.id.button_cancel);
        Button quitBtn = view.findViewById(R.id.btn_quit);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                main.getForeground().setAlpha(0);
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TODO: quit lesson and go back to lesson list
                Toast.makeText(c, "quit lesson", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
