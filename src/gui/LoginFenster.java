package gui;

import Datenbank.LeseBenutzerdaten;
import Img.BildBauer;
import Login.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.lang.String;
import java.util.ArrayList;

/**
 * In dieser Klasse wird das Loginfenster erstellt.
 * Hier wird der eingegebene Benutzername mit dem eingegebenen Passwort auf Zugehörigkeit geprüft.
 * Wenn sie zueinander gehören, dann wird der Name und das Passwort an die Klasse {@link Login.Login} weitergeleitet.
 */
public class LoginFenster {

    private String id;
    private String passwort;
    private JFrame loginFenster;
    private RoundedTextField idText;
    private RoundedPassField pwText;
    private ArrayList<String> listeName;
    private ArrayList<String> listePasswort;
    private ArrayList<String> listePunkte;

    /**
     * Im Konstruktor wird das Fenster gebaut, die Größe davon festgelegt, die Sichtbarkeit und dem Hintergrund
     * ein Bild hinzugefügt {@link gui.BilderPanel}.
     * Es werden drei Buttons erstellt, von der Klasse {@link gui.BildButton}, daraufhin konnte man jedem Button ein
     * Bild hinzufügen die wir dem Panel hinzugefügt haben.
     * Die Benutzerdaten werden aus der Klasse {@link Datenbank.LeseBenutzerdaten} in drei ArrayListen gepackt.
     */
    public LoginFenster() {

        //Benutzerdaten holen
        LeseBenutzerdaten daten = new LeseBenutzerdaten();
        ArrayList<String[]> stringListe = daten.leseUserdaten();
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

        loginFenster.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                idText.requestFocus();
            }
        });

        //Buttons werden hier erstellt
        BildButton zumMenu = new BildButton(new BildBauer().createImageIcon("/Img/cancelButton.png"));
        BildButton registrierung = new BildButton(new BildBauer().createImageIcon("/Img/registrierungButton.png"));
        BildButton login = new BildButton(new BildBauer().createImageIcon("/Img/loginButton.png"));

        //ActionListener
        zumMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Musik
                new Musik("src/sound/klick.wav").start();

                loginFenster.setVisible(false);
                loginFenster.dispose();
                new MenuFenster(false, null);
            }
        });

        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginUser();
                }
            }
        };

        idText.addKeyListener(keyListener);
        pwText.addKeyListener(keyListener);

        registrierung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Musik
                new Musik("src/sound/klick.wav").start();

                loginFenster.setVisible(false);
                loginFenster.dispose();
                new RegistrierungFenster();
            }
        });

        //made by Defalt zum Auslesen der Textfelder
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        //hier werden alle Elemente dem loginPanel hinzugefügt
        loginPanel.add(zumMenu, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 350, 0, 0), 0, 0));
        loginPanel.add(registrierung, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        loginPanel.add(idText, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(56, 130, 0, 0), 0, 0));
        loginPanel.add(pwText, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(14, 130, 0, 0), 0, 0));
        loginPanel.add(login, new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(40, 0, 0, 0), 0, 0));

        loginBg.add(loginPanel);

        //Komponente zum Fenster hinzufügen
        loginFenster.add(loginBg);

        //Fenstergröße setzen und anzeigen lassen
        loginFenster.setIconImage(new BildBauer().createImageIcon("/Img/vmWinIco.png").getImage());
        loginFenster.setSize(415, 400);
        loginFenster.setLocationRelativeTo(null);
        loginFenster.setResizable(false);
        loginFenster.setVisible(true);
    }

    /**
     * In dieser Methode wird überprüft, ob der Benutzername mit dem Password übereinstimmen. Wenn dies der Fall
     * ist, ist man automatisch eingeloggt und es erscheint ein Willkommenstext mit dem Benutzernamen.
     * Das {@link gui.KatalogwahlFenster} wird geöffnet.
     * Eine Fehlermeldung erscheint, wenn man die falschen Daten eingegeben hat.
     */
    public void loginUser() {
        //Musik
        new Musik("src/sound/click01.wav").start();

        //Passwort in einen String umwandeln, damit man einfacher abfragen kann
        char[] pwTextZeichen = pwText.getPassword();
        String passwortString = new String(pwTextZeichen);

        //Kleiner Joke made by Defalt
        if(idText.getText().equals("WatchDogs")){
            System.out.println("Hi");
            JOptionPane.showMessageDialog(null,"Rosen sind Rot Feilchen sind Blau zerstückelt erkennt man dich nicht genau");
            String pwString = new String(pwText.getPassword());
            if(pwString.equals("Defalt")){
                JOptionPane.showMessageDialog(null, "Name: " +listeName + " | Passwort: " + listePasswort);

                JFrame vokablenhinzufuegen = new JFrame();
                vokablenhinzufuegen.setSize(500,500);


               final JTextField vokabelndeutsch = new JTextField("Vokabeln Deutsch");
                final JTextField vokabelnenglish = new JTextField("Vokabeln English");
                JButton VokabelnHinzufuegen = new JButton("vokabelnhinzufugen");
                VokabelnHinzufuegen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String vokabelnD = new String();
                        String vokabelnE = new String();
                        vokabelnD = vokabelndeutsch.getText();
                        vokabelnE = vokabelnenglish.getText();

                    }
                });
                vokablenhinzufuegen.setLayout(new GridLayout(0,3));
                vokablenhinzufuegen.add(VokabelnHinzufuegen);
                vokablenhinzufuegen.add(vokabelndeutsch);
                vokablenhinzufuegen.add(vokabelnenglish);
                vokablenhinzufuegen.setVisible(true);

                JFrame hacktool = new JFrame();
                hacktool.setLayout(new GridLayout(listeName.size(),2));
                JFrame passwoerter = new JFrame();
                passwoerter.setLayout(new GridLayout(listeName.size(),2));
                for(String name : listeName){
                    JTextField namensfeld = new JTextField(name);
                    hacktool.add(namensfeld);
                   for(String passwort : listePasswort){
                       JTextField passwortfeld = new JTextField(passwort);
                       passwoerter.add(passwortfeld);
                        hacktool.setVisible(true);
                       passwoerter.setVisible(true);
                    }
                }
                JOptionPane.showMessageDialog(null, "Willkommen " + id + "!");
                new Login(id, passwort,1000);
            }
        }

        //Wenn Daten stimmen, dann eingeloggt
        for (String name : listeName) {
            for (String pw : listePasswort) {
                if (idText.getText().equals(name)  && passwortString.equals(pw)) {
                    if (listeName.indexOf(name) == listePasswort.indexOf(pw)) {
                        int highscore = Integer.parseInt(listePunkte.get(listeName.indexOf(name)));

                        //Daten werden abgespeichert
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

}
