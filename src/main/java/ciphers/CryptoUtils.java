package ciphers;

import archivers.ArchivationFileManager;
import exceptions.CryptoException;
import interfaces.IStream;
import streams.EncryptingResult;
import streams.ReadingResult;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
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
    public static byte[] GetDecrypting(String key, File inputFile) throws CryptoException {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            Key secretKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            var len = (int) inputFile.length();
            byte[] inputBytes = new byte[len];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));

            inputStream.close();
            return Base64.getDecoder().decode(outputBytes);

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

//            var inputBytes = Base64.getDecoder().decode(stream.bytes());
            var inputBytes = stream.bytes();

            byte[] outputBytes = cipher.doFinal(Base64.getDecoder().decode(inputBytes));

            var str = new String(outputBytes);

            var arrayListOfStrings = new ArrayList<String>();
            var stringTokenizer = new StringTokenizer(str, "\n");
            while (stringTokenizer.hasMoreTokens()) {
                arrayListOfStrings.add(stringTokenizer.nextToken());
            }

            return new EncryptingResult(arrayListOfStrings, outputBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
}
