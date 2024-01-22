package main.controller;

import javax.swing.*;

public class Board {
    private final int[][] board = new int [3][3];
    private int piecesPlaced;

    public Board(){
        this.piecesPlaced = 0;
    }
    public void restart(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                board[i][j] = 0;
            }
        }

        this.piecesPlaced = 0;
    }
    public boolean placePiece(int id, int row, int col){
        if(board[row][col] == 0){
            this.board[row][col] = id;
            return true;
        }
        else{
            return false;
        }

    }

    public int getPieceInRowColum(int row, int column){
        return board[row][column];
    }

    public void increasePiecePlaced(){
        this.piecesPlaced ++;
    }

    public int getPiecesPlaced(){
        return this.piecesPlaced;
    }
}
