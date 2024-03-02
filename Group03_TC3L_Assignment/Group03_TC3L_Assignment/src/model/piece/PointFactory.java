package src.model.piece;
/**
 * The PointFactory is responsible for creating instances of the Point chess piece.
 * This factory follows the Factory Method design pattern, providing a method to create
 * Point pieces with specified positions and colors.
 * 
 * @Author: Yap Jack
 * @version 1.0
 * since 26-1-2024
 */

// Concrete Factory for Point
public class PointFactory implements PieceFactory {
    @Override
    public Piece createChessPiece(int position_X, int position_Y, pieceColor color) {
        return new Point(position_X, position_Y, "Point", color);
    }
}
