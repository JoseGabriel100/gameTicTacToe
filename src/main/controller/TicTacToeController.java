package main.controller;

import main.model.TicTacToeModel;
import main.model.User;
import main.view.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TicTacToeController {


    private final TicTacToeView ticTacToeView;
    private BoardView boardView;
    private final Board board;
    private final User user1, user2;
    private int turn;
    private final String user1IconSource, user2IconSource;
    private final int TOTAL_NUMBER_OF_PIECES;

    public TicTacToeController(TicTacToeView ticTacToeView, TicTacToeModel ticTacToeModel) {

        // Board
        this.board = new Board();

        // Tic Tac Toe View
        this.ticTacToeView = ticTacToeView;

        // users
        this.user1 = new User(1);
        this.user2 = new User(2);

        // turn
        this.turn = user1.getId();

        // total number of pieces
        this.TOTAL_NUMBER_OF_PIECES = 9;

        // user icons
        this.user1IconSource = "src/resources/img/wolf_icon.png";
        this.user2IconSource = "src/resources/img/cat_icon.png";

        // Tic Tac Toe View
        this.ticTacToeView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.ticTacToeView.setSize(600, 600);
        this.ticTacToeView.setLocationRelativeTo(null);
        this.ticTacToeView.setResizable(false);

        this.ticTacToeView.btnPlay.addActionListener(e -> {
            if(this.ticTacToeView.tfUser1.getText().isEmpty() || this.ticTacToeView.tfUser2.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "User mustn't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            else{
                // User names
                this.user1.setName(this.ticTacToeView.tfUser1.getText());
                this.user2.setName(this.ticTacToeView.tfUser2.getText());

                // Close Tic Tac Toe View
                this.ticTacToeView.dispose();

                // Board View
                this.boardView = new BoardView(user1, user2);
                this.boardView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.boardView.setSize(600, 660);
                this.boardView.setLocationRelativeTo(null);
                this.boardView.setResizable(false);
                this.boardView.setVisible(true);

                // Events
                addEvents();
            }
        });
    }

    private void addEvents() {

        // Menu Bar
        this.boardView.miAgain.addActionListener(e -> restart());
        this.boardView.miQuit.addActionListener(e-> System.exit(0));
        this.boardView.miHelp.addActionListener(e -> openURL("https://en.wikipedia.org/wiki/Tic-tac-toe"));
        this.boardView.miResultTable.addActionListener(e -> {
            ResultTableView resultTableView = new ResultTableView(this.ticTacToeView, true, this.user1, this.user2);
            resultTableView.setSize(600, 500);
            resultTableView.setLocationRelativeTo(null);
            resultTableView.setResizable(false);
            resultTableView.setVisible(true);
        });
        // Board Buttons
        this.boardView.btn00.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn00, 0, 0, user1IconSource);
            else
                play(user2, user1, this.boardView.btn00, 0, 0, user2IconSource);
        });

        this.boardView.btn01.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn01, 0, 1, user1IconSource);
            else
                play(user2, user1, this.boardView.btn01, 0, 1, user2IconSource);
        });

        this.boardView.btn02.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn02, 0, 2, user1IconSource);
            else
                play(user2, user1, this.boardView.btn02, 0, 2, user2IconSource);
        });

        this.boardView.btn10.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn10, 1, 0, user1IconSource);
            else
                play(user2, user1, this.boardView.btn10, 1, 0, user2IconSource);
        });

        this.boardView.btn11.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn11, 1, 1, user1IconSource);
            else
                play(user2, user1, this.boardView.btn11, 1, 1, user2IconSource);
        });

        this.boardView.btn12.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn12, 1, 2, user1IconSource);
            else
                play(user2, user1, this.boardView.btn12, 1, 2, user2IconSource);
        });

        this.boardView.btn20.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn20, 2, 0, user1IconSource);
            else
                play(user2, user1, this.boardView.btn20, 2, 0, user2IconSource);
        });

        this.boardView.btn21.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn21, 2, 1, user1IconSource);
            else
                play(user2, user1, this.boardView.btn21, 2, 1, user2IconSource);
        });

        this.boardView.btn22.addActionListener(e2 -> {
            if (this.turn == this.user1.getId())
                play(user1, user2, this.boardView.btn22, 2, 2, user1IconSource);
            else
                play(user2, user1, this.boardView.btn22, 2, 2, user2IconSource);
        });
    }

    private void openURL(String url){
        try{
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = new URI(url);
                desktop.browse(uri);
            }
            else
                JOptionPane.showMessageDialog(null, "Connection Error", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void play(User userInTurn, User userWaiting, JButton btn, int row, int col, String source) {
        boolean isThePiecePlaced = board.placePiece(userInTurn.getId(), row, col);
        if (isThePiecePlaced) {
            placeIcon(btn, source);
            this.turn = userWaiting.getId();
            this.board.increasePiecePlaced();
            if(isWinner(userInTurn.getId())) {

                // Congratulations View
                CongratulationsView congratulationsView = new CongratulationsView(this.boardView, true, userInTurn.getName(), source);
                congratulationsView.setSize(600, 500);
                congratulationsView.setLocationRelativeTo(null);
                congratulationsView.setVisible(true);
                userInTurn.increaseWins();
                userWaiting.increaseLoses();

                // Who Begins View
                WhoBeginsView whoBeginsView = new WhoBeginsView(this.boardView, true, this.user1, this.user2);
                whoBeginsView.setSize(600, 500);
                whoBeginsView.setLocationRelativeTo(null);
                whoBeginsView.setVisible(true);

                this.turn = whoBeginsView.getTurn();

                restart();
            }else if(isDraw()){

                // Draw View
                DrawView drawView = new DrawView(this.boardView, true);
                drawView.setSize(600, 500);
                drawView.setLocationRelativeTo(null);
                drawView.setVisible(true);
                userInTurn.increaseDraws();
                userWaiting.increaseDraws();

                // Who Begins View
                WhoBeginsView whoBeginsView = new WhoBeginsView(this.boardView, true, this.user1, this.user2);
                whoBeginsView.setSize(600, 500);
                whoBeginsView.setLocationRelativeTo(null);
                whoBeginsView.setVisible(true);

                this.turn = whoBeginsView.getTurn();
                restart();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Filled Box", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean isDraw(){
        return board.getPiecesPlaced() == TOTAL_NUMBER_OF_PIECES;
    }
    private void restart(){
        // restart board
        this.board.restart();

        // clean buttons
        this.boardView.btn00.setIcon(null);
        this.boardView.btn01.setIcon(null);
        this.boardView.btn02.setIcon(null);
        this.boardView.btn10.setIcon(null);
        this.boardView.btn11.setIcon(null);
        this.boardView.btn12.setIcon(null);
        this.boardView.btn20.setIcon(null);
        this.boardView.btn21.setIcon(null);
        this.boardView.btn22.setIcon(null);
    }
    private boolean isWinner(int id) {
        if (this.board.getPieceInRowColum(0, 0) == id &&
                this.board.getPieceInRowColum(0, 1) == id &&
                this.board.getPieceInRowColum(0, 2) == id
        ) return true;
        if (this.board.getPieceInRowColum(1, 0) == id &&
                this.board.getPieceInRowColum(1, 1) == id &&
                this.board.getPieceInRowColum(1, 2) == id
        ) return true;
        if (this.board.getPieceInRowColum(2, 0) == id &&
                this.board.getPieceInRowColum(2, 1) == id &&
                this.board.getPieceInRowColum(2, 2) == id
        ) return true;
        if (this.board.getPieceInRowColum(0, 0) == id &&
                this.board.getPieceInRowColum(1, 0) == id &&
                this.board.getPieceInRowColum(2, 0) == id
        ) return true;
        if (this.board.getPieceInRowColum(0, 1) == id &&
                this.board.getPieceInRowColum(1, 1) == id &&
                this.board.getPieceInRowColum(2, 1) == id
        ) return true;
        if (this.board.getPieceInRowColum(0, 2) == id &&
                this.board.getPieceInRowColum(1, 2) == id &&
                this.board.getPieceInRowColum(2, 2) == id
        ) return true;
        if (this.board.getPieceInRowColum(0, 0) == id &&
                this.board.getPieceInRowColum(1, 1) == id &&
                this.board.getPieceInRowColum(2, 2) == id
        ) return true;
        return this.board.getPieceInRowColum(0, 2) == id &&
                this.board.getPieceInRowColum(1, 1) == id &&
                this.board.getPieceInRowColum(2, 0) == id;
    }

    private void placeIcon(@NotNull JButton btn, String source) {
        btn.setIcon(new ImageIcon(source));
    }

    public void init() {
        this.ticTacToeView.setVisible(true);
    }
}
