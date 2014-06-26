import gui.MenuFenster;
import gui.Musik;

/**
 * Startet das Programm Vokabel Master 5000.
 */
public class Start {

    public static void main(String[] a) {

        new MenuFenster(false, null);
        new Musik("src/sound/intro.wav").start();
    }

}