import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.String;

public class LoginFenster {

    public LoginFenster() {

        //Fenster für den Login
        JFrame loginFenster = new JFrame("Anmeldung");
        loginFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel loginBg = new BilderPanel("Img/loginBg.png");

        //VM5000.BilderPanel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false);

        //Textfelder zum Abfragen der Logindaten
        JTextField idText = new JTextField(10);
        JTextField pwText = new JTextField(10);

        //VM5000.DeinButton werden hier erstellt
        ImageIcon zumMenuIcon = new BildBauer().createImageIcon("Img/cancelButton.png");
        DeinButton zumMenu = new DeinButton(zumMenuIcon,400);

        ImageIcon registrierungIcon = new BildBauer().createImageIcon("Img/registrierungButton.png");
        DeinButton registrierung = new DeinButton(registrierungIcon,400);

        ImageIcon loginIcon = new BildBauer().createImageIcon("Img/loginButton.png");
        DeinButton login = new DeinButton(loginIcon,400);

        //hier werden alle Elemente dem loginPanel hinzugefügt
        loginPanel.add(zumMenu,new GridBagConstraints(0,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,350,0,0),0,0));
        loginPanel.add(registrierung,new GridBagConstraints(0,2,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(30,0,0,0),0,0));
        loginPanel.add(idText,new GridBagConstraints(0,3,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(52,130,0,0),0,0));
        loginPanel.add(pwText,new GridBagConstraints(0,4,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(14,130,0,0),0,0));
        loginPanel.add(login,new GridBagConstraints(0,5,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(40,0,0,0),0,0));

        loginBg.add(loginPanel);

        //Komponente zum Fenster hinzufügen
        loginFenster.add(loginBg);


        //Fenstergröße setzen und anzeigen lassen
        loginFenster.setSize(415,400);
        loginFenster.setResizable(false);
        loginFenster.setVisible(true);

    }

    public static void main(String[] a) {

        new LoginFenster();

    }

}
