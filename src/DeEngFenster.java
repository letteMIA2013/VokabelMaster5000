import javax.swing.*;
import java.awt.*;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class DeEngFenster {

    public DeEngFenster() {

        //Fenster für die Katalogwahl
        JFrame deEngFenster = new JFrame("Deutsch/Englisch");
        deEngFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel deEngBg = new BilderPanel("Img/deEngBg.png");

        //BilderPanel
        JPanel deEngPanel = new JPanel(new GridBagLayout());
        deEngPanel.setOpaque(false);

        //Zwischenstandlabel
        ImageIcon zwischenstandIcon = new BildBauer().createImageIcon("Img/zwischenstandLabel.png");
        MeinLabel zwischenstand = new MeinLabel(zwischenstandIcon,false);

        //Buttons werden hier erstellt
        ImageIcon zurueckIcon = new BildBauer().createImageIcon("Img/zurueckKleinButton.png");
        BildButton zurueck = new BildButton(zurueckIcon);

        ImageIcon OkIcon = new BildBauer().createImageIcon("Img/okButton.png");
        BildButton Ok = new BildButton(OkIcon);

        ImageIcon weiterIcon = new BildBauer().createImageIcon("Img/weiterButton.png");
        BildButton weiter = new BildButton(weiterIcon);

        //hier werden alle Elemente dem deEngPanel hinzugefügt
        deEngPanel.add(zwischenstand, new GridBagConstraints(0,0,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(75,0,0,0),0,0));
        deEngPanel.add(zurueck, new GridBagConstraints(0,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(150,0,0,200),0,0));
        deEngPanel.add(Ok, new GridBagConstraints(1,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(150,0,0,0),0,0));
        deEngPanel.add(weiter, new GridBagConstraints(2,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(150,200,0,0),0,0));

        deEngBg.add(deEngPanel);

        //Komponenten zum Fenster hinzufügen
        deEngFenster.add(deEngBg);

        //Fenstergröße setzen und anzeigen lassen
        deEngFenster.setSize(415,400);
        deEngFenster.setResizable(false);
        deEngFenster.setVisible(true);

    }

    public static void main(String[] a) {

        new DeEngFenster();

    }

}
