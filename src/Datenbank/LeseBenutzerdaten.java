package Datenbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Darleen & Marcel
 */

    /**
     * Diese Methode ließt die Daten aus der UserLogin.txt datei aus spaltet <br></br> die Daten zu String
     * Arrays auf uns packt sie in eine ArrayList.<br></br> Am Ende wird die ArrayListe wiedergegeben.
     * return ArrayList<String[]>
     */
    public class LeseBenutzerdaten {

        /**Diese Methode ließt die Daten aus der UserLogin.txt datei aus spaltet <br></br> die Daten zu String
         * Arrays auf uns packt sie in eine ArrayList.<br></br> Am Ende wird die ArrayListe wiedergegeben..
         * return ArrayList<String[]> Die Liste mit den ganzen vorhandenen Benutzernamen.
         */
        public static ArrayList<String[]> leseUserdaten() {
            FileReader fr = null;
            try {
                fr = new FileReader("src/Datenbank/UserLogin.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String[]> b = new ArrayList();
            String zeile = "";

            do{
                //Daten werden aus der txt Datei gelesen und der ArrayList b hinzugefügt hierbei werden die Strings in
                // StringArrays umgewandelt in den Text vor und nach /



                try {
                    zeile = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                if (zeile != null){
                    String[] split = zeile.split("/");
                    b.add(split);

                }
            }
            while( zeile != null);

            //String zeile = br.readLine();


            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();


        }
            return b;
        }


    }

