package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.Override;
import java.lang.String;
import java.lang.System;

/**
 * Diese Klasse erweitert {@link javax.swing.JPanel}.
 * Sie überschreibt die paintComponent, sodass man ein Bild als Hintergrund setzen kann.
 */
public class BilderPanel extends JPanel {

    private Image img;

    /**
     * Mit dem übergebenen String wird das Bild kreiert.
     * @param imgUrl der Pfad wird als String übergeben und daraus wird ein Image erstellt.
     */
    public BilderPanel(String imgUrl) {

        //load the background image
        img = createImageIcon(imgUrl).getImage();
    }

    /**
     * In dieser Methode wird überprüft, ob der angegebene Pfad vorhanden ist.
     * @param pfad der Parameter gibt an das man bei dieser Methode einen String übergeben muss
     * @return Es soll eine URL zurückgegeben werden. Diese Methode haben wir z.B.
     * hier hinzugefügt {@link gui.KatalogwahlFenster}
     */
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
