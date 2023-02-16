package game;

import java.util.*;

public class TextDriver {

    public static void main(String[] args) {
        playFullGame();
    }

    private static void playFullGame() {
        Board board = new Board();
        board.updateOpenSpaces();
        board.addRandomTile();
        board.updateOpenSpaces();
        board.addRandomTile();
        board.print();
        StdOut.println("Actions: w = up, a = left, s = down, d = right, q = exit (hit ENTER to enter)");
        HashMap<Character, String> keyMap = new HashMap<>() {
            {
                put('w', "U");
                put('a', "L");
                put('s', "D");
                put('d', "R");
                put('W', "U");
                put('A', "L");
                put('S', "D");
                put('D', "R");
                put('q', "Exit");
            }
        };

        while (true) {
            String toRead = StdIn.readLine();
            String action = keyMap.getOrDefault(toRead.charAt(0), "Invalid");
            if (action.equals("Exit"))
                break;
            if (!action.equals("Invalid")) {
                int[][] oldBoard = new int[4][4];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        oldBoard[i][j] = board.getBoard()[i][j];
                    }
                }
                board.makeMove(action.charAt(0));
                board.updateOpenSpaces();
                if (!board.isGameLost() && !boardsMatch(oldBoard, board.getBoard())) {
                    board.addRandomTile();
                }
                StdOut.println("                  ");
                board.print();
            }
        }
    }

    private static boolean boardsMatch(int[][] board1, int[][] board2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1[i][j] != board2[i][j])
                    return false;
            }
        }
        return true;
    }
}
