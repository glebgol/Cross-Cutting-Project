import exceptions.CryptoException;
import org.mariuszgromada.math.mxparser.Expression;
import readers.EncryptedFileReader;
import readers.TxtFileReader;
import readers.ZipFileReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CryptoException {
        String key = "1234567812345678";
        //ArchivationFileManager.ZipFile("input.txt");
        var fileReader1 = new ZipFileReader(new EncryptedFileReader(key, new TxtFileReader("input2.zip", "output1.txt")));
        fileReader1.WriteCalculated();

        var fileReader2 = new TxtFileReader("input.txt", "output2.txt");
        fileReader2.WriteCalculated();

//        CryptoUtils.Encrypt(key, new File("input.txt"), new File("input2.txt"));
//        ArchivationFileManager.ZipFile("input2.txt");
        var fileReader3 = new EncryptedFileReader("1234567812345678", new TxtFileReader("input2.txt", "output3.txt"));
        fileReader3.WriteCalculated();
//        CryptoUtils.Encrypt("1234567812345678", "input.txt", "input2.txt");
//        CryptoUtils.Decrypt("1234567812345678","input2.txt", "input3.txt");
//        ArchivationFileManager.ZipFile("input2.txt");

        var exp = new Expression("sin(3.14) sin cos 12 + 34 hi sin 100 + (12 + 13)");
        //exp.disableImpliedMultiplicationMode();
        //exp.disableUnicodeBuiltinKeyWordsMode();
        exp.consolePrintCopyOfInitialTokens();
        System.out.println(exp.calculate());
//        for (var x : exp.getCopyOfInitialTokens()) {
//            System.out.println(x.tokenStr + " "  + x.keyWord + " "  + x.tokenId);
//        }
    }
}