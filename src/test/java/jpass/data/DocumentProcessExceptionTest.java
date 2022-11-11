package jpass.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DocumentProcessExceptionTest {
    @Test
    public void docTest() {
        String msg = "Exception message";
        DocumentProcessException e = new DocumentProcessException(msg);
        assertEquals("Cannot process document due to the following exception:\n" + msg, e.getMessage());
    }
}
