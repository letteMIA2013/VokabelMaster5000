package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.lang.String;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class MenuFenster {

    public MenuFenster() {

        //Fenster für das Menue
        JFrame menuFenster = new JFrame("Menü");
        menuFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Logo
        ImageIcon logoIcon = new BildBauer().createImageIcon("Img/VM5000.png");
        MeinLabel logo = new MeinLabel(logoIcon,true, Color.WHITE);

        //Menupanel für die Buttons
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.WHITE);

        //Buttons
        ImageIcon vokabelnLernenIcon = new BildBauer().createImageIcon("Img/vokabelnlernenButton.png");
        BildButton vokabelnLernen = new BildButton(vokabelnLernenIcon);

        ImageIcon quizIcon = new BildBauer().createImageIcon("Img/quizButton.png");
        BildButton quiz = new BildButton(quizIcon);

        ImageIcon creditsIcon = new BildBauer().createImageIcon("Img/creditsButton.png");
        BildButton credits = new BildButton(creditsIcon);

        //Buttons dem VM5000.gui.BilderPanel hinzufügen
        menuPanel.add(vokabelnLernen,new GridBagConstraints(0,0,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        menuPanel.add(quiz,new GridBagConstraints(0,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        menuPanel.add(credits,new GridBagConstraints(0,2,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //Komponenten zum Fenster hinzufügen
        menuFenster.add(logo, BorderLayout.NORTH);
        menuFenster.add(menuPanel);

        //Fenstergröße setzen und anzeigen lassen
        menuFenster.pack();
        menuFenster.setLocationRelativeTo(null);
        menuFenster.setResizable(false);
        menuFenster.setVisible(true);

    }

    public void actionperformed (ActionEvent ae){

    }

    public static void main(String[] a) {

        new MenuFenster();

    }

}