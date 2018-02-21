package abused_master.javagame;

import javax.swing.*;
import java.awt.*;

public class OldDeprecatedDisplay extends JFrame {

    public static OldDeprecatedDisplay display = new OldDeprecatedDisplay();

    public static int WIDTH = 1000;
    public static int HEIGHT = 600;
    public static String NAME = "JavaGame";

    public OldDeprecatedDisplay() {
    }

    public static void run() {
        OldDeprecatedDisplay frame = new OldDeprecatedDisplay();
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle(NAME);
        frame.getContentPane().setBackground(Color.GREEN);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void renderJLabel(JFrame frame) {
        JLabel label = new JLabel();
        label.setFont(new Font("", Font.PLAIN, 120));
        label.setText("Hello Liam");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.BOTTOM);

        frame.add(label);
    }

    /**
     * Calculator render
     *
     public static JTextField num1 = new JTextField(0);
     public static JTextField num2 = new JTextField(0);
     public static JTextField output = new JTextField(0);
     frame.setLayout(null);
     renderJLabel(frame);
     num1.setBounds((frame.getWidth() / 2) - 150, 50, 300, 20);
     frame.add(num1);
     num2.setBounds((frame.getWidth() / 2) - 150, 100, 300, 20);
     frame.add(num2);
     output.setBounds((frame.getWidth() / 2) - 150, 150, 300, 20);
     frame.add(output);
    public static void renderPane() {
        float x = -1, y = -1;
        if(!num1.getText().isEmpty()) {
            x = Float.parseFloat(num1.getText());
        }
        if(!num2.getText().isEmpty()) {
            y = Float.parseFloat(num2.getText());
        }
        if(x != -1 && y != -1) {
            output.setText("Value: " + (x + y));
        }else {
            output.setText("please enter values for x and y");
        }
    }
     */
}
