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
    public void GetText() {
        DbContext db = new DbContext();
        connect = db.ConnectionClass();
        if (connect != null) {
            String sql = "Select * from Course";
            try {
                Statement state = connect.createStatement();
                ResultSet rs = state.executeQuery(sql);
                List<Course> listResult = new ArrayList<>();
                while (rs.next()) {
                    Course c = new Course();
                    c.setName(rs.getString("Name"));
                    c.setDescription(rs.getString("Description"));
                    c.setNote(rs.getString("Note"));
                    c.setRateAverage(rs.getFloat("RateAverage"));
                    c.setStatus(rs.getInt("Status"));
                    c.setId(rs.getInt("Id"));
                    listResult.add(c);
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
}