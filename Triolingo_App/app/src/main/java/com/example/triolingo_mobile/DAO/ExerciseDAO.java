package com.example.triolingo_mobile.DAO;

import android.util.Log;

import com.example.triolingo_mobile.DataAccess.DbContext;
import com.example.triolingo_mobile.Model.AnswerModel;
import com.example.triolingo_mobile.Model.Exercise;
import com.example.triolingo_mobile.Model.Question;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO extends DbContext {

    private static ExerciseDAO instance;
    private static String EXERCISE_TABLE ="Exercise";
    private static String QUESTION_TABLE ="Question";
    private static String ANSWER_TABLE ="Answer";

    public static ExerciseDAO getInstance() {
        if (ExerciseDAO.instance == null) {
            ExerciseDAO.instance = new ExerciseDAO();
        }
        return ExerciseDAO.instance;
    }

    public ArrayList<Exercise> getExerciseOfLesson(String lessonId){
        ArrayList<Exercise> list = new ArrayList<>();
        String sql = "select * from " +  EXERCISE_TABLE+ " where LessonId = " + lessonId;
        ResultSet rs = getData(sql);
        try{
            while (rs.next()){
                Exercise e = new Exercise();
                e.setId(rs.getInt("Id"));
                e.setStatus(rs.getInt("Status"));
                e.setTitle(rs.getString("Title"));
                e.setDescription(rs.getString("Description"));
                e.setTypeId(rs.getInt("TypeId"));
                e.setLessonId(rs.getInt("LessonId"));
                e.setFile(rs.getString("File"));
                e.setFileName(rs.getString("FileName"));
                list.add(e);
            }
            rs.close();
        }
        catch (Exception ex) {
            Log.d("sql error", "error in ExerciseDAO");
        }
        return list;
    }

    public Question getQuesOfExercise(int exId){
        String sql = "select * from " +  QUESTION_TABLE+ " where ExerciseId = " + exId;
        ResultSet rs = getData(sql);
        try{
            if (rs.next()){
                Question q = new Question(rs.getInt("Id"), rs.getString("Question1"),
                rs.getInt("Status"), exId, rs.getInt("Mark"));
                return q;
            }
            rs.close();
        }
        catch (Exception ex) {
            Log.d("sql error", "error in ExerciseDAO");
        }
        return null;
    }

    public ArrayList<AnswerModel> getAnswerOfQuestion(int quesId){
        ArrayList<AnswerModel> list = new ArrayList<>();
        String sql = "select * from " +  ANSWER_TABLE+ " where QuestionId = " + quesId;
        ResultSet rs = getData(sql);
        try{
            while (rs.next()){
                AnswerModel a = new AnswerModel(rs.getInt("Id"), quesId, rs.getString("Answer1"),
                rs.getInt("Status"), rs.getBoolean("IsCorrect"));
                list.add(a);
            }
            rs.close();
        }
        catch (Exception ex) {
            Log.d("sql error", "error in ExerciseDAO");
        }
        return list;
    }

    public AnswerModel getCorrectAnswer(List<AnswerModel> list){
        for (AnswerModel a: list){
            if (a.isCorrect()) return a;
        }
        return null;
    }
}
