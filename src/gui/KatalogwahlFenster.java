package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Wir haben ActionListener implementiert damit man besser damit arbeiten kann.
 * In dieser Klasse wird das KatalogFenster erstellt, wo man dann über Buttons aussuchen kann,
 * was man lernen möchte oder ob man zum Menu zurück möchte.
 * Der eingeloggte Spieler kann sich auch wieder ausloggen.
 */
public class KatalogwahlFenster implements ActionListener {

    private SpeicherVokabelnLernen speicherVokabelnLernen;
    private JFrame katalogFenster;
    private BildButton deutschEnglisch;
    private BildButton englischDeutsch;
    private BildButton zurueck;
    private BildButton ausloggen;

    /**
     * Im Konstruktor wird das Fenster gebaut, die Größe davon festgelegt, die Sichtbarkeit und dem Hintergrund
     * ein Bild hinzugefügt wird.
     * Es werden 3 Buttons erstellt von der Klasse {@link gui.BildButton} und von der Klasse {@link gui.BildBauer}, daraufhin konnte man jedem Button ein
     * Bild hinzufügen die wir dem Panel hinzugefügt haben.
     * Die drei Buttons besitzen alle ein ActionListener.
     * @param s ist ein Objekt von der Klasse{@link gui.SpeicherVokabelnLernen}
     *          damit dort alles zwischengespeichert wird
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
        ausloggen = new BildButton(new BildBauer().createImageIcon("/Img/ausloggenButton.png"));

        //ActionListener
        deutschEnglisch.addActionListener(this);
        englischDeutsch.addActionListener(this);
        zurueck.addActionListener(this);
        ausloggen.addActionListener(this);

        //System.out.println("" + zwischendstand + fragenSpeicher.size() + antwortenSpeicher.size());

        //hier werden alle Elemente dem katalogPanel hinzugefügt
        katalogPanel.add(deutschEnglisch, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.NONE, new Insets(120, 0, 0, 0), 0, 0));
        katalogPanel.add(englischDeutsch, new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        katalogPanel.add(zurueck, new GridBagConstraints(0, 2, 0, 1, 1, 1, GridBagConstraints.PAGE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        katalogPanel.add(ausloggen, new GridBagConstraints(0, 3, 0, 1, 1, 1, GridBagConstraints.PAGE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        katalogBg.add(katalogPanel);

        //Komponenten zum Fenster hinzufügen
        katalogFenster.add(katalogBg);

        //Fenstergröße setzen und anzeigen lassen

        katalogFenster.setIconImage(new BildBauer().createImageIcon("/Img/vmWinIco.png").getImage());
        katalogFenster.setSize(415, 400);
        katalogFenster.setLocationRelativeTo(null);
        katalogFenster.setResizable(false);
        katalogFenster.setVisible(true);
    }

    /**
     * In dieser Methode überprüfen wir, welcher Button gedrückt wurde. Beim Drücken auf einen Button
     * kommt ein Klick Sound und das Katalogfenster wird geschlossen und dann das entsprechend neue Fenster geöffnet.
     * @param e damit wir ein Objekt von ActionEvent haben und dieses dann in der Methode benutzen können.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Überprüft, welcher Button gedrückt wurde
        if (e.getSource() == deutschEnglisch) {

            //Musik
            new Musik("src/sound/click01.wav").start();

            new DeEngFenster(speicherVokabelnLernen);
            katalogFenster.setVisible(false);
            katalogFenster.dispose();
        }

        if (e.getSource() == englischDeutsch) {

            //Musik
            new Musik("src/sound/click02.wav").start();

            new EngDeFenster(speicherVokabelnLernen);
            katalogFenster.setVisible(false);
            katalogFenster.dispose();
        }

        if (e.getSource() == zurueck) {

            //Musik
            new Musik("src/sound/click03.wav").start();

            katalogFenster.setVisible(false);
            katalogFenster.dispose();
            new MenuFenster(true, speicherVokabelnLernen);
        }

        if (e.getSource() == this.ausloggen) {

            //Musik
            new Musik("src/sound/click03.wav").start();

            katalogFenster.setVisible(false);
            katalogFenster.dispose();
            new MenuFenster(false, null);
        }
    }

}