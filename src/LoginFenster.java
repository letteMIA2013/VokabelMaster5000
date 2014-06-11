import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

public class LoginFenster {

    public LoginFenster() {

        //Fenster für den Login
        final JFrame loginFenster = new JFrame("Anmeldung");
        loginFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel loginBg = new BilderPanel("Img/loginBg.png");

        //BilderPanel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false);

        //Textfelder zum Abfragen der Logindaten
        RoundedTextField idText = new RoundedTextField(12);
        RoundedPassField pwText = new RoundedPassField(12);

        //KeyListener
        idText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println("hi");
            }
        });

        //DeinButton werden hier erstellt
        ImageIcon zumMenuIcon = new BildBauer().createImageIcon("Img/cancelButton.png");
        BildButton zumMenu = new BildButton(zumMenuIcon, 400);

        ImageIcon registrierungIcon = new BildBauer().createImageIcon("Img/registrierungButton.png");
        BildButton registrierung = new BildButton(registrierungIcon, 400);

        ImageIcon loginIcon = new BildBauer().createImageIcon("Img/loginButton.png");
        BildButton login = new BildButton(loginIcon, 400);

        //ActionListener
        zumMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFenster.setVisible(false);
                loginFenster.dispose();
                new MenuFenster();
            }
        });

        registrierung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFenster.setVisible(false);
                loginFenster.dispose();
                new RegistrierungFenster();
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

    public static void main(String[] a) {

        new LoginFenster();

    }

}
