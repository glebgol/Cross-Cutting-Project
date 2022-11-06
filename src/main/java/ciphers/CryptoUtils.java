package ciphers;

import exceptions.CryptoException;
import interfaces.IStream;
import streams.DecryptingResult;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void Encrypt(String key, String inputFilename, String outputFilename)
            throws CryptoException {
        try {
            var inputFile = new File(inputFilename);
            var outputFile = new File(outputFilename);
            Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            var inputBytes = inputStream.readAllBytes();

            byte[] outputBytes;
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            outputBytes = cipher.doFinal(inputBytes);
            outputStream.write(Base64.getEncoder().encode(outputBytes));
            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting file", ex);
        }
    }

    public static void Decrypt(String key, String inputFilename, String outputFilename)
            throws CryptoException {
        try {
            var inputFile = new File(inputFilename);
            var outputFile = new File(outputFilename);
            Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            var inputBytes = inputStream.readAllBytes();

            byte[] outputBytes;
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error decrypting file", ex);
        }
    }

    public static DecryptingResult GetDecrypting(String key, String inputFilename) throws CryptoException {
        try {
            var inputFile = new File(inputFilename);

            Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            var inputBytes = inputStream.readAllBytes();
            byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));

            return new DecryptingResult(outputBytes);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }

    // TODO IStream Decrypt(Istream stream, String key)
    public static IStream Decrypt(IStream stream, String key) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            var inputBytes = stream.bytes();
            byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));

            return new DecryptingResult(outputBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException ex) {
            throw new CryptoException("Decrypting error", ex);
        }
    }
}
