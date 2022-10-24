import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchivationFileManager {
    private static String GetNameOfArchiveFile(String notZipFileName, String fileExtension) {
        var lastIndexOfDot = notZipFileName.lastIndexOf('.');
        var zipFileName = notZipFileName.substring(0, lastIndexOfDot)  + fileExtension;
        return zipFileName;
    }
    public static void ZipFile(String fileName) throws FileNotFoundException, IOException {
        var zipFileName = GetNameOfArchiveFile(fileName, ".zip");
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFileName));
        FileInputStream fis = new FileInputStream(fileName);
        ZipEntry entry1 = new ZipEntry(fileName);
        zout.putNextEntry(entry1);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        zout.write(buffer);
        zout.closeEntry();
    }
}
