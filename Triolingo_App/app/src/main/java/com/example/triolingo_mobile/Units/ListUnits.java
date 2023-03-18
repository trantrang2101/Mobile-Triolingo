package com.example.triolingo_mobile.Units;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.triolingo_mobile.Course.CourseAdapter;
import com.example.triolingo_mobile.Course.ListCoursesActivity;
import com.example.triolingo_mobile.DAO.CourseDAO;
import com.example.triolingo_mobile.DAO.UnitDAO;
import com.example.triolingo_mobile.DataAccess.DbContext;
import com.example.triolingo_mobile.Model.Course;
import com.example.triolingo_mobile.Model.UnitModel;
import com.example.triolingo_mobile.R;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ListUnits extends AppCompatActivity {

    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_units);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);

        List<UnitModel> listResult = UnitDAO.getInstance().getList("Status>0 AND "+(id==0?"":"CourseId="+id));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_unit);
        UnitAdapter adapter = new UnitAdapter(listResult);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(ListUnits.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.setVisibility(View.VISIBLE);
    }
}