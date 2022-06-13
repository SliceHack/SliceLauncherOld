package com.sliceclient;

import com.sliceclient.util.DownloadUtil;
import com.sliceclient.util.Unzip;
import com.sliceclient.util.UnzipUtil;
import com.sliceclient.visual.Background;
import com.sliceclient.visual.Button;
import com.sliceclient.window.Window;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The main class of the launcher
 * */
@Getter @Setter
public class Slice {

    public static Slice INSTANCE;

    private final Window window;

    private Background background;

    /**
     * Constructor
     */
    Slice() {
        INSTANCE = this;

        window = new Window("Slice", 800, 600);
        window.add(background = new Background());
        window.show(true);
    }

    public void drawBackground() {
        Background background = getBackground();
        background.setBounds(0, 0, getWindow().getFrame().getWidth(), getWindow().getFrame().getHeight());
        background.repaint();
    }

    /**
     * The main method of the launcher
     * @param args The arguments of the launcher
     * */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        String path = System.getProperty("user.home") + "//Slice//assets//launcher//background";

        File parent = new File(path).getParentFile();
        File file = new File(path);

        File zip = new File(file + File.separator + "Background.zip");

        File file1 = new File(file, "frame_000_delay-0.03s.png");

        if(!file1.exists()) {
            DownloadUtil.downloadFile("https://github.com/NickReset/SliceResources/raw/main/Background.zip", zip.getAbsolutePath());
            UnzipUtil.unzip(zip.getAbsolutePath(), zip.getParentFile().getAbsolutePath());
        } else if(zip.exists()){
            zip.delete();
        }

        new Slice();
    }


}