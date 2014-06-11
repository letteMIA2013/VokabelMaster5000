import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class DeEngFenster {

    Timer timer;
    int count;
    RoundedTextField eingabe;
    RoundedTextField ausgabe;
    BildButton weiter;

    public DeEngFenster() {

        //Fenster für die Katalogwahl
        final JFrame deEngFenster = new JFrame("Deutsch/Englisch");
        deEngFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel deEngBg = new BilderPanel("Img/deEngBg.png");

        //BilderPanel
        JPanel deEngPanel = new JPanel(new GridBagLayout());
        deEngPanel.setOpaque(false);

        //Zwischenstandlabel
        ImageIcon zwischenstandIcon = new BildBauer().createImageIcon("Img/zwischenstandLabel.png");
        MeinLabel zwischenstand = new MeinLabel(zwischenstandIcon,false);

        //Timer
        count = 10;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Zieht immer eine Sekunde ab
                count--;

                if(count == 0) {
                    eingabe.setEditable(false);
                    eingabe.setFocusable(false);
                    ausgabe.setBackground(new Color(205,38,38));
                    ausgabe.setText("hihi");
                    next();
                }

            }
        });

        timer.start();

        //Textfelder zur Eingabe und Ausgabe der Lösungen
        eingabe = new RoundedTextField(12);
        ausgabe = new RoundedTextField(12);
        ausgabe.setEditable(false);

        //Buttons werden hier erstellt
        ImageIcon zurueckIcon = new BildBauer().createImageIcon("Img/zurueckKleinButton.png");
        BildButton zurueck = new BildButton(zurueckIcon);

        ImageIcon OkIcon = new BildBauer().createImageIcon("Img/okButton.png");
        BildButton Ok = new BildButton(OkIcon);

        ImageIcon weiterIcon = new BildBauer().createImageIcon("Img/weiterButton.png");
        weiter = new BildButton(weiterIcon);

        zurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deEngFenster.setVisible(false);
                deEngFenster.dispose();
                new KatalogwahlFenster();
            }
        });

        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(eingabe.getText().equals("Test")) {
                    ausgabe.setBackground(new Color(180, 238, 180));
                    } else {
                        ausgabe.setBackground(new Color(205,38,38));
                        }

                eingabe.setEditable(false);
                timer.stop();
                next();

            }
        });

        //hier werden alle Elemente dem deEngPanel hinzugefügt
        deEngPanel.add(zwischenstand,new GridBagConstraints(0,0,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(72,0,0,0),0,0));
        deEngPanel.add(eingabe,new GridBagConstraints(0,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(25,150,0,0),0,0));
        deEngPanel.add(ausgabe,new GridBagConstraints(1,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(94,150,0,0),0,0));
        deEngPanel.add(zurueck,new GridBagConstraints(0,2,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(40,0,0,200),0,0));
        deEngPanel.add(Ok,new GridBagConstraints(1,2,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(40,0,0,0),0,0));
        deEngPanel.add(weiter,new GridBagConstraints(2,2,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(40,200,0,0),0,0));

        deEngBg.add(deEngPanel);

        //Komponenten zum Fenster hinzufügen
        deEngFenster.add(deEngBg);

        //Fenstergröße setzen und anzeigen lassen
        deEngFenster.setSize(415,400);
        deEngFenster.setLocationRelativeTo(null);
        deEngFenster.setResizable(false);
        deEngFenster.setVisible(true);

    }

    public void next() {

        weiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eingabe.setEditable(true);
                eingabe.setText("");
                eingabe.setFocusable(true);
                ausgabe.setBackground(Color.LIGHT_GRAY);
                ausgabe.setText("");
                count = 8;
                timer.start();
            }
        });

    }

    public static void main(String[] a) {

        new DeEngFenster();

    }

}
