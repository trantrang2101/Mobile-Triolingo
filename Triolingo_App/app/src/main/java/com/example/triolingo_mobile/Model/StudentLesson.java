package com.example.triolingo_mobile.Model;

import com.example.triolingo_mobile.DAO.CourseDAO;
import com.example.triolingo_mobile.DAO.LessonDAO;
import com.example.triolingo_mobile.DAO.StudentCourseDAO;
import com.example.triolingo_mobile.DAO.UserDAO;

public class StudentLesson {
    private int id;
    private int mark;
    private int lessonId;
    private int studentCourseId;

    public StudentLesson() {
    }

    public StudentLesson(int id, int mark, int lessonId, int studentCourseId) {
        this.id = id;
        this.mark = mark;
        this.lessonId = lessonId;
        this.studentCourseId = studentCourseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public LessonModel getLesson() {
        return LessonDAO.getInstance().getDetail(getLessonId());
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public StudentCourse getStudentCourse() {
        return StudentCourseDAO.getInstance().getDetail(getStudentCourseId());
    }

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }
}
