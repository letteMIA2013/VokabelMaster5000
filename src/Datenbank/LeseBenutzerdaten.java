package Datenbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Darleen Eckmiller
 * on 04 Jun 2014
 * VokabelMaster5000
 */

public class LeseBenutzerdaten {

    FileReader fr;
    ArrayList<String[]> b;

    public LeseBenutzerdaten() {

        try {
            fr = new FileReader("src/Datenbank/UserLogin.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        b = new ArrayList<String[]>();
        String zeile = "";


        do {

            //Daten werden aus der txt Datei gelesen und der ArrayList b hinzugefügt hierbei werden die Strings in
            //StringArrays umgewandelt in den Text vor und nach /

            try {
                zeile = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (zeile != null) {
                String[] split = zeile.split("/");
                b.add(split);

            }
        }
        while (zeile != null);

        /*for (String[] pair : b) {
            System.out.println("User: " + pair[0] + " -> " + "Passwort: " + pair[1] + " -> " + "Highscore: " + pair[2]);
        }*/

        //String zeile = br.readLine();

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String[]> getB() {
        return b;
    }
}
