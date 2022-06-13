package com.sliceclient.window;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * The window of the launcher
 * */
@Getter @Setter
public class Window {

    /** data of the frame */
    private final String title;
    private final int width, height;

    /** The Frame */
    private JFrame frame;

    /**
     * The constructor of the window
     * @param title The title of the window
     * @param width The width of the window
     * @param height The height of the window
     */
    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
    }

    /**
     * Adds to the frame
     * */
    public void add(JComponent component) {
        frame.add(component);
    }


    /**
     * Shows the window
     */
    public void show(boolean visible) {
        frame.setVisible(visible);
    }
}
