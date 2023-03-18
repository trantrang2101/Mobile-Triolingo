package com.example.triolingo_mobile.Course;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triolingo_mobile.Model.Course;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Units.ListUnits;

import java.util.Random;

public class CourseHolder extends RecyclerView.ViewHolder {
    private CardView card;
    private TextView edit_id;
    private TextView edit_name;
    private TextView edit_rate;
    private Context context;
    private Course course;
    private Button btn;

    public CourseHolder(@NonNull View itemView,Context context) {
        super(itemView);
        this.context = context;
    }

    private void bindingAction(View itemView) {
        card.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        Intent intent = new  Intent(itemView.getContext(), ListUnits.class);
        intent.putExtra("id", course.getId());
        itemView.getContext().startActivity(intent);
    }

    public void onClickGuide(View v) {
        Intent intent = new  Intent(itemView.getContext(), CourseDescriptionActivity.class);
        intent.putExtra("id", course.getId());
        intent.putExtra("name", course.getName());
        intent.putExtra("isAssigned", course.isAssign());
        intent.putExtra("description", course.getDescription());
        itemView.getContext().startActivity(intent);
    }
    private void bindingView(View itemView) {
        if(course.isAssign()){
            edit_id = itemView.findViewById(R.id.course_id);
            edit_name = itemView.findViewById(R.id.course_name);
            edit_rate = itemView.findViewById(R.id.course_rate);
            card = itemView.findViewById(R.id.unit_card_view);
            btn = itemView.findViewById(R.id.course_desc);
            card.setVisibility(View.VISIBLE);
            itemView.findViewById(R.id.unit_card_view_not).setVisibility(View.GONE);
        }else{
            edit_id = itemView.findViewById(R.id.course_id_not);
            edit_name = itemView.findViewById(R.id.course_name_not);
            edit_rate = itemView.findViewById(R.id.course_rate_not);
            card = itemView.findViewById(R.id.unit_card_view_not);
            btn = itemView.findViewById(R.id.course_desc_not);
            card.setVisibility(View.VISIBLE);
            itemView.findViewById(R.id.unit_card_view).setVisibility(View.GONE);
        }
    }

    public void setView(@NonNull Course cour) {
        course = cour;
        bindingView(itemView);
        bindingAction(itemView);
        edit_id.setText(cour.getId()+"");
        edit_name.setText(cour.getName()+"");
        edit_rate.setText(cour.getRateAverage()+"");
        if (cour==null||cour.getDescription() == null || cour.getDescription().equals("") || cour.getDescription().trim().equals("")){
            btn.setVisibility(View.INVISIBLE);
        }else{
            btn.setOnClickListener(this::onClickGuide);
        }
    }
}
