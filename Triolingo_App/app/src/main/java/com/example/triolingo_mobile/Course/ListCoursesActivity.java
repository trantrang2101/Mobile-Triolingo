package com.example.triolingo_mobile.Course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.triolingo_mobile.API.APICourse;
import com.example.triolingo_mobile.API.RetrofitClient;
import com.example.triolingo_mobile.Model.Course;
import com.example.triolingo_mobile.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCoursesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);
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
}