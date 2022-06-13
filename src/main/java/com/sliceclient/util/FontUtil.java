package com.sliceclient.util;

import lombok.experimental.UtilityClass;

import java.awt.*;
import java.io.File;

/**
 * Utility class for font handling.
 */
@UtilityClass
public class FontUtil {

    /**
     * Returns a font with the specified name and size.
     *
     * @param fontName the name of the font
     * @param fontSize the size of the font
     */
    public static Font createFont(String fontName, int fontSize) {
        Font font = getFontFromGraphics(fontName, fontSize);

        File fontFile = new File(System.getProperty("user.home") + File.separator + "Slice"
                + File.separator + "assets"
                + File.separator + "launcher"
                + File.separator + "fonts" + File.separator + fontName + ".ttf");

        if (fontFile.exists()) {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(fontSize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return font;
    }

    /**
     * Returns a font with the specified name and size.
     *
     * @param fontName the name of the font
     * @param fontSize the size of the font
     */
    private static Font getFontFromGraphics(String fontName, int fontSize) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        for (Font font : fonts) {
            if (font.getName().equalsIgnoreCase(fontName)) {
                return font.deriveFont(fontSize);
            }
        }
        return null;
    }

}
