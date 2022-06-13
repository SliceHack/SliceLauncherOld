package com.sliceclient.visual;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The button class
 * */
@Getter @Setter
public class Button extends JComponent {

    /** data of the button*/
    private String text;
    private int x, y, width, height;

    private Color colorNormal, colorHover, colorClicked, color;

    public Button(String text, int x, int y, int width, int height, Runnable onClick) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colorNormal = new Color(0, 245, 215);
        this.colorHover = colorNormal.darker();
        this.colorClicked = colorHover.darker();
        this.color = colorNormal;
        setBounds(x, y, width + 100, height + 100);

        // handle the click event
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                onClick.run();
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                repaint();
                setColor(colorNormal);
            }

            public void mouseMoved(MouseEvent e) {
                repaint();
                setColor(e.getX() >= x && e.getX() <= x + width && e.getY() >= y && e.getY() <= y + height ? colorHover : colorNormal);
            }
        });
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(color);
        g2d.fillRoundRect(x, y, getWidth(), getHeight(), 10, 10);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString(text, x + (width / 2) - (text.length() * 5), y + (height / 2) + 5);
    }
}
