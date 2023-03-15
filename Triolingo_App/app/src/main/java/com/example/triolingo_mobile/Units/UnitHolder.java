package com.example.triolingo_mobile.Units;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.triolingo_mobile.DAO.LessonDAO;
import com.example.triolingo_mobile.DAO.UnitDAO;
import com.example.triolingo_mobile.Model.LessonModel;
import com.example.triolingo_mobile.Model.UnitModel;
import com.example.triolingo_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class UnitHolder extends RecyclerView.ViewHolder {
    private Button card;
    private TextView edit_id;
    private TextView edit_name;
    private TextView edit_desc;
    private RecyclerView list_lesson;
    private Context context;

    public UnitHolder(@NonNull View itemView, Context context) {
        super(itemView);
        bindingView(itemView);
        bindingAction(itemView);
        this.context = context;
    }

    private void bindingAction(View itemView) {
        card.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        list_lesson.setVisibility(list_lesson.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
    }
    private void bindingView(View itemView) {
        edit_id = itemView.findViewById(R.id.unit_id);
        edit_name = itemView.findViewById(R.id.unit_name);
        edit_desc = itemView.findViewById(R.id.unit_description);
        card = itemView.findViewById(R.id.unit_btn);
        list_lesson=itemView.findViewById(R.id.lesson_list);
    }

    public void setView(UnitModel unit) {
        edit_id.setText(unit.getId()+"");
        edit_desc.setText(unit.getDescription());
        edit_name.setText(unit.getName());
        List<LessonModel> listResult = LessonDAO.getInstance().getList("UnitId=="+unit.getId());
        LessonAdapter adapter = new LessonAdapter(listResult,list_lesson);
        list_lesson.setAdapter(adapter);
        list_lesson.setVisibility(View.VISIBLE);
        list_lesson.addItemDecoration(new MarginDecoration(10));
        int column = 4;
        GridLayoutManager layoutManager = new GridLayoutManager(context, column) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                int row = 0;
                int col = 2;
                boolean rightToLeft=false;
                for (int i = 0; i < getChildCount(); i++) {
                    View view = getChildAt(i);
                    int left = col * view.getWidth();
                    int top = row * view.getHeight();
                    int right = left + view.getWidth();
                    int bottom = top + view.getHeight();
                    view.layout(left, top, right, bottom);
                    row++;
                    if(rightToLeft){
                        if(col-1<0){
                            col++;
                            rightToLeft=false;
                        }else{
                            col--;
                        }
                    }else{
                        if(col+1>column-1){
                            col--;
                            rightToLeft=true;
                        }else{
                            col++;
                        }
                    }
                }
            }
        };
        list_lesson.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(list_lesson);
    }
}