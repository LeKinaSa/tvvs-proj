package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

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
        
        fail("No exception thrown.");
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
            fail(e.getMessage());
        }
    }

    @Test
    public void normalGetSha256HashTest() {
        char[] text = {'a', 'b', 'c', 'd', 'e', 'f'};
        String expected = "BEF57EC7F53A6D40BEB640A780A639C83BC29AC8A9816F1FC6C5C6DCD93C4721";

        try {
            byte[] results = CryptUtils.getSha256Hash(text);

            assertEquals(expected, bytesToHex(results));
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void offPoint1GetSha256HashTest() {
        char[] text = {'a'};
        String expected = "CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB";

        try {
            byte[] results = CryptUtils.getSha256Hash(text);
            assertEquals(expected, bytesToHex(results));
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    ///
    @Test
    public void thousandIterationsGetPKCS5Sha256HashTest() {
        char[] text = {};
        String expected = "0DC9B0E0900F0CE71F36C359CBCF968D6366F2762F5699A2F5EA5FDCCB70F0C8";

        try {
            byte[] results = CryptUtils.getPKCS5Sha256Hash(text);
            assertEquals(expected, bytesToHex(results));
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
