package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Diese Klasse ist für die Rundung des Passwort Feldes zuständig
 * Copy & Paste aus dem Internet.
 */
class RoundedPassField extends JPasswordField {

    /**
     * Shape wird dazu verwendet Ecken kurviger zu machen (Die Geometrische Form)
     */
    private Shape shape;

    /**
     * @param size ist die Feldbreite.
     */
    public RoundedPassField(int size) {
        super(size);
        setOpaque(false);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    /**
     * Die Klasse setzt die Stärke der Rundung
     *
     * @param x ist für die Breite der Rundung verantwortlich
     * @param y ist für die Höhe der Rundung verantwortlich
     * @return gibt die Stärke der Rundung aus.
     */
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }

}
