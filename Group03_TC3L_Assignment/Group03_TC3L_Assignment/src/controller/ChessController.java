
package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.model.ChessBoard;
import src.model.GameModel;
import src.model.piece.Piece;
import src.model.square.Square;
import src.view.ChessView;
import src.model.piece.pieceColor;

/**
 * The ChessController class serves as the controller in the Model-View-Controller (MVC) architecture for the chess game.
 * It handles user interactions, such as mouse clicks and button presses, and updates the model and view accordingly.
 * The class implements ActionListener to handle button clicks and extends MouseAdapter to handle mouse events on the chessboard.
 *
 * The controller is responsible for managing the flow of the game, validating moves, and updating the GUI based on user inputs.
 * It interacts with the ChessView and ChessBoard classes to update the graphical representation of the game and enforce game rules.
 *
 * @Author: Ng Yi Min, Yap Jack, Nazhan Harraz
 * @version 11.0
 * since 8-1-2024
 */

public class ChessController extends MouseAdapter implements ActionListener {
    private Square sourceSquare;
    private Square sqA;
    private Square sqB;
    private JPanel sourcePane;
    private JPanel destinationPane;
    public Piece selectedPiece;
    private ChessView chessView;
    private ChessBoard chessBoard;
    private GameModel gameModel;
    private boolean checkmate = false;

    public ChessController(ChessView chessView, ChessBoard chessBoard) {
        this.chessView = chessView;
        this.chessBoard = chessBoard;
        this.gameModel = new GameModel();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // get the source panel
        JPanel selectedPanel = (JPanel) e.getSource();

        System.out.println("Name: " + selectedPanel.getName());

        // get the x, y
        int disX = Character.getNumericValue(selectedPanel.getName().charAt(1));
        int disY = Character.getNumericValue(selectedPanel.getName().charAt(0));

        System.out.println("x:" + disX + " y:" + disY);

        // set the sourceSquare
        sourceSquare = chessBoard.getSquareAt(disX, disY);

        // check if sqA is null (the source) and does it containes any pieces
        if (sqA == null && sourceSquare.occupied() && !checkmate) {
            sqA = sourceSquare;
            sourcePane = selectedPanel;
            selectedPiece = sourceSquare.getPiece();
            System.out.println(
                    "Clicked on: row:" + sqA.getX() + " col:" + sqA.getY() + " Type:" + selectedPiece.getPieceType());

            // check if its same color, invalid the move if its not same
            if (selectedPiece.getPieceColour() != gameModel.get_CurrentPlayer()) {
                sqA = null;
                System.out.println("Cant move other color pieces");
            }

        } else { // set the sqB (destination)
            sqB = sourceSquare;
            if (sqA != null) {

                destinationPane = selectedPanel;
                System.out.println("Clicked on: row:" + sqB.getX() + " col:" + sqB.getY());

                // update the GUI if the move is valid
                if (selectedPiece.isValidMove(chessBoard, sqA, sqB)) {
                    gameModel.setCounter(gameModel.addCounter());
                    System.out.println("Counter: " + gameModel.getCounter());

                    chessView.updateChessPosition(sourcePane.getName(), destinationPane.getName(), selectedPiece);
                    if (gameModel.getCounter() % 4 == 0 && gameModel.getCounter() != 0) {
                        // swap plus and times
                        chessBoard.swap_PlusAndTimes();
                        chessView.updatePlusTime();
                        gameModel.switchPlayer();
                        chessView.flipBoard();
                        System.out.println("Swap plus time");

                        //check if its checkmate, codes from Yap Jack
                    } else if (chessBoard.isCheckmate()) {
                        checkmate = true;
                        gameModel.get_Winner();
                        JOptionPane.showMessageDialog(null, gameModel.get_CurrentPlayer() + " is the winner!");

                        Object[] options = { "Yes", "No" };

                        // Show the option dialog with "Yes" and "No" choices
                        int choice = JOptionPane.showOptionDialog(
                                null,
                                "Do you want to restart?",
                                "Restart",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);

                        // Process the user's choice
                        if (choice == JOptionPane.YES_OPTION) {
                            System.out.println("Restart game");
                            gameModel.start_Game();
                            chessView.resetFlip();
                            chessView.updateView();
                            checkmate = false;
                            chessBoard.resetCheckmateflag();
                            JOptionPane.showMessageDialog(null, "Restart game");

                        } else if (choice == JOptionPane.NO_OPTION) {
                            System.out.println("No");

                        } else if (choice == JOptionPane.CLOSED_OPTION) {
                            System.out.println("No");
                        }
                    } else {
                        gameModel.switchPlayer();
                        chessView.flipBoard();
                    }

                }else if(!selectedPiece.isValidMove(chessBoard, sqA, sqB)){
                    System.out.println("Move is invalid");
                }

            }

            // clear the source for next move
            sqA = null;
            sourcePane = null;

        }
    }

