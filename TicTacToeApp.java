package gr.aueb.cf.finalProjects;

import java.util.Scanner;

/**
 * A simple Tic-Tac-Toe game for two players.
 *
 * @author GeorgeRachiotis
 * @version 1.0
 */
public class TicTacToeApp {

    /**
     * The 3x3 game grid.
     * 0 = empty, 1 = Player 1, 2 = Player 2.
     */
    static int[][] grid = new int[3][3];

    /**
     * Controls the flow of the game.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int currentPlayer = 1;
        int moves = 0;

        while (moves < 9) {

            printGrid();

            System.out.println("Player " + currentPlayer + " please enter row and column:");

            if (!in.hasNextInt()) {
                System.out.println("Invalid input. Please enter numbers only.");
                in.nextLine();
                continue;
            }

            int row = in.nextInt();

            if (!in.hasNextInt()) {
                System.out.println("Invalid input. Please enter numbers only.");
                in.nextLine();
                continue;
            }

            int column = in.nextInt();

            if (row < 1 || row > 3 || column < 1 || column > 3) {
                System.out.println("Invalid input.");
                continue;
            }

            boolean success = placeMove(currentPlayer, row - 1, column - 1);

            if (!success) {
                System.out.println("This position is already taken.");
                continue;
            }

            moves++;

            if (isWin(currentPlayer)) {
                printGrid();
                System.out.println("Player " + currentPlayer + " won the game.");
                break;
            }

            if (moves == 9) {
                printGrid();
                System.out.println("We have a tie!");
                break;
            }

            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }

        in.close();
    }

    /**
     * Places a player's move on the grid.
     *
     * @param player the current player
     * @param row the row index
     * @param column the column index
     * @return true if the move was successful, otherwise false
     */
    public static boolean placeMove(int player, int row, int column) {
        if (grid[row][column] != 0) {
            return false;
        }

        grid[row][column] = player;
        return true;
    }

    /**
     * Checks if the player has won the game.
     *
     * @param player the player to check
     * @return true if the player has won, otherwise false
     */
    public static boolean isWin(int player) {
        return isRowWin(player)
                || isColumnWin(player)
                || isMainDiagonalWin(player)
                || isAntiDiagonalWin(player);
    }

    /**
     * Checks if the player has completed a row.
     *
     * @param player the player to check
     * @return true if the player has a complete row
     */
    public static boolean isRowWin(int player) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == player
                    && grid[i][1] == player
                    && grid[i][2] == player) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the player has completed a column.
     *
     * @param player the player to check
     * @return true if the player has a complete column
     */
    public static boolean isColumnWin(int player) {
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == player
                    && grid[1][i] == player
                    && grid[2][i] == player) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the player has completed the main diagonal.
     *
     * @param player the player to check
     * @return true if the player has completed the main diagonal
     */
    public static boolean isMainDiagonalWin(int player) {
        return grid[0][0] == player
                && grid[1][1] == player
                && grid[2][2] == player;
    }

    /**
     * Checks if the player has completed the anti-diagonal.
     *
     * @param player the player to check
     * @return true if the player has completed the anti-diagonal
     */
    public static boolean isAntiDiagonalWin(int player) {
        return grid[0][2] == player
                && grid[1][1] == player
                && grid[2][0] == player;
    }

    /**
     * Prints the current state of the grid.
     */
    public static void printGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (grid[i][j] == 0) {
                    System.out.print(". ");
                } else if (grid[i][j] == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }

            System.out.println();
        }
    }
}
