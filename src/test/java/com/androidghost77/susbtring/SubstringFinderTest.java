package com.androidghost77.susbtring;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SubstringFinderTest {

    private static final String SOURCE_STRING_MULTIPLE_OCCURENCES = "Here would be some other words for some other" +
            " reasons and another text";
    private static final String FIRST_WORD_TO_FOUND = "would";
    private static final String SECOND_WORD_TO_FOUND = "some";

    private SubstringFinder finder;

    @Before
    public void setUp() {
        finder = new SubstringFinder();
    }

    @Test(expected = IllegalStateException.class)
    public void getSubstrIndexEmptyInput() {
        String sourceString = StringUtils.EMPTY;
        String substringToFind = StringUtils.EMPTY;

        finder.getSubstrIndex(sourceString, substringToFind);
    }

    @Test(expected = IllegalStateException.class)
    public void getSubstrIndexNullInput() {
        String sourceString = null;
        String substringToFind = null;

        finder.getSubstrIndex(sourceString, substringToFind);
    }

    @Test(expected = IllegalStateException.class)
    public void getSubstrIndexSourceShorterThanSusbtr() {
        String sourceString = generateStringWithSize(5);
        String substringToFind = generateStringWithSize(10);

        finder.getSubstrIndex(sourceString, substringToFind);
    }

    @Test
    public void getSubstrIndexNotFound() {
        String sourceString = generateStringWithSize(10);
        String substringToFind = "not found";

        int substrIndex = finder.getSubstrIndex(sourceString, substringToFind);
        assertThat(substrIndex, is(-1));
    }

    @Test
    public void getSubstrIndexOneOccurence() {
        int substrIndex = finder.getSubstrIndex(SOURCE_STRING_MULTIPLE_OCCURENCES, FIRST_WORD_TO_FOUND);

        assertThat(substrIndex, is(StringUtils.lastIndexOf(SOURCE_STRING_MULTIPLE_OCCURENCES, FIRST_WORD_TO_FOUND)));
    }

    @Test
    public void getSubstrIndexMultipleOccurence() {
        int substrIndex = finder.getSubstrIndex(SOURCE_STRING_MULTIPLE_OCCURENCES, SECOND_WORD_TO_FOUND);

        assertThat(substrIndex, is(StringUtils.lastIndexOf(SOURCE_STRING_MULTIPLE_OCCURENCES, SECOND_WORD_TO_FOUND)));
    }

    private String generateStringWithSize(int size) {
        char startLetter = 'a';
        StringBuilder buf = new StringBuilder();

        for(int i = 0; i < size; i++) {
            buf.append((char)(startLetter + i));
        }

        return buf.toString();
    }
}
