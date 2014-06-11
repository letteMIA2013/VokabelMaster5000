import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.String;

public class MultiplayerFenster {

    public MultiplayerFenster() {

        //Fenster für den Multiplayer
        JFrame multiplayerFenster = new JFrame();
        multiplayerFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Fragepanel im Norden für den Timer, die Lösung der Frage und der Frage
        JPanel fragePanel = new JPanel(new GridBagLayout());

        //Felder für den Timer, die Lösung der Frage und der Frage
        MeinLabel timer = new MeinLabel("Timer",true,Color.CYAN);
        MeinLabel loesung = new MeinLabel("Lösung",true,Color.GREEN);
        MeinLabel frage = new MeinLabel("Vokabel",true,Color.YELLOW);

        //Timer, Lösung und Frage dem fragePanel hinzufügen
        fragePanel.add(timer, new GridBagConstraints(0,0,0,1,1,1,GridBagConstraints.LINE_START,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        fragePanel.add(loesung, new GridBagConstraints(1,0,0,1,1,1,GridBagConstraints.LINE_END,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        fragePanel.add(frage, new GridBagConstraints(0,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(40,5,20,5),0,0));

        //Antwortpanel in der Mitte für die Antwortmöglichkeiten und den Buzzer
        JPanel antwortPanel = new JPanel();

        //Buttons für die Antworten
        DeinButton antwortEins = new DeinButton("Antwort Eins",500);
        DeinButton antwortZwei = new DeinButton("Antwort Zwei",500);
        DeinButton antwortDrei = new DeinButton("Antwort Drei",500);
        DeinButton antwortVier = new DeinButton("Antwort Vier",500);

        //Buzzer
        MeinLabel buzzer = new MeinLabel("Buzzer",true,Color.RED);

        //antwortEins, antwortZwei, antwortDrei, antwortVier und buzzer dem antwortPanel hinzufügen
        antwortPanel.add(antwortEins);
        antwortPanel.add(antwortZwei);
        antwortPanel.add(antwortDrei);
        antwortPanel.add(antwortVier);
        antwortPanel.add(buzzer, BorderLayout.SOUTH);

        //Spielerpanel im Süden für die Spieler + Punktestand
        JPanel spielerPanel = new JPanel(new GridBagLayout());

        //Felder für die 3 Spieler + Punktestand
        MeinLabel spielerEins = new MeinLabel("Spieler 1",true,Color.LIGHT_GRAY);
        MeinLabel spielerEinsPunkte = new MeinLabel("S1 Punkte",true,Color.WHITE);
        MeinLabel spielerZwei = new MeinLabel("Spieler 2",true,Color.LIGHT_GRAY);
        MeinLabel spielerZweiPunkte = new MeinLabel("S2 Punkte",true,Color.WHITE);
        MeinLabel spielerDrei = new MeinLabel("Spieler 3",true,Color.LIGHT_GRAY);
        MeinLabel spielerDreiPunkte = new MeinLabel("S3 Punkte",true,Color.WHITE);

        //Labels dem spielerPanel hinzufügen
        spielerPanel.add(spielerEins,new GridBagConstraints(0,0,0,1,1,1,GridBagConstraints.LINE_START,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        spielerPanel.add(spielerZwei,new GridBagConstraints(1,0,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        spielerPanel.add(spielerDrei,new GridBagConstraints(2,0,0,1,1,1,GridBagConstraints.LINE_END,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        spielerPanel.add(spielerEinsPunkte,new GridBagConstraints(0,1,0,1,1,1,GridBagConstraints.LINE_START,GridBagConstraints.NONE,new Insets(5,0,5,0),0,0));
        spielerPanel.add(spielerZweiPunkte,new GridBagConstraints(1,1,0,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,0,5,0),0,0));
        spielerPanel.add(spielerDreiPunkte,new GridBagConstraints(2,1,0,1,1,1,GridBagConstraints.LINE_END,GridBagConstraints.NONE,new Insets(5,0,5,0),0,0));

        //Komponenten zum multiplayerFenster hinzufügen
        multiplayerFenster.add(fragePanel, BorderLayout.NORTH);
        multiplayerFenster.add(antwortPanel);
        multiplayerFenster.add(spielerPanel, BorderLayout.SOUTH);

        //Fenstergröße setzen und anzeigen lassen
        multiplayerFenster.setSize(415,400);
        multiplayerFenster.setResizable(false);
        multiplayerFenster.setVisible(true);

    }

    public static void main(String[] a){

        new MultiplayerFenster();

    }

}
