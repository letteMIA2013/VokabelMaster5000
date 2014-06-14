package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class DeEngFenster implements ActionListener{

    int zahlZwischenstand;
    int zufallsVokabel;
    SpeicherVokabelnLernen speicherVokabelnLernen;
    JFrame deEngFenster;
    JLabel vokabel;
    RoundedTextField eingabe;
    RoundedTextField ausgabe;
    BildButton zurueck;
    BildButton ok;
    BildButton weiter;
    MeinLabel zwischenstand;
    ArrayList<String> listeFrage;
    ArrayList<String> listeAntwort;
    Font font;

    public DeEngFenster(SpeicherVokabelnLernen s) {
        speicherVokabelnLernen = s;

        zahlZwischenstand = speicherVokabelnLernen.getZwSpDeEng();
        listeFrage = speicherVokabelnLernen.getFragenListeDeEng();
        listeAntwort = speicherVokabelnLernen.getAntwortenListeDeEng();

        //Fenster für die Katalogwahl
        deEngFenster = new JFrame("Deutsch/Englisch");
        deEngFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel deEngBg = new BilderPanel("/Img/deEngBg.png");

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("src/Img/Chalkduster.ttf"))).deriveFont(Font.PLAIN, 15);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //BilderPanel
        JPanel deEngPanel = new JPanel(new GridBagLayout());
        deEngPanel.setOpaque(false);

        //Zwischenstandlabel
        zwischenstand = new MeinLabel(new BildBauer().createImageIcon("/Img/zwischenstandLabel.png"), zahlZwischenstand + " / 10");

        //(Text)felder für die Vokabelabfrage, Eingabe und Ausgabe der Lösungen
        vokabel = new JLabel();
        vokabel.setFont(font);
        ausgabe = new RoundedTextField(12);
        eingabe = new RoundedTextField(12);
        ausgabe.setEditable(false);

        //Für eine zufällig ausgewählte Vokabel aus der Fragen-ArrayListe
        nextVokabel();

        //Buttons werden hier erstellt
        zurueck = new BildButton(new BildBauer().createImageIcon("/Img/zurueckKleinButton.png"));
        ok = new BildButton(new BildBauer().createImageIcon("/Img/okButton.png"));
        weiter = new BildButton(new BildBauer().createImageIcon("/Img/weiterButton.png"));

        //hier werden alle Elemente dem deEngPanel hinzugefügt
        deEngPanel.add(zwischenstand, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(72, 0, 0, 0), 0, 0));
        deEngPanel.add(vokabel, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(62, 0, 0, 170), 0, 0));
        deEngPanel.add(eingabe, new GridBagConstraints(1, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(59, 150, 0, 0), 0, 0));
        deEngPanel.add(ausgabe, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(16, 150, 0, 0), 0, 0));
        deEngPanel.add(zurueck, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 200), 0, 0));
        deEngPanel.add(ok, new GridBagConstraints(1, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 0), 0, 0));
        deEngPanel.add(weiter, new GridBagConstraints(2, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 200, 0, 0), 0, 0));

        //ActionListener
        zurueck.addActionListener(this);
        ok.addActionListener(this);

        deEngBg.add(deEngPanel);

        //Komponenten zum Fenster hinzufügen
        deEngFenster.add(deEngBg);

        //Fenstergröße setzen und anzeigen lassen
        deEngFenster.setSize(415, 400);
        deEngFenster.setLocationRelativeTo(null);
        deEngFenster.setResizable(false);
        deEngFenster.setVisible(true);
    }

    //public static void main(String[] a) { new DeEngFenster(); }

    public int getFragePos() {

        int fragePos = 0;

        //Position der Vokabel in der ArrayList suchen und als Variable abspeichern
        for (String frage : listeFrage) {
            if (vokabel.getText().equals(frage)) {
                fragePos = listeFrage.indexOf(frage);
            }
        }

        return fragePos;

    }

    public int getAntwortPos() {

        int antwortPos = 0;

        //Position der Antwort in der ArrayList suchen und als Variable abspeichern
        for (String antwort : listeAntwort) {
            if (eingabe.getText().equals(antwort)) {
                antwortPos = listeAntwort.indexOf(antwort);
            }
        }

        return antwortPos;

    }

    public void nextVokabel() {

        zufallsVokabel = new Random().nextInt(listeFrage.size());
        vokabel.setText("" + listeFrage.get(zufallsVokabel));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Überprüft, welcher Button gedrückt wurde
        if (e.getSource() == this.zurueck) {

            //Musik
            new Musik("src/Img/klick.wav").start();

            deEngFenster.setVisible(false);
            if (zahlZwischenstand == 10) {
                zahlZwischenstand = 1;
            }
            if (ausgabe.getText().length() != 0) {
                zahlZwischenstand += 1;
            }

            //Aktueller Stand wird gespeichert und dem Katalog mitgegeben
            speicherVokabelnLernen.setZwSpDeEng(zahlZwischenstand);
            speicherVokabelnLernen.setFragenListeDeEng(listeFrage);
            speicherVokabelnLernen.setAntwortenListeDeEng(listeAntwort);
            new KatalogwahlFenster(speicherVokabelnLernen);
            deEngFenster.dispose();
        }

        if (e.getSource() == this.ok) {
            ok.removeActionListener(this);
            eingabe.setEditable(false);

            //Musik
            new Musik("src/Img/klick.wav").start();

            //Falls der Spieler keine Lösung eingegeben hat, zählt es als falsch
            if (eingabe.getText().length() == 0) {
                ausgabe.setBackground(new Color(255, 80, 74));
                ausgabe.setText("" + listeAntwort.get(getFragePos()));
                System.out.println("nein1: " + listeFrage.size());
                //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                listeFrage.remove(zufallsVokabel);
                listeAntwort.remove(zufallsVokabel);
                System.out.println("nein1: " + listeFrage.size());
            } else {

                //Vergleich der Positionen in der jeweiligen ArrayListe: Vokabelabfrage, Eingabe
                //2 Getter-Methoden weiter unten
                if (getFragePos() == getAntwortPos()) {
                    ausgabe.setBackground(new Color(180, 238, 180));
                    ausgabe.setText("Richtig!");
                    System.out.println("ja: " + listeFrage.size());

                    //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                    listeFrage.remove(zufallsVokabel);
                    listeAntwort.remove(zufallsVokabel);
                    System.out.println("ja: " + listeFrage.size());
                } else {
                    ausgabe.setBackground(new Color(255, 80, 74));
                    ausgabe.setText("" + listeAntwort.get(getFragePos()));
                    System.out.println("nein2: " + listeFrage.size());

                    //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                    listeFrage.remove(zufallsVokabel);
                    listeAntwort.remove(zufallsVokabel);
                    System.out.println("nein2: " + listeFrage.size());
                }
            }

            //Weiter-Button wird bei der letzten Abfrage zum AuswertungsButton -> führt zur Statistik
            if (listeFrage.size() <= 76) {
                weiter.setIcon(new BildBauer().createImageIcon("/Img/auswertungButton.png"));
            } else {
                weiter.addActionListener(this);
            }
        }

        if (e.getSource() == this.weiter) {
            weiter.removeActionListener(this);

            //Musik
            new Musik("src/Img/klick.wav").start();

            //Zwischenstand wird um 1 aufaddiert
            zahlZwischenstand++;
            System.out.println("" + zahlZwischenstand);
            zwischenstand.setText(zahlZwischenstand + " / 10");

            eingabe.setEditable(true);
            eingabe.setText("");
            eingabe.setFocusable(true);
            ausgabe.setBackground(Color.LIGHT_GRAY);
            ausgabe.setText("");

            nextVokabel();

            if (listeFrage.size() >= 75) {
                ok.addActionListener(this);
            }
        }
    }

}
