package gui;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.lang.String;

/**
 * Ka Yan & Frances
 */

/**
 * Diese Klasse erweitert JLabel mit verschiedenen Konstruktorenauswahl.
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
