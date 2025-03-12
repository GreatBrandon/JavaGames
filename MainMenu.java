import javax.swing.*;
import java.awt.*;
public class MainMenu extends JPanel implements Fonts {
    public MainMenu() {
        JLabel title = new JLabel("Choose a game to play");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(SMALL_TITLE_FONT);

        JButton tttButton = new JButton("TicTacToe");
        JButton ludoButton = new JButton("Ludo");
        JButton sudokuButton = new JButton("Sudoku");
        tttButton.addActionListener(e -> {
            Main.createAndShowGUI(new TicTacToe());
        });
        ludoButton.addActionListener(e -> {
            Main.createAndShowGUI(new Ludo());
        });
        sudokuButton.addActionListener(e -> {
            Main.createAndShowGUI(new SudokuGUI());
        });

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(Box.createVerticalGlue());
        top.add(title);
        top.add(Box.createVerticalGlue());

        JPanel bottom = new JPanel();
        bottom.add(tttButton);
        bottom.add(ludoButton);
        bottom.add(sudokuButton);

        setPreferredSize(new Dimension(700, 700));
        setLayout(new GridLayout(4, 1));
        add(top);
        add(bottom);     
    }
}
