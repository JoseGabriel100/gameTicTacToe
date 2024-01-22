package main.view;

import main.model.User;

import javax.swing.*;

public class BoardView extends JFrame{
    public JButton btn00;
    public JButton btn01;
    public JButton btn02;
    public JButton btn10;
    public JButton btn11;
    public JButton btn12;
    public JButton btn20;
    public JButton btn21;
    public JButton btn22;
    private JPanel panel;
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem miResultTable;
    public JMenuItem miAgain;
    public JMenuItem miHelp;
    public JMenuItem miQuit;
    public User user1;
    public User user2;

    public BoardView(User user1, User user2){
        this.setContentPane(panel);
        createJMenu();

        // Users
        this.user1 = user1;
        this.user2 = user2;
    }

    private void createJMenu(){
        // Menu Bar
        menuBar = new JMenuBar();

        // Menu
        menu = new JMenu("menu");
        miResultTable = new JMenuItem("result table");
        miAgain = new JMenuItem("again");
        miHelp = new JMenuItem("help");
        miQuit = new JMenuItem("quit");

        // adding icons to menu items
        miResultTable.setIcon(new ImageIcon("src/resources/img/result_table_icon.png"));
        miAgain.setIcon(new ImageIcon("src/resources/img/again_icon.png"));
        miHelp.setIcon(new ImageIcon("src/resources/img/help_icon.png"));
        miQuit.setIcon(new ImageIcon("src/resources/img/quit_icon.png"));

        // adding menu items to menu
        menu.add(miResultTable);
        menu.add(miAgain);
        menu.add(miHelp);
        menu.add(miQuit);

        // adding menu to menu bar
        menuBar.add(menu);

        // adding menu bar to the window
        this.setJMenuBar(menuBar);

    }
}
