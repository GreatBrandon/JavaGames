import java.awt.Dimension;

import javax.swing.*;

public class Main {
    public static final JFrame f = new JFrame();
    public static final int FRAME_HEIGHT = 700;
    public static final int FRAME_WIDTH = 700;

    public static void createAndShowGUI(JPanel p) {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(p);
        f.setTitle(p.getClass().getSimpleName());
        f.pack();
        f.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI(new MainMenu()));
    }
}
