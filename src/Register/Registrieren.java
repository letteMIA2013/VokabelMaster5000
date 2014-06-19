package Register;

import Datenbank.SchreibeBenutzerDaten;
import gui.RegistrierungFenster;

import javax.swing.*;

/**
 * Created by Maurice Fernitz
 * on 15 May 2014
 * VokabelMaster5000
 */

public class Registrieren {
private String neueid;
    private String neuespw;
    private String pwwiederholen;

    public Registrieren(String pw , String id, String pwWiederholen) {

        neueid = id;
        neuespw = pw;
        this.pwwiederholen = pwWiederholen;

        if(pw.equals(pwwiederholen)){
            SchreibeBenutzerDaten neuerBenutzer = new SchreibeBenutzerDaten();
            neuerBenutzer.benutzerAnlegen(neueid,neuespw);
        }
        else{
        JOptionPane.showMessageDialog(null,"Passwörter stimmen nicht überein!");

    }
    }

}
