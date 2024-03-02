package src.model.piece;

import javax.swing.ImageIcon;

import src.model.ChessBoard;
import src.model.square.Square;

/**
 * An abstract class representing a piece.
 * Subclasses must implements the {@code isValidMove} method to set how a piece can move
 * 
 * @author Ng Yi Min
 * @version 2.0
 * since 15-1-2024
 */
public abstract class Piece {
    
    private String pieceType;
    private pieceColor pieceColour;
    private String color;
    private ImageIcon piece_Icon;
    private int position_X;
    private int position_Y;

    /**
     * Construct a new piece with type and color given at the position X and Y.
     * 
     * @param position_X the position X of the piece.
     * @param position_Y the position Y of the piece.
     * @param pieceType  the type of the piece.
     * @param pieceColor the color of the piece.
     */
    protected Piece(int position_X, int position_Y, String pieceType, pieceColor pieceColor){
        this.position_X = position_X;
        this.position_Y = position_Y;
        this.pieceType = pieceType;
        this.pieceColour = pieceColor;
        switch(pieceColor){
            case YELLOW:
                color = "yellow";
                break;
             
            case BLUE:
                color = "blue";
                break;    
        }
        piece_Icon = new ImageIcon("src/lib/" + color + "_" + pieceType + ".png");
    
    }

    /** 
     * Gets the image of the piece.
     * 
     * @return the ImageIcon of the piece.
     */
    public ImageIcon getImageIcon() {
        return piece_Icon;
    }
    
    /** 
     * Gets the type of piece e.g. Sun, Point etc.
     * 
     * @return the piece type of the piece.
     */
    public String getPieceType() {
        return pieceType;
    }
    
    /**
     * Gets the color of the piece.
     * 
     * @return the color of the piece.
     */
    public pieceColor getPieceColour() {
        return pieceColour;
    }

    /**
     * Gets the position X of piece.
     * 
     * @return the position X of the piece.
     */
    public int getPosition_X() {
        return position_X;
    }
    
    /**
     * Gets the position Y of the piece.
     * 
     * @return the position Y of the piece.
     */
    public int getPosition_Y() {
        return position_Y;
    }

     /**
     * Sets the image icon.
     * 
     * @param piece_Icon the image icon of the piece to set.
     */
    protected void setIcon(ImageIcon piece_Icon) {
        this.piece_Icon = piece_Icon;
    }

     /**
     * Sets the piece color.
     * 
     * @param pieceColour the color of the piece to set.
     */
    protected void setPieceColour(pieceColor pieceColour) {
        this.pieceColour = pieceColour;
    }

    /**
     * Sets the piece type.
     * 
     * @param pieceType the type of piece to set.
     */
    protected void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }
    
    /**
     * Sets the position X of the piece.
     * 
     * @param position_X the position X of the piece to set.
     */
    protected void setPosition_X(int position_X) {
        this.position_X = position_X;
    }

    /**
     * Sets the position Y of the piece.
     * 
     * @param position_Y the position Y of the piece to set.
     */
    protected void setPosition_Y(int position_Y) {
        this.position_Y = position_Y;
    }

    /**
     * Sets how a piece can move on the board.
     * 
     * @param chessBoard the chessboard of the piece is in.
     * @param fromSquare the source square where the piece is moving from.
     * @param toSquare   the destination sqaure where to piece is moving to.
     * @return {@code true} if the move is valid, {@code false} if the move is invalid.
     */
    public abstract boolean isValidMove(ChessBoard chessBoard, Square fromSquare, Square toSquare);



}
