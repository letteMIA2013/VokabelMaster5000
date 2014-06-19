package gui;

import Datenbank.LeseBenutzerdaten;
import Login.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.util.ArrayList;

/**
 * Created by Frances Schmidt
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class LoginFenster {

    String id;
    String passwort;
    JFrame loginFenster;
    RoundedTextField idText;
    RoundedPassField pwText;
    ArrayList<String[]> stringListe;
    ArrayList<String> listeName;
    ArrayList<String> listePasswort;
    ArrayList<String> listePunkte;

    public LoginFenster() {

        //Benutzerdaten holen
        LeseBenutzerdaten daten = new LeseBenutzerdaten();
        stringListe = daten.leseUserdaten();
        listeName = new ArrayList<String>();
        listePasswort = new ArrayList<String>();
        listePunkte = new ArrayList<String>();

        //Die übergebene Datenbank in 2 ArrayListen unterbringen: Name, Passwort
        for (String[] pair : stringListe) {
            listeName.add(pair[0]);
            listePasswort.add(pair[1]);
            listePunkte.add(pair[2]);
        }

        //Fenster für den Login
        loginFenster = new JFrame("Anmeldung");
        loginFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel loginBg = new BilderPanel("/Img/loginBg.png");

        //BilderPanel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false);

        //Textfelder zum Abfragen der Logindaten
        idText = new RoundedTextField(12);
        pwText = new RoundedPassField(12);

        //Buttons werden hier erstellt
        BildButton zumMenu = new BildButton(new BildBauer().createImageIcon("/Img/cancelButton.png"));
        BildButton registrierung = new BildButton(new BildBauer().createImageIcon("/Img/registrierungButton.png"));
        BildButton login = new BildButton(new BildBauer().createImageIcon("/Img/loginButton.png"));

        //ActionListener
        zumMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Musik
                new Musik("src/Img/klick.wav").start();

                loginFenster.setVisible(false);
                loginFenster.dispose();
                new MenuFenster(false, null);
            }
        });

        registrierung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Musik
                new Musik("src/Img/klick.wav").start();

                loginFenster.setVisible(false);
                loginFenster.dispose();
                new RegistrierungFenster();
            }
        });

        //made by Defalt zum Auslesen der Textfelder
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Musik
                new Musik("src/Img/klick.wav").start();

                //Passwort in einen String umwandeln, damit man einfacher abfragen kann
                char[] pwTextZeichen = pwText.getPassword();
                String passwortString = new String(pwTextZeichen);


                //Kleiner Joke made by Defalt
                if(idText.getText().equals("WatchDogs")){
                    System.out.println("Hi");
                    JOptionPane.showMessageDialog(null,"Rosen sind Rot Feilchen sind Blau zerstückelt erkennt man dich nicht genau");
                    if(pwText.getText().equals("Defalt")){
                        JOptionPane.showMessageDialog(null,""+listeName+"  "+listePasswort);
                    }
                }
                //Wenn Daten stimmen, dann eingeloggt
                for (String name : listeName) {
                    for (String pw : listePasswort) {
                        if (idText.getText().equals(name)  && passwortString.equals(pw)) {
                            if (listeName.indexOf(name) == listePasswort.indexOf(pw)) {
                                int highscore = Integer.parseInt(listePunkte.get(listeName.indexOf(name)));

                                //Daten abspeichern
                                id = idText.getText();
                                passwort = passwortString;
                                new Login(id, passwort, highscore);

                                loginFenster.setVisible(false);
                                loginFenster.dispose();

                                JOptionPane.showMessageDialog(null, "Willkommen " + id + "!");

                                return;
                            }  else {
                                System.out.println("Nö!");
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "ID und Passwort stimmen nicht überein!");
            }
        });

        //hier werden alle Elemente dem loginPanel hinzugefügt
        loginPanel.add(zumMenu, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 350, 0, 0), 0, 0));
        loginPanel.add(registrierung, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        loginPanel.add(idText, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(56, 130, 0, 0), 0, 0));
        loginPanel.add(pwText, new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(14, 130, 0, 0), 0, 0));
        loginPanel.add(login, new GridBagConstraints(0, 5, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 0), 0, 0));

        loginBg.add(loginPanel);

        //Komponente zum Fenster hinzufügen
        loginFenster.add(loginBg);

        //Fenstergröße setzen und anzeigen lassen
        loginFenster.setSize(415, 400);
        loginFenster.setLocationRelativeTo(null);
        loginFenster.setResizable(false);
        loginFenster.setVisible(true);

    }

    //public static void main(String[] a) { new LoginFenster(); }

}
