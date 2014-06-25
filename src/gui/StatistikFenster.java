package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Diese Klasse ist für die erstellung des Statiskfensters zuständig
 */
public class StatistikFenster {

    /**
     * Diese Methode baut das Fenster sowie die dazu gehörige Tabelle
     * @param s
     */
    public StatistikFenster(SpeicherVokabelnLernen s) {


        JFrame statistikFenster = new JFrame("Auswertung");
        JPanel statistikPanel = new JPanel(new GridLayout(2, 4));

        // Tabellenkopf mit Zeilen
        JLabel name = new MeinLabel("Name");
        JLabel time = new MeinLabel("Zeit in s");
        JLabel richtigeFragen = new MeinLabel("Anzahl richtiger Fragen");
        JLabel punkte = new MeinLabel("Erreichte Punktzahl");
        JLabel nameUser = new MeinLabel("" + s.getName());
        JLabel timeUser = new MeinLabel("" + s.getTime());
        JLabel richtigeFragenUser = new MeinLabel("" + s.getRichtigeAntworten() + "/40");
        JLabel punkteUser = new MeinLabel("" + s.getPunkte() + "/100");

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
        statistikFenster.setSize(415, 400);

    }

    /**
     * Diese Klasse erweiter JLabel und setzt alle Texte zentriert ein
     */
    private class MeinLabel extends JLabel {
        private MeinLabel(String t) {

            super(t);
            setOpaque(true);
            setHorizontalAlignment(CENTER);

            /*int r = new Random().nextInt(50);
            int g = new Random().nextInt(50);
            int b = new Random().nextInt(50);
            Color color = new Color(r, g, b);
            setBackground(color);*/
        }
    }

    public static void main(String[] a) {
//
//        new StatistikFenster();
//
   }


}

