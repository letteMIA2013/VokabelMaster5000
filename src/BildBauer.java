import javax.swing.ImageIcon;
import java.lang.String;
import java.lang.System;

public class BildBauer {

    public ImageIcon createImageIcon(String pfad) {
        java.net.URL imgURL = getClass().getResource(pfad);
        if (imgURL != null) {
            return new ImageIcon(imgURL, "");
        }
        else {
            System.out.println("Konnte " + pfad + " nicht finden.");
            return null;
        }
    }

}