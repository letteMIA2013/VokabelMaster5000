package Datenbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ka Yan Lam
 * on 15 Jun 2014
 * VokabelMaster5000
 */
public class LeseHighscore {

    public static FileReader fr;

    public static ArrayList<String[]> leseUserdaten() {

        try {
            fr = new FileReader("src/Datenbank/HighscoreListe.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String[]> b = new ArrayList();
        String zeile = "";

        do {

            //Daten werden aus der txt Datei gelesen und der ArrayList b hinzugefügt hierbei werden die Strings in
            // StringArrays umgewandelt in den Text vor und nach /
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
        //String zeile = br.readLine();

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return b;
    }
}
