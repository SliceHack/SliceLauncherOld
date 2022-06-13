package com.sliceclient.util;

import com.sliceclient.Slice;
import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Utility class for resource handling
 */
@UtilityClass
public class ResourceUtil {

    /**
     * Returns the resource as a stream
     *
     * @param path the path to the resource
     */
    public static List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    /**
     * Returns the resource as a stream
     *
     * @param resource the resource to get
     */
    public static InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? Slice.class.getResourceAsStream(resource) : in;
    }

    /**
     * Returns the context class loader
     */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
