import exceptions.CryptoException;
import org.mariuszgromada.math.mxparser.Expression;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Expression expression = new Expression("(3+2) * 2 + 1 + ln(12)");
        System.out.println(expression.calculate());
        try {
            ArchivationFileManager.ZipFile("file.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ArchivationFileManager.UnZipFile("file.zip", "file2.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            CryptoUtils.Encrypt("1234567812345678", new File("file2.txt"), new File("file3.txt"));
        } catch (CryptoException e) {
            throw new RuntimeException(e);
        }
        try {
            CryptoUtils.Decrypt("1234567812345678", new File("file3.txt"), new File("file4.txt"));
        } catch (CryptoException e) {
            throw new RuntimeException(e);
        }
    }
}