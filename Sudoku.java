import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sudoku extends JPanel implements ActionListener, Fonts {
    private final JLabel title = new JLabel("Sudoku");
    private final JButton exit =  new JButton("Exit");
    private final JPanel gamePanel = new JPanel(new GridLayout(9,9));
    private final JTextField[][] fields = new JTextField[9][9];

    public Sudoku() {
        super(new BorderLayout());
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new JTextField();
//                fields[i][j].setEditable(false);
                fields[i][j].setBackground(Color.WHITE);
                fields[i][j].addActionListener(this);
                gamePanel.add(fields[i][j]);
            }
        }
        title.setFont(LARGE_TITLE_FONT);

        exit.addActionListener(this);



        add(title, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(exit, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            Main.exitToMenu();
        }
    }
}
