package Login;

import gui.BildBauer;
import gui.LoginFenster;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by e3_fernitz on 15.05.14.
 */
public class Login {
private String id;

    private String passwort;


    public Login(String id, String passwort) {
    this.id = id;
        this.passwort = passwort;

        auslesen();
    }

    public String auslesen(){
        System.out.println("Die id ist:"+id+"Das Passwort ist"+ passwort);
    return id + passwort;

}

}







