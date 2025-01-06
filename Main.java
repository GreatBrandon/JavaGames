import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main implements ActionListener, Fonts {
    public final JFrame f = new JFrame();
    private final JPanel rootPanel = new JPanel();
    private final JPanel top = new JPanel();
    private final JLabel title = new JLabel("Choose a game to play");
    private final JButton tttButton = new JButton("TicTacToe");
    private final JButton ludoButton = new JButton("Ludo");
    private final JButton sudokuButton = new JButton("Sudoku");
    private final JPanel tictactoe = new TicTacToe();
    private final JPanel ludo = new Ludo();
    private final JPanel sudoku = new Sudoku();

    private void createAndShowGUI() {
        tttButton.addActionListener(this);
        ludoButton.addActionListener(this);
        sudokuButton.addActionListener(this);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(SMALL_TITLE_FONT);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(Box.createVerticalGlue());
        top.add(title);
        top.add(Box.createVerticalGlue());

        rootPanel.setPreferredSize(new Dimension(700, 700));
        rootPanel.setLayout(new GridLayout(4, 1));
        rootPanel.add(top);
        rootPanel.add(tttButton);
        rootPanel.add(ludoButton);
        rootPanel.add(sudokuButton);

        f.setContentPane(rootPanel);
        f.setTitle("Choose a game");
        f.pack();
        f.setVisible(true);
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                f.getContentPane().setPreferredSize(new Dimension(f.getWidth()-f.getInsets().left-f.getInsets().right, f.getHeight()-f.getInsets().top-f.getInsets().bottom));
                rootPanel.setSize(f.getContentPane().getPreferredSize());
                tictactoe.setSize(f.getContentPane().getPreferredSize());
                ludo.setSize(f.getContentPane().getPreferredSize());
                sudoku.setSize(f.getContentPane().getPreferredSize());
            }
        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static final Main main = new Main();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(main::createAndShowGUI);
    }

    public static void exitToMenu() {
        main.f.setTitle("Choose a game");
        main.f.setContentPane(main.rootPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tttButton) {
            f.setTitle("TicTacToe");
            f.setContentPane(tictactoe);
        } else if (e.getSource() == ludoButton) {
            f.setTitle("Ludo");
            f.setContentPane(ludo);
        } else if (e.getSource() == sudokuButton) {
            f.setTitle("Sudoku");
            f.setContentPane(sudoku);
        }
    }
}
