import archivers.ArchivationFileManager;
import ciphers.CryptoUtils;
import exceptions.CryptoException;
import org.mariuszgromada.math.mxparser.Expression;
import parsers.ProcessingTxtFile;

import java.io.File;
import java.io.IOException;

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
        try {
            ProcessingTxtFile.Calculate("file2.txt", "file5.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}