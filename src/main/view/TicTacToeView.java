package main.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TicTacToeView extends JFrame{
    private JPanel panel;
    private JLabel lblTicTacToe;
    private JLabel lblUser1;
    public JTextField tfUser1;
    private JLabel lblUser2;
    public JTextField tfUser2;
    public JButton btnPlay;

    public TicTacToeView(){
        this.setContentPane(panel);
    }

    private void createUIComponents() {
        try {
            // components
            lblTicTacToe = new JLabel("Tic Tac Toe");
            lblUser1 = new JLabel("User 1");
            lblUser2 = new JLabel("User 2");

            // Custom Font
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/JomhuriaRegular.ttf"));

            // Register Font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            // Using Font
            lblTicTacToe.setFont(font.deriveFont(Font.BOLD, 100f));
            lblUser1.setFont(font.deriveFont(Font.BOLD, 50f));
            lblUser2.setFont(font.deriveFont(Font.BOLD, 50f));

        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
