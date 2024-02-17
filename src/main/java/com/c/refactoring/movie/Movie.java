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
        return isRatingNotNull()
                && isValidGradRating();

    }

    private boolean isValidGradRating() {
        return isValidGroupBRating() ||
                isValidGroupARating();
    }

    private boolean isValidGroupBRating() {
        return isGrading(GRADE_B)
                && isRatingSizeValid(2) && isValidNumericForGradeB();
    }

    private boolean isValidGroupARating() {
        String secondAndThirdCharacters = getSubRatingString(1, 3);
        return isGrading(GRADE_A)
                && isRatingSizeValid(3)
                && isValidNumeric(secondAndThirdCharacters);
    }

    private boolean isGrading(MovieGrade movieGrade) {
        String firstCharacter = getSubRatingString(0, 1);
        return firstCharacter.equalsIgnoreCase(movieGrade.toString());
    }

    private boolean isValidNumericForGradeB() {
        String secondCharacter = getSubRatingString(1, 2);

        if (!isValidNumeric(secondCharacter)) return false;
        int numericRating = Integer.parseInt(secondCharacter);
        return (numericRating) > GRADE_B_LOWER_LIMIT && (numericRating) < GRADE_B_UPPER_LIMIT;
    }

    private boolean isRatingSizeValid(int size) {
        return this.getRating().length() == size;
    }

    private boolean isRatingNotNull() {
        return this.getRating() != null;
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

