package com.example.triolingo_mobile.DAO;

import com.example.triolingo_mobile.DataAccess.DbContext;
import com.example.triolingo_mobile.Model.StudentCourse;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDAO extends DbContext {

    private static StudentCourseDAO instance;
    private static String DB_TABLE_NAME="StudentCourse";

    public static StudentCourseDAO getInstance() {
        if (StudentCourseDAO.instance == null) {
            StudentCourseDAO.instance = new StudentCourseDAO();
        }
        return StudentCourseDAO.instance;
    }

    public List<StudentCourse> getList(String search) {
        List<StudentCourse> list = new ArrayList<>();
        String sql = "Select * from "+DB_TABLE_NAME+" where  "+search;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                StudentCourse c = new StudentCourse();
                c.setStudentId(rs.getInt("StudentId"));
                c.setCourseId(rs.getInt("CourseId"));
                c.setComment(rs.getString("Comment"));
                c.setRate(rs.getFloat("Rate"));
                c.setId(rs.getInt("Id"));
                list.add(c);
            }
            rs.close();
        } catch (Exception ex) {

        }
        return list;
    }
    public StudentCourse getDetail(int id) {
        String sql = "Select * from "+DB_TABLE_NAME+" where id= "+id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                StudentCourse c = new StudentCourse();
                c.setStudentId(rs.getInt("StudentId"));
                c.setCourseId(rs.getInt("CourseId"));
                c.setComment(rs.getString("Comment"));
                c.setRate(rs.getFloat("Rate"));
                c.setId(rs.getInt("Id"));
                return c;
            }
            rs.close();
        } catch (Exception ex) {

        }
        return null;
    }
}
