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
        APICourse apiService = RetrofitClient.getRetrofitInstance().create(APICourse.class);
        Call<List<Course>> call = apiService.getList();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                System.out.println(1);
                if (response.isSuccessful()) {
                    List<Course> list = response.body();
                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list_courses);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(ListCoursesActivity.this);
                    recyclerView.setAdapter(new RecyclerView.Adapter<CourseHolder>(){
                        @NonNull
                        @Override
                        public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_course, parent, false);
                            CourseHolder viewHolder = new CourseHolder(v, parent.getContext());
                            return viewHolder;
                        }

                        @Override
                        public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
                            Course course = list.get(position);
                            holder.setView(course);
                        }

                        @Override
                        public int getItemCount() {
                            return list.size();
                        }
                    });
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    System.out.println(0);
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(-1);
            }
        });
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
                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list_courses);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(ListCoursesActivity.this);
                recyclerView.setAdapter(new RecyclerView.Adapter<CourseHolder>(){
                    @NonNull
                    @Override
                    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_course, parent, false);
                        CourseHolder viewHolder = new CourseHolder(v, parent.getContext());
                        return viewHolder;
                    }

                    @Override
                    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
                        Course course = listResult.get(position);
                        holder.setView(course);
                    }

                    @Override
                    public int getItemCount() {
                        return listResult.size();
                    }
                });
                recyclerView.setLayoutManager(manager);
                recyclerView.setVisibility(View.VISIBLE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            TextView tv = findViewById(R.id.connectDb);
            tv.setText("bO MAY CHAY DUOC ROI");
        }
    }
}