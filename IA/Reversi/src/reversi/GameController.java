package reversi;

import gui.GUI;

/**
 *
 * @author renato
 */
public class GameController {

    private GameBoard gameBoard;
    private AI ai;
    private GUI gui;

    public void initializeGame() {
        gameBoard = new GameBoard();
        ai = new AI(GameBoard.WHITE, 3000);
        gameBoard.initialize();
        gui = new GUI(this);
    }

    public void play(int x, int y) {
        try {
            gameBoard.makeMove(GameBoard.BLACK, x, y);
            Position aiMove = ai.computeNextMove(gameBoard);
            System.out.println("IA MOVE: " + aiMove.getI() + " " + aiMove.getJ());
            gameBoard.makeMove(GameBoard.WHITE, aiMove.getI(), aiMove.getJ());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            gui.displayError(x, y);
        }
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
