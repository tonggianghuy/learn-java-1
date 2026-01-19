package com.learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Student Management System for course registration.
 */
public class StudentManagement {
    private ArrayList<Student> students;
    private Scanner scanner;
    private int ordinalCounter;
    private static final String[] VALID_COURSES = {"Java", ".Net", "C/C++"};

    public StudentManagement() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.ordinalCounter = 1;
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Nhap lua chon cua ban: ");

            switch (choice) {
                case 1:
                    registerStudents();
                    break;
                case 2:
                    searchAndSort();
                    break;
                case 3:
                    updateOrDelete();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    running = false;
                    System.out.println("\nCam on ban da su dung chuong trinh!");
                    break;
                default:
                    System.out.println("\nLua chon khong hop le! Vui long chon lai.");
            }
        }
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CHAO MUNG DEN QUAN LY DANG KY HOC PHAN CUA SINH VIEN");
        System.out.println("=".repeat(60));
        System.out.println("1. Dang ky hoc phan tung sinh vien");
        System.out.println("2. Tim kiem va Sap xep");
        System.out.println("3. Cap nhat/Xoa");
        System.out.println("4. Bao cao");
        System.out.println("5. Thoat");
        System.out.println("=".repeat(60));
    }

    private void registerStudents() {
        System.out.println("\n--- DANG KY HOC PHAN TUNG SINH VIEN ---");
        int count = 0;
        boolean continueRegistration = true;

        while (continueRegistration) {
            if (count >= 10) {
                String choice = getStringInput("\nBan da dang ky " + count + " sinh vien. Ban co muon tiep tuc? (Y/N): ");
                if (!choice.equalsIgnoreCase("Y")) {
                    break;
                }
            }

            System.out.println("\n--- Sinh vien thu " + (count + 1) + " ---");

            String id = getStringInput("Nhap ma sinh vien: ");
            String name = getStringInput("Nhap ten sinh vien: ");
            String semester = getStringInput("Nhap hoc ky (VD: Fall2022, Spring2023): ");

            System.out.println("Cac khoa hoc kha dung: 1. Java, 2. .Net, 3. C/C++");
            int courseChoice = getIntInput("Chon khoa hoc (1-3): ");
            String courseName = getCourseFromChoice(courseChoice);

            if (courseName == null) {
                System.out.println("Lua chon khoa hoc khong hop le!");
                continue;
            }

            if (isDuplicateRegistration(id, semester, courseName)) {
                System.out.println("Sinh vien " + id + " da dang ky khoa hoc " + courseName + " trong ky " + semester + " roi!");
                System.out.println("Khong the dang ky trung khoa hoc trong cung mot ky!");
                continue;
            }

            Student student = new Student(ordinalCounter++, id, name, semester, courseName);
            students.add(student);
            count++;
            System.out.println("Dang ky thanh cong!");
        }

        System.out.println("\nDa dang ky thanh cong " + count + " sinh vien!");
    }

    private boolean isDuplicateRegistration(String id, String semester, String courseName) {
        for (Student student : students) {
            if (student.getId().equals(id) &&
                student.getSemester().equals(semester) &&
                student.getCourseName().equals(courseName)) {
                return true;
            }
        }
        return false;
    }

    private String getCourseFromChoice(int choice) {
        if (choice >= 1 && choice <= VALID_COURSES.length) {
            return VALID_COURSES[choice - 1];
        }
        return null;
    }

    private void searchAndSort() {
        System.out.println("\n--- TIM KIEM VA SAP XEP ---");
        String searchName = getStringInput("Nhap ten sinh vien can tim (co the nhap mot phan): ");

        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentName().toLowerCase().contains(searchName.toLowerCase())) {
                foundStudents.add(student);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("Khong tim thay sinh vien nao!");
            return;
        }

        Collections.sort(foundStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
            }
        });

        System.out.println("\nKet qua tim kiem (da sap xep theo ten):");
        System.out.println("-".repeat(80));
        System.out.printf("%-25s | %-15s | %-15s\n", "Ten sinh vien", "Hoc ky", "Khoa hoc");
        System.out.println("-".repeat(80));

        for (Student student : foundStudents) {
            System.out.printf("%-25s | %-15s | %-15s\n",
                student.getStudentName(), student.getSemester(), student.getCourseName());
        }
    }

    private void updateOrDelete() {
        System.out.println("\n--- CAP NHAT/XOA SINH VIEN ---");
        String searchId = getStringInput("Nhap ma sinh vien can tim: ");

        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getId().equals(searchId)) {
                foundStudents.add(student);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("Khong tim thay sinh vien co ma " + searchId);
            return;
        }

        System.out.println("\nDanh sach cac ban ghi cua sinh vien " + searchId + ":");
        System.out.println("-".repeat(90));
        System.out.printf("%-5s | %-10s | %-25s | %-15s | %-10s\n",
            "STT", "Ma SV", "Ten SV", "Hoc ky", "Khoa hoc");
        System.out.println("-".repeat(90));

        for (int i = 0; i < foundStudents.size(); i++) {
            Student s = foundStudents.get(i);
            System.out.printf("%-5d | %-10s | %-25s | %-15s | %-10s\n",
                (i + 1), s.getId(), s.getStudentName(), s.getSemester(), s.getCourseName());
        }

        int recordChoice = getIntInput("\nChon so thu tu ban ghi muon cap nhat/xoa: ");
        if (recordChoice < 1 || recordChoice > foundStudents.size()) {
            System.out.println("Lua chon khong hop le!");
            return;
        }

        Student selectedStudent = foundStudents.get(recordChoice - 1);
        String action = getStringInput("Ban muon Cap nhat (U) hay Xoa (D)? ");

        if (action.equalsIgnoreCase("U")) {
            updateStudent(selectedStudent);
        } else if (action.equalsIgnoreCase("D")) {
            students.remove(selectedStudent);
            System.out.println("Da xoa ban ghi thanh cong!");
        } else {
            System.out.println("Lua chon khong hop le!");
        }
    }

    private void updateStudent(Student student) {
        System.out.println("\n--- CAP NHAT THONG TIN ---");
        System.out.println("Nhan Enter de giu nguyen thong tin hien tai");

        String newName = getStringInput("Ten moi (" + student.getStudentName() + "): ");
        if (!newName.isEmpty()) {
            student.setStudentName(newName);
        }

        String newSemester = getStringInput("Hoc ky moi (" + student.getSemester() + "): ");
        if (!newSemester.isEmpty()) {
            student.setSemester(newSemester);
        }

        System.out.println("Khoa hoc hien tai: " + student.getCourseName());
        System.out.println("Cac khoa hoc kha dung: 1. Java, 2. .Net, 3. C/C++, 0. Giu nguyen");
        int courseChoice = getIntInput("Chon khoa hoc moi: ");

        if (courseChoice > 0 && courseChoice <= VALID_COURSES.length) {
            String newCourse = getCourseFromChoice(courseChoice);
            student.setCourseName(newCourse);
        }

        System.out.println("Cap nhat thanh cong!");
    }

    private void generateReport() {
        System.out.println("\n--- BAO CAO ---");

        if (students.isEmpty()) {
            System.out.println("Khong co du lieu!");
            return;
        }

        Map<String, StudentSummary> summaryMap = new HashMap<>();

        for (Student student : students) {
            String key = student.getId();
            if (!summaryMap.containsKey(key)) {
                summaryMap.put(key, new StudentSummary(student.getId(), student.getStudentName()));
            }
            summaryMap.get(key).addCourse(student.getCourseName());
        }

        System.out.println("-".repeat(80));
        System.out.printf("%-12s | %-25s | %-25s | %-10s\n",
            "Ma SV", "Ten SV", "Cac khoa hoc", "Tong so");
        System.out.println("-".repeat(80));

        for (StudentSummary summary : summaryMap.values()) {
            System.out.printf("%-12s | %-25s | %-25s | %-10d\n",
                summary.getId(), summary.getName(), summary.getCoursesString(), summary.getTotalCourses());
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
    }

    private static class StudentSummary {
        private String id;
        private String name;
        private Set<String> courses;

        public StudentSummary(String id, String name) {
            this.id = id;
            this.name = name;
            this.courses = new HashSet<>();
        }

        public void addCourse(String course) {
            courses.add(course);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getTotalCourses() {
            return courses.size();
        }

        public String getCoursesString() {
            return String.join(", ", courses);
        }
    }

    public static void main(String[] args) {
        StudentManagement system = new StudentManagement();
        system.run();
    }
}
