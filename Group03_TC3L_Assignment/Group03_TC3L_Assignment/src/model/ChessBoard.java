package src.model;

import src.model.piece.Sun;
import src.model.piece.pieceColor;
import src.model.square.Square;
import src.model.piece.SunFactory;
import src.model.piece.TimeFactory;
import src.model.piece.HourglassFactory;
import src.model.piece.PointFactory;
import src.model.piece.PlusFactory;

/**
 * The ChessBoard class represents the game board for the custom chess variant. It manages the arrangement of
 * different chess pieces on the board, provides methods to access squares, and enforces certain game rules,
 * such as swapping Plus and Time pieces and checking for checkmate.
 *
 * The class follows the Singleton pattern to ensure there is only one instance of the ChessBoard in the game.
 * @Author: Yap Jack, Ng Yi Min, Yong Qi Xiang, Nazhan Harraz
 * @version 9.0
 * since 9-1-2024
 */

public class ChessBoard {
    private Square[][] squares = new Square[6][7];
    private int positionX;
    private int positionY;
    private static ChessBoard instance;
    private PointFactory pointFactory = new PointFactory();
    private SunFactory sunFactory = new SunFactory();
    private HourglassFactory hourglassFactory = new HourglassFactory();
    private TimeFactory timeFactory = new TimeFactory();
    private PlusFactory plusFactory = new PlusFactory();
    private boolean sunIsCaptured = false;
    
    public ChessBoard() {
        initializeSquares();
    }

    public static ChessBoard getInstance() {
        if (instance == null) {
            synchronized (ChessBoard.class) {
                if (instance == null) {
                    instance = new ChessBoard(); //create a singleton instance
                }
            }
        }
        return instance; //return the instance
        
    }
    private void initializeSquares() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                squares[row][col] = new Square(row, col);
            }
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Square getSquareAt(int x, int y) {
        return squares[y][x];
    }

    //swap plus and time
    public void swap_PlusAndTimes(){
        //swap plus and times
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if(squares[row][col].getPiece() != null){
                    if(squares[row][col].getPiece().getPieceType() == "Plus"){
                        if(squares[row][col].getPiece().getPieceColour() == pieceColor.BLUE){
                            squares[row][col].setPiece(timeFactory.createChessPiece(col, row, pieceColor.BLUE));
                        }
                        else if(squares[row][col].getPiece().getPieceColour() == pieceColor.YELLOW){
                            squares[row][col].setPiece(timeFactory.createChessPiece(col, row, pieceColor.YELLOW));
                        }
                        
                    }
                    else if(squares[row][col].getPiece().getPieceType() == "Time"){
                        if(squares[row][col].getPiece().getPieceColour() == pieceColor.BLUE){
                            squares[row][col].setPiece(plusFactory.createChessPiece(col, row, pieceColor.BLUE));
                        }
                        else if(squares[row][col].getPiece().getPieceColour() == pieceColor.YELLOW){
                            squares[row][col].setPiece(plusFactory.createChessPiece(col, row, pieceColor.YELLOW));
                        }
                    }
                }
            }
        }
    }

    //setup the starting of the game
    public void startBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
            //blue side
             if (row == 0) {
                if (col == 0 || col == 6) {
                    squares[row][col].setPiece(plusFactory.createChessPiece(row, col, pieceColor.BLUE));
                } 
                else if (col == 1 || col == 5) {
                    squares[row][col].setPiece(hourglassFactory.createChessPiece(row, col, pieceColor.BLUE));
                } 
                else if (col == 2 || col == 4) {
                    squares[row][col].setPiece(timeFactory.createChessPiece(row, col, pieceColor.BLUE));
                } 
                else {
                    squares[row][col].setPiece(sunFactory.createChessPiece(row, col, pieceColor.BLUE));
                }

            }

            if (row == 1) {
                    squares[row][col].setPiece(pointFactory.createChessPiece(row, col, pieceColor.BLUE));
            }


            //yellow side
            if (row == 5) {
                if (col == 0 || col == 6) {
                    squares[row][col].setPiece(plusFactory.createChessPiece(row, col, pieceColor.YELLOW));
                } 
                else if (col == 1 || col == 5) {
                    squares[row][col].setPiece(hourglassFactory.createChessPiece(row, col, pieceColor.YELLOW));
                } 
                else if (col == 2 || col == 4) {
                    squares[row][col].setPiece(timeFactory.createChessPiece(row, col, pieceColor.YELLOW));
                } 
                else {
                    squares[row][col].setPiece(sunFactory.createChessPiece(row, col, pieceColor.YELLOW));
                }
            }

            if (row == 4) {
                squares[row][col].setPiece(pointFactory.createChessPiece(row, col, pieceColor.YELLOW));
            }
        }   
    }
    }

    //reset the checkmate flag in to false
    public void resetCheckmateflag() {
        this.sunIsCaptured = false;
    }

    //check whether it is checkmate
    public boolean isCheckmate() {
        int noOfSun = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if(squares[row][col].getPiece() != null){
                    if(squares[row][col].getPiece() instanceof Sun){
                        noOfSun ++;
                    }
                }

            }

            //break if number of Sun more than 1
            if (noOfSun > 1) {
                sunIsCaptured = false;
                break;
            } else {
                sunIsCaptured = true;
            }
        
        }
        return sunIsCaptured;
    }

}
