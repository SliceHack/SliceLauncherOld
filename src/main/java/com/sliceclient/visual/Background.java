package com.sliceclient.visual;

import com.sliceclient.Slice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends JComponent {

    /** index of background animation */
    private int index;
    private int tick;

    public Background() {
        setBounds(0, 0, Slice.INSTANCE.getWindow().getWidth(), Slice.INSTANCE.getWindow().getHeight());
    }

    /**
     * Draws the background
     */
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        String path = System.getProperty("user.home") + "//Slice//assets//launcher//background";
        path += "//" + "frame_" + format3Places(index) + "_delay-0.03s" + ".png";

        File file = new File(path);

        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // add saturation to the image
        g2d.drawImage(img, 0, 0, getWidth(), getHeight(), Color.WHITE, null);

        Slice.INSTANCE.drawBackground();
        tick++;
        if(tick >= 3) {
            updateIndex();
            tick = 0;
        }


        int width = (getFontMetrics(getFont()).stringWidth("User Logged in " + Slice.INSTANCE.getSession().getName())*2)-10;

        g2d.setColor(new Color(0, 0, 0, 0.5f));
        g2d.fillRoundRect(getWidth() - width, 0, width, 45, 25, 25);
        g2d.setFont(new Font("Poppins", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString("User Logged in " + Slice.INSTANCE.getSession().getName(), getWidth() - g2d.getFontMetrics().stringWidth("User Logged in " + Slice.INSTANCE.getSession().getName()) - 20, 30);

        Slice.INSTANCE.getComponentManager().draw(g2d);
        Slice.INSTANCE.getComponentManager().getComponents().forEach(component -> component.setWindowHeight(getHeight()));
        Slice.INSTANCE.getComponentManager().getComponents().forEach(component -> component.setWindowWidth(getWidth()));
        Slice.INSTANCE.getComponentManager().getComponents().forEach(component -> component.setMouseX(Slice.INSTANCE.getMouseX()));
        Slice.INSTANCE.getComponentManager().getComponents().forEach(component -> component.setMouseY(Slice.INSTANCE.getMouseY()));
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
