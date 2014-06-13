package gui;

import datenbank.LeseBenutzerdaten;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class DeEngFenster {

    int count;
    int zahlZwischenstand = 0;
    int zufallsVokabel;
    int fragePos;
    int antwortPos;
    Timer timer;
    JLabel vokabel;
    RoundedTextField eingabe;
    RoundedTextField ausgabe;
    BildButton ok;
    BildButton weiter;
    MeinLabel zwischenstand;
    ArrayList<String[]> stringListe;
    ArrayList<String> listeFrage;
    ArrayList<String> listeAntwort;
    ActionListener weiterListener;
    ActionListener okListener;

    public DeEngFenster() {

        //Vokabeln aus der Datenbank
        LeseBenutzerdaten daten = new LeseBenutzerdaten();
        listeFrage = new ArrayList<String>();
        listeAntwort = new ArrayList<String>();
        stringListe = daten.getB();

        //Die übergebene Datenbank in 2 ArrayListen unterbringen: Fragen, Antworten
        for (String[] pair : stringListe) {
            listeFrage.add(pair[0]);
            listeAntwort.add(pair[1]);
        }

        //Fenster für die Katalogwahl
        final JFrame deEngFenster = new JFrame("Deutsch/Englisch");
        deEngFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel deEngBg = new BilderPanel("/img/deEngBg.png");

        //BilderPanel
        JPanel deEngPanel = new JPanel(new GridBagLayout());
        deEngPanel.setOpaque(false);

        //Zwischenstandlabel
        zwischenstand = new MeinLabel(new BildBauer().createImageIcon("/img/zwischenstandLabel.png"), zahlZwischenstand + " / 10");

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
                    ausgabe.setBackground(new Color(255, 80, 74));
                    ausgabe.setText("" + listeAntwort.get(getFragePos()));
                    listeFrage.remove(zufallsVokabel);
                    listeAntwort.remove(zufallsVokabel);
                    weiter.addActionListener(weiterListener);
                    ok.removeActionListener(okListener);
                }

            }
        });

        timer.start();

        //(Text)felder für die Vokabelabfrage, Eingabe und Ausgabe der Lösungen
        vokabel = new JLabel();
        vokabel.setFont(new Font("Trebuchet MS", Font.ITALIC, 12));
        ausgabe = new RoundedTextField(12);
        eingabe = new RoundedTextField(12);
        ausgabe.setEditable(false);

        //Für eine zufällig ausgewählte Vokabel aus der Fragen-ArrayListe
        zufallsVokabel = new Random().nextInt(listeFrage.size());
        vokabel.setText("" + listeFrage.get(zufallsVokabel));

        //Buttons werden hier erstellt
        BildButton zurueck = new BildButton(new BildBauer().createImageIcon("/img/zurueckKleinButton.png"));
        ok = new BildButton(new BildBauer().createImageIcon("/img/okButton.png"));
        weiter = new BildButton(new BildBauer().createImageIcon("/img/weiterButton.png"));

        //hier werden alle Elemente dem deEngPanel hinzugefügt
        deEngPanel.add(zwischenstand, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(72, 0, 0, 0), 0, 0));
        deEngPanel.add(vokabel, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(62, 0, 0, 170), 0, 0));
        deEngPanel.add(eingabe, new GridBagConstraints(1, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(59, 150, 0, 0), 0, 0));
        deEngPanel.add(ausgabe, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(16, 150, 0, 0), 0, 0));
        deEngPanel.add(zurueck, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 200), 0, 0));
        deEngPanel.add(ok, new GridBagConstraints(1, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 0), 0, 0));
        deEngPanel.add(weiter, new GridBagConstraints(2, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 200, 0, 0), 0, 0));

        //ActionListener
        okListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok.removeActionListener(okListener);

                eingabe.setEditable(false);
                timer.stop();

                if (eingabe.getText().length() == 0) {
                    ausgabe.setBackground(new Color(255, 80, 74));
                    ausgabe.setText("" + listeAntwort.get(getFragePos()));

                    //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                    listeFrage.remove(zufallsVokabel);
                    listeAntwort.remove(zufallsVokabel);
                }
                //Vergleich der Positionen in der jeweiligen ArrayListe: Vokabelabfrage, Eingabe
                //2 Getter-Methoden weiter unten
                else if (getFragePos() == getAntwortPos()) {
                    ausgabe.setBackground(new Color(180, 238, 180));
                    ausgabe.setText("Richtig!");

                    //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                    listeFrage.remove(zufallsVokabel);
                    listeAntwort.remove(zufallsVokabel);
                } else {
                    ausgabe.setBackground(new Color(255, 80, 74));
                    ausgabe.setText("" + listeAntwort.get(getFragePos()));

                    //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                    listeFrage.remove(zufallsVokabel);
                    listeAntwort.remove(zufallsVokabel);
                }

                //Weiter-Button wird bei der letzten Abfrage zum AuswertungsButton -> führt zur Statistik
                if (listeFrage.size() == 0) {
                    weiter.setIcon(new BildBauer().createImageIcon("/img/auswertungButton.png"));
                } else {
                    weiter.addActionListener(weiterListener);
                }
            }
        };

        ok.addActionListener(okListener);

        weiterListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weiter.removeActionListener(weiterListener);

                //Zwischenstand wird um 1 aufaddiert
                zahlZwischenstand++;
                zwischenstand.setText(zahlZwischenstand + " / 12");

                eingabe.setEditable(true);
                eingabe.setText("");
                eingabe.setFocusable(true);
                ausgabe.setBackground(Color.LIGHT_GRAY);
                ausgabe.setText("");
                count = 8;
                timer.start();

                nextVokabel();

                if (listeFrage.size() > 0) {
                    ok.addActionListener(okListener);
                }
            }
        };

        zurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deEngFenster.setVisible(false);
                deEngFenster.dispose();
                new KatalogwahlFenster();
            }
        });

        getFragePos();

        deEngBg.add(deEngPanel);

        //Komponenten zum Fenster hinzufügen
        deEngFenster.add(deEngBg);

        //Fenstergröße setzen und anzeigen lassen
        deEngFenster.setSize(415, 400);
        deEngFenster.setLocationRelativeTo(null);
        deEngFenster.setResizable(false);
        deEngFenster.setVisible(true);

    }

    public static void main(String[] a) {

        new DeEngFenster();
        new Musik("src/img/klick.wav").start();

    }

    public void nextVokabel() {

        zufallsVokabel = new Random().nextInt(listeFrage.size());
        vokabel.setText("" + listeFrage.get(zufallsVokabel));

        /*for (int i = 0; i < listeFrage.size(); i++) {
            System.out.println("" + listeFrage.get(i));
        }
        System.out.println("-------------------------");*/

    }

    public int getFragePos() {

        for (String frage : listeFrage) {
            if (vokabel.getText().equals(frage)) {
                fragePos = listeFrage.indexOf(frage);
            }
        }

        return fragePos;

    }

    public int getAntwortPos() {

        for (String antwort : listeAntwort) {
            if (eingabe.getText().equals(antwort)) {
                antwortPos = listeAntwort.indexOf(antwort);
            }
        }

        return antwortPos;

    }

}
