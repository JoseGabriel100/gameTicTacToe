package main.view;

import main.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class WhoBeginsView extends JDialog {
    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnUser1;
    private JButton btnUser2;
    private JLabel lblWhoBegins;
    private int turn;

    public WhoBeginsView(JFrame parent, boolean modal, User user1, User user2) {
        super(parent, modal);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnBack);

        // User Buttons
        this.btnUser1.setText(user1.getName());
        this.btnUser2.setText(user2.getName());

        // turn
        this.turn = user1.getId();

        // Button Events
        btnBack.addActionListener(e -> dispose());
        btnUser1.addActionListener(e -> {
            dispose();
            this.turn = user1.getId();
        });

        btnUser2.addActionListener(e -> {
            dispose();
            this.turn = user2.getId();
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public int getTurn(){
        return this.turn;
    }

    private void createUIComponents() {
        try{
            // components
            lblWhoBegins = new JLabel();

            // Custom Font
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/JomhuriaRegular.ttf"));

            // Register Font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            // Using Font
            lblWhoBegins.setFont(font.deriveFont(Font.BOLD, 100f));

        }catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
