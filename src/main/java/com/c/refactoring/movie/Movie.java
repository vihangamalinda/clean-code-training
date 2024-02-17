package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

import static com.c.refactoring.movie.MovieGrade.*;

public class Movie {

    public static final int GRADE_B_LOWER_LIMIT = 0;
    public static final int GRADE_B_UPPER_LIMIT = 5;
    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {
        if (!isRatingNull()) {
            if (getSubRatingString(0, 1).equalsIgnoreCase(GRADE_B.toString())
                    && isSizeValid(2)) {
               return isValidNumericForGradeB() ;
            } else {
                String string = GRADE_A.toString();
                if (getSubRatingString(0, 1).equalsIgnoreCase(string)
                        && isSizeValid(3)
                        && isValidNumeric(getSubRatingString(1, 3)))
                    return true;
            }

        }
        return false;
    }

    private boolean isValidNumericForGradeB() {
        String numericRatingString = getSubRatingString(1, 2);

        if (!isValidNumeric(numericRatingString)) return false;
        int numericRating = Integer.parseInt(numericRatingString);
        return (numericRating) > GRADE_B_LOWER_LIMIT && (numericRating) < GRADE_B_UPPER_LIMIT;
    }

    private boolean isSizeValid(int size) {
        return this.getRating().length() == size;
    }

    private boolean isRatingNull() {
        return this.getRating() == null;
    }

    private static boolean isValidNumeric(String subRatingString) {
        return StringUtils.isNumeric(subRatingString);
    }

    private String getSubRatingString(int beginIndex, int endIndex) {
        return this.getRating().substring(beginIndex, endIndex);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

