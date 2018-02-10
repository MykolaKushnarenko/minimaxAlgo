import java.util.ArrayList;
import java.util.Scanner;

class Board {

    private Scanner scan = new Scanner(System.in);

    ArrayList<Board> myListBoard = new ArrayList<Board>();
    private char[][] cell = new char[3][3];
    char player = 'X';
    double state = 0.0;
    char playerWhoPlay;

    public void searchTwo(char symbol){//полу ход
        if(myListBoard.size() == 0){
            for (int i = 0; i < cell.length; i++) {
                for (int j = 0; j < cell.length; j++) {
                    set(i, j, symbol);
                }
            }
        }
        else {
            for (int i = 0; i < myListBoard.size(); i++) {
                myListBoard.get(i).searchTwo(symbol);
            }
        }
    }

    public char[][] clone(){
        char[][] cell = new char[3][3];
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell.length; j++) {
                cell[i][j] = this.cell[i][j];
            }
        }
        return cell;
    }

    Board set(int i, int j, char ch){
        Boolean isOk = false;
        Board board = new Board(this.player);
        board.cell = clone();
        if(cell[i][j] == '-' && !this.hasWon('X') && !this.hasWon('O')){
            isOk = true;
            board.cell[i][j] = ch;
        }
        if(isOk){
            board.myListBoard.clear();
            this.myListBoard.add(board);
        }
        return board;
    }

    public int numberForEvr(char ch){
        int scoreX = 0;
        int scoreO = 0;
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell.length; j++) {
                if(cell[i][j] == 'O'){
                    scoreO++;
                }
                if(cell[i][j]=='X'){
                    scoreX++;
                }
            }
        }
        if (hasWon('O')){
            scoreO += 100;
        }
        if (hasWon('X')){
            scoreX += 100;
        }
        if(player == 'O'){
            return scoreO - scoreX;
        }
        else return scoreX - scoreO;
    }

    Board(char playerz){
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell.length; j++) {
                cell[i][j] = '-';
            }
        }
        this.playerWhoPlay = player;
    }

    public void inputStat() {

        System.out.println("Enter number figure: ");
        int countX = 0;
        int countO = 0;
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            if (countX == countO) {
                //System.out.println("111LLL:" + cell.length);
                System.out.print("Symbol 'X', enter row[1-3] col[1-3]: ");
                int row = scan.nextInt() - 1;
                int col = scan.nextInt() - 1;
                cell[row][col] = 'X';
                countX++;
                printBoard();
            } else {
                System.out.print("Symbol 'O', enter row[1-3] col[1-3]: ");
                int row = scan.nextInt() - 1;
                int col = scan.nextInt() - 1;
                cell[row][col] = 'O';
                countO++;
                printBoard();
            }
        }
    }


    public void printBoard() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cell[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }


    boolean hasWon(char theSeed) {
        return (cell[0][0] == theSeed
                && cell[1][1] == theSeed
                && cell[2][2] == theSeed

                || cell[0][0] == theSeed
                && cell[1][0] == theSeed
                && cell[2][0] == theSeed

                || cell[0][0] == theSeed
                && cell[0][1] == theSeed
                && cell[0][2] == theSeed

                || cell[1][0] == theSeed
                && cell[1][1] == theSeed
                && cell[1][2] == theSeed

                || cell[2][0] == theSeed
                && cell[2][1] == theSeed
                && cell[2][2] == theSeed

                || cell[0][1] == theSeed
                && cell[1][1] == theSeed
                && cell[2][1] == theSeed

                || cell[0][2] == theSeed
                && cell[1][2] == theSeed
                && cell[2][2] == theSeed

                || cell[0][2] == theSeed
                && cell[1][1] == theSeed
                && cell[2][0] == theSeed);
    }
}