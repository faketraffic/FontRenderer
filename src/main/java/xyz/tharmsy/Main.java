package xyz.tharmsy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        FontManager fontManager = new FontManager();
        fontManager.loadFont("Roboto", "Roboto-Regular.ttf");
        fontManager.loadFont("Matemasie", "Matemasie-Regular.ttf");
        fontManager.loadFont("New;Amsterdam", "NewAmsterdam-Regular.ttf");
        fontManager.loadFont("OpenSans", "OpenSans-Regular.ttf");

        JFrame frame = new JFrame("Font Rendring Testsing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FontSelectionPanel panel = new FontSelectionPanel(fontManager);
        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class FontSelectionPanel extends JPanel {
    private FontManager fontManager;
    private JComboBox<String> fontComboBox;
    private JSpinner sizeSpinner;
    private JCheckBox boldCheckBox;
    private TextPanel textPanel;

    public FontSelectionPanel(FontManager fontManager) {
        this.fontManager = fontManager;
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        fontComboBox = new JComboBox<>(new String[]{"Roboto", "Matemasie", "NewAmsterdam","OpenSans"});
        controlPanel.add(new JLabel("Font:"));
        controlPanel.add(fontComboBox);

        sizeSpinner = new JSpinner(new SpinnerNumberModel(48, 8, 100, 1));
        controlPanel.add(new JLabel("Size:"));
        controlPanel.add(sizeSpinner);

        boldCheckBox = new JCheckBox("Bold");
        controlPanel.add(boldCheckBox);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ApplyButtonListener());
        controlPanel.add(applyButton);

        add(controlPanel, BorderLayout.NORTH);

        textPanel = new TextPanel(fontManager);
        textPanel.setPreferredSize(new Dimension(800, 600));
        add(textPanel, BorderLayout.CENTER);
    }

    private class ApplyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fontName = (String) fontComboBox.getSelectedItem();
            int fontSize = (int) sizeSpinner.getValue();
            int fontStyle = boldCheckBox.isSelected() ? Font.BOLD : Font.PLAIN;
            textPanel.updateFont(fontName, fontStyle, fontSize);
        }
    }
}

class TextPanel extends JPanel {
    private FontManager fontManager;
    private String fontName = "Roboto";
    private int fontStyle = Font.PLAIN;
    private int fontSize = 48;

    public TextPanel(FontManager fontManager) {
        this.fontManager = fontManager;
    }

    public void updateFont(String fontName, int fontStyle, int fontSize) {
        this.fontName = fontName;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Font font = fontManager.getFont(fontName, fontStyle, fontSize);
        g2d.setFont(font);
        g2d.drawString("Hello Test TEST Na", 100, 100);
    }
}
