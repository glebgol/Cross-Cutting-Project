package reactuitest;

import java.io.File;

public class FileUtil {
    public static boolean isExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
