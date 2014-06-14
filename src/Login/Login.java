package Login;

import Datenbank.LeseDeEngVok;
import gui.KatalogwahlFenster;
import gui.SpeicherVokabelnLernen;

import java.util.ArrayList;

/**
 * Created by Maurice Fernitz
 * on 15 May 2014
 * VokabelMaster5000
 */

public class Login {

    String id;
    String passwort;

    public Login(String id, String passwort) {
        this.id = id;
        this.passwort = passwort;

        auslesen();
        start();
    }

    public String auslesen() {

        //System.out.println("ID: " + id + "\nPasswort: " + passwort);
        System.out.println(id + " wurde erfolgreich eingeloggt.");

        return id + passwort;
    }

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
        speicherVokabelnLernen.setZwSpDeEng(1);
        speicherVokabelnLernen.setZwSpEngDe(1);
        speicherVokabelnLernen.setFragenListeDeEng(listeFrageDeEng);
        speicherVokabelnLernen.setFragenListeEngDe(listeFrageEngDe);
        speicherVokabelnLernen.setAntwortenListeDeEng(listeAntwortDeEng);
        speicherVokabelnLernen.setAntwortenListeEngDe(listeAntwortEngDe);
        speicherVokabelnLernen.setName(id);
        speicherVokabelnLernen.setPasswort(passwort);

        new KatalogwahlFenster(speicherVokabelnLernen);
    }

}







