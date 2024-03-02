package src.model.piece;
/**
 * The SunFactory is responsible for creating instances of the Sun chess piece.
 * This factory follows the Factory Method design pattern, providing a method to create
 * Sun pieces with specified positions and colors.
 * 
 * @Author: Yap Jack
 * @version 1.0
 * since 26-1-2024
 */

// Concrete Factory for Sun
public class SunFactory implements PieceFactory {
    @Override
    public Piece createChessPiece(int position_X, int position_Y, pieceColor color) {
        return new Sun(position_X, position_Y, "Sun", color);
    }
}
