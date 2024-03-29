package com.sliceclient.ui.components.pane;

import com.sliceclient.Slice;
import com.sliceclient.minecraft.Minecraft;
import com.sliceclient.ui.Component;
import com.sliceclient.ui.components.Button;
import com.sliceclient.ui.components.LoginField;
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
        buttons.add(new Button("Play", 0, 120, width, 50, Minecraft::start));
        buttons.add(new Button("Login", 0, 180, width, 50, () -> Slice.INSTANCE.setLoginField(!Slice.INSTANCE.isLoginField())));
        buttons.forEach(Component::init);
    }

    public void draw(Graphics2D g2d) {
        if(Slice.INSTANCE.isLoginField())
            return;

        int fontHeight = g2d.getClipBounds().height / 9;
        Font font = new Font("Poppins-Thin", Font.PLAIN, fontHeight);

        int scaledWidth = getWindowWidth() / 3;
        g2d.setColor(new Color(0, 0, 0, 0.5f));
        g2d.fillRect(0, 0, scaledWidth, getWindowHeight());

        width = scaledWidth;
        height = getWindowHeight();

        float scaledWidth2 = (getWindowWidth()+fontHeight) / 4.5f;
        float scaledHeight = (getWindowHeight()-fontHeight) / 3.2f;
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

    public void mouseMoved(MouseEvent e) {
        buttons.forEach(button -> button.mouseMoved(e));
    }
}
