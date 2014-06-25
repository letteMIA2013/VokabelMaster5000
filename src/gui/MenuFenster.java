package gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFenster implements ActionListener {

    boolean istAngemeldet;
    SpeicherVokabelnLernen speicherVokabelnLernen;
    JFrame menuFenster;
    BildButton vokabelnLernen;
    BildButton quiz;
    BildButton credits;

    public MenuFenster(boolean istAngemeldet, SpeicherVokabelnLernen speicherVokabelnLernen) {
        this.istAngemeldet = istAngemeldet;
        this.speicherVokabelnLernen = speicherVokabelnLernen;

        //Fenster für das Menue
        menuFenster = new JFrame("Menü");
        menuFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFenster.setIconImage(new BildBauer().createImageIcon("/Img/vmWinIco.png").getImage());

        //Hintergrundbild
        BilderPanel menuBg = new BilderPanel("/Img/VM5000.png");
        menuBg.setBackground(Color.WHITE);

        //Menupanel für die Buttons
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setOpaque(false);

        //Buttons
        vokabelnLernen = new BildButton(new BildBauer().createImageIcon("/Img/vokabelnlernenButton.png"));
        quiz = new BildButton(new BildBauer().createImageIcon("/Img/quizButton.png"));
        credits = new BildButton(new BildBauer().createImageIcon("/Img/creditsButton.png"));

        //ActionListener
        vokabelnLernen.addActionListener(this);
        quiz.addActionListener(this);
        credits.addActionListener(this);

        //Buttons dem Panel hinzufügen
        menuPanel.add(vokabelnLernen, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(25, 0, 0, 180), 0, 0));
        menuPanel.add(quiz, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 280), 0, 0));
        menuPanel.add(credits, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 220), 0, 0));

        menuBg.add(menuPanel);

        //Komponenten zum Fenster hinzufügen
        menuFenster.add(menuBg);

        //Fenstergröße setzen und anzeigen lassen
        menuFenster.setSize(450, 400);
        menuFenster.setLocationRelativeTo(null);
        menuFenster.setResizable(false);
        menuFenster.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Überprüft, welcher Button gedrückt wurde
        if (e.getSource() == this.vokabelnLernen) {

            //Musik
            new Musik("src/sound/klick.wav").start();

            menuFenster.setVisible(false);
            menuFenster.dispose();
            if (istAngemeldet) {
                new KatalogwahlFenster(speicherVokabelnLernen);
                JOptionPane.showMessageDialog(null, "Willkommen zurück " + speicherVokabelnLernen.getName() + "!");
            } else {
                new LoginFenster();
            }
        }

        if (e.getSource() == this.quiz) {

            //Musik
            new Musik("src/sound/klick.wav").start();

            menuFenster.setVisible(false);
            menuFenster.dispose();
            new MultiplayerFenster();
        }

        if (e.getSource() == this.credits) {

            //Musik
            new Musik("src/sound/klick.wav").start();

            menuFenster.setVisible(false);
            menuFenster.dispose();
            if (istAngemeldet) {
                new CreditsFenster(speicherVokabelnLernen);
                JOptionPane.showMessageDialog(null, "Willkommen zurück " + speicherVokabelnLernen.getName() + "!");
            } else {
                new CreditsFenster();
            }

        }
    }

}