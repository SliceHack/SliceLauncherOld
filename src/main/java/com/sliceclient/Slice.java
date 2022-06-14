package com.sliceclient;

import com.sliceclient.api.API;
import com.sliceclient.handler.ComponentManager;
import com.sliceclient.input.KeyHandler;
import com.sliceclient.input.MouseHandler;
import com.sliceclient.minecraft.session.Session;
import com.sliceclient.util.DownloadUtil;
import com.sliceclient.util.HardwareUtil;
import com.sliceclient.util.LoginUtil;
import com.sliceclient.util.UnzipUtil;
import com.sliceclient.visual.Background;
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

    private boolean isAuthorized;

    /**
     * Constructor
     */
    Slice() {
        INSTANCE = this;
        API.sendAuthRequest();
        componentManager = new ComponentManager();
        window = new Window("Slice", 800, 600);
        session = new Session("Player", "0", "0", "legacy");
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

        File parent = new File(path).getParentFile(),
                file = new File(path),
                fileFont = new File(parent + File.separator + "font"),
                zip = new File(file + File.separator + "Background.zip"),
                zipFont = new File(parent + File.separator + "Poppins.zip"),
                file1 = new File(file, "frame_000_delay-0.03s.png"),
                file2 = new File(fileFont, "Poppins-Regular.ttf"),
                ofl = new File(fileFont, "OFL.txt");

        if(!parent.exists()) parent.mkdirs();
        else if(!fileFont.exists()) fileFont.mkdirs();

        if(!file1.exists()) {
            DownloadUtil.downloadFile("https://github.com/NickReset/SliceResources/raw/main/Background.zip", zip.getAbsolutePath());
            UnzipUtil.unzip(zip.getAbsolutePath(), zip.getParentFile().getAbsolutePath());
        }

        if(!file2.exists()) {
            DownloadUtil.downloadFile("https://github.com/NickReset/SliceResources/raw/main/Poppins.zip", zipFont.getAbsolutePath());
            UnzipUtil.unzip(zipFont.getAbsolutePath(),fileFont.getAbsolutePath());
        }

        if(zip.exists() && file1.exists()) zip.delete();
        if(zipFont.exists() && file2.exists()) zipFont.delete();
        if(ofl.exists()) ofl.delete();

        new Slice();
    }


}