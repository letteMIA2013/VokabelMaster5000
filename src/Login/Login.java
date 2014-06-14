package Login;

/**
 * Created by Maurice Fernitz
 * on 15 May 2014
 * VokabelMaster5000
 */

public class Login {

    private String id;
    private String passwort;

    public Login(String id, String passwort) {

        this.id = id;
        this.passwort = passwort;

        auslesen();

    }

    public String auslesen() {

        System.out.println("ID: " + id + "\nPasswort: " + passwort);
        return id + passwort;

    }

}







