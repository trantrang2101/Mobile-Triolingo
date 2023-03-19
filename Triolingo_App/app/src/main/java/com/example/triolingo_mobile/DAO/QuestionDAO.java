package com.example.triolingo_mobile.DAO;

import com.example.triolingo_mobile.DataAccess.DbContext;
import com.example.triolingo_mobile.Model.LessonModel;
import com.example.triolingo_mobile.Model.Question;
import com.example.triolingo_mobile.Model.StudentCourse;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO extends DbContext {

    private static QuestionDAO instance;
    private static String DB_TABLE_NAME="Question";

    public static QuestionDAO getInstance() {
        if (QuestionDAO.instance == null) {
            QuestionDAO.instance = new QuestionDAO();
        }
        return QuestionDAO.instance;
    }

    public int getMarkByLesson(int lessonid){
        Integer sum= 0;
        String sql ="select sum(Mark) as Mark from "+DB_TABLE_NAME+" where ExerciseId in (select id from Exercise where LessonId="+lessonid+")";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                sum = rs.getInt("Mark");
            }
            rs.close();
        } catch (Exception ex) {

        }

        return sum;
    }
}
