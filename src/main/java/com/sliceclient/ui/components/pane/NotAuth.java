package com.sliceclient.ui.components.pane;

import com.sliceclient.ui.Component;
import lombok.Getter;

import java.awt.*;

@Getter
public class NotAuth extends Component {

    private int x, y;

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Poppins-Regular", Font.PLAIN, 50));
        g2d.drawString("Not authorized", getWindowWidth() / 2 - g2d.getFontMetrics().stringWidth("Not authorized") / 2, (getWindowHeight() / 2)-50);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Poppins-Regular", Font.PLAIN, 25));
        g2d.drawString("If you have not ran SliceHWID please do so to run Slice", getWindowWidth() / 2 - g2d.getFontMetrics().stringWidth("If you have not ran SliceHWID please do so to run Slice") / 2, (getWindowHeight() / 2) );
    }
}
