package xyz.tharmsy;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FontManager {
    private Map<String, Font> fonts;

    public FontManager() {
        fonts = new HashMap<>();
    }

    public void loadFont(String fontName, String fontFilePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fontFilePath)) {
            if (is == null) {
                throw new IOException("Font s: " + fontFilePath);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            fonts.put(fontName, font);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Font getFont(String fontName, int style, float size) {
        Font font = fonts.getOrDefault(fontName, new Font("Serif", style, (int) size));
        return font.deriveFont(style, size);
    }
}
