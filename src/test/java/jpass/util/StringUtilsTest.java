package jpass.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilsTest {
    ////////////////////////////////////////////////////////////////////////////////////////////
    // public static String stripNonValidXMLCharacters(final String in)                       //
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void nullStripNonValidXMLCharactersTest() {
        String in = null;

        String result = StringUtils.stripNonValidXMLCharacters(in);

        assertEquals("", result);
    }
    
    @Test
    public void emptyStripNonValidXMLCharactersTest() {
        String in = "";

        String result = StringUtils.stripNonValidXMLCharacters(in);

        assertEquals("", result);
    }
    
    @Test
    public void validStripNonValidXMLCharactersTest() {
        String in = "abcdef";

        String result = StringUtils.stripNonValidXMLCharacters(in);

        assertEquals(in, result);
    }
    
    @Test
    public void invalidStripNonValidXMLCharactersTest() {
        String in = "\u0012";

        String result = StringUtils.stripNonValidXMLCharacters(in);

        assertEquals("?", result);
    }
    
    @Test
    public void bothStripNonValidXMLCharactersTest() {
        String in = "abc\u0012";

        String result = StringUtils.stripNonValidXMLCharacters(in);

        assertEquals("abc?", result);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // public static String stripString(String text, int length)                              //
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void nullPositiveStripStringTest() {
        String text = null;
        int length = 3;

        String result = StringUtils.stripString(text, length);

        assertEquals(text, result);
    }

    @Test
    public void emptyPositiveStripStringTest() {
        String text = "";
        int length = 3;

        String result = StringUtils.stripString(text, length);

        assertEquals(text, result);
    }

    @Test
    public void emptyZeroStripStringTest() {
        String text = "";
        int length = 0;

        String result = StringUtils.stripString(text, length);

        assertEquals(text, result);
    }

    @Test
    public void normalNegativeStripStringTest() {
        String text = "abcdef";
        int length = -1;

        String result = StringUtils.stripString(text, length);

        assertEquals("", result);
    }

    @Test
    public void normalSmallerStripStringTest() {
        String text = "abcdef";
        int length = 3;

        String result = StringUtils.stripString(text, length);

        assertEquals("abc...", result);
    }

    @Test
    public void normalSameSizeStripStringTest() {
        String text = "abcdef";
        int length = 6;

        String result = StringUtils.stripString(text, length);

        assertEquals(text, result);
    }

    @Test
    public void biggerStripStringTest() {
        String text = "abcdef";
        int length = 9;

        String result = StringUtils.stripString(text, length);

        assertEquals(text, result);
    }
}
