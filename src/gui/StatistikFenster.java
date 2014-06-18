package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class StatistikFenster {

    public StatistikFenster(String namee, int zeit, int fragen, int punktee) {

        JFrame statistikFenster = new JFrame("Auswertung");
        JPanel statistikPanel = new JPanel(new GridLayout(2, 4));

        // Tabellenkopf mit Zeilen
        JLabel name = new MeinLabel("Name");
        JLabel time = new MeinLabel("Zeit in s");
        JLabel richtigeFragen = new MeinLabel("Anzahl richtiger Fragen");
        JLabel punkte = new MeinLabel("Erreichte Punktzahl");
        JLabel nameUser = new MeinLabel("" + namee);
        JLabel timeUser = new MeinLabel("" + zeit);
        JLabel richtigeFragenUser = new MeinLabel("" + fragen + "/40");
        JLabel punkteUser = new MeinLabel("" + punktee + "/100");

        statistikFenster.add(statistikPanel);
        statistikPanel.add(name);
        statistikPanel.add(time);
        statistikPanel.add(richtigeFragen);
        statistikPanel.add(punkte);
        statistikPanel.add(nameUser);
        statistikPanel.add(timeUser);
        statistikPanel.add(richtigeFragenUser);
        statistikPanel.add(punkteUser);

        statistikFenster.pack();
        statistikFenster.setVisible(true);
        statistikFenster.setSize(600, 300);

    }

    private class MeinLabel extends JLabel {
        private MeinLabel(String t) {

            super(t);
            setOpaque(true);
            setHorizontalAlignment(CENTER);

            int r = new Random().nextInt(255);
            int g = new Random().nextInt(255);
            int b = new Random().nextInt(255);
            Color color = new Color(r, g, b);
            setBackground(color);
        }
    }

    public static void main(String[] a) {

        new StatistikFenster("Peter", 89, 20, 50);

    }


}

