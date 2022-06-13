package com.sliceclient.input;

import com.sliceclient.Slice;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The key handler class
 *
 * @author Nick
 * */
public class KeyHandler extends KeyAdapter {

    /**
     * Handles the key pressed event
     *
     * @param e The key event
     * */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F11) {
            Slice.INSTANCE.getWindow().setFullScreen(!Slice.INSTANCE.getWindow().isFullScreen());
        }

        Slice.INSTANCE.getComponentManager().keyPressed(e);
    }

    /**
     * Handles the key released event
     *
     * @param e The key event
     * */
    public void keyReleased(KeyEvent e) {
        Slice.INSTANCE.getComponentManager().keyReleased(e);
    }

}
