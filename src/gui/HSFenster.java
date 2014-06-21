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
        setLayout(new GridLayout(6, 3, 5, 5));
        setLocationRelativeTo(null);
        setResizable(false);
        add(new JLabel("Platz"));
        add(new JLabel("Name"));
        add(new JLabel("Punkte"));

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
                add(new JLabel("" + i++));
                add(new JLabel(name));
                add(new JLabel(score));
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {

        // wir erzeugen uns ein paar Pseudo Daten:
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Justus/5435");
        strings.add("Marc/5");
        strings.add("Ka-Yan/57934");
        strings.add("Marius/176");

        new HSFenster(strings);
    }
}
