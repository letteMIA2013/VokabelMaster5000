package clientserver;

import java.io.Serializable;
import java.lang.String;import java.util.ArrayList;

/**
 * Created by e2_parkhomenko on 18.06.14.
 */
public class ClientData implements Serializable {
    ArrayList<String> spielerNamen;
    String message;




    /**
     * Variablen die an den server gesenden werden muessen hier geschrieben werden
     * Diese Klasse ist Serializable
     * Bitte "transient" als keyword for der Variablen benutzen wenn diese nicht serialisiert werden soll
     */

    public ClientData(String namen) {

        spielerNamen.add( namen );

    }

    public int getId(){
        return -1;
    }

    public ArrayList<String> getSpielerNamen() {
        return spielerNamen;
    }

    public String getMessage() {
         return message;
    }


}
