package gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Diese Klasse ist für die Highscore zuständig. Sie erweitert die Klasse JFrame.
 * Sie holt sich die Daten aus der Datenbank {@link Datenbank.LeseHighscore} und setzt
 * die einzelnen Daten als Text in JLabels ein, die wiederum in ein GridLayout gepackt werden,
 * damit man die Daten tabellarisch festhalten kann.
 * Die Highscoreliste zeigt nur die 5 besten Spieler an.
 */
public class HSFenster extends JFrame {

    /**
     * Im Konstruktor wird das Aussehen des Fensters/JFrames so angepasst, dass man drei Spalten für
     * Platz, Name und Punkte hat und entsprechende Zeilen, je nachdem wieviele Einträge die Datenbank hat.
     * Ab dem 5. Eintrag werden die Einträge der HighscoreListe nicht mehr ausgelesen.
     * Die Punkte werden nach der Größe sortiert. Die höchste Punktzahl landet auf den ersten Platz in der Tabelle.
     * @param daten ist eine ArrayListe mit Strings, die die Namen und die Punkte jeden Spielers beinhaltet
     */
    public HSFenster(ArrayList<String> daten) {

        //Größe, Titel und Layout setzen
        setSize(400, 400);
        setTitle("Highscore");
        if (daten.size() < 5) {
            setLayout(new GridLayout(daten.size() + 1, 3, 5, 5));
        } else {
            setLayout(new GridLayout(6, 3, 5, 5));
        }
        setLocationRelativeTo(null);
        setResizable(false);
        add(new JLabel("Platz", SwingConstants.CENTER));
        add(new JLabel("Name", SwingConstants.CENTER));
        add(new JLabel("Punkte", SwingConstants.CENTER));

        //Daten aus der Datenbank in Listen packen
        Map<String, String> map = new HashMap<String, String>();
        ArrayList<String> alleScores = new ArrayList<String>();

        for (String s : daten) {
            String[] split = s.split("/");
            String score = split[1];
            String name = split[0];
            map.put(score, name);
            alleScores.add(score);
        }

        //Nach Punktzahl sortieren
        Collections.sort(alleScores, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int one = Integer.parseInt(o1);
                int two = Integer.parseInt(o2);
                return one < two? 1: -1;
            }
        });

        int i = 1;

        for (String score : alleScores) {
            if (i <= 5) {
                String name = map.get(score);
                add(new JLabel("" + i++, SwingConstants.CENTER));
                add(new JLabel(name, SwingConstants.CENTER));
                add(new JLabel(score, SwingConstants.CENTER));
            }
        }

        setVisible(true);
    }
}
