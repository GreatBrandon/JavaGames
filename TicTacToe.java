import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JPanel implements ActionListener, Fonts {
    private final JLabel title = new JLabel("TicTacToe");
    private final JPanel top = new JPanel();
    private final JPanel left = new JPanel();
    private final JPanel right = new JPanel();
    private final JPanel middle = new JPanel(new GridLayout(3,3,5,5));
    private final JPanel bottom = new JPanel();
    private final JLabel player1 = new JLabel("Player 1");
    private final JLabel player1X = new JLabel("X");
    private final JLabel player2 = new JLabel("Player 2");
    private final JLabel player2O = new JLabel("O");
    private String player;
    private final JLabel bottomText = new JLabel("X's turn");
    private final JButton[] buttons = new JButton[9];
    private int count;

    public TicTacToe() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Main.FRAME_WIDTH, Main.FRAME_HEIGHT));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(); // cannot be shorthand for loop due to constructor inside
            buttons[i].setFont(BUTTON_FONT);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(this);
            middle.add(buttons[i]);
        }

        title.setFont(LARGE_TITLE_FONT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottomText.setFont(PLAIN_FONT);
        bottomText.setAlignmentX(Component.CENTER_ALIGNMENT);

        player1.setFont(PLAIN_FONT);
        player2.setFont(PLAIN_FONT);
        player1X.setFont(PLAIN_FONT);
        player2O.setFont(PLAIN_FONT);

        player1.setAlignmentX(Component.CENTER_ALIGNMENT);
        player1X.setAlignmentX(Component.CENTER_ALIGNMENT);
        player2.setAlignmentX(Component.CENTER_ALIGNMENT);
        player2O.setAlignmentX(Component.CENTER_ALIGNMENT);

        top.add(title);

        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(new EmptyBorder(0,10,0,10));
        left.add(Box.createVerticalGlue());
        left.add(player1);
        left.add(player1X);
        left.add(Box.createVerticalGlue());

        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(0,10,0,10));
        right.add(Box.createVerticalGlue());
        right.add(player2);
        right.add(player2O);
        right.add(Box.createVerticalGlue());

        bottom.add(bottomText);

        setUpNewGame();

        setLayout(new BorderLayout());
        add(top, BorderLayout.PAGE_START);
        add(left, BorderLayout.LINE_START);
        add(right, BorderLayout.LINE_END);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        source.setText(player);
        source.setEnabled(false);
        count++;
        if (count >= 3 && determineWinner()) {
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
            showMessage(player+" WON!");
        } else if (count == 9) {
            showMessage("Game was a tie!");
        } else {
            if (player.equals("X")) {
                player = "O";
            } else {
                player = "X";
            }
            bottomText.setText(player+"'s turn");
        }
    }


    private boolean determineWinner() {
        if (buttons[0].getText().equals(buttons[1].getText()) && buttons[0].getText().equals(buttons[2].getText()) && (buttons[0].getText().equals(player))) {
            return true;
        } else if (buttons[3].getText().equals(buttons[4].getText()) && buttons[3].getText().equals(buttons[5].getText()) && (buttons[3].getText().equals(player))) {
            return true;
        } else if (buttons[6].getText().equals(buttons[7].getText()) && buttons[6].getText().equals(buttons[8].getText()) && (buttons[6].getText().equals(player))) {
            return true;
        } else if (buttons[0].getText().equals(buttons[3].getText()) && buttons[0].getText().equals(buttons[6].getText()) && (buttons[0].getText().equals(player))) {
            return true;
        } else if (buttons[1].getText().equals(buttons[4].getText()) && buttons[1].getText().equals(buttons[7].getText()) && (buttons[1].getText().equals(player))) {
            return true;
        } else if (buttons[2].getText().equals(buttons[5].getText()) && buttons[2].getText().equals(buttons[8].getText()) && (buttons[2].getText().equals(player))) {
            return true;
        } else if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && (buttons[0].getText().equals(player))) {
            return true;
        } else return buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && (buttons[2].getText().equals(player));
    }

    private void setUpNewGame() {
        player = "X";
        bottomText.setText(player+"'s turn");
        count = 0;
        for (JButton button: buttons) {
            button.setText("");
            button.setEnabled(true);
        }
    }

    private void showMessage(String message) {
        String[] options = {"New game", "View board", "Close"};
        int n = JOptionPane.showOptionDialog(this, message, "Game over!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,options[0]);
        if (n == 0) {
            setUpNewGame();
        } else if (n!=1) {
            Main.createAndShowGUI(new MainMenu());
        }
    }
}