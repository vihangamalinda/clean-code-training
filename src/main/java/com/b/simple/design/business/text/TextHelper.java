package com.b.simple.design.business.text;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {
		if (str==null)return "";
		int stringLength = str.length();
		if (stringLength <= 1) {
			return str;
		}
		char lastChar = str.charAt(stringLength - 1);
		char oneBeforeLastChar = str.charAt(stringLength - 2);
		return str.substring(0, stringLength - 2) + lastChar + oneBeforeLastChar;
	}

	public String truncateAInFirst2Positions(String str) {
		return null;
	}
}
