package com.sliceclient.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Used for unzipping files.
 *
 * @author Nick
 * */
@UtilityClass
public class UnzipUtil {

    /**
     * Unzips a file.
     *
     * @param file - the file to unzip
     * @param destination - the destination to unzip to
     * */
    @SuppressWarnings("all")
    public static void unzip(String file, String destination) {
        try {
            String fileZip = file;
            File destDir = new File(destination);
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = new File(destDir + File.separator + zipEntry.getName());
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (Exception ignored) {}
    }
}
