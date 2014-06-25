package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diese Klasse ist für die erstellung des Statiskfensters zuständig
 */
public class StatistikFenster {

    boolean istDeEngKatalog;
    SpeicherVokabelnLernen speicherVokabelnLernen;
    JFrame statistikFenster;
    MeinLabel timeUser;
    MeinLabel richtigeFragenUser;
    MeinLabel punkteUser;

    /**
     * Diese Methode baut das Fenster sowie die dazu gehörige Tabelle
     * @param s ist ein Objekt von der Klasse{@link gui.SpeicherVokabelnLernen}
     *          aus denen wir uns die zwischengespeicherten Daten holen
     */
    public StatistikFenster(SpeicherVokabelnLernen s, boolean istDeEng) {
        speicherVokabelnLernen = s;
        istDeEngKatalog = istDeEng;

        statistikFenster = new JFrame("Auswertung");

        //Hintergrundbild
        BilderPanel statistikBg = new BilderPanel("/Img/statsBg.png");

        JPanel statistikPanel = new JPanel(new GridBagLayout());
        statistikPanel.setOpaque(false);

        // Tabellenkopf mit Zeilen
        MeinLabel nameUser = new MeinLabel(s.getName());
        if (istDeEngKatalog) {
            timeUser = new MeinLabel("" + s.getTimeDeEng());
            richtigeFragenUser = new MeinLabel("" + s.getRichtigeAntwortenDeEng() + "/" + s.getZwischenStandDeEng());
            punkteUser = new MeinLabel("" + ((86*s.getRichtigeAntwortenDeEng())/100));
        } else {
            timeUser = new MeinLabel("" + s.getTimeEngDe());
            richtigeFragenUser = new MeinLabel("" + s.getRichtigeAntwortenEngDe() + "/" + s.getZwischenStandEngDe());
            punkteUser = new MeinLabel("" + ((86*s.getRichtigeAntwortenEngDe())/100));
        }

        BildButton zumMenu = new BildButton(new BildBauer().createImageIcon("/Img/cancelButton.png"));
        zumMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statistikFenster.setVisible(false);
                statistikFenster.dispose();
                if (istDeEngKatalog) {
                    if (speicherVokabelnLernen.getZwischenStandDeEng() == 86) {
                        speicherVokabelnLernen.setZwischenStandDeEng(1);
                    } else if (speicherVokabelnLernen.getZwischenStandDeEng() < 86) {
                        speicherVokabelnLernen.setZwischenStandDeEng(speicherVokabelnLernen.getZwischenStandDeEng() + 1);
                    }
                } else {
                    if (speicherVokabelnLernen.getZwischenStandEngDe() == 86) {
                        speicherVokabelnLernen.setZwischenStandEngDe(1);
                    } else if (speicherVokabelnLernen.getZwischenStandEngDe() < 86) {
                        speicherVokabelnLernen.setZwischenStandEngDe(speicherVokabelnLernen.getZwischenStandEngDe() + 1);
                    }
                }

                new KatalogwahlFenster(speicherVokabelnLernen);
            }
        });

        statistikPanel.add(zumMenu, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 350, 0, 0), 0, 0));
        statistikPanel.add(nameUser, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(108, 0, 0, 200), 0, 0));
        statistikPanel.add(punkteUser, new GridBagConstraints(1, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(108, 200, 0, 0), 0, 0));
        statistikPanel.add(richtigeFragenUser, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(85, 0, 0, 200), 0, 0));
        statistikPanel.add(timeUser, new GridBagConstraints(1, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(85, 200, 0, 0), 0, 0));

        statistikBg.add(statistikPanel);
        statistikFenster.add(statistikBg);

        statistikFenster.setSize(415, 400);
        statistikFenster.setLocationRelativeTo(null);
        statistikFenster.setResizable(false);
        statistikFenster.setVisible(true);

    }

}

