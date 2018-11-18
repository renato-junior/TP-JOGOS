package reversi;

import java.io.PrintStream;

/**
 *
 * @author renato.junior
 */
public class GameBoard {

    private int[][] board;

    public final static int BOARD_SIZE = 8;
    public final static int BLACK = 0;
    public final static int WHITE = 1;
    public final static int NONE = -1;

    public GameBoard() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Inicializa o tabuleiro com um outro tabuleiro. O estado do outro
     * tabuleiro é copiado para o novo.
     *
     * @param game o tabuleiro que se deseja copiar.
     */
    public GameBoard(GameBoard game) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.copyBoard(game);
    }

    /**
     * Limpa o tabuleiro, deixando todas as posiçõe vazias.
     */
    public void clearBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = NONE;
            }
        }
    }

    /**
     * Inicializa o tabuleiro, limpando-o e colocando as peças iniciais.
     * Inicialmente, as 4 posições centrais estão preenchidas com 2 peças
     * brancas e duas peças pretas formando um X
     */
    public void initialize() {
        this.clearBoard();
        this.board[3][3] = WHITE;
        this.board[4][4] = WHITE;
        this.board[4][3] = BLACK;
        this.board[3][4] = BLACK;
    }

    public boolean canMove(int color) {
        if (color != WHITE && color != BLACK) {
            throw new IllegalArgumentException("Cor inválida!");
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == NONE) {
                    if (canMoveFromPosition(color, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canMoveFromPosition(int color, int i, int j) {
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (checkNeighborhood(color, i, j, k, l)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se as peças em dada direção possibilitam colocar a peça na
     * posição i,j.
     *
     * @param color a cor que será colocada.
     * @param i a posição da peça.
     * @param j a posição da peça.
     * @param iinc o incremento na vertical.
     * @param jinc o incremento na horizontal.
     * @return se é possível colocar a peça da cor color na posição i,j.
     */
    private boolean checkNeighborhood(int color, int i, int j, int iinc, int jinc) {
        int otherColor = (color == BLACK ? WHITE : BLACK);
        int k, l;
        boolean executed = false;

        k = i + iinc;
        l = j + jinc;

        if (!isPositionValid(k, l)) { // Verifica se a posição está fora do tabuleiro
            return false;
        }

        while (board[k][l] == otherColor) {
            k += iinc;
            l += jinc;
            executed = true;

            if (!isPositionValid(k, l)) { // Verifica se a posição está fora do tabuleiro
                return false;
            }
        }
        return (executed && board[k][l] == color);
    }

    /**
     * Verifica se a posição i,j está dentro do tabuleiro.
     *
     * @param i coordenada x da posição que será verificada.
     * @param j coordenada y da posição que será verificada.
     * @return true, se a posição for válida, false, caso contrário.
     */
    private boolean isPositionValid(int i, int j) {
        return (i >= 0 && i < BOARD_SIZE) && (j >= 0 && j < BOARD_SIZE);
    }

    private boolean isMovePositionValid(int i, int j) {
        return isPositionValid(i, j) && this.board[i][j] == NONE;
    }

    public void makeMove(int color, int i, int j) {
        if (!isMovePositionValid(i, j) || !canMoveFromPosition(color, i, j)) {
            throw new IllegalArgumentException("Posição inválida!");
        }
        board[i][j] = color;
        int otherColor = (color == BLACK ? WHITE : BLACK);
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                int k1 = i + k;
                int l1 = j + l;
                if (!isPositionValid(k1, l1)) { 
                    continue;
                }
                while (board[k1][l1] == otherColor) {
                    k1 += k;
                    l1 += l;
                    if (!isPositionValid(k1, l1)) { 
                        break;
                    }
                }
                if (!isPositionValid(k1, l1)) { 
                    continue;
                }
                if (board[k1][l1] == color) {
                    // Muda as peças nessa direção para a cor de quem fez a jogada
                    k1 -= k;
                    l1 -= l;
                    while (board[k1][l1] == otherColor) {
                        board[k1][l1] = color;
                        k1 -= k;
                        l1 -= l;
                    }
                }
            }
        }
    }

    public final void copyBoard(GameBoard game) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = game.getBoard()[i][j];
            }
        }
    }

    public void printGameBoardToOutput(PrintStream output) {
        output.println("----------");
        output.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < BOARD_SIZE; i++) {
            output.print(i + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                output.print((this.board[i][j] != NONE ? this.board[i][j] : "x") + " ");
            }
            output.println();

        }
    }

    public double countPlayerStones(int playerColor) {
        double stoneCount = 0;
        for (int i = 0; i < GameBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < GameBoard.BOARD_SIZE; j++) {
                if (board[i][j] == playerColor) {
                    stoneCount++;
                }
            }
        }
        return stoneCount;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < GameBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < GameBoard.BOARD_SIZE; j++) {
                if (board[i][j] == NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

}
