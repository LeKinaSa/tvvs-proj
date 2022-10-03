package jpass.util;

import static org.junit.Assert.assertEquals;

import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateUtilsTest {
    ////////////////////////////////////////////////////////////////////////////////////////////
    // public static DateTimeFormatter createFormatter(String format)                         //
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void nullCreateFormatterTest() {
        DateTimeFormatter formatter = DateUtils.createFormatter(null);
        
        assertEquals(DateTimeFormatter.ISO_DATE, formatter);
    }

    @Test
    public void emptyCreateFormatterTest() {
        String formatString = "";

        DateTimeFormatter formatter = DateUtils.createFormatter(formatString);
        assertEquals(DateTimeFormatter.ISO_DATE, formatter);

    }

    @Test
    public void invalidFormatCreateFormatterTest() {
        String formatString = "q";

        DateTimeFormatter formatter = DateUtils.createFormatter(formatString);

        assertEquals(DateTimeFormatter.ISO_DATE, formatter);
    }

    @Test
    public void validFormatCreateFormatterTest() {
        String formatString = "HH:mm:ss";

        DateTimeFormatter formatter = DateUtils.createFormatter(formatString);

        assertEquals(DateTimeFormatter.ISO_LOCAL_TIME, formatter);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    // public static String formatIsoDateTime(String dateString, DateTimeFormatter formatter) //
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    private static String EPOCH = "1970-01-01T01:00:00";

    @Test
    public void validISOFormatIsoDateTimeTest() {
        String validString = "2022-10-03T01:00:00";
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        String result = DateUtils.formatIsoDateTime(validString, isoFormatter);

        assertEquals(validString, result);
    }

    @Test
    public void validOtherFormatIsoDateTimeTest() {
        String validString = "2022-10-03T01:00:00";
        DateTimeFormatter otherFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        String result = DateUtils.formatIsoDateTime(validString, otherFormatter);

        assertEquals("2022-10-03", result);
    }

    @Test
    public void validNullFormatIsoDateTimeTest() {
        String validString = "2022-10-03T01:00:00";

        String result = DateUtils.formatIsoDateTime(validString, null);

        assertEquals(EPOCH, result);
    }

    @Test
    public void invalidISOFormatIsoDateTimeTest() {
        String invalidString = "2022-10-03";
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        String result = DateUtils.formatIsoDateTime(invalidString, isoFormatter);

        assertEquals(EPOCH, result);
    }

    @Test
    public void nullISOFormatIsoDateTimeTest() {
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        String result = DateUtils.formatIsoDateTime(null, isoFormatter);

        assertEquals(EPOCH, result);
    }
}
