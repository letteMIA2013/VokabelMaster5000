package gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class MultiplayerFenster {

    Timer t;
    int count;
    JLabel time;

    public MultiplayerFenster() {

        //Fenster für den Multiplayer
        JFrame multiplayerFenster = new JFrame();
        multiplayerFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel multiplayerBg = new BilderPanel("/Img/multiplayerBg.png");

        //Fragepanel im Norden für den Timer, die Lösung der Frage und der Frage
        JPanel multiplayerPanel = new JPanel(new GridBagLayout());
        multiplayerPanel.setOpaque(false);

        //Felder für den Timer und der Frage
        time = new JLabel();
        ImageIcon frageIcon = new BildBauer().createImageIcon("/Img/frageLabel.png");
        MeinLabel frage = new MeinLabel(frageIcon, false);
        frage.setText("Vokabel");
        frage.setHorizontalTextPosition(SwingConstants.CENTER);

        //Buttons für die Antworten
        BildButton antwortEins = new BildButton("Antwort Eins", 500);
        BildButton antwortZwei = new BildButton("Antwort Zwei", 500);
        BildButton antwortDrei = new BildButton("Antwort Drei", 500);
        BildButton antwortVier = new BildButton("Antwort Vier", 500);

        //Buzzer
        MeinLabel buzzer = new MeinLabel("Buzzer", true, Color.RED);

        //Felder für die 3 Spieler + Punktestand
        ImageIcon spielerEinsIcon = new BildBauer().createImageIcon("/Img/spielerRosaLabel.png");
        MeinLabel spielerEins = new MeinLabel(spielerEinsIcon, true);

        ImageIcon spielerZweiIcon = new BildBauer().createImageIcon("/Img/spielerRosaLabel.png");
        MeinLabel spielerZwei = new MeinLabel(spielerZweiIcon, true);

        ImageIcon spielerDreiIcon = new BildBauer().createImageIcon("/Img/spielerRosaLabel.png");
        MeinLabel spielerDrei = new MeinLabel(spielerDreiIcon, true);

        ImageIcon spielerEinsPunkteIcon = new BildBauer().createImageIcon("/Img/spielerRosaLabel.png");
        MeinLabel spielerEinsPunkte = new MeinLabel(spielerEinsPunkteIcon, true);

        ImageIcon spielerZweiPunkteIcon = new BildBauer().createImageIcon("/Img/spielerRosaLabel.png");
        MeinLabel spielerZweiPunkte = new MeinLabel(spielerZweiPunkteIcon, true);

        ImageIcon spielerDreiPunkteIcon = new BildBauer().createImageIcon("/Img/spielerRosaLabel.png");
        MeinLabel spielerDreiPunkte = new MeinLabel(spielerDreiPunkteIcon, true);

        //Timer
        count = 16;
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Zieht immer eine Sekunde ab
                count--;

                if(count < 10) {
                    time.setText("00:0" + String.valueOf(count));
                } else {
                    time.setText("00:" + String.valueOf(count));
                }

                //färbt die Schriftfarbe rot ab 5sec
                if(count <= 5) {
                    time.setForeground(Color.RED);
                }

                //Wenn Zeit abgelaufen, dann nächste Frage
                if (count == 0) {
                    System.out.println("Jetzt sollte die nächste Frage erscheinen");
                    t.stop();
                }

            }
        });

        t.start();

        //Timer und Frage dem multiplayerPanel hinzufügen
        multiplayerPanel.add(frage, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(75, 0, 0, 0), 0, 0));
        multiplayerPanel.add(time, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-205, 0, 0, 215), 0, 0));

        //Antworten und Buzzer dem multiplayerPanel hinzufügen
        multiplayerPanel.add(antwortEins, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortZwei, new GridBagConstraints(1, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortDrei, new GridBagConstraints(2, 0, 0, 1, 1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortVier, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        multiplayerPanel.add(buzzer, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));

        //Spieler und Punkte dem multiplayerPanel hinzufügen
        multiplayerPanel.add(spielerEins, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        multiplayerPanel.add(spielerZwei, new GridBagConstraints(1, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        multiplayerPanel.add(spielerDrei, new GridBagConstraints(2, 0, 0, 1, 1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        multiplayerPanel.add(spielerEinsPunkte, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        multiplayerPanel.add(spielerZweiPunkte, new GridBagConstraints(1, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        multiplayerPanel.add(spielerDreiPunkte, new GridBagConstraints(2, 1, 0, 1, 1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));

        multiplayerBg.add(multiplayerPanel);

        //Komponenten zum multiplayerFenster hinzufügen
        multiplayerFenster.add(multiplayerBg);

        //Fenstergröße setzen und anzeigen lassen
        multiplayerFenster.setSize(415, 400);
        multiplayerFenster.setLocationRelativeTo(null);
        multiplayerFenster.setResizable(false);
        multiplayerFenster.setVisible(true);

    }

    public static void main(String[] a) {

        new MultiplayerFenster();

    }

}
