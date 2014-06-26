package Register;

import Datenbank.SchreibeBenutzerDaten;

import javax.swing.*;

/**
 * Maurice
 */

/**
 * In der Klasse wird überprüft, ob die Passwörter identisch sind.
 * Falls sie stimmen, wird der gewünschte Benutzername mit Passwort in die Datenbank mit {@link Datenbank.SchreibeBenutzerDaten}
 * eingetragen.
 */
public class Registrieren {

    public Registrieren(String pw, String id, String pwWiederholen) {

        if (pw.equals(pwWiederholen)) {
            SchreibeBenutzerDaten neuerBenutzer = new SchreibeBenutzerDaten();
            neuerBenutzer.benutzerAnlegen(id, pw);

        } else {
            JOptionPane.showMessageDialog(null, "Passwörter stimmen nicht überein!");

        }
    }

}
