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
        boolean gameWin = false;
        //clear the board, move count to 0, and set player to x
        while (!gameDone){
            clearBoard();
            moveCount = 0;
            player = "X";

            // show board, get coordinate for move (1-3 for row and col)
            do {
                display();
                System.out.println("Player " + player + ", enter your move (row [1-3] column [1-3]: ");
                //loop till move is valid
                row = SafeInput.getRangedInt(in, "Row: ", 1, 3) - 1;
                col = SafeInput.getRangedInt(in, "Column: ", 1, 3) - 1;
            }while (!gameWin);

            //record the move on the board and increment the move counter

            //check for win (if possible)

            //announce win or tie if applicable, prompt to play again

            //toggle player for next move




        }
        gameDone = SafeInput.getYNConfirm(in, "Would you like to play again?");
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

    /*
    private static boolean isTie() {
        //check for a tie condition ie: all spaces filled or no possible 3 in a row wins
    }

     */

}