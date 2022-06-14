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
        this.width = getScaledWidth() / 3;
        this.height = getScaledHeight() / 13;
        float fontHeight = getScaledHeight() / 10.5f;
        float radius = (getScaledHeight() + getScaledWidth()) / 50f;

        this.hovered = mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;

        if (this.x == 0) {
            this.x = 1;
        }

        if (this.y == 0) {
            this.y = 1;
        }

        int y = (getScaledHeight() / 5) * this.y / 100;
        int x = (getScaledWidth() / 9) * this.x / 100;

        g2d.setFont(new Font("Poppins-Regular", Font.PLAIN, (int)fontHeight));
        g2d.setColor(!hovered ? new Color(Integer.MIN_VALUE) : Color.pink);
        g2d.drawRoundRect(x, y, width, height, (int)radius, (int)radius);

        g2d.setColor(Color.WHITE);
        g2d.drawString(this.text, x + (this.width / 2f),y + (this.height / 2f) - (fontHeight / 2) + 6);
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getX() > scaledX && e.getX() <= scaledX + scaledWidth && e.getY() > scaledY && (e.getY() <= scaledY + scaledHeight + scaledWidth)) {
            onClick.run();
        }
    }
}
