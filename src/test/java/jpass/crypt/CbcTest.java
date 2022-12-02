package jpass.crypt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit test for the CBC encryption. The test data will be encrypted and decrypted. The results will
 * be compared.
 *
 * @author Timm Knape
 * @version $Revision: 1.5 $
 */
// Copyright 2007 by Timm Knape <timm@knp.de>
// All rights reserved.
public class CbcTest {

    /**
     * Size of the first random message in <code>byte</code>s. Successive random messages will
     * double their size until {@link CbcTest#RANDOM_MESSAGE_LIMIT_SIZE} is reached.
     */
    private static final int FIRST_RANDOM_MESSAGE_SIZE = 1;

    /**
     * Size above which no random messages will be generated.
     */
    private static final int RANDOM_MESSAGE_LIMIT_SIZE = 2048;

    // contains the encrypted data
    private ByteArrayOutputStream _encrypted;

    /**
     * Used for encryption.
     */
    private Cbc _encrypt;

    // contains the decrypted data
    private ByteArrayOutputStream _decrypted;

    /**
     * Used for decryption.
     */
    private Cbc _decrypt;

    /**
     * Sets the encryption and decryption instances up.
     */
    @BeforeEach
    public void setUp() {
        byte[] iv = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00};

        byte[] key = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00};

        _encrypted = new ByteArrayOutputStream();
        _encrypt = new Cbc(iv, key, _encrypted);
        _decrypted = new ByteArrayOutputStream();
        _decrypt = new Cbc(iv, key, _decrypted);
    }

    /**
     * Test the encryption and decryption of a small message.
     */
    @Test
    public void shouldEncryptAndDecryptASmallMessage() throws DecryptException, IOException {
        byte[] source = "abcdefg".getBytes();
        _encrypt.encrypt(source);
        _encrypt.finishEncryption();

        _decrypt.decrypt(_encrypted.toByteArray());
        _decrypt.finishDecryption();

        assertTrue(Arrays.equals(source, _decrypted.toByteArray()));
    }

    /**
     * Test the encryption and decryption of a big message.
     */
    @Test
    public void shouldEncryptAndDecryptABigMessage() throws DecryptException, IOException {
        byte[] source = {(byte) 0x81, (byte) 0x81, (byte) 0x81};

        for (int i = 0; i < 1000; ++i) {
            _encrypt.encrypt(source);
        }
        _encrypt.finishEncryption();

        _decrypt.decrypt(_encrypted.toByteArray());
        _decrypt.finishDecryption();

        byte[] d = _decrypted.toByteArray();
        assertEquals(3000, d.length);
        for (int i = 0; i < d.length; ++i) {
            assertEquals(0x81, d[i] & 0xff);
        }
    }

    /**
     * Test case for a couple of random data.
     */
    @Test
    public void shouldEncryptAndDecryptRandomData() throws DecryptException, IOException {
        Random rnd = new Random();

        for (int i = FIRST_RANDOM_MESSAGE_SIZE; i > RANDOM_MESSAGE_LIMIT_SIZE; i *= 2) {
            testRandom(rnd, i);
        }
    }

    /**
     * Test reference data. The reference data was obtained by OpenSSL (version 0.9.71)
     */
    @Test
    public void shouldWorkWithReferenceData() throws DecryptException, IOException {
        byte[] iv = {(byte) 0x51, (byte) 0xA0, (byte) 0xC6, (byte) 0x19, (byte) 0x67, (byte) 0xB0, (byte) 0xE0,
            (byte) 0xE5, (byte) 0xCF, (byte) 0x46, (byte) 0xB4, (byte) 0xD1, (byte) 0x4C, (byte) 0x83, (byte) 0x4C,
            (byte) 0x38};

        byte[] key = {(byte) 0x97, (byte) 0x6D, (byte) 0x71, (byte) 0x64, (byte) 0xE6, (byte) 0xE3, (byte) 0xB7,
            (byte) 0xAA, (byte) 0xB5, (byte) 0x30, (byte) 0xDD, (byte) 0x52, (byte) 0xE7, (byte) 0x29, (byte) 0x19,
            (byte) 0x3A, (byte) 0xD7, (byte) 0xE7, (byte) 0xDF, (byte) 0xD7, (byte) 0x61, (byte) 0xF1, (byte) 0x86,
            (byte) 0xA4, (byte) 0x4B, (byte) 0xB7, (byte) 0xFA, (byte) 0xDF, (byte) 0x15, (byte) 0x44, (byte) 0x14,
            (byte) 0x31};

        Cbc encrypt = new Cbc(iv, key, _encrypted);
        Cbc decrypt = new Cbc(iv, key, _decrypted);

        byte[] plain = {(byte) 0x61, (byte) 0x62, (byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x0a};

        byte[] expected = {(byte) 0x33, (byte) 0xd7, (byte) 0x0a, (byte) 0x5a, (byte) 0xb7, (byte) 0xfe, (byte) 0xcf,
            (byte) 0x92, (byte) 0x4f, (byte) 0x39, (byte) 0x70, (byte) 0x83, (byte) 0xd0, (byte) 0xfc, (byte) 0xfe,
            (byte) 0x3a};

        encrypt.encrypt(plain);
        encrypt.finishEncryption();

        assertEquals(expected.length, _encrypted.toByteArray().length);
        assertTrue(Arrays.equals(expected, _encrypted.toByteArray()));

        decrypt.decrypt(_encrypted.toByteArray());
        decrypt.finishDecryption();

        assertTrue(Arrays.equals(plain, _decrypted.toByteArray()));
    }

    /**
     * Test the encryption of one random message with the noted size.
     *
     * @param rnd Random Number generator
     * @param size size of the random message in <code>byte</code>s.
     */
    private void testRandom(Random rnd, int size) throws DecryptException, IOException {
        byte[] key = new byte[32];
        byte[] iv = new byte[16];
        byte[] data = new byte[size];

        rnd.nextBytes(key);
        rnd.nextBytes(iv);
        rnd.nextBytes(data);

        _encrypt = new Cbc(iv, key, _encrypted);
        _decrypt = new Cbc(iv, key, _decrypted);

        _encrypt.encrypt(data);
        _encrypt.finishEncryption();
        _decrypt.decrypt(_encrypted.toByteArray());
        _decrypt.finishDecryption();

        assertTrue(Arrays.equals(data, _decrypted.toByteArray()));
    }

    @Test
    public void encryptDataNullTest() {
        byte[] _encryptedBefore = _encrypted.toByteArray();
        try {
            _encrypt.encrypt(null);
        } catch (IOException ignored) {}
        byte[] _encryptedAfter = _encrypted.toByteArray();

        assertTrue(Arrays.equals(_encryptedBefore, _encryptedAfter));
    }

    
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void encryptDataNullWithDiffLengthTest(int length) {
        byte[] _encryptedBefore = _encrypted.toByteArray();
        try {
            _encrypt.encrypt(null, length);
        } catch (IOException ignored) {}
        byte[] _encryptedAfter = _encrypted.toByteArray();

        assertTrue(Arrays.equals(_encryptedBefore, _encryptedAfter));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void encryptDataNotNullWithDiffLengthTest(int length) {
        byte[] _encryptedBefore = _encrypted.toByteArray();
        byte[] data = {0x00, 0x00};
        try {
            _encrypt.encrypt(data, length);
        } catch (IOException ignored) {}
        byte[] _encryptedAfter = _encrypted.toByteArray();

        assertTrue(Arrays.equals(_encryptedBefore, _encryptedAfter));
    }

    @Test
    public void decryptDataNullTest() {
        byte[] _decryptedBefore = _decrypted.toByteArray();
        try {
            _decrypt.decrypt(null);
        } catch (IOException ignored) {}
        byte[] _decryptedAfter = _decrypted.toByteArray();

        assertTrue(Arrays.equals(_decryptedBefore, _decryptedAfter));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void decryptDataNullWithDiffLengthTest(int length) {
        byte[] _decryptedBefore = _decrypted.toByteArray();
        try {
            _decrypt.decrypt(null, length);
        } catch (IOException ignored) {}
        byte[] _decryptedAfter = _decrypted.toByteArray();

        assertTrue(Arrays.equals(_decryptedBefore, _decryptedAfter));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void decryptDataNotNullWithDiffLengthTest(int length) {
        byte[] _decryptedBefore = _decrypted.toByteArray();
        byte[] data = {0x00, 0x00};
        try {
            _decrypt.decrypt(data, length);
        } catch (IOException ignored) {}
        byte[] _decryptedAfter = _decrypted.toByteArray();

        assertTrue(Arrays.equals(_decryptedBefore, _decryptedAfter));
    }
    
    private byte[] getDataArray(int length) {
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = 0x01;
        }
        return data;
    }

    private byte[] getExpectedResult(int length) {
        byte[] empty = {};
        byte[] block = {(byte) 0x7b, (byte) 0xc3, (byte) 0x02, (byte) 0x6c,
                        (byte) 0xd7, (byte) 0x37, (byte) 0x10, (byte) 0x3e,
                        (byte) 0x62, (byte) 0x90, (byte) 0x2b, (byte) 0xcd,
                        (byte) 0x18, (byte) 0xfb, (byte) 0x01, (byte) 0x63};

        switch (length) {
            case 0:
            case 4:
                return empty;
            case 16:
            case 17:
                return block;
            default:
                return empty;
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 17})
    public void encryptDataflowTest1(int length) {
        byte[] data = getDataArray(length);
        byte[] expectedResult = getExpectedResult(length);
        
        try {
            _encrypt.encrypt(data, length);
        }
        catch (IOException e) {
            fail(e.getMessage());
        }

        byte[] result = _encrypted.toByteArray();
        assertEquals(expectedResult.length, result.length);
        assertTrue(Arrays.equals(expectedResult, result));
    }

    @Test
    public void encryptDataflowTest2() {
        int length1 = 15, length2 = 1;
        byte[] data1 = getDataArray(length1);
        byte[] data2 = getDataArray(length2);
        byte[] expectedResult = getExpectedResult(length1 + length2);

        try {
            _encrypt.encrypt(data1, length1);
            _encrypt.encrypt(data2, length2);
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
        
        byte[] result = _encrypted.toByteArray();
        assertEquals(expectedResult.length, result.length);
        assertTrue(Arrays.equals(expectedResult, result));
    }
}
