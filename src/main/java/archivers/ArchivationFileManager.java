package archivers;

import streams.ReadingResult;
import streams.UnzippingResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchivationFileManager {
    private static String GetNameOfArchiveFile(String notZipFileName, String fileExtension) {
        var lastIndexOfDot = notZipFileName.lastIndexOf('.');
        var zipFileName = notZipFileName.substring(0, lastIndexOfDot)  + fileExtension;
        return zipFileName;
    }
    public static void ZipFile(String fileName) throws IOException {
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

    public static void UnZipFile(String fileName, String outputFile) throws IOException {
        ZipInputStream zin = new ZipInputStream(new FileInputStream(fileName));
        ZipEntry entry;
        while((entry = zin.getNextEntry()) != null) {
            FileOutputStream fout = new FileOutputStream(outputFile);
            for (int c = zin.read(); c != -1; c = zin.read()) {
                fout.write(c);
            }
            fout.flush();
            zin.closeEntry();
            fout.close();
        }
    }

    public static UnzippingResult GetUnZipped(String filename) throws IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(filename));
        ZipEntry zipEntry = zis.getNextEntry();
        StringBuilder stringBuilder = new StringBuilder();
        while (zipEntry != null) {
            int len;
            while ((len = zis.read(buffer)) > 0) {
                String str = new String(buffer, 0, len, StandardCharsets.UTF_8);
                stringBuilder.append(str);
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        var bytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);

        var arrayListOfStrings = new ArrayList<String>();
        var stringTokenizer = new StringTokenizer(stringBuilder.toString(), "\n");
        while (stringTokenizer.hasMoreTokens()) {
            arrayListOfStrings.add(stringTokenizer.nextToken());
        }
        return new UnzippingResult(arrayListOfStrings, bytes);
    }
}
