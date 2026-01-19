package com.learn;

/**
 * Student class representing a student course registration.
 */
public class Student {
    private int ordinalNumber;
    private String id;
    private String studentName;
    private String semester;
    private String courseName;

    public Student(int ordinalNumber, String id, String studentName, String semester, String courseName) {
        this.ordinalNumber = ordinalNumber;
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-10s | %-25s | %-15s | %-10s",
            ordinalNumber, id, studentName, semester, courseName);
    }
}
