import java.util.*;

public class Main {
    static class Move {
        int start;
        int skip;
        int end;

        Move(int s, int sk, int e) {
            start = s;
            skip = sk;
            end = e;
        }
    }

    static boolean[] startingBoard = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
    final static boolean[] complete_board = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    static boolean[] board = Arrays.copyOf(startingBoard, startingBoard.length);
    static Deque<Integer> stack = new LinkedList<>();
    final static Move[] POSS_MOVES = {new Move(0, 3, 8), new Move(0, 1, 2), new Move(1, 4, 9), new Move(2, 5, 10), new Move(2, 1, 0), new Move(3, 8, 15), new Move(3, 4, 5), new Move(4, 9, 16), new Move(5, 10, 17), new Move(5, 4, 3), new Move(6, 13, 20), new Move(6, 7, 8), new Move(7, 14, 21), new Move(7, 8, 9), new Move(8, 15, 22), new Move(8, 3, 0), new Move(8, 9, 10), new Move(8, 7, 6), new Move(9, 16, 23), new Move(9, 4, 1), new Move(9, 10, 11), new Move(9, 8, 7), new Move(10, 17, 24), new Move(10, 5, 2), new Move(10, 11, 12), new Move(10, 9, 8), new Move(11, 18, 25), new Move(11, 10, 9), new Move(12, 19, 26), new Move(12, 11, 10), new Move(13, 14, 15), new Move(14, 15, 16), new Move(15, 22, 27), new Move(15, 8, 3), new Move(15, 16, 17), new Move(15, 14, 13), new Move(16, 23, 28), new Move(16, 9, 4), new Move(16, 17, 18), new Move(16, 15, 14), new Move(17, 24, 29), new Move(17, 10, 5), new Move(17, 18, 19), new Move(17, 16, 15), new Move(18, 17, 16), new Move(19, 18, 17), new Move(20, 13, 6), new Move(20, 21, 22), new Move(21, 14, 7), new Move(21, 22, 23), new Move(22, 27, 30), new Move(22, 15, 8), new Move(22, 23, 24), new Move(22, 21, 20), new Move(23, 28, 31), new Move(23, 16, 9), new Move(23, 24, 25), new Move(23, 22, 21), new Move(24, 29, 32), new Move(24, 17, 10), new Move(24, 25, 26), new Move(24, 23, 22), new Move(25, 18, 11), new Move(25, 24, 23), new Move(26, 19, 12), new Move(26, 25, 24), new Move(27, 22, 15), new Move(27, 28, 29), new Move(28, 23, 16), new Move(29, 24, 17), new Move(29, 28, 27), new Move(30, 27, 22), new Move(30, 31, 32), new Move(31, 28, 23), new Move(32, 29, 24), new Move(32, 31, 30)};

    public static void display(boolean[] currentBoard) {
        int[] LINES = {3, 3, 7, 7, 7, 3, 3};
        int i = 0;
        System.out.println();
        for (int line : LINES) {
            StringBuilder txt = new StringBuilder();
            if (line == 3) {
                txt.append("    ");
            }
            for (int j = 0; j < line; j++) {
                if (currentBoard[i]) {
                    txt.append("O ");
                } else {
                    txt.append("_ ");
                }
                i++;
            }
            System.out.println(txt);
        }
    }

    public static void solve(){
        int j = 0;
        while (true){
            boolean no_moves_found = true;
            for (int i = j; i < POSS_MOVES.length; i++) {
                Move move = POSS_MOVES[i];
                if (board[move.start] && board[move.skip] && !board[move.end]){
                    stack.push(i);
                    board[move.start] = false;
                    board[move.skip] = false;
                    board[move.end] = true;
                    no_moves_found = false;
                    break;
                }
            }
            if (Arrays.equals(board,complete_board)){
                displaySolution();
                return;
            } else if (no_moves_found) {
                j = stack.pop();
                Move move = POSS_MOVES[j];
                board[move.start] = true;
                board[move.skip] = true;
                board[move.end] = false;
                j++;
            } else {
                j = 0;
            }
        }
    }

    public static void displaySolution() {
        int[] moves = new int[stack.size()];
        int i = 0;
        for (int moveI: stack){
            moves[i] = moveI;
            i++;
        }
        for (int moveIndex: moves){
            try {
                Move move = POSS_MOVES[moveIndex];
                startingBoard[move.start] = false;
                startingBoard[move.skip] = false;
                startingBoard[move.end] = true;
                display(startingBoard);
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    
    public static void main(String[] args) {
        solve();
    }
}