package Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by e3_fernitz on 15.05.14.
 */
public class Login {


    private final ActionListener button_listener;

    public Login(final JFrame loginfenster) {
//
        button_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loginfenster.setVisible(false);
                //menu();
            }
        };

    }

    public ActionListener getButton_listener() {
        return button_listener;
    }
}


