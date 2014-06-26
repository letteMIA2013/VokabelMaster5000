package gui;

import javax.swing.*;
import java.awt.Dimension;

/**
 * Diese Klasse erweitert die Klasse {@link javax.swing.JButton}.
 * Sie verändert das Aussehen des Buttons, indem sie den Rahmen und den Fokus rausnimmt.
 * Außerdem werden alle Texte zentriert auf den Button gesetzt.
 * Die Klasse bietet mehrere Varianten an Konstruktoren.
 */
public class BildButton extends JButton {

    /**
     * Sie verändert das Aussehen des Buttons, indem sie den Rahmen und den Fokus rausnimmt.
     * Außerdem werden alle Texte zentriert auf den Button gesetzt.
     * @param icon setzt das übergebene Icon auf den Button.
     */
    public BildButton(Icon icon) {
        setIcon(icon);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setHorizontalAlignment(CENTER);
    }

    /**
     * Sie verändert das Aussehen des Buttons, indem sie den Rahmen und den Fokus rausnimmt.
     * Außerdem werden alle Texte zentriert auf den Button gesetzt.
     * @param icon setzt das übergebene Icon auf den Button.
     * @param dimensionWidth setzt die breite des Buttons, die der Button für sich in Anspruch nimmt
     */
    public BildButton(Icon icon, int dimensionWidth) {
        setIcon(icon);
        setPreferredSize(new Dimension(dimensionWidth, this.getPreferredSize().height));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setHorizontalAlignment(CENTER);
    }

}
