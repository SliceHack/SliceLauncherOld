package com.sliceclient.ui.components;

import com.sliceclient.Slice;
import com.sliceclient.ui.Component;
import com.sliceclient.util.LoginUtil;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

@Getter @Setter
public class LoginField extends Component {

    private int x, y, width, height;

    private JFrame frame;
    private JTextField passwordField, usernameField;

    public LoginField(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        frame = new JFrame("Login");
        frame.setSize(152*2, 180*2);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        usernameField = new JTextField();
        usernameField.setBounds((frame.getWidth()/2)-75, (frame.getHeight()/2)-30, 150, 30);

        passwordField = new JTextField();
        passwordField.setBounds((frame.getWidth()/2)-75, (frame.getHeight()/2), 150, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds((frame.getWidth()/2)-75, (frame.getHeight()/2)+30, 150, 30);
        loginButton.addActionListener(e -> {
            Slice.INSTANCE.setSession(Objects.requireNonNull(LoginUtil.loginMicrosoft(usernameField.getText(), passwordField.getText())).getSession());
            Slice.INSTANCE.setLoginField(false);
            frame.setVisible(false);
        });

        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(loginButton);
    }

    public void draw(Graphics2D g2d) {
        if(!frame.isVisible() && Slice.INSTANCE.isLoginField()) {
            frame.setVisible(true);
        }

        if(frame.isVisible() && !Slice.INSTANCE.isLoginField()) {
            frame.setVisible(false);
        }

    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            frame.setVisible(false);
            Slice.INSTANCE.setLoginField(false);
        }
    }
}
