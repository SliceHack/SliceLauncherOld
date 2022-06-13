package com.sliceclient.ui.components;

import com.sliceclient.ui.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseEvent;

@Getter @Setter
public class Button extends Component {

    private String text;
    private int x, y, width, height;
    private Runnable onClick;

    private int scaledX, scaledY, scaledWidth, scaledHeight;

    public Button(String text, int x, int y, int width, int height, Runnable onClick) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onClick = onClick;
    }

    public void draw(Graphics2D g2d) {
        int fontHeight = g2d.getClipBounds().height / height;

        this.scaledX = x;
        this.scaledY = y;
        this.scaledWidth = g2d.getClipBounds().width / width;
        this.scaledHeight = g2d.getClipBounds().height / height;

        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(scaledX, scaledY, scaledWidth, scaledHeight, 20, 20);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Poppins-Thin", Font.BOLD, fontHeight));
        g2d.drawString(text, scaledX + (scaledWidth / 2) - (g2d.getFontMetrics().stringWidth(text) / 2), (scaledY + (scaledHeight / 2) + (fontHeight / 2))-(fontHeight / 5));

    }

    public void mouseReleased(MouseEvent e) {
        if (e.getX() > scaledX && e.getX() <= scaledX + scaledWidth && e.getY() > scaledY && (e.getY() <= scaledY + scaledHeight + scaledWidth)) {
            onClick.run();
        }
    }
}
