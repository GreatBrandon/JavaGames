import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuGUI extends JPanel implements ActionListener, Fonts {
    private final JLabel title = new JLabel("Sudoku");
    private final JButton newPuzzle =  new JButton("New puzzle");
    private final JButton hint =  new JButton("Hint");
    private final JButton checkSolution =  new JButton("Check solution");
    private final JButton exit =  new JButton("Exit");
    private final JPanel buttonPanel = new JPanel();
    private final JPanel gamePanel = new JPanel(new GridLayout(9,9));
    private final JTextField[][] fields = new JFormattedTextField[9][9];
    private Sudoku sudoku;

    public SudokuGUI() {
        super(new BorderLayout());
        createAndShowGUI();
        setupNewGame();
    }

    private void createAndShowGUI() {
        DocumentFilter filter = new DocumentFilter() {
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null || text.isEmpty() || text.matches("[1-9]") && fb.getDocument().getLength() == 0) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new JFormattedTextField();
//                fields[i][j].setEditable(false);
                ((AbstractDocument) fields[i][j].getDocument()).setDocumentFilter(filter);
                fields[i][j].setBackground(Color.WHITE);
                fields[i][j].addActionListener(this);
                gamePanel.add(fields[i][j]);
            }
        }
        title.setFont(LARGE_TITLE_FONT);


        newPuzzle.addActionListener(this);
        checkSolution.addActionListener(this);
        hint.addActionListener(this);
        exit.addActionListener(this);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(newPuzzle);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(checkSolution);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(hint);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(exit);

        gamePanel.setBorder(new EmptyBorder(10,20,10,20));
        buttonPanel.setBorder(new EmptyBorder(10,20,10,20));

        add(title, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void setupNewGame() {
        try {
            Scanner sc = new Scanner(new File("sudoku_puzzles.csv"));
            sc.useDelimiter(",");
            int puzzle = (int) (Math.random()*100);
            for (int i = 0; i < puzzle; i++) {
                sc.nextLine();
            }
            this.sudoku = new Sudoku(sc.next(), sc.next(), sc.next());
            sc.close();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudoku.getCell(i,j) != '\0') {
                        fields[i][j].setText(sudoku.getCell(i,j)+"");
                        fields[i][j].setEditable(false);
                    } else {
                        fields[i][j].setText("");
                        fields[i][j].setEditable(true);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            Main.createAndShowGUI(new MainMenu());
        } else if (e.getSource() == newPuzzle) {
            setupNewGame();
        }
    }
}
