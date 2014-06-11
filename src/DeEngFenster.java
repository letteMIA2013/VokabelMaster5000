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

        //VM5000.BilderPanel
        JPanel katalogPanel = new JPanel(new GridBagLayout());
        katalogPanel.setOpaque(false);

        //Buttons werden hier erstellt
        ImageIcon deutschEnglischIcon = new BildBauer().createImageIcon("Img/deEngButton.png");
        DeinButton deutschEnglisch = new DeinButton(deutschEnglischIcon);

        ImageIcon englischDeutschIcon = new BildBauer().createImageIcon("Img/engDeButton.png");
        DeinButton englischDeutsch = new DeinButton(englischDeutschIcon);

        ImageIcon zurueckIcon = new BildBauer().createImageIcon("Img/zurueckGrossButton.png");
        DeinButton zurueck = new DeinButton(zurueckIcon);

        //hier werden alle Elemente dem katalogPanel hinzugefügt
        katalogPanel.add(deutschEnglisch,new GridBagConstraints(0,0,0,1,1,1,GridBagConstraints.PAGE_START,GridBagConstraints.NONE,new Insets(120,0,0,0),0,0));
        katalogPanel.add(englischDeutsch,new GridBagConstraints(0,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        katalogPanel.add(zurueck,new GridBagConstraints(0,2,0,1,1,1,GridBagConstraints.PAGE_END,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        deEngBg.add(katalogPanel);

        //Komponenten zum Fenster hinzufügen
        deEngFenster.add(deEngBg);

        //Fenstergröße setzen und anzeigen lassen
        deEngFenster.setSize(415, 400);
        deEngFenster.setResizable(false);
        deEngFenster.setVisible(true);

    }

    public static void main(String[] a) {

        new DeEngFenster();

    }

}
