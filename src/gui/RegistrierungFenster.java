package gui;

import Register.Registrieren;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Diese Klasse ist für die Erstellung des Registrierungs Fensters zuständig.
 * Neue Nutzer können sich hier einen eigenen Account zulegen.
 */
public class RegistrierungFenster {

    JFrame registrierungsFenster;
    RoundedTextField idText;
    RoundedTextField pwText;
    RoundedTextField pwNochmalText;

    /**
     * In dieser Methode wird das Fenster erstellt, der Hintergrund wird gesetzt und eine Weiterleitung
     * zum Loginfenster durch einen ActionListener
     */
    public RegistrierungFenster() {

        //Fenster für die Registrierung
        registrierungsFenster = new JFrame("Anmeldung");
        registrierungsFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel registrierungsBg = new BilderPanel("/Img/registrierungBg.png");

        //gui.BilderPanel
        JPanel registrierungsPanel = new JPanel(new GridBagLayout());
        registrierungsPanel.setOpaque(false);

        //Textfelder zum Abfragen der Daten
        idText = new RoundedTextField(12);
        pwText = new RoundedTextField(12);
        pwNochmalText = new RoundedTextField(12);


        //Buttons werden hier erstellt
        ImageIcon zumMenuIcon = new BildBauer().createImageIcon("/Img/cancelButton.png");
        BildButton zumMenu = new BildButton(zumMenuIcon, 400);

        ImageIcon signUpIcon = new BildBauer().createImageIcon("/Img/signUpButton.png");
        BildButton signUp = new BildButton(signUpIcon, 400);

//Zum Anzeigen der Passwortstaerke made by Defalt
       final JLabel passwortstaerke = new JLabel(" ");
        passwortstaerke.setOpaque(true);
        pwText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(pwText.getText().length() <= 4){
                    passwortstaerke.setBackground(Color.red);
                    passwortstaerke.setText("Das Passwort ist zu schwach");

                }
                if(pwText.getText().length() >= 4){
                    passwortstaerke.setBackground(Color.ORANGE);
                    passwortstaerke.setText("Das Passwort ist nahja");

                }
                if(pwText.getText().length() >= 6){
                    passwortstaerke.setBackground(Color.green);
                    passwortstaerke.setText("Das Passwort ist stark");

                }
            }
        });




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
                new Registrieren(pwText.getText(), idText.getText(), pwNochmalText.getText());
            }
        });

        //hier werden alle Elemente dem registrierungsPanel hinzugefügt
        registrierungsPanel.add(zumMenu, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 350, 0, 0), 0, 0));
        registrierungsPanel.add(idText, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(123, 150, 0, 0), 0, 0));
        registrierungsPanel.add(pwText, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(13, 150, 0, 0), 0, 0));
        registrierungsPanel.add(passwortstaerke, new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 200, 0, 0), 0, 0));
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
