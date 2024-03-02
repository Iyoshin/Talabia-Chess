package src.model.piece;
/**
 * It declares a method for creating chess pieces with specified positions and colors.
 * Concrete classes implementing this interface will provide specific implementations
 * for creating different types of chess pieces.
 *
 * @Author: Yap Jack
 * @version 3.0
 * @since 26-1-2024
 */

// Factory Interface
public interface PieceFactory {
    Piece createChessPiece(int position_X, int position_Y, pieceColor color);
}
