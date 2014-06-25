package Datenbank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Ka Yan Lam
 * on 21 Jun 2014
 * VokabelMaster5000
 */
public class SchreibeHighscore {

    //Enums zum anzeigen des Fehlertyps
    public enum FEHLER_TYP{
        KEIN_FEHLER,
        UNBEKANNTER_NUTZER
    }

    public static FEHLER_TYP highScoreAendern(String id, int neuerWert) {
        PrintWriter pw = null;

        ArrayList<String[]> alleVorhandenenDaten = LeseHighscore.leseUserdaten();

        boolean datenAktualisiert = false;

        try
        {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("src/Datenbank/HighscoreListe.txt")));

            for (String[] pair : alleVorhandenenDaten) {

                if (pair[0].equals(id)){
                    pw.println( pair[0] + "/" + neuerWert);
                    datenAktualisiert = true;
                }
                else{
                    pw.println( pair[0] + "/" + pair[1]);
                }

            }

        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {

            if(pw != null)
            {
                pw.flush();
                pw.close();
            }
        }
        return datenAktualisiert? FEHLER_TYP.KEIN_FEHLER: FEHLER_TYP.UNBEKANNTER_NUTZER;
    }

    public static FEHLER_TYP benutzerAnlegen(String id) {
        PrintWriter pw = null;
        ArrayList<String[]> alleVorhandenenDaten = LeseHighscore.leseUserdaten();

        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("src/Datenbank/HighscoreListe.txt")));
            if (alleVorhandenenDaten.size() >= 0) {
                System.out.println("hiiii" + alleVorhandenenDaten.size());
                for (String[] pair : alleVorhandenenDaten) {
                    pw.println(pair[0] + "/" + pair[1]);
                }
                pw.println(id + "/" + 0);
            } else {
                pw.println(id + "/" + 0);
            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {

            if(pw != null)
            {
                pw.flush();
                pw.close();
            }
        }
        return FEHLER_TYP.KEIN_FEHLER;
    }

    /*public static void main(String[] args) {
        FEHLER_TYP ky = SchreibeHighscore.highScoreAendern("Ka Yan00", 30);
        System.out.println(ky);
        FEHLER_TYP neu = SchreibeHighscore.benutzerAnlegen("Ka Yan00");
        System.out.println(neu);

        ArrayList<String[]> strings = LeseHighscore.leseUserdaten();
        for (String[] string : strings) {
            System.out.println(string[0] + ", " + string[1]);
        }
    }*/
}
