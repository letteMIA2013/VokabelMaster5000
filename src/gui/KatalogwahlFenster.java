package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Frances Schmidt
 * on 11 Jun 2014
 * VokabelMaster5000
 */

/**
 * Wir haben ActionListener implementiert damit man besser damit arbeiten kann.
 * In dieser Klasse wird das KatalogFenster erstellt, wo man dann über Buttons aussuchen kann,
 * was man lernen möchte oder ob man zum Menu zurück möchte.
 */
public class KatalogwahlFenster implements ActionListener {

    SpeicherVokabelnLernen speicherVokabelnLernen;
    JFrame katalogFenster;
    BildButton deutschEnglisch;
    BildButton englischDeutsch;
    BildButton zurueck;

    /**
     *
     * @param s ist ein Objekt von der Klasse{@link gui.SpeicherVokabelnLernen}
     *           damit dort alles zwischengespeichert wird
     *  Im Konstruktor wird das Fenster gebaut, wo die Größe, die Sichtbarkeit und dem Hintergrund
     *  ein Bild hinzugefügt wird.
     *  Es werden 3 Buttons erstellt von der Klasse {@link gui.BildButton}, daraufhin konnte man jedem Button ein
     *  Bild hinzufügen die wir dem Panel hinzugefügt haben.
     *  Die 3 Buttons besitzen alle ein ActionListener.
     */
    public KatalogwahlFenster(SpeicherVokabelnLernen s) {
        speicherVokabelnLernen = s;

        //Fenster für die Katalogwahl
        katalogFenster = new JFrame("Katalogauswahl");
        katalogFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Hintergrundbild
        BilderPanel katalogBg = new BilderPanel("/Img/katalogwahlBg.png");

        //gui.BilderPanel
        JPanel katalogPanel = new JPanel(new GridBagLayout());
        katalogPanel.setOpaque(false);

        //Buttons werden hier erstellt
        deutschEnglisch = new BildButton(new BildBauer().createImageIcon("/Img/deEngButton.png"));
        englischDeutsch = new BildButton(new BildBauer().createImageIcon("/Img/engDeButton.png"));
        zurueck = new BildButton(new BildBauer().createImageIcon("/Img/zurueckGrossButton.png"));

        //ActionListener
        deutschEnglisch.addActionListener(this);
        englischDeutsch.addActionListener(this);
        zurueck.addActionListener(this);

        //System.out.println("" + zwischendstand + fragenSpeicher.size() + antwortenSpeicher.size());

        //hier werden alle Elemente dem katalogPanel hinzugefügt
        katalogPanel.add(deutschEnglisch, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.NONE, new Insets(120, 0, 0, 0), 0, 0));
        katalogPanel.add(englischDeutsch, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        katalogPanel.add(zurueck, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.PAGE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        katalogBg.add(katalogPanel);

        //Komponenten zum Fenster hinzufügen
        katalogFenster.add(katalogBg);

        //Fenstergröße setzen und anzeigen lassen
        katalogFenster.setSize(415, 400);
        katalogFenster.setLocationRelativeTo(null);
        katalogFenster.setResizable(false);
        katalogFenster.setVisible(true);
    }

    /**
     *
     * @param e damit wir ein Objekt von ActionEvent haben und dieses dann in der Methode benutzen können.
     *
     * In dieser Methode überprüfen wir welcher Button gedrückt wurde und beim Drücken auf einem Button
     * kommt ein Klick Sound und das katalogfenster wird geschlossen und dann das entsprechend neue Fenster geöffnet.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Überprüft, welcher Button gedrückt wurde
        if (e.getSource() == deutschEnglisch) {

            //Musik
            new Musik("src/sound/klick.wav").start();

            new DeEngFenster(speicherVokabelnLernen);
            katalogFenster.setVisible(false);
            katalogFenster.dispose();
        }

        if (e.getSource() == englischDeutsch) {

            //Musik
            new Musik("src/sound/klick.wav").start();

            new EngDeFenster(speicherVokabelnLernen);
            katalogFenster.setVisible(false);
            katalogFenster.dispose();
        }

        if (e.getSource() == zurueck) {

            //Musik
            new Musik("src/sound/klick.wav").start();

            katalogFenster.setVisible(false);
            katalogFenster.dispose();
            new MenuFenster(true, speicherVokabelnLernen);
        }
    }

}