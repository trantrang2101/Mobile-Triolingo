package com.example.triolingo_mobile.DAO;

import com.example.triolingo_mobile.DataAccess.DbContext;
import com.example.triolingo_mobile.Model.StudentCourse;
import com.example.triolingo_mobile.Model.StudentLesson;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentLessonDAO extends DbContext {

    private static StudentLessonDAO instance;
    private static String DB_TABLE_NAME="[StudentLesson]";

    public static StudentLessonDAO getInstance() {
        if (StudentLessonDAO.instance == null) {
            StudentLessonDAO.instance = new StudentLessonDAO();
        }
        return StudentLessonDAO.instance;
    }

    public List<StudentLesson> getList(String search) {
        List<StudentLesson> list = new ArrayList<>();
        String sql = "Select * from "+DB_TABLE_NAME+" where  "+search;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                StudentLesson c = new StudentLesson();
                c.setId(rs.getInt("Id"));
                c.setMark(rs.getInt("Mark"));
                c.setLessonId(rs.getInt("LessionId"));
                c.setStudentCourseId(rs.getInt("StudentCourseId"));
                list.add(c);
            }
            rs.close();
        } catch (Exception ex) {

        }
        return list;
    }
    public StudentLesson getDetail(int id) {
        String sql = "Select * from "+DB_TABLE_NAME+" where id= "+id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                StudentLesson c = new StudentLesson();
                c.setId(rs.getInt("Id"));
                c.setMark(rs.getInt("Mark"));
                c.setLessonId(rs.getInt("LessionId"));
                c.setStudentCourseId(rs.getInt("StudentCourseId"));
                return c;
            }
            rs.close();
        } catch (Exception ex) {

        }
        return null;
    }
}
