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
        KEIN_FEHLER
    }


    public FEHLER_TYP benutzerAnlegen(String id, String passwort) {
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


    public static void main(String[] args) {
        FEHLER_TYP fehler_typ = new SchreibeBenutzerDaten().benutzerAnlegen("Horst", "Hotte");
        System.out.println(fehler_typ);
    }
}
