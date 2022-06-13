package com.sliceclient;

import com.sliceclient.util.DownloadUtil;
import com.sliceclient.util.Unzip;
import com.sliceclient.util.UnzipUtil;
import com.sliceclient.visual.Background;
import com.sliceclient.visual.Button;
import com.sliceclient.window.Window;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * The main class of the launcher
 * */
@Getter @Setter
public class Slice {

    public static Slice INSTANCE;

    private final Window window;

    /**
     * Constructor
     */
    Slice() {
        INSTANCE = this;

        window = new Window("Slice", 800, 600);
        window.add(new Background());
        window.show(true);
    }

    public void drawBackground() {
        window.getFrame().getComponents()[0].repaint();
    }

    /**
     * The main method of the launcher
     * @param args The arguments of the launcher
     * */
    public static void main(String[] args) {
        String path = System.getProperty("user.home") + "//Slice//assets//launcher//background";

        File parent = new File(path).getParentFile();
        File file = new File(path);

        File zip = new File(file + File.separator + "Background.zip");

        File file1 = new File(file, "frame_000_delay-0.03s.png");

        if(!file1.exists()) {
            DownloadUtil.downloadFile("https://github.com/NickReset/SliceResources/raw/main/Background.zip", zip.getAbsolutePath());
            UnzipUtil.unzip(zip.getAbsolutePath(), zip.getParentFile().getAbsolutePath());
        }

        new Slice();
    }


}