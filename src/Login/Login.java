package Login;

import Datenbank.LeseDeEngVok;
import gui.KatalogwahlFenster;
import gui.SpeicherVokabelnLernen;

import java.util.ArrayList;

/**
 * Maurice
 */

/**
 * Die Klasse überprüft ob die im Loginfenster eingegeben namen vorhanden sind wenn ja werden die Daten aus der Datenbank ausgelesen
 */
public class Login {

    String id;
    String passwort;
    int punkte;

    public Login(String id, String passwort, int punkte) {
        this.id = id;
        this.passwort = passwort;
        this.punkte  = punkte;

        auslesen();
        start();
    }

    /**
     * Zum Testen.
     * @return Name und Highscore
     */
    public String auslesen() {

        //System.out.println("ID: " + id + "\nPasswort: " + passwort);
        System.out.println(id + " wurde erfolgreich eingeloggt. Highscore: " + punkte);

        return id + " wurde erfolgreich eingeloggt. Highscore: " + punkte;
    }

    /**
     * Erstellt die Zwischenablage und packt die Fragen und Antworten in jeweils eine Arraylist.
     * Die Fragen und Antworten werden aus der Datenbank ausgelesen. {@link Datenbank.LeseDeEngVok}
     */
    public void start() {

        //Vokabeln aus der Datenbank
        LeseDeEngVok daten = new LeseDeEngVok();
        ArrayList<String[]> stringListe = daten.getB();
        ArrayList<String> listeFrageDeEng = new ArrayList<String>();
        ArrayList<String> listeAntwortDeEng = new ArrayList<String>();
        ArrayList<String> listeFrageEngDe = new ArrayList<String>();
        ArrayList<String> listeAntwortEngDe = new ArrayList<String>();

        //Die übergebene Datenbank in 2 ArrayListen unterbringen: Fragen, Antworten
        for (String[] pair : stringListe) {
            listeFrageDeEng.add(pair[0]);
            listeAntwortDeEng.add(pair[1]);
        }

        for (String[] pair : stringListe) {
            listeFrageEngDe.add(pair[1]);
            listeAntwortEngDe.add(pair[0]);
        }

        SpeicherVokabelnLernen speicherVokabelnLernen = new SpeicherVokabelnLernen();
        speicherVokabelnLernen.setZwischenStandDeEng(1);
        speicherVokabelnLernen.setZwischenStandEngDe(1);
        speicherVokabelnLernen.setAlleFragenListeDeEng(listeFrageDeEng);
        speicherVokabelnLernen.setAlleFragenListeEngDe(listeFrageEngDe);
        speicherVokabelnLernen.setAlleAntwortenListeDeEng(listeAntwortDeEng);
        speicherVokabelnLernen.setAlleAntwortenListeEngDe(listeAntwortEngDe);
        speicherVokabelnLernen.setName(id);
        speicherVokabelnLernen.setPasswort(passwort);
        speicherVokabelnLernen.setIstAngemeldet(true);
        speicherVokabelnLernen.setPunkte(punkte);

        new KatalogwahlFenster(speicherVokabelnLernen);
    }

}







