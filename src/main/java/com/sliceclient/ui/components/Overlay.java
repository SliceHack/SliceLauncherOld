package com.sliceclient.ui.components;

import com.sliceclient.ui.Component;
import com.sliceclient.util.FontUtil;

import java.awt.*;

public class Overlay extends Component {

    public void draw(Graphics2D g2d) {
        int fontHeight = g2d.getClipBounds().height / 9;
        Font font = new Font("Poppins-Thin", Font.PLAIN, fontHeight);

        int scaledWidth = getWindowWidth() / 3;
        g2d.setColor(new Color(0, 0, 0, 0.5f));
        g2d.fillRect(0, 0, scaledWidth, getWindowHeight());

        float scaledWidth2 = getWindowWidth() / 4.2f;
        float scaledHeight = getWindowHeight() / 3.2f;

        float scaledX = (((getWindowWidth() - scaledWidth) / 2f)-(scaledWidth2));
        float scaledY = ((getWindowHeight() - fontHeight) / 2f)-(scaledHeight);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Slice", (scaledX), scaledY);
    }
}
