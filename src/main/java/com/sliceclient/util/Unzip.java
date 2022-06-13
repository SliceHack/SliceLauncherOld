package com.sliceclient.util;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipInputStream;

/**
 * UnzipUtil
 *
 * @author Nick
 * @version 1.0
 * @since 1.0
 * */
@Getter @Setter
public class Unzip implements Runnable {

    private String path, zipPath;

    public Unzip(String zipPip, String path) {
        this.zipPath = zipPip;
        this.path = path;
    }

    public void run() {
        try {
//            ZipInputStream zis = new ZipInputStream(Files.newInputStream(new File(zipPath).toPath()));
//            extractFile(zis, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts a zip entry (file entry)
     *
     * @param zipIn The zip input stream
     * @param filePath The path of the file
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        byte[] buffer = new byte[1024];
        int read;
        while ((read = zipIn.read(buffer)) != -1) {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath + File.separator + zipIn.getNextEntry().getName()));
            bos.write(buffer, 0, read);
            bos.flush();
            bos.close();
        }
        System.out.println("Extracted " + filePath);
        zipIn.closeEntry();
    }
}
