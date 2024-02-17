package com.c.refactoring.movie;

public enum MovieGrade {
    GRADE_A("A"),
    GRADE_B("B");

    private final String label;

    private MovieGrade(String val) {
        this.label = val;
    }


    @Override
    public String toString() {
        return this.label;
    }
}
