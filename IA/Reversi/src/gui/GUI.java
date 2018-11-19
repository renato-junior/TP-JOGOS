package gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import reversi.GameBoard;
import reversi.GameController;

/**
 *
 * @author renato
 */
public class GUI extends Frame implements MouseListener {

    private GameController gameController;

    public static final int X_PADDING = 200;
    public static final int Y_PADDING = 50;
    public static final int BOARD_SIZE = 400;

    public GUI(GameController gc) {
        super("Reversi");
        setSize(800, 600);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        addMouseListener(this);
        setVisible(true);

        this.gameController = gc;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBoardLines(g);
        drawBoardStones(g);
    }

    private void drawBoardLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        // Retângulo em volta
        g2d.drawRect(200, 50, 400, 400);
        // Linhas horizontais
        g2d.draw(new Line2D.Double(200, 100, 600, 100));
        g2d.draw(new Line2D.Double(200, 150, 600, 150));
        g2d.draw(new Line2D.Double(200, 200, 600, 200));
        g2d.draw(new Line2D.Double(200, 250, 600, 250));
        g2d.draw(new Line2D.Double(200, 300, 600, 300));
        g2d.draw(new Line2D.Double(200, 350, 600, 350));
        g2d.draw(new Line2D.Double(200, 400, 600, 400));
        // Linhas verticais
        g2d.draw(new Line2D.Double(250, 50, 250, 450));
        g2d.draw(new Line2D.Double(300, 50, 300, 450));
        g2d.draw(new Line2D.Double(350, 50, 350, 450));
        g2d.draw(new Line2D.Double(400, 50, 400, 450));
        g2d.draw(new Line2D.Double(450, 50, 450, 450));
        g2d.draw(new Line2D.Double(500, 50, 500, 450));
        g2d.draw(new Line2D.Double(550, 50, 550, 450));
    }

    private void drawBoardStones(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int[][] board = gameController.getGameBoard().getBoard();
        for (int i = 0; i < GameBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < GameBoard.BOARD_SIZE; j++) {
                switch (board[i][j]) {
                    case GameBoard.BLACK:
                        g2d.setPaint(Color.BLACK);
                        g2d.fill(new Ellipse2D.Double(j * 50 + X_PADDING, i * 50 + Y_PADDING, 50, 50));
                        break;
                    case GameBoard.WHITE:
                        g2d.setPaint(Color.WHITE);
                        g2d.fill(new Ellipse2D.Double(j * 50 + X_PADDING, i * 50 + Y_PADDING, 50, 50));
                        break;
                    default:
                        break;
                }

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Obtém as coordenadas do clique
        int x = e.getX();
        int y = e.getY();
        if (isClickInsideBoard(x, y)) {
            // Calcula qual é a posição da matriz correspondente
            int i = ((y - Y_PADDING) - ((y - Y_PADDING) % 50)) / 50;
            int j = ((x - X_PADDING) - ((x - X_PADDING) % 50)) / 50;
            System.out.println("I: " + i + " J: " + j);
            gameController.play(i, j);
            this.paint(this.getGraphics());
            if (gameController.getGameBoard().isGameOver()) {
                int black = (int) gameController.getGameBoard().countPlayerStones(GameBoard.BLACK);
                int white = (int) gameController.getGameBoard().countPlayerStones(GameBoard.WHITE);
                if (black > white) {
                    JOptionPane.showMessageDialog(this, "Preto ganhou com " + black + " peças!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                } else if (white > black) {
                    JOptionPane.showMessageDialog(this, "Branco ganhou com " + white + " peças!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                } else if (white == black) {
                    JOptionPane.showMessageDialog(this, "Empate!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private boolean isClickInsideBoard(int x, int y) {
        return x >= X_PADDING && x <= (X_PADDING + BOARD_SIZE) && y >= Y_PADDING && y <= (Y_PADDING + BOARD_SIZE);
    }

    public void displayError(int i, int j) {
        int x = X_PADDING + j * 50;
        int y = Y_PADDING + i * 50;
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(Color.red);
        g2d.fill(new Rectangle(x, y, 50, 50));
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
        }
        g2d.clearRect(x, y, 50, 50);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
