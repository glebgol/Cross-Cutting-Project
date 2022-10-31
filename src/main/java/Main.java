import archivers.ArchivationFileManager;
import ciphers.CryptoUtils;
import exceptions.CryptoException;
import org.mariuszgromada.math.mxparser.Expression;
import parsers.ProcessingTxtFile;
import readers.DefaultReader;
import readers.EncryptedFileReader;
import readers.TxtFileReader;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CryptoException {

        CryptoUtils.Encrypt("1234567812345678", new File("input.txt"), new File("input2.txt"));

        var txtFileReader = new EncryptedFileReader("1234567812345678", new TxtFileReader(new DefaultReader("input2.txt", "output2.txt")));
        txtFileReader.Write(txtFileReader.Calculate(txtFileReader.Read()));
    }
}