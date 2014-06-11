import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.lang.String;

public class MeinLabel extends JLabel {

    public MeinLabel(String name, boolean opaque, Color color) {

        setText(name);
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(opaque);
        setBackground(color);

    }

    public MeinLabel(Icon icon, boolean opaque, Color color) {

        setIcon(icon);
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(opaque);
        setBackground(color);

    }

    public MeinLabel(Icon icon, boolean opaque) {

        setIcon(icon);
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(opaque);

    }

}
