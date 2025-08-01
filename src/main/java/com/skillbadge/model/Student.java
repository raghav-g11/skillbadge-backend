package com.skillbadge.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private int age;
    private String department;
    private int classNo;
    private int codingRank;
    private int sportRank;
    private String codingLanguage;
    private String sport;
    private int academicRank;
    private int editingRank;
    private int speakingRank;
    private int teachingRank;


    // Full-parameter constructor (required by StudentDAO)
    public Student(int id, String name, int age, String department, int classNo,
                   int codingRank, int sportRank, String codingLanguage, String sport) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.classNo = classNo;
        this.codingRank = codingRank;
        this.sportRank = sportRank;
        this.codingLanguage = codingLanguage;
        this.sport = sport;
    }
    private List<SBadge> badgeList = new ArrayList<>();
    // Empty constructor (optional for frameworks or form binding)
    public Student() {}

    // Getters and Setters
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public int getCodingRank() {
        return codingRank;
    }

    public void setCodingRank(int codingRank) {
        this.codingRank = codingRank;
    }

    public int getSportRank() {
        return sportRank;
    }

    public void setSportRank(int sportRank) {
        this.sportRank = sportRank;
    }

    public String getCodingLanguage() {
        return codingLanguage;
    }

    public void setCodingLanguage(String codingLanguage) {
        this.codingLanguage = codingLanguage;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void addBadge(SBadge badge) {
    badgeList.add(badge);
    }

    public List<SBadge> getBadges() {
    return badgeList;
    }

    public int getAcademicRank() {
    return academicRank;
    }
    public int getEditingRank() {
    return editingRank;
    }
    public int getSpeakingRank() {
    return speakingRank;
    }
    public int getTeachingRank() {
    return teachingRank;
    }


}
