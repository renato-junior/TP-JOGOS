/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 *
 * @author Renato
 */
public class Reversi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        GameBoard gb = new GameBoard();
        gb.initialize();
        gb.printGameBoardToOutput(System.out);
        System.out.println(gb.canMove(GameBoard.WHITE));
    }
    
}
