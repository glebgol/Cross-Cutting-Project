package reactuitest;

import java.io.File;

public class FileUtil {
    public static boolean isExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + "\\" + fileName);
        file.delete();
    }
}
