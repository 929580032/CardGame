package com.xwh.game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BackgroundImage2 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        URL headerUrl = BackgroundImage.class.getResource("/c.jpg");
        ImageIcon imageIcon = new ImageIcon(headerUrl);
        imageIcon.paintIcon(this, g, 0, 0);
    }
}
