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

        var fileReader = new ZipFileReader(new TxtFileReader(new DefaultReader("input.zip", "output3.txt")));
        fileReader.Write(fileReader.Calculate(fileReader.Read()));
    }
}