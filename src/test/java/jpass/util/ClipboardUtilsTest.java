package jpass.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ClipboardUtilsTest {
    @Test
    public void test() {
        String text1 = "Hello";
        try {
            ClipboardUtils.clearClipboardContent();
            assertNull(ClipboardUtils.getClipboardContent());
            ClipboardUtils.setClipboardContent(text1);
            assertEquals(text1, ClipboardUtils.getClipboardContent());
            ClipboardUtils.setClipboardContent(null);
            assertNull(ClipboardUtils.getClipboardContent());

        }
        catch (Exception e) {
            fail();
        }
    }
}
