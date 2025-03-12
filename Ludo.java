import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ludo extends JPanel implements ActionListener, Fonts{
    private final JLabel title = new JLabel("Ludo");
    private final JPanel panel = new JPanel();
    private final JButton exit = new JButton("Exit");

    public Ludo(){
        super(new BorderLayout());
        createAndShowGUI();
    }

    private void createAndShowGUI(){
        title.setFont(LARGE_TITLE_FONT);

        exit.addActionListener(this);
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(exit, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            Main.createAndShowGUI(new MainMenu());
        }
    }
}
