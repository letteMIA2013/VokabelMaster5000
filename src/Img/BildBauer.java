package Img;

import javax.swing.ImageIcon;
import java.lang.String;
import java.lang.System;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class BildBauer {
    /**
     * @param pfad der Parameter gibt an das man bei dieser Methode einen String übergeben muss
     * @return Es soll eine URL zurück gegeben werden diese Methode haben wir z.B.
     * hier hinzugefügt {@link gui.KatalogwahlFenster}
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