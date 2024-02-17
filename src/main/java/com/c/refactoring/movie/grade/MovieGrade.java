package com.c.refactoring.movie.grade;

public enum MovieGrade {
    GRADE_A("A"),
    GRADE_B("B");

    public final String label;

    private MovieGrade(String value) {
        this.label = value;
    }


    @Override
    public String toString() {
        return this.label;
    }
}
