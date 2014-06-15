package gui;

import Datenbank.LeseHighscore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.util.ArrayList;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class MultiplayerFenster implements KeyListener, ActionListener {

    int count;
    int i = 1;
    int anzahlSpieler;
    String nameBlau;
    Timer timer;
    JFrame multiplayerFenster;
    JLabel time;
    MeinLabel frage;
    MeinLabel buzzer;
    MeinLabel spielerZwei;
    MeinLabel spielerDrei;
    BildButton zurueck;
    BildButton antwortEins;
    BildButton antwortZwei;
    BildButton antwortDrei;
    BildButton antwortVier;
    ArrayList<String> liste;

    public MultiplayerFenster() {

        //Highscore aus der Datenbank
        LeseHighscore daten = new LeseHighscore();
        ArrayList<String[]> stringListe = daten.getB();

        liste = new ArrayList<String>();

        //Die übergebene Datenbank in 2 ArrayListen unterbringen: Name, Punkte
        for (String[] pair : stringListe) {
            liste.add(pair[0] + "/" + pair[1]);
        }

        //Fenster für den Multiplayer
        multiplayerFenster = new JFrame();
        multiplayerFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Spieleranzahl + Namen
        String anzahlSpielerString = JOptionPane.showInputDialog("Anzahl an Spielern eingeben (2-3):");
        anzahlSpieler = Integer.parseInt(anzahlSpielerString);
        String nameRosa = JOptionPane.showInputDialog("Spieler 1");
        String nameGruen = JOptionPane.showInputDialog("Spieler 2");
        if (anzahlSpieler == 3) {
            nameBlau = JOptionPane.showInputDialog("Spieler 3");
        }

        //Hintergrundbild
        BilderPanel multiplayerBg = new BilderPanel("/Img/multiplayerBg.png");

        //Fragepanel im Norden für den Timer, die Lösung der Frage und der Frage
        JPanel multiplayerPanel = new JPanel(new GridBagLayout());
        multiplayerPanel.setOpaque(false);

        //Felder für den Timer und der Frage
        time = new JLabel("00:00");
        frage = new MeinLabel(new BildBauer().createImageIcon("/Img/frageLabel.png"), "Question 1");
        zurueck = new BildButton(new BildBauer().createImageIcon("/Img/cancelButton.png"));

        //Buttons für die Antworten
        ImageIcon antwort = new BildBauer().createImageIcon("/Img/antwortenLabel.png");
        antwortEins = new BildButton(antwort, "Answer 1");
        antwortZwei = new BildButton(antwort, "Answer 1");
        antwortDrei = new BildButton(antwort, "Answer 1");
        antwortVier = new BildButton(antwort, "Answer 1");

        //Buzzer
        buzzer = new MeinLabel(new BildBauer().createImageIcon("/Img/buzzerRotLabel.png"));

        //Felder für die 3 Spieler + Punktestand
        MeinLabel spielerEins = new MeinLabel(new BildBauer().createImageIcon("/Img/spielerRosaLabel.png"), nameRosa);
        if (anzahlSpieler == 3) {
            spielerZwei = new MeinLabel(new BildBauer().createImageIcon("/Img/spielerGruenLabel.png"), nameGruen);
            spielerDrei = new MeinLabel(new BildBauer().createImageIcon("/Img/spielerBlauLabel.png"), nameBlau);
        } else {
            spielerDrei = new MeinLabel(new BildBauer().createImageIcon("/Img/spielerGruenLabel.png"), nameGruen);
        }


        ImageIcon spielerPunkteIcon = new BildBauer().createImageIcon("/Img/punkteLabel.png");
        MeinLabel spielerEinsPunkte = new MeinLabel(spielerPunkteIcon);
        MeinLabel spielerZweiPunkte = new MeinLabel(spielerPunkteIcon);
        MeinLabel spielerDreiPunkte = new MeinLabel(spielerPunkteIcon);

        //Timer zu Beginn der Abfrage
        resettedTimer();

        //KeyListener
        multiplayerFenster.setFocusable(true);
        multiplayerFenster.addKeyListener(this);

        //Timer und Frage dem multiplayerPanel hinzufügen
        multiplayerPanel.add(time, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-35, 0, 0, 215), 0, 0));
        multiplayerPanel.add(zurueck, new GridBagConstraints(1, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 350, 0, 0), 0, 0));
        multiplayerPanel.add(frage, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));

        //Antworten und Buzzer dem multiplayerPanel hinzufügen
        multiplayerPanel.add(antwortEins, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortZwei, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortDrei, new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortVier, new GridBagConstraints(0, 5, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(buzzer, new GridBagConstraints(0, 6, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        //Spieler und Punkte dem multiplayerPanel hinzufügen
        multiplayerPanel.add(spielerEins, new GridBagConstraints(0, 7, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 300), 0, 0));
        multiplayerPanel.add(spielerEinsPunkte, new GridBagConstraints(0, 8, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 300), 0, 0));
        if (anzahlSpieler == 3) {
            multiplayerPanel.add(spielerZwei, new GridBagConstraints(1, 7, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
            multiplayerPanel.add(spielerZweiPunkte, new GridBagConstraints(1, 8, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
        }
        multiplayerPanel.add(spielerDrei, new GridBagConstraints(2, 7, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 300, 0, 0), 0, 0));
        multiplayerPanel.add(spielerDreiPunkte, new GridBagConstraints(2, 8, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 300, 0, 0), 0, 0));


        multiplayerBg.add(multiplayerPanel);

        //Komponenten zum multiplayerFenster hinzufügen
        multiplayerFenster.add(multiplayerBg);

        //Fenstergröße setzen und anzeigen lassen
        multiplayerFenster.setSize(415, 400);
        multiplayerFenster.setLocationRelativeTo(null);
        multiplayerFenster.setResizable(false);
        multiplayerFenster.setVisible(true);
    }

    public void naechsteFrage() {
        i++;

        multiplayerFenster.addKeyListener(this);
        buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerRotLabel.png"));
        resettedTimer();

        frage.setText("Question " + i);
        antwortEins.setText("Answer " + i);
        antwortZwei.setText("Answer " + i);
        antwortDrei.setText("Answer " + i);
        antwortVier.setText("Answer " + i);
    }

    public void resettedTimer() {
        count = 21;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Zieht immer eine Sekunde ab
                count--;

                if(count < 10) {
                    time.setText("00:0" + String.valueOf(count));
                } else {
                    time.setText("00:" + String.valueOf(count));
                }

                //färbt die Schriftfarbe rot ab 5sec abwärts
                if(count <= 5) {
                    time.setForeground(Color.RED);

                    //Musik
                    new Musik("src/Img/klick.wav").start();
                } else {
                    time.setForeground(Color.BLACK);
                }

                //Wenn Zeit abgelaufen, dann nächste Frage
                if (count == 0) {
                    System.out.println("Jetzt sollte die nächste Frage erscheinen");
                    timer.stop();
                    naechsteFrage();
                }
            }
        });

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Überprüft, welcher Button gedrückt wurde
        if (e.getSource() == this.zurueck) {
            new MenuFenster(false, null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //Timer resetten
        timer.stop();
        resettedTimer();

        //Musik
        new Musik("src/Img/buzzer.wav").start();

        if (e.getKeyCode() == 83) {
            buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerRosaLabel.png"));
            multiplayerFenster.removeKeyListener(this);
        }

        if (anzahlSpieler == 3) {
            if (e.getKeyCode() == 66) {
                buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerGruenLabel.png"));
                multiplayerFenster.removeKeyListener(this);
                new HSFenster(liste);
            }

            if (e.getKeyCode() == 76) {
                buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerBlauLabel.png"));
                multiplayerFenster.removeKeyListener(this);
            }
        } else {
            if (e.getKeyCode() == 76) {
                buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerGruenLabel.png"));
                multiplayerFenster.removeKeyListener(this);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
