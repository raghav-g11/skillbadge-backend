package com.skillbadge.model;

import com.skillbadge.exception.InvalidRankException;

public class SBadge {
    private int badgeId;
    private int studentId;
    private String category;
    private String subcategory;
    private int rank;
    private int stars;
    private String department;
    private int classNo;
    private String name;

    // Constructor for simplified version (used by BaseBadgeAssigner)
    public SBadge(String category, int stars) {
        this.category = category;
        this.stars = stars;
    }

    // Constructor used by BadgeService
    public SBadge(int badgeId, int studentId, String category, String subcategory,
                  int rank, String department, int classNo) throws InvalidRankException {
        this.badgeId = badgeId;
        this.studentId = studentId;
        this.category = category;
        this.subcategory = subcategory;
        this.rank = rank;
        this.department = department;
        this.classNo = classNo;
        this.stars = calculateStars(rank);
        this.name = category + " - " + subcategory;
    }

    private int calculateStars(int rank) throws InvalidRankException {
        if (rank <= 0 || rank > 100) throw new InvalidRankException("Rank must be between 1 and 100");
        if (rank <= 3) return 5;
        else if (rank <= 10) return 4;
        else if (rank <= 15) return 3;
        else if (rank <= 25) return 2;
        else return 1;
    }

    public void displayBadgeInfo() {
        System.out.println(" Badge Info → Badge ID: " + badgeId +
            ", Student ID: " + studentId +
            ", Category: " + category +
            ", Subcategory: " + subcategory +
            ", Rank: " + rank +
            ", Stars: " + stars +
            ", Dept: " + department +
            ", Class: " + classNo);
    }

    // ✅ Getter methods
    public int getBadgeId() { return badgeId; }
    public int getStudentId() { return studentId; }
    public String getCategory() { return category; }
    public String getSubcategory() { return subcategory; }
    public int getRank() { return rank; }
    public int getStars() { return stars; } // existing
    public String getDepartment() { return department; }
    public int getClassNo() { return classNo; }
    public String getName() { return name; }
    public String getBadgeType() { return category; }

    // ✅ Add these missing methods for testing
    public int getStar() {
        return stars;
    }

    public String getBadgeField() {
        return category;
    }
}
