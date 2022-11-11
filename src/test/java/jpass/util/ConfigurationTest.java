package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ConfigurationTest {
    @Test
    public void configTest() {
        Configuration config1 = Configuration.getInstance();
        Configuration config2 = Configuration.getInstance();
        assertEquals(config1, config2);
    }

    @Test
    public void booleanTest() {
        Configuration config = Configuration.getInstance();
        Boolean result = config.is("clear.clipboard.on.exit.enabled", false);

        assertFalse(result);
    }

    @Test
    public void integerTest() {
        Configuration config = Configuration.getInstance();
        Integer integer = config.getInteger("default.password.generation.length", 10);

        assertEquals((Integer) 14, integer);
    }

    @Test
    public void stringTest() {
        Configuration config = Configuration.getInstance();
        String string = config.get("date.format", "dd/mm/yyyy");

        assertEquals("yyyy-MM-dd", string);
    }

    @Test
    public void arrayNullTest() {
        Configuration config = Configuration.getInstance();
        String[] defaultValue = {"Not", "Found"};

        String[] array = config.getArray("not.found", defaultValue);

        assertEquals(defaultValue.length, array.length);
        assertTrue(Arrays.equals(defaultValue, array));
    }

    @Test
    public void arrayNotNullTest() {
        Configuration config = Configuration.getInstance();
        String[] expected = {"TITLE", "MODIFIED"};

        String[] result = config.getArray("entry.details", null);

        assertEquals(expected.length, result.length);
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void integerErrorTest() {
        Configuration config = Configuration.getInstance();
        Integer integer = config.getInteger("date.format", 10);

        assertEquals((Integer) 10, integer);
    }
}
