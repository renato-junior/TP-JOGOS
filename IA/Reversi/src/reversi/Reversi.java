/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.Scanner;

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
        while (true) {
            System.out.println("Reversi v. Alpha 1");
            System.out.println("Selecione a opção: ");
            System.out.println("\t1 - Jogar como Branco.");
            System.out.println("\t2 - Jogar como Preto.");
            System.out.println("\t3 - Imprimir estado do tabuleiro.");
            System.out.println("\tOutro - Sair.");
            int option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1:
                    gb.printGameBoardToOutput(System.out);
                    System.out.println("Digite a posição X:");
                    int x = new Scanner(System.in).nextInt();
                    System.out.println("Digite a posição Y:");
                    int y = new Scanner(System.in).nextInt();
                    try {
                        gb.makeMove(GameBoard.WHITE, x, y);
                    } catch(IllegalArgumentException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;
                case 2:
                    gb.printGameBoardToOutput(System.out);
                    System.out.println("Digite a posição X:");
                    int xx = new Scanner(System.in).nextInt();
                    System.out.println("Digite a posição Y:");
                    int yy = new Scanner(System.in).nextInt();
                    try {
                        gb.makeMove(GameBoard.WHITE, xx, yy);
                    } catch(IllegalArgumentException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;
                case 3:
                    gb.printGameBoardToOutput(System.out);
                    break;
                default:
                    System.exit(0);
                    break;

            }
        }
//        gb.printGameBoardToOutput(System.out);
//        gb.makeMove(GameBoard.WHITE, 2, 4);
//        gb.printGameBoardToOutput(System.out);
//        System.out.println(gb.canMove(GameBoard.WHITE));
    }

}
