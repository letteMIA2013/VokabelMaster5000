package Datenbank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Darleen on 04.06.14.
 */
public class SchreibeBenutzerDaten
{
    //Enums zum anzeigen des Fehlertyps
    public enum FEHLER_TYP{
        FILE_ZUGRIFF,
        KEIN_PASSWORT,
        NUTZER_SCHON_VORHANDEN,
        KEIN_FEHLER,
        UNBEKANNTER_NUTZER;
    }

    public static FEHLER_TYP highScoreAendern(String id, int neuerWert) {
        PrintWriter pw = null;

        ArrayList<String[]> alleVorhandenenDaten = LeseBenutzerdaten.leseUserdaten();


        boolean datenAktualisiert = false;

        try
        {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("src/Datenbank/UserLogin.txt")));

            for (String[] pair : alleVorhandenenDaten) {

                if (pair[0].equals(id)){
                    pw.println( pair[0] + "/" + pair[1] + "/" + neuerWert);
                    datenAktualisiert = true;
                }
                else{
                    pw.println( pair[0] + "/" + pair[1] + "/" + pair[2]);
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


    public static FEHLER_TYP benutzerAnlegen(String id, String passwort) {
        PrintWriter pw = null;

        ArrayList<String[]> alleVorhandenenDaten = LeseBenutzerdaten.leseUserdaten();

        if(passwort == null || passwort.equals("")){
            return FEHLER_TYP.KEIN_PASSWORT;
        }

        for (String[] datenSatz : alleVorhandenenDaten) {
            if (datenSatz[0].equals(id)){
                return FEHLER_TYP.NUTZER_SCHON_VORHANDEN;
            }
        }




        try
        {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("src/Datenbank/UserLogin.txt")));
            for (String[] pair : alleVorhandenenDaten) {


            pw.println( pair[0] + "/" + pair[1] + "/" + pair[2]);

            }
            pw.println(id + "/" + passwort + "/" + 0);
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

    /**
     * nur zum testen
     * @param args
     */
    public static void main(String[] args) {
        FEHLER_TYP fehler_typ = SchreibeBenutzerDaten.benutzerAnlegen("Horst", "Hotte");
        System.out.println(fehler_typ);
        FEHLER_TYP horst = SchreibeBenutzerDaten.highScoreAendern("Horst", 45);
        System.out.println(horst);

        ArrayList<String[]> strings = LeseBenutzerdaten.leseUserdaten();
        for (String[] string : strings) {
            System.out.println(string[0] + ", " + string[2]);
        }
    }
}