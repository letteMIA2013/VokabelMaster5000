import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Table {


    JFrame frame = new JFrame();

    JPanel panel = new JPanel(new GridLayout(2,4));


public Table(){
// Tabellenkopf mit zeilen
    JLabel label = new MeinLabel("Name");
    JLabel label2 = new MeinLabel("Zeit in s");
    JLabel label3 = new MeinLabel("Anzahl richitger Fragen");
    JLabel label4 = new MeinLabel("Erreichte Punktzahl");
    JLabel label5 = new MeinLabel("Peter");
    JLabel label6 = new MeinLabel("100");
    JLabel label7 = new MeinLabel("20/40");
    JLabel label8 = new MeinLabel("50/100");
    frame.add(panel);
    panel.add(label);
    panel.add(label2);
    panel.add(label3);
    panel.add(label4);
    panel.add(label5);
    panel.add(label6);
    panel.add(label7);
    panel.add(label8);





frame.pack();
frame.setVisible(true);
    frame.setSize(600,300);






}

    private class MeinLabel extends JLabel{
        private MeinLabel(String t) {

            super(t);
            setOpaque(true);
            setHorizontalAlignment(CENTER);

            int r = new Random().nextInt(255);
            int g = new Random().nextInt(255);
            int b = new Random().nextInt(255);
            Color color = new Color(r,g,b);
            setBackground(color);
        }
    }























}

