package com.sliceclient.ui.components;

import com.sliceclient.ui.Component;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Text field
 * */
@Getter @Setter
public class TextField extends Component {

    private int x, y, width, height;
    private String typedText, highlightedText;
    private boolean hiddenText;

    private int cursorPosition;

    private int[] disallowedKeys = { KeyEvent.VK_BACK_SPACE, KeyEvent.VK_DELETE, KeyEvent.VK_ENTER, KeyEvent.VK_ESCAPE, KeyEvent.VK_TAB };

    public TextField(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.typedText = "";
        this.highlightedText = "";
        this.hiddenText = false;
    }

    public void draw(Graphics2D g2d) {
        int y = (g2d.getClipBounds().height / 5) * this.y / 100;
        int x = (g2d.getClipBounds().width / 9) * this.x / 100;

        g2d.drawRect(x, y, (g2d.getClipBounds().width / 9) * this.width / 100, (g2d.getClipBounds().height / 5) * this.height / 100);
        g2d.drawString(highlightedText.length() > 0 ? typedText.replace(highlightedText, "") : typedText, x, y);

        if(typedText.length() > 0) {
            g2d.drawString(highlightedText, g2d.getFontMetrics().stringWidth(typedText.replace(highlightedText, "")) + x, y);
        }
    }

    @SuppressWarnings("all")
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) backspace();
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) space();
        else if(e.getModifiers() == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_C) controlC();
        else if(e.getModifiers() == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_V) controlV();
        else if(e.getModifiers() == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_X) controlX();
        else if(e.getModifiers() == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_A) controlA();

        for (int disallowedKey : disallowedKeys) {
            if (e.getKeyCode() != disallowedKey) {
                StringBuilder sb = new StringBuilder(typedText);
                sb.append(typedText).append(e.getKeyChar());
                typedText = sb.toString();
            }
        }
    }

    /**
     * Space
     */
    public void space() {
        typedText += " ";
        cursorPosition++;
    }

    /**
     * Copies the text to the clipboard
     */
    public void controlC() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(typedText), null);
    }

    /**
     * Highlights all text in the text field
     * */
    public void controlA() {
        highlightedText = typedText;
    }

    /**
     * Pastes the text from the clipboard
     */
    public void controlV() {
        try {
            typedText += (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (Exception ignored){}
    }

    /**
     * Deletes and copies the text to the clipboard
     * */
    public void controlX() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(typedText), null);
        typedText = "";
    }

    /**
     * Deletes the last character
     */
    public void backspace() {
        if (typedText.length() > 0) {
            typedText = typedText.substring(0, typedText.length() - 1);
        }
        cursorPosition--;
    }


}
