package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.Override;
import java.lang.String;
import java.lang.System;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class BilderPanel extends JPanel {
    private Image img;

    public BilderPanel(String imgUrl) {
        //load the background image
        img = createImageIcon(imgUrl).getImage();
    }


    public ImageIcon createImageIcon(String pfad) {
        java.net.URL imgURL = getClass().getResource(pfad);
        if (imgURL != null) {
            return new ImageIcon(imgURL, "");
        } else {
            System.out.println("Konnte " + pfad + " nicht finden.");
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint the background image and scale it to fill the entire space
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
