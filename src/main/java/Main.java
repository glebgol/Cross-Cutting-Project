import archivers.ArchivationFileManager;
import ciphers.CryptoUtils;
import exceptions.CryptoException;
import org.mariuszgromada.math.mxparser.Expression;
import parsers.ProcessingTxtFile;
import readers.DefaultReader;
import readers.EncryptedFileReader;
import readers.TxtFileReader;
import readers.ZipFileReader;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CryptoException {
        String key = "1234567812345678";
        //ArchivationFileManager.ZipFile("input.txt");
        //var fileReader = new ZipFileReader(new EncryptedFileReader(key, new TxtFileReader(new DefaultReader("input2.zip", "output.txt"))));
        //fileReader.Write(fileReader.Calculate(fileReader.Read()));

        //CryptoUtils.Encrypt(key, new File("input.txt"), new File("input2.txt"));
        //ArchivationFileManager.ZipFile("input2.txt");
        var fileReader = new EncryptedFileReader("1234567812345678", new TxtFileReader(new DefaultReader("input2.txt", "output5.txt")));
        fileReader.Write(fileReader.Calculate(fileReader.Read()));
    }
}