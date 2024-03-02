package src.model.piece;
/**
 * The TimeFactory is responsible for creating instances of the Time chess piece.
 * This factory follows the Factory Method design pattern, providing a method to create
 * Time pieces with specified positions and colors.
 * 
 * @Author: Yap Jack
 * @version 1.0
 * since 26-1-2024
 */

// Concrete Factory for Time
public class TimeFactory implements PieceFactory {
    @Override
    public Piece createChessPiece(int position_X, int position_Y, pieceColor color) {
        return new Time(position_X, position_Y, "Time", color);
    }
}
