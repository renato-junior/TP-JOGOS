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
        GameController controller = new GameController();
        controller.initializeGame();
//        while (true) {
//            System.out.println("Reversi v. Alpha 1");
//            System.out.println("Selecione a opção: ");
//            System.out.println("\t1 - Jogar (Preto).");
//            System.out.println("\t2 - Imprimir estado do tabuleiro.");
//            System.out.println("\tOutro - Sair.");
//            int option = new Scanner(System.in).nextInt();
//            switch (option) {
//                case 1:
//                    gb.printGameBoardToOutput(System.out);
//                    System.out.println("Digite a posição X:");
//                    int x = new Scanner(System.in).nextInt();
//                    System.out.println("Digite a posição Y:");
//                    int y = new Scanner(System.in).nextInt();
//                    try {
//                        gb.makeMove(GameBoard.BLACK, x, y);
//                        System.out.println("IA irá jogar agora!");
//                        Position aiMove = ai.computeNextMove(gb);
//                        System.out.println("IA MOVE: "+aiMove.getI()+" "+aiMove.getJ());
//                        gb.makeMove(GameBoard.WHITE, aiMove.getI(), aiMove.getJ());
//                        System.out.println("IA jogou!");
//                        gb.printGameBoardToOutput(System.out);
//                    } catch(IllegalArgumentException ex) {
//                        System.out.println(ex.getLocalizedMessage());
//                    }
//                    break;
//                case 2:
//                    gb.printGameBoardToOutput(System.out);
//                    break;
//                default:
//                    System.exit(0);
//                    break;
//
//            }
//        }

//        while (true) {
//            System.out.println("Reversi v. Alpha 1");
//            System.out.println("Selecione a opção: ");
//            System.out.println("\t1 - Jogar como Branco.");
//            System.out.println("\t2 - Jogar como Preto.");
//            System.out.println("\t3 - Imprimir estado do tabuleiro.");
//            System.out.println("\tOutro - Sair.");
//            int option = new Scanner(System.in).nextInt();
//            switch (option) {
//                case 1:
//                    gb.printGameBoardToOutput(System.out);
//                    System.out.println("Digite a posição X:");
//                    int x = new Scanner(System.in).nextInt();
//                    System.out.println("Digite a posição Y:");
//                    int y = new Scanner(System.in).nextInt();
//                    try {
//                        gb.makeMove(GameBoard.WHITE, x, y);
//                    } catch(IllegalArgumentException ex) {
//                        System.out.println(ex.getLocalizedMessage());
//                    }
//                    break;
//                case 2:
//                    gb.printGameBoardToOutput(System.out);
//                    System.out.println("Digite a posição X:");
//                    int xx = new Scanner(System.in).nextInt();
//                    System.out.println("Digite a posição Y:");
//                    int yy = new Scanner(System.in).nextInt();
//                    try {
//                        gb.makeMove(GameBoard.BLACK, xx, yy);
//                    } catch(IllegalArgumentException ex) {
//                        System.out.println(ex.getLocalizedMessage());
//                    }
//                    break;
//                case 3:
//                    gb.printGameBoardToOutput(System.out);
//                    break;
//                default:
//                    System.exit(0);
//                    break;
//
//            }
//        gb.printGameBoardToOutput(System.out);
//        gb.makeMove(GameBoard.WHITE, 2, 4);
//        gb.printGameBoardToOutput(System.out);
//        System.out.println(gb.canMove(GameBoard.WHITE));
    }

}
