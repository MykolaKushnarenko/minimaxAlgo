import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Test1 test1 = new Test1();
        test1.repaint();
//
//        int countX = 0;
//        int countO = 0;
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Who's the player?\n" +
//                "Enter '1' - X and enter 'O' - 0: ");
//
//        int symbol = scan.nextInt();
//
//        if (symbol == 1) {
//            Board board = new Board('X');
//            board.inputStat();
//            System.out.print("Enter number move: ");
//            int move = scan.nextInt();
//
//            for (int i = 0; i < move * 2; i++) {
//                if (countX == countO) {
//                    board.searchTwo('X');
//                    countX++;
//                } else {
//                    board.searchTwo('O');
//                    countO++;
//                }
//            }
//            MinMax mx = new MinMax();
//            mx.minMax(board,true);
//
//        } else if (symbol == 0) {
//            Board board = new Board('O');
//            board.inputStat();
//            System.out.print("Enter number move: ");
//            int move = scan.nextInt();
//
//            for (int i = 0; i < move * 2; i++) {
//                if (countO != countX) {
//                    board.searchTwo('O');
//                    countO++;
//                } else {
//                    board.searchTwo('X');
//                    countX++;
//                }
//            }
//            MinMax mx = new MinMax();
//            mx.minMax(board,true);
//        }
    }
}