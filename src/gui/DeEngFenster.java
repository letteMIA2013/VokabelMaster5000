package gui;

import Img.BildBauer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ka Yan & Frances & Nils
 */

/**
 * In dieser Klasse wird das Deutsch-Englisch Fenster erstellt, ActionListener und KeyListener werden implementiert,
 * damit man besser damit arbeiten kann. <br />
 * Hier wird der Timer für das Lernen der Vokabeln geschrieben und die Position der Fragen und der Antworten festgelegt.
 * Bei der ActionPerformed Methode wird geprüft, welcher Button gedrückt wurde, ob der Spieler eine Lösung eingegeben
 * hat und ob diese richtig oder falsch ist.
 */
public class DeEngFenster implements ActionListener, KeyListener {

    private int zahlZwischenstand;
    private int zufallsVokabel;
    private int richtigeAntworten;
    private int count;
    private boolean istAuswertung;
    private SpeicherVokabelnLernen speicherVokabelnLernen;
    private Timer timer;
    private JFrame deEngFenster;
    private JLabel vokabel;
    private Color FARBE_ROT = new Color(255, 80, 74);
    private Color FARBE_GRUEN = new Color(180, 238, 180);
    private RoundedTextField eingabe;
    private RoundedTextField ausgabe;
    private BildButton zurueck;
    private BildButton ok;
    private BildButton weiter;
    private BildButton auswertung;
    private MeinLabel zwischenstand;
    private ArrayList<String> listeFrage;
    private ArrayList<String> listeAntwort;
    private Font font;

