package game;

import java.util.ArrayList;

public class Board {
    private int[][] gameBoard;               // the game board array
    private ArrayList<BoardSpot> openSpaces; // the ArrayList of open spots: board cells without numbers.

    /**
     * Zero-argument Constructor: initializes a 4x4 game board.
     **/
    
    public Board() {
        gameBoard = new int[4][4];
        openSpaces = new ArrayList<>();
    }

    /**
     * One-argument Constructor: initializes a game board based on a given array.
     * 
     * @param board the board array with values to be passed through
     **/

    public Board ( int[][] board ) {
        gameBoard = new int[board.length][board[0].length];
        for ( int r = 0; r < gameBoard.length; r++ ) {
            for ( int c = 0; c < gameBoard[r].length; c++ ) {
                gameBoard[r][c] = board[r][c];
            }
        }
        openSpaces = new ArrayList<>();
    }

    /**
     * 1. Initializes the instance variable openSpaces (open board spaces) with an empty array.
     * 2. Adds open spots to openSpaces (the ArrayList of open BoardSpots).
     **/

    public void updateOpenSpaces() {
        
            openSpaces = new ArrayList<BoardSpot>();
            for (int row = 0; row < gameBoard.length; row++) {
                for (int col = 0; col < gameBoard[row].length; col++) {
                    if (gameBoard[row][col] == 0) {
                        openSpaces.add(new BoardSpot(row, col));
                    }

                }

            }
        }

    /**
     * Adds a random tile to an open spot with a 90% chance of a 2 value and a 10% chance of a 4 value.
     * Requires separate uses of StdRandom.uniform() to find a random open space and determine probability of a 4 or 2 tile.
     **/

    public void addRandomTile() {

        BoardSpot bspot = openSpaces.get(StdRandom.uniform(0, openSpaces.size()));
        double randomVal = StdRandom.uniform(0.0, 1.0);
        if (randomVal < 0.1) {
            gameBoard[bspot.getRow()][bspot.getCol()] = 4;
        }    
        else {
            gameBoard[bspot.getRow()][bspot.getCol()] = 2;
        }

    }

    /**
     * Swipes the entire board left, shifting all nonzero tiles as far left as possible.
     * Maintains the same number and order of tiles. 
     **/

    public void swipeLeft() {

        int newRow[] = new int[gameBoard[0].length];
        int counter = 0;
        for (int row = 0; row < gameBoard.length; row++) {

            for (int col = 0; col < gameBoard[row].length; col++) {
                if (gameBoard[row][col] != 0) {
                    newRow[counter] = gameBoard[row][col];
                    counter++;
                }
           
                
            }  

            counter = 0;
            gameBoard[row] = newRow;
            newRow = new int[gameBoard[0].length];  
               
        }

    }

    /**
     * Find and merge all identical left pairs in the board. Ex: "2 2 2 2" will become "2 0 2 0".
     * The leftmost value takes on double its own value, and the rightmost empties and becomes 0.
     **/

    public void mergeLeft() {

        for (int row = 0; row < gameBoard.length; row++) {
            int j = 1;
            for (int col = 0; col < gameBoard[row].length - 1; col++) {
                if (gameBoard[row][col] == gameBoard[row][j]) {
                    gameBoard[row][col] = gameBoard[row][col] + gameBoard[row][j];
                    gameBoard[row][j] = 0;            
                }               
                j++;
            }
        }
    }

    /**
     * Rotates 90 degrees clockwise by taking the transpose of the board and then reversing rows. 
     **/

    public void rotateBoard() {
        transpose();
        flipRows();
    }

    /**
     * Updates the instance variable gameBoard to be its transpose. 
     * Transposing flips the board along its main diagonal (top left to bottom right).
     **/

    public void transpose() {

        int[][] updatedArr = new int[gameBoard.length][gameBoard[0].length];
        for (int col = 0; col < gameBoard[0].length; col++) {
            
            for (int row = 0; row < gameBoard.length; row++) {
                updatedArr[col][row] = gameBoard[row][col];
            }

        }
        gameBoard = updatedArr;
    }

    /**
     * Updates the instance variable gameBoard to reverse its rows.
     * Reverses all rows. Columns 1, 2, 3, and 4 become 4, 3, 2, and 1. 
     **/

    public void flipRows() {

        int newRow[] = new int[gameBoard.length];

        for (int row = 0; row < gameBoard.length; row++) {
            int counter = 0;
            for (int col = gameBoard[row].length - 1; col >= 0; col--) {

                newRow[counter] = gameBoard[row][col];
                counter++;

            }   
            gameBoard[row] = newRow;
            newRow = new int[gameBoard.length];  

        } 
    
    }

    /**
     * Calls previous methods to make right, left, up and down moves.
     **/

    public void makeMove(char letter) {
        if (letter == 'U') {
            rotateBoard();
            rotateBoard();
            rotateBoard();
            swipeLeft();
            mergeLeft();
            swipeLeft();
            rotateBoard();

        } else if (letter == 'D') {
            rotateBoard();
            swipeLeft();
            mergeLeft();
            swipeLeft();
            rotateBoard();
            rotateBoard();
            rotateBoard();

        } else if (letter == 'R') {
            rotateBoard();
            rotateBoard();
            swipeLeft();
            mergeLeft();
            swipeLeft();
            rotateBoard();
            rotateBoard();
        
        } else if (letter == 'L') {
            swipeLeft();
            mergeLeft();
            swipeLeft();

        }

    }

    /**
     * Returns true when the game is lost and no empty spaces are available. Ignored
     * when testing methods in isolation.
     **/

    public boolean isGameLost() {
        return openSpaces.size() == 0;
    }

    /**
     * Shows a final score when the game is lost.
     **/

    public int showScore() {
        int score = 0;
        for ( int r = 0; r < gameBoard.length; r++ ) {
            for ( int c = 0; c < gameBoard[r].length; c++ ) {
                score += gameBoard[r][c];
            }
        }
        return score;
    }

    /**
     * Prints the board as integer values in the text window.
     **/

    public void print() {
        for ( int r = 0; r < gameBoard.length; r++ ) {
            for ( int c = 0; c < gameBoard[r].length; c++ ) {
                String g = Integer.toString(gameBoard[r][c]);
                StdOut.print((g.equals("0")) ? "-" : g);
                for ( int o = 0; o < (5 - g.length()); o++ ) {
                    StdOut.print(" ");
                }
            }
            StdOut.println();
        }
    }

    /**
     * Prints the board as integer values in the text window, with open spaces denoted by "**"". Used by TextDriver.
     **/

    public void printOpenSpaces() {
        for ( int r = 0; r < gameBoard.length; r++ ) {
            for ( int c = 0; c < gameBoard[r].length; c++ ) {
                String g = Integer.toString(gameBoard[r][c]);
                for ( BoardSpot bs : getOpenSpaces() ) {
                    if (r == bs.getRow() && c == bs.getCol()) {
                        g = "**";
                    }
                }
                StdOut.print((g.equals("0")) ? "-" : g);
                for ( int o = 0; o < (5 - g.length()); o++ ) {
                    StdOut.print(" ");
                }
            }
            StdOut.println();
        }
    }

    public Board(long seed) {
        StdRandom.setSeed(seed);
        gameBoard = new int[4][4];
    }

    /**
     * Gets the open board spaces.
     **/

    public ArrayList<BoardSpot> getOpenSpaces() {
        return openSpaces;
    }

    /**
     * Gets the board 2D array values.
     **/

    public int[][] getBoard() {
        return gameBoard;
    }
}
