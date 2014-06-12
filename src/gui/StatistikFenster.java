package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class StatistikFenster {

    public StatistikFenster() {

        JFrame statistikFenster = new JFrame();
        JPanel statistikPanel = new JPanel(new GridLayout(2, 4));

        // Tabellenkopf mit Zeilen
        JLabel name = new MeinLabel("Name");
        JLabel time = new MeinLabel("Zeit in s");
        JLabel richtigeFragen = new MeinLabel("Anzahl richitger Fragen");
        JLabel punkte = new MeinLabel("Erreichte Punktzahl");
        JLabel nameUser = new MeinLabel("Peter");
        JLabel timeUser = new MeinLabel("100");
        JLabel richtigeFragenUser = new MeinLabel("20/40");
        JLabel punkteUser = new MeinLabel("50/100");

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

        new StatistikFenster();

    }


}

