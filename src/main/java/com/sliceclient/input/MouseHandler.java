package com.sliceclient.input;

import com.sliceclient.Slice;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The mouse handler class
 *
 * @author Nick
 * */
public class MouseHandler extends MouseAdapter {

    /**
     * The mouse pressed method
     *
     * @param e The mouse event
     * */
    public void mousePressed(MouseEvent e) {
        Slice.INSTANCE.getComponentManager().mouseClicked(e);
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