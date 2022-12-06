package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;

import org.junit.jupiter.api.Test;

public class DateUtilsTest {
    ////////////////////////////////////////////////////////////////////////////////////////////
    // public static DateTimeFormatter createFormatter(String format)                         //
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void nullCreateFormatterTest() {
        String formatString = null;

        DateTimeFormatter formatter = DateUtils.createFormatter(formatString);

        assertEquals(DateTimeFormatter.ISO_DATE, formatter);
    }

    @Test
    public void emptyCreateFormatterTest() {
        String formatString = "";

        DateTimeFormatter formatter = DateUtils.createFormatter(formatString);

        assertEquals("", formatter.toString());
    }

    @Test
    public void invalidFormatCreateFormatterTest() {
        String formatString = ".b";

        DateTimeFormatter formatter = DateUtils.createFormatter(formatString);

        assertEquals(DateTimeFormatter.ISO_DATE, formatter);
    }

    @Test
    public void validFormatCreateFormatterTest() {
        String formatString = "HH:mm:ss";
        LocalDateTime nowDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.systemDefault());
        TemporalAccessor now = nowDate.truncatedTo(ChronoUnit.MILLIS);

        DateTimeFormatter formatter = DateUtils.createFormatter(formatString);

        assertEquals(DateTimeFormatter.ISO_LOCAL_TIME.format(now), formatter.format(now));
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
        DateTimeFormatter anotherFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        String result = DateUtils.formatIsoDateTime(validString, anotherFormatter);

        assertEquals("2022-10-03", result);
    }

    // @Test
    // public void validNullFormatIsoDateTimeTest() {
    //     String validString = "2022-10-03T01:00:00";
    //     DateTimeFormatter nullFormatter = null;

    //     String result = DateUtils.formatIsoDateTime(validString, nullFormatter);

    //     assertEquals(EPOCH, result);
    // }

    @Test
    public void invalidISOFormatIsoDateTimeTest() {
        String invalidString = "2022-10-03";
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        String result = DateUtils.formatIsoDateTime(invalidString, isoFormatter);

        assertEquals(EPOCH, result);
    }

    @Test
    public void nullISOFormatIsoDateTimeTest() {
        String nullString = null;
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        String result = DateUtils.formatIsoDateTime(nullString, isoFormatter);

        assertEquals(EPOCH, result);
    }

    @Test
    public void timestampFormatIsoDateTimeTest() {
        String timestamp = "1667932223000";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        
        String result = DateUtils.formatIsoDateTime(timestamp, formatter);

        assertEquals("2022-11-08", result);
    }
}
