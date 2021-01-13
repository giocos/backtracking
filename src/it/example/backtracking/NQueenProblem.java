package it.example.backtracking;

public class NQueenProblem {

    private static final int N = 8;

    private static boolean nQueen(final int[][] board, final int row) {
        for (int col = 0; col < N; col++) {
            board[row][col] = 1;
            if (findSolution(board, (row + 1), 0, 1)) {
                return true;
            }
            board[row][col] = 0;
        }
        return false;
    }

    /**
     * Recursive function.
     * Given two starting points, the function will find first right solution.
     *
     * @param board
     * @param row
     * @param col
     * @param count
     * @return
     */
    private static boolean findSolution(final int[][] board, int row, int col, int count) {
        if (count == N) {
            return true;
        }
        if (col == N) {
            return false;
        }

        if (canPlace(board, row, col)) {
            board[row][col] = 1;
            if (findSolution(board, (row + 1), 0, (count + 1))) {
                return true;
            }
            board[row][col] = 0;
        }
        return findSolution(board, row, (col + 1), count);
    }

    /**
     * Check if for current position in board[row][col] can place a queen.
     * The for loop will cycle until reach the start position.
     * If not any check is fired the method will return true.
     * @param board
     * @param row
     * @param col
     * @return
     */
    private static boolean canPlace(int[][] board, int row, int col) {
        for (int i = row; i > 0; i--) {
            final int xDiagSx = row - i;
            final int yDiagSx = col - i;
            final int yDiagDx = col + i;
            if (isNotEmpty(board, row, yDiagSx)) return false;
            if (isNotEmpty(board, xDiagSx, col)) return false;
            if (isNotEmpty(board, xDiagSx, yDiagSx)) return false;
            if (isNotEmpty(board, xDiagSx, yDiagDx)) return false;
        }
        return true;
    }

    private static boolean isNotEmpty(int[][] board, int currX, int currY) {
        if (currX < 0 || currX >= N || currY < 0 || currY >= N) {
            return false;
        }
        return board[currX][currY] == 1;
    }

    /**
     * Utility print function
     * @param board
     */
    private static void print(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final int[][] board = new int[N][N];

        if (nQueen(board, 0)) {
            print(board);
        } else {
            System.err.println("SOLUTION NOT FOUND!");
        }
    }
}
