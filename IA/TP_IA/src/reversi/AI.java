package reversi;

/**
 * Classe que controla a Inteligência Artificial do jogo.
 * A IA irá maximizar o valor.
 *
 * @author renato
 */
public class AI {

    private long startTime;
    private Position bestMove;
    private final int aiColor;
    private final long timeout;

    private final int MAX_TREE_DEPTH = 4;

    public AI(int color, long timeout) {
        this.aiColor = color;
        this.timeout = timeout;
        this.bestMove = null;
    }

    public Position computeNextMove(GameBoard gameBoard) {
        this.startTime = System.currentTimeMillis();
        if (!gameBoard.canMove(aiColor)) {
            throw new IllegalArgumentException("Não há jogadas disponíveis");
        }

        // Chama maxValue para obter o próximo movimento
        maxValue(gameBoard, 0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

        return this.bestMove;
    }

    private double maxValue(GameBoard gameBoard, int treeDepth, double alpha, double beta) {
        if (treeDepth >= MAX_TREE_DEPTH || !isThereTime() || isGameOver(gameBoard)) {
            return evaluateGameBoard(gameBoard);
        }
        if (!gameBoard.canMove(aiColor)) { // Passa a jogada se não puder mover
            return minValue(gameBoard, treeDepth + 1, alpha, beta);
        }
        GameBoard gameBoardCopy = new GameBoard(gameBoard);
        Position pos = null;
        Position localBestMove = null;

        double bestResult = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < GameBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < GameBoard.BOARD_SIZE; j++) {
                pos = new Position(i, j);
                if (gameBoardCopy.canMoveFromPosition(aiColor, i, j)) {
                    gameBoardCopy.makeMove(aiColor, i, j);

                    alpha = minValue(gameBoardCopy, treeDepth + 1, alpha, beta);
                    if (alpha > bestResult) {
                        localBestMove = pos;
                        bestResult = alpha;
                    }

                }
                if (alpha >= beta) {
                    break;
                }
                gameBoardCopy = new GameBoard(gameBoard);
            }
        }
        bestMove = localBestMove;
        return alpha;
    }

    private double minValue(GameBoard gameBoard, int treeDepth, double alpha, double beta) {
        if (treeDepth >= MAX_TREE_DEPTH || !isThereTime() || isGameOver(gameBoard)) {
            return evaluateGameBoard(gameBoard);
        }
        int enemyColor = getEnemyColor();
        if (!gameBoard.canMove(enemyColor)) {
            return maxValue(gameBoard, treeDepth + 1, alpha, beta);
        }
        GameBoard gameBoardCopy = new GameBoard(gameBoard);
        Position pos = null;
        for (int i = 0; i < GameBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < GameBoard.BOARD_SIZE; j++) {
                pos = new Position(i, j);
                if (gameBoardCopy.canMoveFromPosition(enemyColor, i, j)) {
                    gameBoardCopy.makeMove(enemyColor, i, j);
                    beta = Math.min(beta, maxValue(gameBoardCopy, treeDepth + 1, alpha, beta));
                }
                if (beta <= alpha) {
                    break;
                }
                gameBoardCopy = new GameBoard(gameBoard);
            }
        }
        return beta;

    }

    private double evaluateGameBoard(GameBoard gb) {
        int enemyColor = getEnemyColor();
        double aiCount = gb.countPlayerStones(aiColor);
        double enemyCount = gb.countPlayerStones(enemyColor);
        return aiCount - enemyCount;
    }

    private boolean isThereTime() {
        if ((timeout - (System.currentTimeMillis() - startTime)) > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isGameOver(GameBoard gb) {
        return gb.isGameOver();
    }

    private int getEnemyColor() {
        return (this.aiColor == GameBoard.BLACK) ? GameBoard.WHITE : GameBoard.BLACK;
    }

}
