package gui;

import Img.BildBauer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by Justus Heyen
 * on 12 Jun 2014
 * VokabelMaster5000
 *
 * Diese Klasse ist für die Highscore zuständig. Sie erweitert die Klasse JFrame.
 * Sie holt sich die Daten aus der Datenbank {@link Datenbank.LeseHighscore} und setzt
 * die einzelnen Daten als Text in JLabels ein, die wiederum in ein GridLayout gepackt werden,
 * damit man die Daten tabellarisch festhalten kann.
 * Die Highscoreliste zeigt nur die 5 besten Spieler an.
 * */

public class HSFenster extends JFrame {


    private final Font font;

    public HSFenster(ArrayList<String> daten) {

        //Größe, Titel und Layout setzen
        setSize(400, 400);
        setTitle("Highscore");
                setLocationRelativeTo(null);

        font = new Font("Arial", Font.BOLD, 16);


        JPanel panel = new BilderPanel("/Img/VM5000.png", 0.25f);
        panel.setLayout(new GridLayout(6, 3, 5, 5));

//        setResizable(false);
        panel.add(new MeinLabel("Platz"));
        panel.add(new MeinLabel("Name"));
        panel.add(new MeinLabel("Punkte"));
        setIconImage(new BildBauer().createImageIcon("/Img/vmWinIco.png").getImage());





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
                return one < two ? 1 : -1;
            }
        });

        int i = 1;

        for (String score : alleScores) {
            if (i <= 5) {
                String name = map.get(score);
               panel.add(new MeinLabel("" + i++));
               panel.add(new MeinLabel(name));
               panel.add(new MeinLabel(score));
            }
        }

        add(panel);

        setVisible(true);
    }

    private class MeinLabel extends JLabel{

        private MeinLabel() {
            setFont(font);
            setForeground(Color.RED);
        }
        private MeinLabel(String s) {
            super(s);
            setFont(font);
            setForeground(Color.RED);
        }
    }


//    public static void main(String[] args) {
//
//        // wir erzeugen uns ein paar Pseudo Daten:
//
//        ArrayList<String> strings = new ArrayList<String>();
//        strings.add("Justus/5435");
//        strings.add("Marc/0");
//        strings.add("Ka-Yan/12");
//        strings.add("Marius/176");
//        strings.add("Justus/3214");
//        strings.add("Marc/34");
//        strings.add("Ka-Yan/546");
//        strings.add("Marius/345");
//        strings.add("Ka-Yan/64");
//        strings.add("Marius/766");
//
//
//        new HSFenster(strings);
//    }



}
