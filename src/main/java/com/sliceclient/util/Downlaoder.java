package com.sliceclient.util;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class for downloading files
 *
 * @author Nick
 */
@Getter @Setter
public class Downlaoder implements Runnable {

    private String url;
    private File out;

    public Downlaoder(String url, File out) {
        this.url = url;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            double fileSize = connection.getContentLength();
            BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
            FileOutputStream fos = new FileOutputStream(this.out);
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte[] data = new byte[1024];
            double downloaded = 0;
            int read = 0;
            while ((read = in.read(data, 0, 1024)) >= 0) {
                bout.write(data, 0, read);
                downloaded += read;
                double percent = (downloaded / fileSize) * 100;
                System.out.println("Downloading " + this.out.getName() + " " + percent + "%");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
