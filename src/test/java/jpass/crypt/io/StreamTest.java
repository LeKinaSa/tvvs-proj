package jpass.crypt.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Tests {@link jpass.crypt.io.CryptInputStream} and {@link jpass.crypt.io.CryptOutputStream}. A
 * random message will be encrypted and decrypted.
 *
 * @author Timm Knape
 * @version $Revision: 1.3 $
 */
// Copyright 2007 by Timm Knape <timm@knp.de>
// All rights reserved.
public class StreamTest {

    /**
     * Length of the message in <code>byte</code>s.
     */
    private static final int DATA_SIZE = 100;

    byte[] iv = {(byte) 0x51, (byte) 0xA0, (byte) 0xC6, (byte) 0x19, (byte) 0x67, (byte) 0xB0, (byte) 0xE0,
        (byte) 0xE5, (byte) 0xCF, (byte) 0x46, (byte) 0xB4, (byte) 0xD1, (byte) 0x4C, (byte) 0x83, (byte) 0x4C,
        (byte) 0x38};

    byte[] key = {(byte) 0x97, (byte) 0x6D, (byte) 0x71, (byte) 0x64, (byte) 0xE6, (byte) 0xE3, (byte) 0xB7,
        (byte) 0xAA, (byte) 0xB5, (byte) 0x30, (byte) 0xDD, (byte) 0x52, (byte) 0xE7, (byte) 0x29, (byte) 0x19,
        (byte) 0x3A, (byte) 0xD7, (byte) 0xE7, (byte) 0xDF, (byte) 0xD7, (byte) 0x61, (byte) 0xF1, (byte) 0x86,
        (byte) 0xA4, (byte) 0x4B, (byte) 0xB7, (byte) 0xFA, (byte) 0xDF, (byte) 0x15, (byte) 0x44, (byte) 0x14,
        (byte) 0x31};

    /**
     * A random message will be encrypted and decrypted.
     */
    @Test
    public void shouldDecryptAnEncryptedRandomMessage() throws IOException {
        byte[] key = new byte[32];
        Random rnd = new Random();
        rnd.nextBytes(key);

        ByteArrayOutputStream encrypted = new ByteArrayOutputStream();
        CryptOutputStream output = new CryptOutputStream(encrypted, key);

        byte[] plain = new byte[DATA_SIZE];
        rnd.nextBytes(plain);

        output.write(plain);
        output.close();

        CryptInputStream decrypter = new CryptInputStream(new ByteArrayInputStream(encrypted.toByteArray()), key);
        ByteArrayOutputStream decrypted = new ByteArrayOutputStream();

        int read;
        while ((read = decrypter.read()) >= 0) {
            decrypted.write(read);
        }
        decrypted.close();
        decrypter.close();

        assertEquals(plain.length, decrypted.toByteArray().length);
        assertTrue(Arrays.equals(plain, decrypted.toByteArray()));
    }

    @Test
    public void differentConstructorsTest() throws IOException {
        Random rnd = new Random();

        ByteArrayOutputStream encrypted = new ByteArrayOutputStream();
        CryptOutputStream output = new CryptOutputStream(encrypted, key, iv);

        byte[] plain = new byte[1];
        rnd.nextBytes(plain);
        int number = (int) (plain[0]);
        
        output.write(number);
        output.close();

        CryptInputStream decrypter = new CryptInputStream(new ByteArrayInputStream(encrypted.toByteArray()), key, iv);
        ByteArrayOutputStream decrypted = new ByteArrayOutputStream();

        int read;
        while ((read = decrypter.read()) >= 0) {
            decrypted.write(read);
        }
        decrypted.close();
        decrypter.close();

        assertEquals(plain.length, decrypted.toByteArray().length);
        assertTrue(Arrays.equals(plain, decrypted.toByteArray()));
    }
}
