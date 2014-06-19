package gui;

import Datenbank.LeseDeEngVok;
import Datenbank.LeseHighscore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class MultiplayerFenster implements KeyListener, ActionListener {

    int count;
    int countPause;
    int anzahlSpieler;
    int zufallsVokabel;
    int zufallsAntwort;
    int zufallsPos;
    int punkteRosa = 0;
    int punkteGruen = 0;
    int punkteBlau = 0;
    int gedruecktPos;
    boolean buzzRosa;
    boolean buzzGruen;
    boolean buzzBlau;
    boolean buttonEins;
    boolean buttonZwei;
    boolean buttonDrei;
    boolean buttonVier;
    String nameBlau;
    Timer timer;
    Timer pause;
    JFrame multiplayerFenster;
    JLabel time;
    MeinLabel frage;
    MeinLabel buzzer;
    MeinLabel spielerZwei;
    MeinLabel spielerDrei;
    MeinLabel spielerEinsPunkte;
    MeinLabel spielerZweiPunkte;
    MeinLabel spielerDreiPunkte;
    BildButton zurueck;
    ArrayList<BildButton> buttons;
    ArrayList<String> listeFrage;
    ArrayList<String> listeAntwort;
    ArrayList<String> liste;

    public MultiplayerFenster() {
        highscore();
        fragenKatalog();

        //Fenster für den Multiplayer
        multiplayerFenster = new JFrame();
        multiplayerFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Spieleranzahl + Namen
        // Erstellung Array vom Datentyp Object, Hinzufügen der Optionen
        Object[] options = {"2 Spieler", "3 Spieler"};

        //Bei 2 Spielern = 0   ;   Bei 3 Spielern = 1
        anzahlSpieler = JOptionPane.showOptionDialog(null, "Wie viele Spieler?", "Quiz", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String nameRosa = JOptionPane.showInputDialog("Spieler 1");
        String nameGruen = JOptionPane.showInputDialog("Spieler 2");
        if (anzahlSpieler == 1) {
            nameBlau = JOptionPane.showInputDialog("Spieler 3");
        }

        //Hintergrundbild
        BilderPanel multiplayerBg = new BilderPanel("/Img/multiplayerBg.png");

        //Fragepanel im Norden für den Timer, die Lösung der Frage und der Frage
        JPanel multiplayerPanel = new JPanel(new GridBagLayout());
        multiplayerPanel.setOpaque(false);

        //Felder für den Timer und der Frage
        time = new JLabel("00:00");
        frage = new MeinLabel(new BildBauer().createImageIcon("/Img/frageLabel.png"));
        zurueck = new BildButton(new BildBauer().createImageIcon("/Img/cancelButton.png"));

        //Buttons für die Antworten in die Buttons-ArrayListe einfügen
        buttons = new ArrayList<BildButton>();
        buttons.add(new BildButton(new BildBauer().createImageIcon("/Img/antwortenButton.png")));
        buttons.add(new BildButton(new BildBauer().createImageIcon("/Img/antwortenButton.png")));
        buttons.add(new BildButton(new BildBauer().createImageIcon("/Img/antwortenButton.png")));
        buttons.add(new BildButton(new BildBauer().createImageIcon("/Img/antwortenButton.png")));

        //Buzzer
        buzzer = new MeinLabel(new BildBauer().createImageIcon("/Img/buzzerRotLabel.png"));

        naechsteFrage();

        //Felder für die 3 Spieler + Punktestand
        MeinLabel spielerEins = new MeinLabel(new BildBauer().createImageIcon("/Img/spielerRosaLabel.png"), nameRosa);
        if (anzahlSpieler == 1) {
            spielerZwei = new MeinLabel(new BildBauer().createImageIcon("/Img/spielerGruenLabel.png"), nameGruen);
            spielerDrei = new MeinLabel(new BildBauer().createImageIcon("/Img/spielerBlauLabel.png"), nameBlau);
        } else {
            spielerDrei = new MeinLabel(new BildBauer().createImageIcon("/Img/spielerGruenLabel.png"), nameGruen);
        }

        ImageIcon spielerPunkteIcon = new BildBauer().createImageIcon("/Img/punkteLabel.png");
        spielerEinsPunkte = new MeinLabel(spielerPunkteIcon, "" + punkteRosa);
        if (anzahlSpieler == 1) {
            spielerZweiPunkte = new MeinLabel(spielerPunkteIcon, "" + punkteGruen);
            spielerDreiPunkte = new MeinLabel(spielerPunkteIcon, "" + punkteBlau);
        } else {
            spielerDreiPunkte = new MeinLabel(spielerPunkteIcon, "" + punkteGruen);
        }

        //ActionListener
        zurueck.addActionListener(this);

        //KeyListener
        multiplayerFenster.setFocusable(true);
        multiplayerFenster.addKeyListener(this);

        //Timer und Frage dem multiplayerPanel hinzufügen
        multiplayerPanel.add(time, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-35, 0, 0, 215), 0, 0));
        multiplayerPanel.add(zurueck, new GridBagConstraints(1, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 350, 0, 0), 0, 0));
        multiplayerPanel.add(frage, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));

        //Antworten und Buzzer dem multiplayerPanel hinzufügen
        multiplayerPanel.add(buttons.get(0), new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        multiplayerPanel.add(buttons.get(1), new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(buttons.get(2), new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(buttons.get(3), new GridBagConstraints(0, 5, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(buzzer, new GridBagConstraints(0, 6, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        //Spieler und Punkte dem multiplayerPanel hinzufügen
        multiplayerPanel.add(spielerEins, new GridBagConstraints(0, 7, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 300), 0, 0));
        multiplayerPanel.add(spielerEinsPunkte, new GridBagConstraints(0, 8, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 300), 0, 0));
        if (anzahlSpieler == 1) {
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

//    public static void main(String[] a) {
//        new MultiplayerFenster();
//    }

    public int getFragePos() {
        int fragePos = 0;

        //Position der Vokabel in der ArrayList suchen und als Variable abspeichern
        for (String question : listeFrage) {
            if (frage.getText().equals(question)) {
                fragePos = listeFrage.indexOf(question);
            }
        }

        return fragePos;
    }

    public void naechsteFrage() {
        multiplayerFenster.setFocusable(true);
        multiplayerFenster.addKeyListener(this);

        buttons.get(0).setText("");
        buttons.get(1).setText("");
        buttons.get(2).setText("");
        buttons.get(3).setText("");
        buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerRotLabel.png"));
        resettedTimer();

        //zufällige Fragen
        zufallsVokabel = new Random().nextInt(listeFrage.size());
        frage.setText("" + listeFrage.get(zufallsVokabel));

        //zufällige Antworten: 3 falsche und 1 richtige
        zufallsPos = new Random().nextInt(4);

        //Zufallsposition der richtigen Lösung
        switch(zufallsPos) {
            case 0: buttons.get(0).setText("" + listeAntwort.get(getFragePos()));
                break;
            case 1: buttons.get(1).setText("" + listeAntwort.get(getFragePos()));
                break;
            case 2: buttons.get(2).setText("" + listeAntwort.get(getFragePos()));
                break;
            case 3: buttons.get(3).setText("" + listeAntwort.get(getFragePos()));
        }

        //restliche Buttons mit Zufallsantworten füllen
        //Wenn deutsche Vokabelabfrage, dann englische Antworten und andersherum
        for (BildButton b : buttons) {
            b.setFocusable(false);
            b.setIcon(new BildBauer().createImageIcon("/Img/antwortenButton.png"));

            if (b.getText().length() == 0) {
                if (getFragePos() < 86) {
                    zufallsAntwort = new Random().nextInt(85);
                    while (zufallsAntwort == zufallsVokabel) {
                        zufallsAntwort = new Random().nextInt(85);
                    }
                } else {
                    zufallsAntwort = new Random().nextInt(85) + 86;
                    while (zufallsAntwort == zufallsVokabel) {
                        zufallsAntwort = new Random().nextInt(85) + 86;
                    }
                }
                b.setText("" + listeAntwort.get(zufallsAntwort));
            }
        }

    }

    public void highscore() {

        //Highscore aus der Datenbank
        LeseHighscore daten = new LeseHighscore();
        ArrayList<String[]> stringListe = daten.getB();

        liste = new ArrayList<String>();

        //Die übergebene Datenbank in 2 ArrayListen unterbringen: Name, Punkte
        for (String[] pair : stringListe) {
            liste.add(pair[0] + "/" + pair[1]);
        }
    }

    public void fragenKatalog() {

        //Vokabeln aus der Datenbank
        LeseDeEngVok daten = new LeseDeEngVok();
        ArrayList<String[]> stringListe = daten.getB();
        listeFrage = new ArrayList<String>();
        listeAntwort = new ArrayList<String>();

        //Die übergebene Datenbank in 2 ArrayListen unterbringen: Fragen, Antworten
        for (String[] pair : stringListe) {
            listeFrage.add(pair[0]);
            listeAntwort.add(pair[1]);
        }

        for (String[] pair : stringListe) {
            listeFrage.add(pair[1]);
            listeAntwort.add(pair[0]);
        }
    }

    public void kleinePauseTimer() {
        countPause = 3;
        pause = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Zieht immer eine Sekunde ab
                countPause--;

                //Wenn Zeit abgelaufen, dann nächste Frage
                if (countPause == 0) {
                    pause.stop();
                    naechsteFrage();
                }
            }
        });

        pause.start();
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

    public void buzzered() {
        multiplayerFenster.removeKeyListener(this);

        //Timer resetten
        timer.stop();
        resettedTimer();

        //Musik
        new Musik("src/Img/buzzer.wav").start();

        buttons.get(0).addActionListener(this);
        buttons.get(1).addActionListener(this);
        buttons.get(2).addActionListener(this);
        buttons.get(3).addActionListener(this);
    }

    public void deaktiviereButton() {
        buttons.get(0).removeActionListener(this);
        buttons.get(1).removeActionListener(this);
        buttons.get(2).removeActionListener(this);
        buttons.get(3).removeActionListener(this);
    }

    public int getGedruecktPos() {
        return gedruecktPos;
    }

    public void pruefeAntwort() {
        timer.stop();
        kleinePauseTimer();
        buttons.get(zufallsPos).setIcon(new BildBauer().createImageIcon("/Img/antwortenRichtigButton.png"));

        //Überprüft auf richtige Antwort
        if (getFragePos() == getGedruecktPos()) {
            if (buzzRosa) {
                punkteRosa++;
                buzzRosa = false;
                spielerEinsPunkte.setText("" + punkteRosa);
            }

            if (buzzGruen) {
                punkteGruen++;
                buzzGruen = false;
                if (anzahlSpieler == 1) {
                    spielerZweiPunkte.setText("" + punkteGruen);
                } else {
                    spielerDreiPunkte.setText("" + punkteGruen);
                }
            }

            if (buzzBlau) {
                punkteBlau++;
                buzzBlau = false;
                spielerDreiPunkte.setText("" + punkteBlau);
            }
        } else {
            if (buttonEins) {
                buttons.get(0).setIcon(new BildBauer().createImageIcon("/Img/antwortenFalschButton.png"));
            }

            if (buttonZwei) {
                buttons.get(1).setIcon(new BildBauer().createImageIcon("/Img/antwortenFalschButton.png"));
            }

            if (buttonDrei) {
                buttons.get(2).setIcon(new BildBauer().createImageIcon("/Img/antwortenFalschButton.png"));
            }

            if (buttonVier) {
                buttons.get(3).setIcon(new BildBauer().createImageIcon("/Img/antwortenFalschButton.png"));
            }
        }

        buttonEins = false;
        buttonZwei = false;
        buttonDrei = false;
        buttonVier = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Überprüft, welcher Button gedrückt wurde
        if (e.getSource() == this.zurueck) {
            timer.stop();
            multiplayerFenster.setVisible(false);
            multiplayerFenster.dispose();
            new MenuFenster(false, null);
        }

        deaktiviereButton();

        if (e.getSource() == buttons.get(0)) {
            buttonEins = true;
            for (String answer : listeAntwort) {
                if (buttons.get(0).getText().equals(answer)) {
                    gedruecktPos = listeAntwort.indexOf(answer);
                }
            }
        }

        if (e.getSource() == buttons.get(1)) {
            buttonZwei = true;
            for (String answer : listeAntwort) {
                if (buttons.get(1).getText().equals(answer)) {
                    gedruecktPos = listeAntwort.indexOf(answer);
                }
            }
        }

        if (e.getSource() == buttons.get(2)) {
            buttonDrei = true;
            for (String answer : listeAntwort) {
                if (buttons.get(2).getText().equals(answer)) {
                    gedruecktPos = listeAntwort.indexOf(answer);
                }
            }
        }

        if (e.getSource() == buttons.get(3)) {
            buttonVier = true;
            for (String answer : listeAntwort) {
                if (buttons.get(3).getText().equals(answer)) {
                    gedruecktPos = listeAntwort.indexOf(answer);
                }
            }
        }

        pruefeAntwort();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //Rosa Buzzer
        if (e.getKeyCode() == 83) {
            buzzRosa = true;
            buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerRosaLabel.png"));
            buzzered();
        }

        //Bei 3 Spielern
        if (anzahlSpieler == 1) {

            //Gruener Buzzer
            if (e.getKeyCode() == 66) {
                buzzGruen = true;
                buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerGruenLabel.png"));
                buzzered();
            }

            //Blauer Buzzer
            if (e.getKeyCode() == 76) {
                buzzBlau = true;
                buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerBlauLabel.png"));
                buzzered();
            }
        } else {

            //Gruener Buzzer
            if (e.getKeyCode() == 76) {
                buzzGruen = true;
                buzzer.setIcon(new BildBauer().createImageIcon("/Img/buzzerGruenLabel.png"));
                buzzered();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
