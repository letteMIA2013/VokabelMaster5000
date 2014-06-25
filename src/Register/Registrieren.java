package Register;

import Datenbank.SchreibeBenutzerDaten;

import javax.swing.*;

/**
 * Created by Maurice Fernitz
 * on 15 May 2014
 * VokabelMaster5000
 */

public class Registrieren {

    public Registrieren(String pw , String id, String pwWiederholen) {

        if(pw.equals(pwWiederholen)){
            SchreibeBenutzerDaten neuerBenutzer = new SchreibeBenutzerDaten();
            neuerBenutzer.benutzerAnlegen(id,pw);

        }
        else{
        JOptionPane.showMessageDialog(null,"Passwörter stimmen nicht überein!");

    }
    }

}
