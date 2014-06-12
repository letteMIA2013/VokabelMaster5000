package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.String;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class MultiplayerFenster {

    Timer t;
    int count;
    boolean buzz;
    JLabel time;
    MeinLabel buzzer;

    public MultiplayerFenster() {

        //Fenster für den Multiplayer
        JFrame multiplayerFenster = new JFrame();
        multiplayerFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        MyDispatcher myDispatcher = new MyDispatcher();
        manager.addKeyEventDispatcher(myDispatcher);

        //Hintergrundbild
        BilderPanel multiplayerBg = new BilderPanel("/img/multiplayerBg.png");

        //Fragepanel im Norden für den Timer, die Lösung der Frage und der Frage
        JPanel multiplayerPanel = new JPanel(new GridBagLayout());
        multiplayerPanel.setOpaque(false);

        //Felder für den Timer und der Frage
        time = new JLabel("00:00");
        MeinLabel frage = new MeinLabel(new BildBauer().createImageIcon("/img/frageLabel.png"), "Vokabel");

        //Buttons für die Antworten
        ImageIcon antwort = new BildBauer().createImageIcon("/img/antwortenLabel.png");
        BildButton antwortEins = new BildButton(antwort, "Antwort 1");
        BildButton antwortZwei = new BildButton(antwort, "Antwort 2");
        BildButton antwortDrei = new BildButton(antwort, "Antwort 3");
        BildButton antwortVier = new BildButton(antwort, "Antwort 4");

        //Buzzer
        buzzer = new MeinLabel(new BildBauer().createImageIcon("/img/buzzerRotLabel.png"));

        //Felder für die 3 Spieler + Punktestand
        MeinLabel spielerEins = new MeinLabel(new BildBauer().createImageIcon("/img/spielerRosaLabel.png"), "Rosa");
        MeinLabel spielerZwei = new MeinLabel(new BildBauer().createImageIcon("/img/spielerGruenLabel.png"), "Grün");
        MeinLabel spielerDrei = new MeinLabel(new BildBauer().createImageIcon("/img/spielerBlauLabel.png"), "Blau");

        ImageIcon spielerPunkteIcon = new BildBauer().createImageIcon("/img/punkteLabel.png");
        MeinLabel spielerEinsPunkte = new MeinLabel(spielerPunkteIcon);
        MeinLabel spielerZweiPunkte = new MeinLabel(spielerPunkteIcon);
        MeinLabel spielerDreiPunkte = new MeinLabel(spielerPunkteIcon);

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

                //färbt die Schriftfarbe rot ab 5sec abwärts
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

        //Wenn gebuzzert
        if (buzz) {
            aktiviereButtons();
            manager.removeKeyEventDispatcher(myDispatcher);
            buzz = false;
        }

        //Timer und Frage dem multiplayerPanel hinzufügen
        multiplayerPanel.add(time, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-3, 0, 0, 215), 0, 0));
        multiplayerPanel.add(frage, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(60, 0, 0, 0), 0, 0));

        //Antworten und Buzzer dem multiplayerPanel hinzufügen
        multiplayerPanel.add(antwortEins, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortZwei, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortDrei, new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(antwortVier, new GridBagConstraints(0, 5, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(buzzer, new GridBagConstraints(0, 6, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        //Spieler und Punkte dem multiplayerPanel hinzufügen
        multiplayerPanel.add(spielerEins, new GridBagConstraints(0, 7, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 300), 0, 0));
        multiplayerPanel.add(spielerZwei, new GridBagConstraints(1, 7, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        multiplayerPanel.add(spielerDrei, new GridBagConstraints(2, 7, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 300, 0, 0), 0, 0));
        multiplayerPanel.add(spielerEinsPunkte, new GridBagConstraints(0, 8, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 300), 0, 0));
        multiplayerPanel.add(spielerZweiPunkte, new GridBagConstraints(1, 8, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
        multiplayerPanel.add(spielerDreiPunkte, new GridBagConstraints(2, 8, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 300, 0, 0), 0, 0));

        multiplayerBg.add(multiplayerPanel);

        //Komponenten zum multiplayerFenster hinzufügen
        multiplayerFenster.add(multiplayerBg);

        //Fenstergröße setzen und anzeigen lassen
        multiplayerFenster.setSize(415, 400);
        multiplayerFenster.setLocationRelativeTo(null);
        multiplayerFenster.setResizable(false);
        multiplayerFenster.setVisible(true);

    }

    public void aktiviereButtons() {



    }

    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                //System.out.println(e.getKeyCode());
                if (e.getKeyCode() == 83) {
                    System.out.println("Rosa");
                    buzzer.setIcon(new BildBauer().createImageIcon("/img/buzzerRosaLabel.png"));
                    buzz = true;
                }

                if (e.getKeyCode() == 66) {
                    System.out.println("Grün");
                    buzzer.setIcon(new BildBauer().createImageIcon("/img/buzzerGruenLabel.png"));
                }

                if (e.getKeyCode() == 76) {
                    System.out.println("Blau");
                    buzzer.setIcon(new BildBauer().createImageIcon("/img/buzzerBlauLabel.png"));
                }
            }
            return false;
        }
    }

    public static void main(String[] a) {
        new MultiplayerFenster();
    }

}
