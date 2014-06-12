package gui;

import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.lang.String;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class BildButton extends JButton {

    public BildButton(String name, int dimensionWidth) {

        setText(name);
        setPreferredSize(new Dimension(dimensionWidth, this.getPreferredSize().height));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setHorizontalAlignment(CENTER);

    }

    public BildButton(Icon icon) {

        setIcon(icon);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setHorizontalAlignment(CENTER);

    }

    public BildButton(Icon icon, int dimensionWidth) {

        setIcon(icon);
        setPreferredSize(new Dimension(dimensionWidth, this.getPreferredSize().height));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setHorizontalAlignment(CENTER);

    }

}