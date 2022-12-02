package com.example.organizer.model;

public class Lesson {
    private int id;
    private String name;
    private String teacher;
    private String room;
    private String type;
    private int dayOfWeek;
    private int numberOfWeek;

    public Lesson(String name, String teacher, String room, String type, int dayOfWeek, int numberOfWeek) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }

    public Lesson(int id, String name, String teacher, String room, String type, int dayOfWeek, int numberOfWeek) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getNumberOfWeek() {
        return numberOfWeek;
    }

    public void setNumberOfWeek(int numberOfWeek) {
        this.numberOfWeek = numberOfWeek;
    }
}
