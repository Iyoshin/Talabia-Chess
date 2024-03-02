package src.chess;

import src.model.ChessBoard;
import src.view.ChessView;

public class Game {
    public static void main(String[] args) {
        ChessBoard chessBoard = ChessBoard.getInstance();
        new ChessView(chessBoard);
        
    }
}
