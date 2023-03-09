package com.example.triolingo_mobile.Model;

public class LessonModel {
    private int id;
    private String name;
    private int unitId;
    private String description;
    private int status;
    private String note;

    public LessonModel() {
    }

    public LessonModel(int id, String name, int unitId, String description, int status, String note) {
        this.id = id;
        this.name = name;
        this.unitId = unitId;
        this.description = description;
        this.status = status;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "LessonModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitId=" + unitId +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", note='" + note + '\'' +
                '}';
    }
}
