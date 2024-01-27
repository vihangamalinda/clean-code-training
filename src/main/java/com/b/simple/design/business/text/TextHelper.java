package com.b.simple.design.business.text;

public class TextHelper {

    public String swapLastTwoCharacters(String str) {
        if (str == null) return "";
        int stringLength = str.length();
        if (stringLength <= 1) {
            return str;
        }
        int lastIndex = stringLength - 1;
        int oneBeforeLastIndex = stringLength - 2;
        return str.substring(0, oneBeforeLastIndex) + str.charAt(lastIndex) + str.charAt(oneBeforeLastIndex);
    }

    public String truncateAInFirst2Positions(String str) {
        return null;
    }
}
