package com.b.simple.design.business.text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextHelper {

    private static final String STRING_A = "A";

    public String swapLastTwoCharacters(String str) {
        if (str == null) return "";
        int stringLength = str.length();
        if (stringLength <= 1) {
            return str;
        }
        String stringWithExcludedLastTwoChars = str.substring(0, stringLength - 2);
        char lastChar = str.charAt(stringLength - 1);
        char oneBeforeLastChar = str.charAt(stringLength - 2);
        return stringWithExcludedLastTwoChars + lastChar + oneBeforeLastChar;
    }

    public String truncateAInFirst2Positions(String str) {
        if (str == null || str.isEmpty()) return "";
        if (isCharAContainsOnFirst2Positions(str)) {
            return getFirstTwoCharactersValidated(str) + str.substring(2);
        }
        return str;
    }

    private static String getFirstTwoCharactersValidated(String str) {
        String[] firstTwoCharacters = str.substring(0, 2).split("");
        return Stream.of(firstTwoCharacters).map(character -> isCharacterA(character) ? "" : character).collect(Collectors.joining());
    }

    private static boolean isCharacterA(String character) {
        return character.equals(STRING_A);
    }

    private static boolean isCharAContainsOnFirst2Positions(String str) {
        return str.substring(0, 2).contains(STRING_A);
    }
}
