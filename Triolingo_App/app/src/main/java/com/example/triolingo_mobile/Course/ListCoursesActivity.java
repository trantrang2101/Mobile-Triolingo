package com.example.triolingo_mobile.Course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.triolingo_mobile.API.APICourse;
import com.example.triolingo_mobile.API.RetrofitClient;
import com.example.triolingo_mobile.DAO.CourseDAO;
import com.example.triolingo_mobile.DataAccess.DbContext;
import com.example.triolingo_mobile.Model.Course;
import com.example.triolingo_mobile.R;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCoursesActivity extends AppCompatActivity {
    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);
        GetText();
    }
    public void GetText()
    {
        try {
            List<Course> listResult = CourseDAO.getInstance().getList("Status>0");
            for (Course cour: listResult) {
                cour.setAssign(new Random().nextBoolean());
            }
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_courses);
            CourseAdapter adapter = new CourseAdapter(listResult);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(ListCoursesActivity.this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(manager);
            recyclerView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}