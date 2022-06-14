package com.sliceclient.ui;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The component class
 *
 * @author Nick
 * */
@Getter @Setter
@SuppressWarnings("all")
public abstract class Component {

    /** Window positions */
    private int windowHeight, windowWidth;

    /** mouse positions */
    private float mouseX, mouseY;

    /**
     * draws the component
     *
     * @param g2d The graphics object
     * */
    public abstract void draw(Graphics2D g2d);

    /**
     * Mouse events
     *
     * @param e The mouse event
     * */
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    /**
     * key events
     *
     * @param e The key event
     * */
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    /**
     * When the component is created
     * */
    public void init() {}

}
