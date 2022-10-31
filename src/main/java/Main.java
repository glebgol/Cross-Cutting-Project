import archivers.ArchivationFileManager;
import ciphers.CryptoUtils;
import exceptions.CryptoException;
import org.mariuszgromada.math.mxparser.Expression;
import parsers.ProcessingTxtFile;
import readers.DefaultReader;
import readers.TxtFileReader;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CryptoException {
        var txtFileReader = new TxtFileReader(new DefaultReader("input.txt", "output.txt"));
        txtFileReader.Write(txtFileReader.Calculate());
    }
}