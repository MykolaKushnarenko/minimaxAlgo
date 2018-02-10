import static java.lang.Math.max;
import static java.lang.Math.min;

class MinMax{

    int minMax(Board board, boolean flag ){
        int result = 0;
        if (board.myListBoard.size() == 0) {
            board.state = board.numberForEvr(board.player);
            return board.numberForEvr(board.player);
        }
        if (flag) {
            result = -1000000000;
        }
        else if (!flag) {
            result = 1000000000;
        }

        for (int i = 0; i < board.myListBoard.size(); i++) {
            if (flag) {
                result = max(result, this.minMax(board.myListBoard.get(i), !flag));

            } else {
                result = min(result, this.minMax(board.myListBoard.get(i), !flag));
            }
        }
        board.state = result;
        return result;
    }
}