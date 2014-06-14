package gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Justus Heyen
 * on 12 Jun 2014
 * VokabelMaster5000
 */

public class HSFenster extends JFrame {

    public HSFenster(ArrayList<String> daten) {

        //Größe, Titel und Layout setzen
        setSize(400, 400);
        setTitle("Highscore");
        setLayout(new GridLayout(daten.size(), 3, 0, 5));

        //Daten aus der Datenbank in Listen packen
        Map<String, String> map = new HashMap<String, String>();
        ArrayList<String> alleScores = new ArrayList<String>();

        for (String s : daten) {
            String[] split = s.split("/");
            String score = split[2];
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
            String name = map.get(score);
            add(new JLabel("" + i++));
            add(new JLabel(name));
            add(new JLabel(score));
        }

        setVisible(true);
    }

    public static void main(String[] args) {

        // wir erzeugen uns ein paar Pseudo Daten:
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Justus/passwort/5435");
        strings.add("Marc/passwort/5");
        strings.add("Ka-Yan/passwort/57934");
        strings.add("Marius/passwort/176");

        new HSFenster(strings);
    }
}
