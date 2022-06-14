package com.sliceclient.ui.components;

import com.sliceclient.Slice;
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

    private boolean hovered;

    private float mouseX, mouseY;

    public Button(String text, int x, int y, int width, int height, Runnable onClick) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onClick = onClick;
    }

    public void draw(Graphics2D g2d) {
        this.width = g2d.getClipBounds().width / 3;
        this.height = g2d.getClipBounds().height / 15;

        float fontHeight = g2d.getClipBounds().height / 15f;
        float radius = (g2d.getClipBounds().height + g2d.getClipBounds().width) / 50f;

        if (this.x == 0) this.x = 1;
        if (this.y == 0) this.y = 1;

        mouseX = Slice.INSTANCE.getMouseX();
        mouseY = Slice.INSTANCE.getMouseY();

        int y = (g2d.getClipBounds().height / 5) * this.y / 100;
        int x = (g2d.getClipBounds().width / 9) * this.x / 100;

        this.hovered = mouseX > x && mouseX < x + (width+radius) && mouseY > (y+30) && mouseY < (y+30) + height;

        g2d.setFont(new Font("Poppins-Regular", Font.BOLD, (int)fontHeight));
        g2d.setColor(!hovered ? Color.WHITE : new Color(255, 155, 255));
        g2d.fillRoundRect(x, y, width, height, (int)radius, (int)radius);

        g2d.setColor(Color.BLACK);
        g2d.drawString(text, x + (width / 2f) - (g2d.getFontMetrics().stringWidth(text) / 2f), y + (height / 2f) + (fontHeight / 3));
    }

    public void mouseReleased(MouseEvent e) {
        if (hovered) {
            onClick.run();
        }
    }
}
