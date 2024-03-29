package com.sliceclient;

import com.sliceclient.config.Config;
import com.sliceclient.handler.ComponentManager;
import com.sliceclient.input.KeyHandler;
import com.sliceclient.input.MouseHandler;
import com.sliceclient.minecraft.session.Session;
import com.sliceclient.util.*;
import com.sliceclient.visual.Background;
import com.sliceclient.window.Window;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

import static com.sliceclient.api.API.sendAuthRequest;

/**
 * The main class of the launcher
 * */
@Getter @Setter
public class Slice {

    public static Slice INSTANCE;

    /** window */
    private final Window window;

    /** UI */
    private Background background;

    /** input */
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    /** manager */
    private ComponentManager componentManager;

    /** mouse */
    private float mouseX, mouseY;

    /** Minecraft */
    private Session session;

    /** booleans */
    private boolean authorized, loginField;

    private Config config;

    /**
     * Constructor
     */
    Slice() {
        INSTANCE = this;
        sendAuthRequest();
        componentManager = new ComponentManager();
        window = new Window("Slice", 800, 600);
        session = new Session("Player", "0", "0", "legacy", "0");
        config = new Config();
        initBackground();
    }

    public void initBackground() {
        window.addKeyListener(keyHandler = new KeyHandler());
        window.addMouseListener(mouseHandler = new MouseHandler());
        window.addMouseMotionListener(mouseHandler);
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
        System.setProperty("user.dir", System.getProperty("user.home") + File.separator + "Slice");
        String path = System.getProperty("user.home") + "//Slice//assets//launcher//background";

        File filePath = new File(System.getProperty("user.home") + File.separator + "Slice");

        File parent = new File(path).getParentFile(),
                file = new File(path),
                fileFont = new File(parent + File.separator + "font"),
                filelib = new File(filePath + File.separator + "lib"),
                fileZip = new File(filePath + File.separator + "lib.zip"),
                zip = new File(file + File.separator + "Background.zip"),
                zipFont = new File(parent + File.separator + "Poppins.zip"),
                ziplib = new File(filePath + File.separator + "lib.zip"),
                file1 = new File(file, "frame_000_delay-0.03s.png"),
                file2 = new File(fileFont, "Poppins-Regular.ttf"),
                file3 = new File(filelib, "1.8.jar"),
                ofl = new File(fileFont, "OFL.txt");

        if(!parent.exists()) parent.mkdirs();
        else if(!fileFont.exists()) fileFont.mkdirs();
        else if(!filelib.exists()) filelib.mkdirs();

        if(!file1.exists()) {
            DownloadUtil.downloadFile("https://github.com/NickReset/SliceResources/raw/main/Background.zip", zip.getAbsolutePath());
            UnzipUtil.unzip(zip.getAbsolutePath(), zip.getParentFile().getAbsolutePath());
        }

        if(!file2.exists()) {
            DownloadUtil.downloadFile("https://github.com/NickReset/SliceResources/raw/main/Poppins.zip", zipFont.getAbsolutePath());
            UnzipUtil.unzip(zipFont.getAbsolutePath(), fileFont.getAbsolutePath());
        }

        if(!file3.exists()) {
            DownloadUtil.downloadFile("https://api.sliceclient.com/download/lib", ziplib.getAbsolutePath());
            UnzipUtil.unzip(ziplib.getAbsolutePath(), filelib.getAbsolutePath());
        }


        if(zip.exists() && file1.exists()) zip.delete();
        if(zipFont.exists() && file2.exists()) zipFont.delete();
        if(ziplib.exists() && file3.exists()) ziplib.delete();
        if(ofl.exists()) ofl.delete();

        new Slice();
    }

}