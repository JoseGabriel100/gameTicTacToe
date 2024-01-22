package main;

import main.controller.TicTacToeController;
import main.model.TicTacToeModel;
import main.view.TicTacToeView;

public class Main {
    public static void main(String[] args) {
        TicTacToeView ticTacToeView = new TicTacToeView();
        TicTacToeModel ticTacToeModel = new TicTacToeModel();
        TicTacToeController ticTacToeContoller = new TicTacToeController(ticTacToeView, ticTacToeModel);

        ticTacToeContoller.init();

    }
}
