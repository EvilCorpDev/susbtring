package com.androidghost77.susbtring;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class SubstringFinder {

    public int getSubstrIndex(String source, String substring) {
        if (isInputDataInvalid(source, substring)) {
            throw new IllegalStateException("Input data cant be empty and substr must be shorter then source");
        }

        char[] sourceChars = source.toCharArray();
        int substrLen = substring.length();
        char[] susbtrChars = substring.toCharArray();
        for (int i = source.length() - 1; i > substrLen; i--) {
            if (compareSubstringBorders(sourceChars, susbtrChars, i)
                    && checkSubstring(sourceChars, susbtrChars, i)) {
                return i - substrLen + 1;
            }
        }

        return -1;
    }

    //checks borders of substring, eg first and last letters in substring
    private boolean compareSubstringBorders(char[] source, char[] susbtring, int index) {
        int substrLast = susbtring.length - 1;
        return source[index] == susbtring[substrLast] && source[index - substrLast] == susbtring[0];
    }

    //check middle of substring
    private boolean checkSubstring(char[] chars, char[] substring, int end) {
        int start = end - substring.length + 1;
        for (int i = 0; i < substring.length; i++) {
            if (chars[i + start] != substring[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isInputDataInvalid(String source, String substring) {
        return isEmpty(source) || isEmpty(substring) || substring.length() > source.length();
    }
}
