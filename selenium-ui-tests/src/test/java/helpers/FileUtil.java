package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileUtil {
    public static boolean isExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + "\\" + fileName);
        file.delete();
    }

    public static boolean isEqualFileBytes(String fileName1, String fileName2) throws IOException {
        FileInputStream fis1 = new FileInputStream(fileName1);
        FileInputStream fis2 = new FileInputStream(fileName2);
        byte[] bytes1 = fis1.readAllBytes();
        byte[] bytes2 = fis2.readAllBytes();
        boolean isEqual = Arrays.equals(bytes1, bytes2);
        fis1.close();
        fis2.close();
        return isEqual;
    }
}
