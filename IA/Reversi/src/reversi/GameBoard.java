/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

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
        for (int i = 1; i < BOARD_SIZE - 1; i++) {
            for (int j = 1; j < BOARD_SIZE - 1; j++) {
                if (board[i][j] == NONE) {
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if (checkNeighborhood(color, i, j, l, l)) {
                                return true;
                            }
                        }
                    }
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
     * @return se é possível colocar a peça da color color na posição i,j.
     */
    private boolean checkNeighborhood(int color, int i, int j, int iinc, int jinc) {
        int otherColor = ~color;
        int k, l;
        boolean executed = false;

        k = i + iinc;
        l = j + jinc;

        while (board[k][l] == otherColor) {
            k += iinc;
            l += jinc;
            executed = true;
        }
        return (executed && board[k][l] == color);
    }
    
    public final void copyBoard(GameBoard game) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(game.getBoard()[i], 0, this.board[i], 0, BOARD_SIZE);
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

}
