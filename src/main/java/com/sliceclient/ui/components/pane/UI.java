package com.sliceclient.ui.components.pane;

import com.sliceclient.Slice;
import com.sliceclient.ui.Component;
import com.sliceclient.ui.components.LoginField;
import lombok.Getter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UI extends Component {

    private final List<Component> components = new ArrayList<>();

    private final LoginField loginField;

    public UI() {
        components.add(loginField = new LoginField(0, 0, 300, 30));
    }

    public void draw(Graphics2D g2d) {
        if(Slice.INSTANCE.isLoginField()) loginField.draw(g2d);
    }
    public void mouseMoved(MouseEvent e) {
        if(Slice.INSTANCE.isLoginField()) loginField.mouseMoved(e);
    }
    public void mousePressed(MouseEvent e) {
        if(Slice.INSTANCE.isLoginField()) loginField.mousePressed(e);
    }
    public void mouseReleased(MouseEvent e) {
        if(Slice.INSTANCE.isLoginField()) loginField.mouseReleased(e);
    }
    public void keyPressed(KeyEvent e) {
        if(Slice.INSTANCE.isLoginField()) loginField.keyPressed(e);
    }
    public void keyReleased(KeyEvent e) {
        if(Slice.INSTANCE.isLoginField()) loginField.keyReleased(e);
    }
}
