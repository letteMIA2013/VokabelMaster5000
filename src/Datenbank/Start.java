package Datenbank;

import gui.MenuFenster;
import gui.Musik;

/**
 * Created by Ka Yan Lam
 * on 13 Jun 2014
 * VokabelMaster5000
 */

public class Start {

    public static void main(String[] a) {
        new MenuFenster(false, null);
        new Musik("src/Img/intro.wav").start();
    }

}
