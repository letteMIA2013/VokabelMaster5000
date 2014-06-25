package gui;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ka Yan Lam
 * on 15 Jun 2014
 * VokabelMaster5000
 */

/**
 * In dieser Klasse wird das Fenster Credits erstellt wo man dann erkennen soll, wer was gemacht hat.
 */
public class CreditsFenster implements ActionListener {

    SpeicherVokabelnLernen speicherVokabelnLernen;
    JFrame creditsFenster;
    BildButton zurueck;

    /**
     * Im Konstruktor wird die Methode fensterAnzeige() benutzt, damit das Credit Fenster beim Aufrufen dieser Klasse
     * erstellt wird. Diese Klasse wird in der Klasse {@link gui.MenuFenster} benutzt.
     */
    public CreditsFenster() {
        fensterAnzeige();
    }

    /**
     * @param speicherVokabelnLernen diesen braucht man damit in der Klasse SpeicherVokabelnLernen
     *                               die Daten abgelegt werdem
     *
     */
    public CreditsFenster(SpeicherVokabelnLernen speicherVokabelnLernen) {
        this.speicherVokabelnLernen = speicherVokabelnLernen;
        fensterAnzeige();
    }

    /**
     * hier wird das Fenster gebaut, wo man die Größe festlegt und es sichtbar macht.
     * Dem Hintergrund wird auch ein Bild hinzugefügt.
     * Im Menupanel haben wir die Button hinzugefügt
     */
    public void fensterAnzeige() {

        //Fenster für das Menue
        creditsFenster = new JFrame("Credits");
        creditsFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel creditsBg = new BilderPanel("/Img/creditsBg.png");

        //Menupanel für die Buttons
        JPanel creditsPanel = new JPanel(new GridBagLayout());
        creditsPanel.setOpaque(false);

        //Buttons
        zurueck = new BildButton(new BildBauer().createImageIcon("/Img/zurueckGrossButton.png"));

        //ActionListener
        zurueck.addActionListener(this);

        //Buttons dem Panel hinzufügen
        creditsPanel.add(zurueck, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(280, 0, 0, 0), 0, 0));

        creditsBg.add(creditsPanel);

        //Komponenten zum Fenster hinzufügen
        creditsFenster.add(creditsBg);

        //Fenstergröße setzen und anzeigen lassen
        creditsFenster.setSize(405, 400);
        creditsFenster.setLocationRelativeTo(null);
        creditsFenster.setResizable(false);
        creditsFenster.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Musik
        new Musik("src/sound/klick.wav").start();

        creditsFenster.setVisible(false);
        creditsFenster.dispose();
        if (speicherVokabelnLernen != null) {
            new MenuFenster(true, speicherVokabelnLernen);
        } else {
            new MenuFenster(false, null);
        }
    }

}