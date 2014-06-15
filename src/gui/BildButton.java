package gui;

import javax.swing.*;
import java.awt.Dimension;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

public class BildButton extends JButton {

    public BildButton(Icon icon) {
        setIcon(icon);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
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
