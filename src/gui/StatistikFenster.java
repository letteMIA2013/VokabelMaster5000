package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Diese Klasse ist für die erstellung des Statiskfensters zuständig
 */
public class StatistikFenster {

    JLabel timeUser;
    JLabel richtigeFragenUser;
    JLabel punkteUser;

    /**
     * Diese Methode baut das Fenster sowie die dazu gehörige Tabelle
     * @param s ist die Speicherablage, aus denen wir uns die Daten holen
     */
    public StatistikFenster(SpeicherVokabelnLernen s, boolean istDeEng) {
        JFrame statistikFenster = new JFrame("Auswertung");

        //Hintergrundbild
        BilderPanel statistikBg = new BilderPanel("/Img/statsBg.png");

        JPanel statistikPanel = new JPanel(new GridLayout(2, 2));

        // Tabellenkopf mit Zeilen
        JLabel nameUser = new MeinLabel("" + s.getName());
        if (istDeEng) {
            timeUser = new MeinLabel("" + s.getTimeDeEng());
            richtigeFragenUser = new MeinLabel("" + s.getRichtigeAntwortenDeEng() + "/" + s.getZwSpDeEng());
            punkteUser = new MeinLabel("" + ((86*s.getRichtigeAntwortenDeEng())/100));
        } else {
            timeUser = new MeinLabel("" + s.getTimeEngDe());
            richtigeFragenUser = new MeinLabel("" + s.getRichtigeAntwortenEngDe() + "/" + s.getZwSpEngDe());
            punkteUser = new MeinLabel("" + ((86*s.getRichtigeAntwortenEngDe())/100));
        }

        statistikBg.add(statistikPanel);
        statistikFenster.add(statistikBg);

        statistikPanel.add(nameUser);
        statistikPanel.add(punkteUser);
        statistikPanel.add(richtigeFragenUser);
        statistikPanel.add(timeUser);

        statistikFenster.setSize(415, 400);
        statistikFenster.setLocationRelativeTo(null);
        statistikFenster.setResizable(false);
        statistikFenster.setVisible(true);

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