    /**
     * Im Konstruktor wird das Fenster gebaut, die Größe davon festgelegt, die Sichtbarkeit und dem Hintergrund
     * ein Bild hinzugefügt {@link gui.BilderPanel}.
     * Es werden drei Buttons erstellt, von der Klasse {@link gui.BildButton}, daraufhin konnte man jedem Button ein
     * Bild hinzufügen die wir dem Panel hinzugefügt haben.
     * Die vier Buttons besitzen alle ein ActionListener.
     * Die Daten aus dem Speicher werden den Variablen übergeben, damit man von dort weiterlernen kann,
     * wo man abgebrochen hat.
     * Der Timer wird im Konstruktor auch mit gestartet und läuft solange, bis der Spieler entweder auf Auswertung oder auf Zurück klickt.
     * Der Timer wird erst dann resettet, wenn der Spieler alle Vokabeln durchgegangen ist, ansonsten pausiert
     * er beim Klicken auf Asuwertung oder Zurück.
     * @param s ist ein Objekt von der Klasse {@link gui.SpeicherVokabelnLernen}
     *          damit dort alles zwischengespeichert wird und das Zwischengespeicherte wieder aufgefanen werden kann.
     */
    public DeEngFenster(SpeicherVokabelnLernen s) {

        //Parameter als globale Klasseneigenschaft abspeichern
        speicherVokabelnLernen = s;

        //Die Daten aus dem Speicher den Variablen übergeben, damit man von dort weiterlernen kann, wo man abgebrochen hat
        richtigeAntworten = speicherVokabelnLernen.getRichtigeAntwortenDeEng();
        zahlZwischenstand = speicherVokabelnLernen.getZwischenStandDeEng();

        //
        if (zahlZwischenstand == 1) {
            listeFrage = new ArrayList<String>();
            listeAntwort = new ArrayList<String>();
            for (String stringFrage : speicherVokabelnLernen.getAlleFragenListeDeEng()) {
                listeFrage.add(stringFrage);
            }
            for (String stringAntwort : speicherVokabelnLernen.getAlleAntwortenListeDeEng()) {
                listeAntwort.add(stringAntwort);
            }
        } else {
            listeFrage = speicherVokabelnLernen.getFragenListeDeEng();
            listeAntwort = speicherVokabelnLernen.getAntwortenListeDeEng();
        }

        //Fenster für das Lernen der Englisch Vokabeln
        deEngFenster = new JFrame("Deutsch/Englisch");
        deEngFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel deEngBg = new BilderPanel("/Img/deEngBg.png");

        //Schriftart für die abgefragte Vokabel
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("src/fonts/Chalkduster.ttf"))).deriveFont(Font.PLAIN, 13);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //BilderPanel
        JPanel deEngPanel = new JPanel(new GridBagLayout());
        deEngPanel.setOpaque(false);

        //Zwischenstandlabel
        zwischenstand = new MeinLabel(new BildBauer().createImageIcon("/Img/zwischenstandLabel.png"), zahlZwischenstand + " / " + speicherVokabelnLernen.getAlleFragenListeDeEng().size());

        //(Text)felder für die Vokabelabfrage, Eingabe und Ausgabe der Lösungen
        vokabel = new JLabel();
        vokabel.setFont(font);
        eingabe = new RoundedTextField(12);
        ausgabe = new RoundedTextField(12);
        ausgabe.setEditable(false);

        //Timer starten
        startTimer();

        //Für eine zufällig ausgewählte Vokabel aus der Fragen-ArrayListe
        nextVokabel();

        //Buttons werden hier erstellt
        zurueck = new BildButton(new BildBauer().createImageIcon("/Img/zurueckKleinButton.png"));
        ok = new BildButton(new BildBauer().createImageIcon("/Img/okButton.png"));
        weiter = new BildButton(new BildBauer().createImageIcon("/Img/weiterButton.png"));
        auswertung = new BildButton(new BildBauer().createImageIcon("/Img/auswertungButton.png"));

        //hier werden alle Elemente dem deEngPanel hinzugefügt
        deEngPanel.add(zwischenstand, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(72, 0, 0, 0), 0, 0));
        deEngPanel.add(vokabel, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(62, 0, 0, 170), 0, 0));
        deEngPanel.add(eingabe, new GridBagConstraints(1, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(59, 150, 0, 0), 0, 0));
        deEngPanel.add(ausgabe, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(14, 150, 0, 0), 0, 0));
        deEngPanel.add(zurueck, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 200), 0, 0));
        deEngPanel.add(ok, new GridBagConstraints(1, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 0), 0, 0));
        deEngPanel.add(weiter, new GridBagConstraints(2, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 200, 0, 0), 0, 0));
        deEngPanel.add(auswertung, new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        //ActionListener
        zurueck.addActionListener(this);
        ok.addActionListener(this);
        eingabe.addKeyListener(this);
        auswertung.addActionListener(this);

        deEngBg.add(deEngPanel);

        //Komponenten zum Fenster hinzufügen
        deEngFenster.add(deEngBg);

        //Fenstergröße setzen und anzeigen lassen
        deEngFenster.setIconImage(new BildBauer().createImageIcon("/Img/vmWinIco.png").getImage());
        deEngFenster.setSize(415, 400);
        deEngFenster.setLocationRelativeTo(null);
        deEngFenster.setResizable(false);
        deEngFenster.setVisible(true);
    }

    /**
     * Startet den Timer. Beim Klicken auf den Auswertungsbutton wird der Timer gestoppt und die Statistik wird angezeigt.
     */
    public void startTimer() {
        if (zahlZwischenstand == 86) {
            count = 0;
        } else {
            count = speicherVokabelnLernen.getTimeDeEng();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Zieht immer eine Sekunde ab
                count++;

                //Wenn Zeit abgelaufen, dann nächste Frage
                if (istAuswertung) {
                    System.out.println("Zeit: " + count);
                    timer.stop();
                    speicherVokabelnLernen.setTimeDeEng(count);
                    speicherVokabelnLernen.setRichtigeAntwortenDeEng(richtigeAntworten);
                    if (ausgabe.getText().length() == 0 || zahlZwischenstand == 87) {
                        zahlZwischenstand -= 1;
                    }
                    speicherVokabelnLernen.setZwischenStandDeEng(zahlZwischenstand);
                    deEngFenster.setVisible(false);
                    deEngFenster.dispose();
                    new StatistikFenster(speicherVokabelnLernen, true);
                }
            }
        });

        timer.start();
    }

    /**
     * In dieser Methode wird die Position der Frage dargestellt.
     * @return eine Zahl, die die Position der Frage in der Fragen-ArrayList darstellt.
     */
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

    /**
     * In dieser Methode wird die Position der Antwort dargestellt.
     * @return eine Zahl, die die Position der Antwort in der Antworten-ArrayList darstellt.
     */
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


    /**
     * Gibt die nächste Vokabel zufällig aus der Fragen-ArrayListe heraus.
     */
    public void nextVokabel() {
        zufallsVokabel = new Random().nextInt(listeFrage.size());
        vokabel.setText("" + listeFrage.get(zufallsVokabel));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Überprüft, welcher Button gedrückt wurde

        //Zurück-Button
        if (e.getSource() == this.zurueck) {

            //Klick-Geräusch
            new Musik("src/sound/klick.wav").start();

            //Speichert den Zwischenstand, wenn man mit dem Lernen abbricht
            //Wenn der Zwischenstand bei der letzten Vokabel angelangt ist, dann setzen wir den und den Timer wieder auf 0
            deEngFenster.setVisible(false);
            if (zahlZwischenstand == 86) {
                zahlZwischenstand = 0;
                count = 0;
            }

            //Beim erneutem Eintreten zum Lernen wird der Zwischenstand automatisch um 1 erhöht
            if (ausgabe.getText().length() != 0) {
                zahlZwischenstand += 1;
            }

            //Aktueller Stand wird gespeichert und dem Katalog mitgegeben
            speicherVokabelnLernen.setZwischenStandDeEng(zahlZwischenstand);
            speicherVokabelnLernen.setFragenListeDeEng(listeFrage);
            speicherVokabelnLernen.setAntwortenListeDeEng(listeAntwort);
            speicherVokabelnLernen.setRichtigeAntwortenDeEng(richtigeAntworten);
            speicherVokabelnLernen.setTimeDeEng(count);
            new KatalogwahlFenster(speicherVokabelnLernen);
            deEngFenster.dispose();
        }

        //Ok-Button
        if (e.getSource() == this.ok) {
            ok.removeActionListener(this);
            eingabe.setEditable(false);

            //Klick-Geräusch
            new Musik("src/sound/klick.wav").start();

            //Falls der Spieler keine Lösung eingegeben hat, zählt es als falsch
            if (eingabe.getText().length() == 0) {
                ausgabe.setBackground(FARBE_ROT);
                ausgabe.setText("" + listeAntwort.get(getFragePos()));

                //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                listeFrage.remove(zufallsVokabel);
                listeAntwort.remove(zufallsVokabel);
            } else {

                //Vergleich der Positionen in der jeweiligen ArrayListe: Vokabelabfrage, Eingabe
                //2 Getter-Methoden weiter unten

                //richtig
                if (getFragePos() == getAntwortPos()) {
                    ausgabe.setBackground(FARBE_GRUEN);
                    ausgabe.setText("Richtig!");
                    richtigeAntworten++;

                    //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                    listeFrage.remove(zufallsVokabel);
                    listeAntwort.remove(zufallsVokabel);
                } else {
                    ausgabe.setBackground(FARBE_ROT);
                    ausgabe.setText("" + listeAntwort.get(getFragePos()));

                    //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                    listeFrage.remove(zufallsVokabel);
                    listeAntwort.remove(zufallsVokabel);
                }
            }

            //Weiter-Button wird bei der letzten Abfrage zum Auswertungsbutton -> führt zur Statistik
            if (listeFrage.size() <= 0) {
                weiter.removeActionListener(this);
            } else {
                weiter.addActionListener(this);
            }
        }

        //Weiter-Button
        if (e.getSource() == this.weiter) {
            weiter.removeActionListener(this);

            //Klick-Geräusch
            new Musik("src/sound/klick.wav").start();

            //Zwischenstand wird um 1 aufaddiert
            zahlZwischenstand++;
            zwischenstand.setText(zahlZwischenstand + " / " + speicherVokabelnLernen.getAlleFragenListeDeEng().size());

            //Bei dem Eingabefenster kann man wieder was eingeben + hat den Fokus
            eingabe.setEditable(true);
            eingabe.setFocusable(true);

            //Hintergrundfarbe des Lösungsfeldes wieder auf Grau setzen
            ausgabe.setBackground(Color.LIGHT_GRAY);

            //Text zurücksetzen
            eingabe.setText("");
            ausgabe.setText("");

            //Gibt die nächste Vokabel aus
            nextVokabel();

            //Fügt den Listener für den Ok-Button nur dann wieder hinzu, wenn noch Fragen in der Fragen-ArrayListe sind
            if (listeFrage.size() > 0) {
                ok.addActionListener(this);
            }
        }

        //setzt istAuswertung auf true, damit der Timer darauf reagieren kann (s. oben in der Methode startTimer)
        if (e.getSource() == this.auswertung) {
            istAuswertung = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //Mit Enter kann man bestätigen
        if (listeFrage.size() != 0) {
            System.out.println("Liste: " + listeFrage.size() + " Alt: " + speicherVokabelnLernen.getAlleFragenListeDeEng().size());
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (ausgabe.getText().length() == 0) {
                    ok.removeActionListener(this);
                    eingabe.setEditable(false);

                    //Klick-Geräusch
                    new Musik("src/sound/klick.wav").start();

                    //Falls der Spieler keine Lösung eingegeben hat, zählt es als falsch
                    if (eingabe.getText().length() == 0) {
                        ausgabe.setBackground(FARBE_ROT);
                        ausgabe.setText("" + listeAntwort.get(getFragePos()));

                        //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                        listeFrage.remove(zufallsVokabel);
                        listeAntwort.remove(zufallsVokabel);
                    } else {

                        //Vergleich der Positionen in der jeweiligen ArrayListe: Vokabelabfrage, Eingabe
                        //2 Getter-Methoden weiter unten
                        if (getFragePos() == getAntwortPos()) {
                            ausgabe.setBackground(FARBE_GRUEN);
                            ausgabe.setText("Richtig!");
                            richtigeAntworten++;

                            //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                            listeFrage.remove(zufallsVokabel);
                            listeAntwort.remove(zufallsVokabel);
                        } else {
                            ausgabe.setBackground(FARBE_ROT);
                            ausgabe.setText("" + listeAntwort.get(getFragePos()));

                            //schmeißt die schon abgefragten Vokabeln und Lösungen raus
                            listeFrage.remove(zufallsVokabel);
                            listeAntwort.remove(zufallsVokabel);
                        }
                    }

                    //Weiter-Button wird bei der letzten Abfrage zum AuswertungsButton -> führt zur Statistik
                    if (listeFrage.size() <= 0) {
                        weiter.removeActionListener(this);
                    } else {
                        weiter.addActionListener(this);
                    }
                } else {
                    if (listeFrage.size() > 0) {
                        weiter.removeActionListener(this);

                        //Klick-Geräusch
                        new Musik("src/sound/klick.wav").start();

                        //Zwischenstand wird um 1 aufaddiert
                        zahlZwischenstand++;
                        zwischenstand.setText(zahlZwischenstand + " / " + speicherVokabelnLernen.getAlleFragenListeDeEng().size());

                        //Bei dem Eingabefenster kann man wieder was eingeben + hat den Fokus
                        eingabe.setEditable(true);
                        eingabe.setFocusable(true);

                        //Hintergrundfarbe des Lösungsfeldes wieder auf Grau setzen
                        ausgabe.setBackground(Color.LIGHT_GRAY);

                        //Text zurücksetzen
                        eingabe.setText("");
                        ausgabe.setText("");

                        //Gibt die nächste Vokabel aus
                        nextVokabel();

                        //Fügt den Listener für den Ok-Button nur dann wieder hinzu, wenn noch Fragen in der Fragen-ArrayListe sind
                        ok.addActionListener(this);
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
