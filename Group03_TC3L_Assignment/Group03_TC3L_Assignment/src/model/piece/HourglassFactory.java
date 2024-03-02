package src.model.piece;
/**
 * The HourglassFactory is responsible for creating instances of the Hourglass chess piece.
 * This factory follows the Factory Method design pattern, providing a method to create
 * Hourglass pieces with specified positions and colors.
 * 
 * @Author: Yap Jack
 * @version 1.0
 * since 26-1-2024
 */

// Concrete Factory for Hourglass
public class HourglassFactory implements PieceFactory {
    @Override
    public Piece createChessPiece(int position_X, int position_Y, pieceColor color) {
        return new Hourglass(position_X, position_Y, "Hourglass", color);
    }
}
