package com.glebgol.businesslogic.utils.archivers;


import com.glebgol.businesslogic.contracts.interfaces.IStream;
import com.glebgol.businesslogic.streams.TxtStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchivingFileManager {
    public static String getNameOfArchiveFile(String notZipFileName) {
        var lastIndexOfDot = notZipFileName.lastIndexOf('.');
        return notZipFileName.substring(0, lastIndexOfDot)  + ".zip";
    }
    public static void zipFile(String fileName) throws IOException {
        String zipFileName = getNameOfArchiveFile(fileName);
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFileName));
        FileInputStream fis = new FileInputStream(fileName);
        ZipEntry entry1 = new ZipEntry(fileName);
        zout.putNextEntry(entry1);
        byte[] buffer = new byte[fis.available()];
        zout.write(buffer);
        zout.closeEntry();
    }

    public static void unZipFile(String fileName, String outputFile) throws IOException {
        ZipInputStream zin = new ZipInputStream(new FileInputStream(fileName));
        while((zin.getNextEntry()) != null) {
            FileOutputStream fout = new FileOutputStream(outputFile);
            for (int c = zin.read(); c != -1; c = zin.read()) {
                fout.write(c);
            }
            fout.flush();
            zin.closeEntry();
            fout.close();
        }
    }

    public static IStream getUnZipped(String filename) throws IOException {
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(filename);
        ZipInputStream zis = new ZipInputStream(fis);
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
        fis.close();

        var bytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        return new TxtStream(bytes);
    }
}
