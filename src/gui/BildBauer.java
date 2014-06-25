package gui;

import javax.swing.ImageIcon;
import java.lang.String;
import java.lang.System;

/**
 * In dieser Klasse wird ein ImageIcon gebaut, welches wir dann in anderen Klassen als Methode übergeben können.
 */

public class BildBauer {
    /**
     * @param pfad der Parameter gibt an das man bei dieser Methode einen String übergeben muss
     * @return Es soll eine URL zurückgegeben werden diese Methode haben wir z.B.
     * wird zum Beispiel hier hinzugefügt {@link gui.KatalogwahlFenster}
     */
    public ImageIcon createImageIcon(String pfad) {
        java.net.URL imgURL = getClass().getResource("../" + pfad);
        if (imgURL != null) {
            return new ImageIcon(imgURL, "");
        } else {
            System.out.println("Konnte " + pfad + " nicht finden.");
            return null;
        }
    }

}