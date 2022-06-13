package com.sliceclient.visual;

import com.sliceclient.Slice;
import com.sliceclient.util.DownloadUtil;
import com.sliceclient.util.ResourceUtil;
import com.sliceclient.util.UnzipUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Background extends JComponent {

    /** index of background animation */
    private int index;

    public Background() {
        setBounds(0, 0, Slice.INSTANCE.getWindow().getWidth(), Slice.INSTANCE.getWindow().getHeight());
    }


    /**
     * Draws the background
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        String path = System.getProperty("user.home") + "//Slice//assets//launcher//background";
        path += "//" + "frame_" + format3Places(index) + "_delay-0.03s" + ".png";

        File parent = new File(path).getParentFile();
        File file = new File(path);

        try {
            if (!parent.exists()) parent.mkdirs();
            if (!file.exists()) {
                return;
            }
        } catch (Exception ignored){}

        Slice.INSTANCE.drawBackground();
        updateIndex();
    }

    /**
     * format an int to a string with 3 digits
     * */
    public String format3Places(int places) {
        if(places < 10) return "00" + places;
        else if(places == 100) return "100";
        else if(places < 100) return "0" + places;
        else return "" + places;
    }

    /**
     * updates the background
     * count
     * */
    private void updateIndex() {
        if(index >= 215) index = 0;
        index++;
    }
}
