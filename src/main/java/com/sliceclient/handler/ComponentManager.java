package com.sliceclient.handler;

import com.sliceclient.ui.Component;
import com.sliceclient.ui.components.Button;
import com.sliceclient.ui.components.Overlay;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ComponentManager extends Component {

    private List<Component> components = new ArrayList<>();

    public ComponentManager() {
        register(new Overlay());
        components.forEach(Component::init);
    }

    /**
     * Adds a component to the gui
     *
     * @param component The component to add
     */
    public void register(Component component) {
        components.add(component);
    }

    /**
     * Draws all the components
     *
     * @param g2d The graphics object
     */
    public void draw(Graphics2D g2d) {
        components.forEach(component -> component.draw(g2d));
    }

    /**
     * Mouse events
     *
     * @param e The mouse event
     */
    public void mouseClicked(MouseEvent e) {
        components.forEach(component -> component.mousePressed(e));
    }

    public void mouseReleased(MouseEvent e) {
        components.forEach(component -> component.mouseReleased(e));
    }

    /**
     * key events
     *
     * @param e The key event
     */
    public void keyPressed(KeyEvent e) {
        components.forEach(component -> component.keyPressed(e));
    }

    public void keyReleased(KeyEvent e) {
        components.forEach(component -> component.keyReleased(e));
    }

}
