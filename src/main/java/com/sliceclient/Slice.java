package com.sliceclient;

import com.sliceclient.window.Window;
import lombok.Getter;
import lombok.Setter;

/**
 * The main class of the launcher
 * */
@Getter @Setter
public class Slice {

    private final Window window;

    /**
     * Constructor
     */
    Slice() {
        window = new Window("Slice", 800, 600);
        window.show(true);
    }

    /**
     * The main method of the launcher
     * @param args The arguments of the launcher
     * */
    public static void main(String[] args) {
        new Slice();
    }


}
