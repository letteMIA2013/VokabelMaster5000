package gui;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.lang.String;

/**
 * Created by Ka Yan Lam
 * on 11 Jun 2014
 * VokabelMaster5000
 */

/**
 * Diese Klasse erweitert JLabel
 */
public class MeinLabel extends JLabel {

    public MeinLabel(String text) {
        setText(text);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setOpaque(false);
    }

    public MeinLabel(Icon icon, String text) {
        setIcon(icon);
        setText(text);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setOpaque(false);
    }

    public MeinLabel(Icon icon) {
        setIcon(icon);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setOpaque(false);
    }

}
