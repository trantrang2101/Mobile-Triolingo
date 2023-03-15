package com.example.triolingo_mobile.Course;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triolingo_mobile.Model.Course;
import com.example.triolingo_mobile.R;
import com.example.triolingo_mobile.Units.ListUnits;

public class CourseHolder extends RecyclerView.ViewHolder {
    private RelativeLayout card;
    private TextView edit_id;
    private TextView edit_name;
    private TextView edit_rate;
    private Context context;

    public CourseHolder(@NonNull View itemView,Context context) {
        super(itemView);
        bindingView(itemView);
        bindingAction(itemView);
        this.context = context;
    }

    private void bindingAction(View itemView) {
        card.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        Intent intent = new  Intent(itemView.getContext(), ListUnits.class);
        intent.putExtra("id", Integer.parseInt(edit_id.getText().toString()));
        itemView.getContext().startActivity(intent);
    }
    private void bindingView(View itemView) {
        edit_id = itemView.findViewById(R.id.course_id);
        edit_name = itemView.findViewById(R.id.course_name);
        edit_rate = itemView.findViewById(R.id.course_rate);
        card = itemView.findViewById(R.id.courseBtn);
    }

    public void setView(Course course) {
        edit_id.setText(course.getId()+"");
        edit_name.setText(course.getName());
        edit_rate.setText(course.getRateAverage()+"");
    }
}
