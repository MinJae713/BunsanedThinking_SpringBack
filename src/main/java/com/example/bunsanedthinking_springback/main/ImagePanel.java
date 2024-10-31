package com.example.bunsanedthinking_springback.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private BufferedImage image;

    public ImagePanel(Image im) {
    	image = new BufferedImage(im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
    	Graphics bg = image.getGraphics();
    	bg.drawImage(im, 0, 0, null);
    	bg.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
