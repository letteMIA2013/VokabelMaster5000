import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.String;

/**
 * Created by Frances Schmidt
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class RegistrierungFenster {

    public RegistrierungFenster() {

        //Fenster für die Registrierung
        JFrame registrierungsFenster = new JFrame("Anmeldung");
        registrierungsFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel registrierungsBg = new BilderPanel("Img/registrierungBg.png");

        //BilderPanel
        JPanel registrierungsPanel = new JPanel(new GridBagLayout());
        registrierungsPanel.setOpaque(false);

        //Textfelder zum Abfragen der Daten
        JTextField idText = new JTextField(12);
        JTextField pwText = new JTextField(12);
        JTextField pwNochmalText = new JTextField(12);

        //Buttons werden hier erstellt
        ImageIcon zumMenuIcon = new BildBauer().createImageIcon("Img/cancelButton.png");
        BildButton zumMenu = new BildButton(zumMenuIcon,400);

        ImageIcon signUpIcon = new BildBauer().createImageIcon("Img/signUpButton.png");
        BildButton signUp = new BildButton(signUpIcon,400);

        //hier werden alle Elemente dem registrierungsPanel hinzugefügt
        registrierungsPanel.add(zumMenu,new GridBagConstraints(0,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,350,0,0),0,0));
        registrierungsPanel.add(idText,new GridBagConstraints(0,2,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(120,150,0,0),0,0));
        registrierungsPanel.add(pwText,new GridBagConstraints(0,3,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(13,150,0,0),0,0));
        registrierungsPanel.add(pwNochmalText,new GridBagConstraints(0,4,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(12,150,0,0),0,0));
        registrierungsPanel.add(signUp,new GridBagConstraints(0,5,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(40,0,0,0),0,0));

        registrierungsBg.add(registrierungsPanel);

        //Komponenten zum Fenster hinzufügen
        registrierungsFenster.add(registrierungsBg);

        //Fenstergröße setzen und anzeigen lassen
        registrierungsFenster.setSize(415, 400);
        registrierungsFenster.setResizable(false);
        registrierungsFenster.setVisible(true);

    }

    public static void main(String[] a) {

        new RegistrierungFenster();

    }

}
