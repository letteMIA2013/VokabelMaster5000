package gui;

import Register.Registrieren;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.String;

/**
 * Created by Frances Schmidt
 * on 11 Jun 2014
 * VokabelMaster5000
 */

/**
 * Diese Klasse ist für die Erstellung des Registrierungs Fensters zuständig
 */
public class RegistrierungFenster {

    /**
     * In dieser Methode wird das Fenster erstellt, der Hintergrund wird gesetzt und eine weiterleitung zum Loginfenster durch ein ActionListener
     */
    public RegistrierungFenster() {

        //Fenster für die Registrierung
        final JFrame registrierungsFenster = new JFrame("Anmeldung");
        registrierungsFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel registrierungsBg = new BilderPanel("/Img/registrierungBg.png");

        //gui.BilderPanel
       final JPanel registrierungsPanel = new JPanel(new GridBagLayout());
        registrierungsPanel.setOpaque(false);

        //Textfelder zum Abfragen der Daten
        final RoundedTextField idText = new RoundedTextField(12);
       final RoundedTextField pwText = new RoundedTextField(12);
       final  RoundedTextField pwNochmalText = new RoundedTextField(12);


        //Zum Passwortstärke anzeigen
       final  JLabel passwortstaerke = new JLabel("   ");
        passwortstaerke.setBackground(Color.black);
        pwText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if(pwText.getText().length() <= 6){
                    System.out.println("Anzahl der Zeichen: " + pwText.getText().length());
                    passwortstaerke.setBackground(Color.red);
                    passwortstaerke.setText("Das Passwort ist schwach");
                    passwortstaerke.setOpaque(true);
//                    passwortstaerke.setSize(100,15);
                    passwortstaerke.setVisible(true);

                }

                if(pwText.getText().length() >= 6){
                    System.out.println("Anzahl der Zeichen: " + pwText.getText().length());
                    passwortstaerke.setBackground(Color.ORANGE);
                    passwortstaerke.setText("Das Passwort ist nahja");
                    passwortstaerke.setOpaque(true);
//                    passwortstaerke.setSize(100,15);
                    passwortstaerke.setVisible(true);

                }
            }
        });


        //Buttons werden hier erstellt
        ImageIcon zumMenuIcon = new BildBauer().createImageIcon("/Img/cancelButton.png");
        BildButton zumMenu = new BildButton(zumMenuIcon, 400);

        ImageIcon signUpIcon = new BildBauer().createImageIcon("/Img/signUpButton.png");
        BildButton signUp = new BildButton(signUpIcon, 400);





        //ActionListener
        zumMenu.addActionListener(new ActionListener() {
            @Override
            /**
             * Dieser ActionListener dient nur dazu, eine Weiterleitung zum Loginfenster zu bilden
             */
            public void actionPerformed(ActionEvent e) {
                registrierungsFenster.setVisible(false);
                registrierungsFenster.dispose();
                new LoginFenster();
            }
        });
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //made by Defalt rgistrieren

                Registrieren rgister = new Registrieren(pwText.getText(),idText.getText(),pwNochmalText.getText());
            }
        });

        //hier werden alle Elemente dem registrierungsPanel hinzugefügt
        registrierungsPanel.add(zumMenu, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 350, 0, 0), 0, 0));
        registrierungsPanel.add(idText, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(123, 150, 0, 0), 0, 0));
        registrierungsPanel.add(pwText, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(13, 150, 0, 0), 0, 0));
        registrierungsPanel.add(passwortstaerke, new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 200,0,0), 0, 0));
        registrierungsPanel.add(pwNochmalText, new GridBagConstraints(0, 5, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 150, 0, 0), 0, 0));
        registrierungsPanel.add(signUp, new GridBagConstraints(0, 6, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 0), 0, 0));


        registrierungsBg.add(registrierungsPanel);

        //Komponenten zum Fenster hinzufügen
        registrierungsFenster.add(registrierungsBg);

        //Fenstergröße setzen und anzeigen lassen
        registrierungsFenster.setSize(415, 400);
        registrierungsFenster.setLocationRelativeTo(null);
        registrierungsFenster.setResizable(false);
        registrierungsFenster.setVisible(true);

    }

}
