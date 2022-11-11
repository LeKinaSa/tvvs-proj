package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

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
            ClipboardUtils.setClipboardContent("");
            assertNull(ClipboardUtils.getClipboardContent());
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
