package gui;

import javax.swing.ImageIcon;
import java.lang.String;
import java.lang.System;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class BildBauer {

    public ImageIcon createImageIcon(String pfad) {
        java.net.URL imgURL = getClass().getResource(pfad);
        if (imgURL != null) {
            return new ImageIcon(imgURL, "");
        } else {
            System.out.println("Konnte " + pfad + " nicht finden.");
            return null;
        }
    }

}