    //The actionPerformed function is for all buttons 
    @Override
    public void actionPerformed(ActionEvent e) {
        //if button pressed is Save Game button
        if (e.getSource() == chessView.newGameButton) {
            gameModel.start_Game();
            chessView.resetFlip();
            chessView.updateView();
            checkmate = false;
            chessBoard.resetCheckmateflag();
            JOptionPane.showMessageDialog(null, "New Game");
        }
        //if button pressed is Load Game 
        if (e.getSource() == chessView.loadGameButton) {
            if (gameModel != null) {
                gameModel.load_Game();
                chessView.resetFlip();
                chessView.updateView();
                if (gameModel.get_CurrentPlayer().equals(pieceColor.BLUE)) {
                    chessView.flipBoard();
                }
                checkmate = false;
                chessBoard.resetCheckmateflag();
            } else {
                System.err.println("Error: gameModel is null.");
            }
        }
                //if button pressed is Save Game 
        if (e.getSource() == chessView.saveGameButton) {
            if (gameModel != null) {
                gameModel.save_Game();
            } else {
                System.err.println("Error: gameModel is null.");
            }
        }
        //if button pressed is Resign Game 
        if (e.getSource() == chessView.resignButton) {
            gameModel.resigning();
            pieceColor current_player = gameModel.get_CurrentPlayer();
            String resigning_player = current_player.toString();
            String message = resigning_player + " resigned";
            gameModel.start_Game();
            chessView.resetFlip();
            chessView.updateView();
            checkmate = false;
            chessBoard.resetCheckmateflag();
            JOptionPane.showMessageDialog(null, message);
        }
        //if button pressed is Draw 
        if (e.getSource() == chessView.drawButton) {
            String message = null;
            pieceColor current_player = gameModel.get_CurrentPlayer();
            if (current_player == pieceColor.YELLOW) {
                message = pieceColor.BLUE.toString() + " do you accept?";
            } else {
                message = pieceColor.YELLOW.toString() + " do you accept?";
            }
            // Combine the variables into a single message

            Object[] options = { "Yes", "No" };

            // Show the option dialog with "Yes" and "No" choices
            int choice = JOptionPane.showOptionDialog(
                    null,
                    message,
                    "Draw Request",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            // Process the user's choice
            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("It is a draw");
                gameModel.start_Game();
                chessView.resetFlip();
                chessView.updateView();
                checkmate = false;
                chessBoard.resetCheckmateflag();
                JOptionPane.showMessageDialog(null, "It is a draw");

            } else if (choice == JOptionPane.NO_OPTION) {
                System.out.println("No");
                JOptionPane.showMessageDialog(null, "Draw request rejected");

            } else if (choice == JOptionPane.CLOSED_OPTION) {
                System.out.println("No");
                JOptionPane.showMessageDialog(null, "Draw request rejected");
            }

        }
    }

}
