package jpass.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CryptUtilsTest {
    // https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    // public static byte[] getSha256Hash(final char[] text) throws Exception                 //
    ////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void nullGetSha256HashTest() {
        char[] text = null;
        try {
            CryptUtils.getSha256Hash(text);
        }
        catch (Exception e) {
            return;
        }
        fail();
    }

    @Test
    public void emptyGetSha256HashTest() {
        char[] text = {};
        String expected = "E3B0C44298FC1C149AFBF4C8996FB92427AE41E4649B934CA495991B7852B855";

        try {
            byte[] results = CryptUtils.getSha256Hash(text);
            assertEquals(expected, bytesToHex(results));
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    public void normalGetSha256HashTest() {
        char[] text = {'a', 'b', 'c', 'd', 'd', 'e'};
        String expected = "27410DDD3671EFF7BDE332C234EB3E3154F34FD2FFA34477EC61AB87F58BF4FE";

        try {
            byte[] results = CryptUtils.getSha256Hash(text);
            assertEquals(expected, bytesToHex(results));
        }
        catch (Exception e) {
            fail();
        }
    }
}