package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class EngDeFenster {

    Timer timer;
    int count;
    RoundedTextField eingabe;
    RoundedTextField ausgabe;
    BildButton weiter;

    public EngDeFenster() {

        //Fenster für die Katalogwahl
        final JFrame engDeFenster = new JFrame("Englisch/Deutsch");
        engDeFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel engDeBg = new BilderPanel("Img/engDeBg.png");

        //gui.BilderPanel
        JPanel engDePanel = new JPanel(new GridBagLayout());
        engDePanel.setOpaque(false);

        //Zwischenstandlabel
        ImageIcon zwischenstandIcon = new BildBauer().createImageIcon("Img/zwischenstandLabel.png");
        MeinLabel zwischenstand = new MeinLabel(zwischenstandIcon, false);

        //Timer
        count = 10;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Zieht immer eine Sekunde ab
                count--;

                if (count == 0) {
                    eingabe.setEditable(false);
                    eingabe.setFocusable(false);
                    ausgabe.setBackground(new Color(205, 38, 38));
                    ausgabe.setText("hihi");
                    next();
                }

            }
        });

        timer.start();

        //Textfelder zur Eingabe und Ausgabe der Lösungen
        eingabe = new RoundedTextField(12);
        ausgabe = new RoundedTextField(12);
        ausgabe.setEditable(false);

        //Buttons werden hier erstellt
        ImageIcon zurueckIcon = new BildBauer().createImageIcon("Img/zurueckKleinButton.png");
        BildButton zurueck = new BildButton(zurueckIcon);

        ImageIcon OkIcon = new BildBauer().createImageIcon("Img/okButton.png");
        BildButton Ok = new BildButton(OkIcon);

        ImageIcon weiterIcon = new BildBauer().createImageIcon("Img/weiterButton.png");
        weiter = new BildButton(weiterIcon);

        zurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engDeFenster.setVisible(false);
                engDeFenster.dispose();
                new KatalogwahlFenster();
            }
        });

        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (eingabe.getText().equals("Super")) {
                    ausgabe.setBackground(new Color(180, 238, 180));
                } else {
                    ausgabe.setBackground(new Color(205, 38, 38));
                }

                eingabe.setEditable(false);
                timer.stop();
                next();

            }
        });

        //hier werden alle Elemente dem engDePanel hinzugefügt
        engDePanel.add(zwischenstand, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(72, 0, 0, 0), 0, 0));
        engDePanel.add(eingabe, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(25, 150, 0, 0), 0, 0));
        engDePanel.add(ausgabe, new GridBagConstraints(1, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(94, 150, 0, 0), 0, 0));
        engDePanel.add(zurueck, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 200), 0, 0));
        engDePanel.add(Ok, new GridBagConstraints(1, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 0), 0, 0));
        engDePanel.add(weiter, new GridBagConstraints(2, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 200, 0, 0), 0, 0));

        engDeBg.add(engDePanel);

        //Komponenten zum Fenster hinzufügen
        engDeFenster.add(engDeBg);

        //Fenstergröße setzen und anzeigen lassen
        engDeFenster.setSize(415, 400);
        engDeFenster.setLocationRelativeTo(null);
        engDeFenster.setResizable(false);
        engDeFenster.setVisible(true);

    }

    public static void main(String[] a) {

        new EngDeFenster();

    }

    public void next() {

        weiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eingabe.setEditable(true);
                eingabe.setText("");
                eingabe.setFocusable(true);
                ausgabe.setBackground(Color.LIGHT_GRAY);
                ausgabe.setText("");
                count = 8;
                timer.start();
            }
        });

    }

}
