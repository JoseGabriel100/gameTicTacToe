package main.view;

import main.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class ResultTableView extends JDialog {
    private JPanel contentPane;
    private JButton btnBack;
    private JTable tblResult;
    private DefaultTableModel defaultTableModel;
    private final User user1, user2;

    public ResultTableView(JFrame parent, boolean modal, User user1, User user2) {
        super(parent, modal);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnBack);

        // Users
        this.user1 = user1;
        this.user2 = user2;

        // adding values of the users
        addUserData();

        btnBack.addActionListener(e -> dispose());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void addUserData(){
        // User1
        defaultTableModel.setValueAt(this.user1.getName(), 0, 0);
        defaultTableModel.setValueAt(this.user1.getWins(), 0, 1);
        defaultTableModel.setValueAt(this.user1.getLosses(), 0, 2);
        defaultTableModel.setValueAt(this.user1.getDraws(), 0, 3);

        // User2
        defaultTableModel.setValueAt(this.user2.getName(), 1, 0);
        defaultTableModel.setValueAt(this.user2.getWins(), 1, 1);
        defaultTableModel.setValueAt(this.user2.getLosses(), 1, 2);
        defaultTableModel.setValueAt(this.user2.getDraws(), 1, 3);
    }

    private void createUIComponents() {
        tblResult = new JTable();
        String[] columnNames = {"name", "wins", "loses", "draws"};
        defaultTableModel = new DefaultTableModel(columnNames, 2);

        tblResult.setModel(defaultTableModel);
    }
}
