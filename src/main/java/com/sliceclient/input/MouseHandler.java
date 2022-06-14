package com.sliceclient.input;

import com.sliceclient.Slice;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * The mouse handler class
 *
 * @author Nick
 * */
public class MouseHandler extends MouseAdapter implements MouseMotionListener {

    /**
     * The mouse pressed method
     *
     * @param e The mouse event
     * */
    public void mousePressed(MouseEvent e) {
        Slice.INSTANCE.getComponentManager().mouseClicked(e);
    }

    public void mouseMoved(MouseEvent e) {
        Slice.INSTANCE.setMouseX(e.getX());
        Slice.INSTANCE.setMouseY(e.getY());
        Slice.INSTANCE.getComponentManager().mouseMoved(e);
    }

    /**
     * The mouse released method
     *
     * @param e The mouse event
     * */
    public void mouseReleased(MouseEvent e) {
        Slice.INSTANCE.getComponentManager().mouseReleased(e);
    }
}