package com.sliceclient.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for font handling.
 */
@UtilityClass
public class FontUtil {

    @Getter
    private static final Map<String, Font> fonts = new HashMap<>();

    @Getter
    private static final String path = (System.getProperty("user.home") + File.separator + "Slice"
            + File.separator + "assets"
            + File.separator + "launcher"
            + File.separator + "font");

    /**
     * Returns a font with the specified name and size.
     *
     * @param fontName the name of the font
     * @param fontSize the size of the font
     */
    public static Font createFont(String fontName, int fontSize) {
        Font font = fonts.get(fontName + fontSize);
        if(font == null) {
            Font font1 = createFontFromFile(fontName, fontSize);
            fonts.put(fontName + fontSize, font1);
            font = font1;
        }
        return font;
    }

    /**
     * Creates a font from a file
     *
     * @param fontName the name of the font
     * @param fontSize the size of the font
     * */
    public static Font createFontFromFile(String fontName, int fontSize) {
        System.out.println("Creating font: " + fontName + " " + fontSize);
        Font font = null;

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path + File.separator + fontName + ".ttf"));
            ge.registerFont(font);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Created font: " + fontName + " " + fontSize);

        return font;
    }

}
