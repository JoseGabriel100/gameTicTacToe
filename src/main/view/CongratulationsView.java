package main.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class CongratulationsView extends JDialog {
    private JPanel contentPane;
    private JButton btnBack;
    private JLabel lblCongratulations;
    private JLabel lblUser;
    private JLabel lblUserIcon;

    public CongratulationsView(JFrame parent, boolean modal, String username, String userIconSource) {
        super(parent, modal);
        setContentPane(contentPane);

        // focused button
        getRootPane().setDefaultButton(btnBack);

        // username
        lblUser.setText(username);
        lblUserIcon.setIcon(new ImageIcon(userIconSource));

        // adding listener to back button
        btnBack.addActionListener(e -> dispose());

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

    private void createUIComponents() {
        try{
            // components
            lblCongratulations = new JLabel();
            lblUser = new JLabel();

            // Custom Font
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/JomhuriaRegular.ttf"));

            // Register Font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            // Using Font
            lblCongratulations.setFont(font.deriveFont(Font.BOLD, 100f));
            lblUser.setFont(font.deriveFont(Font.BOLD, 100f));

        }catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
