package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ka Yan Lam
 * on 12 Jun 2014
 * VokabelMaster5000
 */

public class CreditsFenster {

    public CreditsFenster() {

        //Fenster für das Menue
        JFrame creditsFenster = new JFrame("Menü");
        creditsFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Menupanel für die Buttons
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.WHITE);

        //Buttons
        ImageIcon vokabelnLernenIcon = new BildBauer().createImageIcon("img/vokabelnlernenButton.png");
        BildButton vokabelnLernen = new BildButton(vokabelnLernenIcon);

        ImageIcon quizIcon = new BildBauer().createImageIcon("img/quizButton.png");
        BildButton quiz = new BildButton(quizIcon);

        ImageIcon creditsIcon = new BildBauer().createImageIcon("img/creditsButton.png");
        BildButton credits = new BildButton(creditsIcon);

        //Buttons dem VM5000.gui.BilderPanel hinzufügen
        menuPanel.add(vokabelnLernen, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        menuPanel.add(quiz, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        menuPanel.add(credits, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        //Komponenten zum Fenster hinzufügen
        creditsFenster.add(menuPanel);

        //Fenstergröße setzen und anzeigen lassen
        creditsFenster.pack();
        creditsFenster.setLocationRelativeTo(null);
        creditsFenster.setResizable(false);
        creditsFenster.setVisible(true);

    }

    public static void main(String[] a) {

        new CreditsFenster();

    }

}
