import com.sun.rowset.internal.Row;

import java.sql.SQLOutput;
import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean gameDone = false;
        int moveCount = 0;
        String player = "";
        int row = 0;
        int col = 0;
        boolean gameWinOrTie = false;
        //Step 1: clear the board, move count to 0, and set player to x
        while (!gameDone) {
            clearBoard();
            moveCount = 0;
            player = "X";
            gameWinOrTie = false;
            //Step 4: loop 2+3 until valid move
            do {
                //Step 2: show board, get coordinate for move (1-3 for row and col)
                display();
                System.out.println("Player " + player + ", make your move!");
                //Step 3: Convert player move to the array indices (by - 1)
                row = SafeInput.getRangedInt(in, "Row ", 1, 3) - 1;
                col = SafeInput.getRangedInt(in, "Column ", 1, 3) - 1;
                //Step 4: Loop 2+3 until valid move
                if (isValidMove(row, col)) {
                    //Step 5: record the move on the board and increment the counter
                    board[row][col] = player;
                    moveCount++;
                    //Step 6: check for win if possible
                    if (moveCount >= 5) {
                        //Step 7: Announce win or tie if applicable
                        if (isWin(player) == true) {
                            gameWinOrTie = true;
                            System.out.println("Congrats! Player " + player + " has won the game!");
                            break;
                        } else if (isTie() == true) {
                            gameWinOrTie = true;
                            display();
                            break;
                        } else {
                            gameWinOrTie = false;
                        }
                    }
                    //Step 8: toggle player for next move
                    if (player.equals("X")) {
                        player = "O";
                    } else if (player.equals("O")) {
                        player = "X";
                    }
                } else {
                    System.out.println("Illegal Move! Try again!");
                }
            } while (!gameWinOrTie);
            gameDone = SafeInput.getYNConfirm(in, "Would you like to play again? ");
        }
    }

    private static void clearBoard() {
        //set all the board elements to a space
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        //shows the Tic Tac Toe game as used as part of the prompt for the users move choice
        System.out.println("-------------");
        for (int row = 0; row < ROW; row++)
        {
            System.out.print("| ");
            for (int col = 0; col < COL; col++)
            {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");

        }

    }

    private static boolean isValidMove(int row, int col) {
        //returns true if there is a space at the given proposed move coordinate which means it is a legal move
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        //checks to see if there is a win state on the current board for specified player (X or O)
        //This method in turn calls three additional methods that break down the 3 kinds of wins
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player) {
        //check for a col win for specified player
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        //checks for a row win for specified player
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        //check for diagonal win for specified player
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        } else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull()  {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasPossibleWin(String player) {
        //check rows for possible wins
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(" ")
            || board[row][0].equals(player) && board[row][1].equals(" ") && board[row][2].equals(player)
            || board[row][0].equals(" ") && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        //check columns for possible wins
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(" ")
                || board[0][col].equals(player) && board[1][col].equals(" ") && board[2][col].equals(player)
                || board[0][col].equals(" ") && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        //check diagonals for possible wins
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(" ")
        || board[0][0].equals(player) && board[1][1].equals(" ") && board[2][2].equals(player)
        || board[0][0].equals(" ") && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        }
        if (board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(" ")
        || board[2][0].equals(player) && board[1][1].equals(" ") && board[0][2].equals(player)
        || board[2][0].equals(" ") && board[1][1].equals(player) && board[0][2].equals(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        //check for a tie condition ie: all spaces filled or no possible 3 in a row wins
        if (isBoardFull() && !isWin("X") && !isWin("O"))
        {
            System.out.println("The Board is full! It's a tie!");
            return true;
        } else if (!hasPossibleWin("X") && !hasPossibleWin("O")) {
            System.out.println("There are no possible wins left on the board! It's a tie!");
            return true;
        }
        return false;
    }
}