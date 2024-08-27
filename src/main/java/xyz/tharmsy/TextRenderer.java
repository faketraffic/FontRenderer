package xyz.tharmsy;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TextRenderer {

    private FontManager fontManager;

    public TextRenderer(FontManager fontManager) {
        this.fontManager = fontManager;
    }

    public BufferedImage renderText(String text, String fontName, int style, float size, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        Font font = fontManager.getFont(fontName, style, size);
        g2d.setFont(font);

        int x = (width - g2d.getFontMetrics().stringWidth(text)) / 2;
        int y = (height - g2d.getFontMetrics().getHeight()) / 2 + g2d.getFontMetrics().getAscent();
        g2d.drawString(text, x, y);

        g2d.dispose();
        return image;
    }
}
