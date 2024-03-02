/**
 * The square class represents a square on the chess board. It manages the position of the square on the board,
 * whether the square is occupied by a piece, and the piece on the square.
 * 
 * @Author: Nazhan Harraz
 * @version 3.0
 * since 1-1-2024
 */

package src.model.square;

import src.model.piece.Piece;

public class Square {
    private int x, y, xy;
    private Piece pieceOnSquare;

    public Square(int x,int y) {
        this.x = x;
        this.y = y;
        this.xy = x*10 + y;
    }

    public void setPiece(Piece piece) {
        this.pieceOnSquare = piece;
    }

    public Piece getPiece(){
        return pieceOnSquare;
    }

    public void removePiece() {
        this.pieceOnSquare = null;
    }
    
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getCoordinate() {
        return xy;
    }

    public boolean occupied() {
        if(pieceOnSquare != null)
            return true;
        else
            return false;
    }
}
