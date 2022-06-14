package com.sliceclient.ui.components;

import com.sliceclient.ui.Component;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyEvent;

@Getter @Setter
public class LoginField extends Component {

    private int x, y, width, height;

    private TextField usernameField, passwordField;

    public LoginField(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.usernameField = new TextField(x, y, width, height);
        this.passwordField = new TextField(x, y, width, height);
    }


    public void draw(Graphics2D g2d) {
        usernameField.draw(g2d);
    }

    public void keyReleased(KeyEvent e) {
        usernameField.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        usernameField.keyPressed(e);
    }
}
