package com.sliceclient.ui.components;

import com.sliceclient.ui.Component;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

@Getter @Setter
public class Overlay extends Component {

    private List<Button> buttons = new ArrayList<>();

    private int width, height;

    public void init() {
        buttons.add(new Button("Play", 150, 150, 5, 10, () -> System.out.println("Play")));
        buttons.forEach(Component::init);
    }

    public void draw(Graphics2D g2d) {
        int fontHeight = g2d.getClipBounds().height / 9;
        Font font = new Font("Poppins-Thin", Font.PLAIN, fontHeight);

        int scaledWidth = getWindowWidth() / 3;
        g2d.setColor(new Color(0, 0, 0, 0.5f));
        g2d.fillRect(0, 0, scaledWidth, getWindowHeight());

        width = scaledWidth;
        height = getWindowHeight();

        float scaledWidth2 = getWindowWidth() / 4.2f;
        float scaledHeight = getWindowHeight() / 3.2f;
        float scaledX = (((getWindowWidth() - scaledWidth) / 2f)-(scaledWidth2));
        float scaledY = ((getWindowHeight() - fontHeight) / 2f)-(scaledHeight);

        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Slice", (scaledX), scaledY);

        buttons.forEach(button -> button.draw(g2d));
    }

    public void mouseReleased(MouseEvent e) {
        buttons.forEach(button -> button.mouseReleased(e));
    }
}
