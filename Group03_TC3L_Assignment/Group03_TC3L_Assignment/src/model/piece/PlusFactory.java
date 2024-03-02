package src.model.piece;
/**
 * The PlusFactory is responsible for creating instances of the Plus chess piece.
 * This factory follows the Factory Method design pattern, providing a method to create
 * Plus pieces with specified positions and colors.
 * 
 * @Author: Yap Jack
 * @version 1.0
 * since 26-1-2024
 */

// Concrete Factory for Plus
public class PlusFactory implements PieceFactory {
    @Override
    public Piece createChessPiece(int position_X, int position_Y, pieceColor color) {
        return new Plus(position_X, position_Y, "Plus", color);
    }
}
