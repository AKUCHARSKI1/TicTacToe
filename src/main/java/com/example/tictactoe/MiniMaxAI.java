package com.example.tictactoe;

public class MiniMaxAI {
    private static final int MAX_DEPTH = 6;

    private MiniMaxAI() {
    }

    public static int miniMax(char[][] board, int depth, boolean isMax) {
        int boardVal = evaluateBoard(board);

        // Terminal node (win/lose/draw) or max depth reached.
        if (Math.abs(boardVal) == 10 || depth == 0 || HelloController.counter == 0
                ) {
            return boardVal;
        }

        // Maximising player, find the maximum attainable value.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == '_') {
                        board[row][col] = 'o';
                        highestVal = Math.max(highestVal, miniMax(board,
                                depth - 1, true));
                        board[row][col] = '_';
                    }
                }
            }
            return highestVal;
            // Minimising player, find the minimum attainable value;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == '_') {
                        board[row][col] = 'x';
                        lowestVal = Math.min(lowestVal, miniMax(board,
                                depth - 1, true));
                        board[row][col] = '_';
                    }
                }
            }


            return lowestVal;
        }
    }

    public static int[] getBestMove(char[][] board) {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '_') {
                    board[row][col] = 'o';
                    int moveValue = miniMax(board, MAX_DEPTH, false);
                    board[row][col] = '_';
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }


    private static int evaluateBoard(char[][] board) {
        int rowSumX = 0;
        int rowSumO = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if(board[row][col] == 'o') rowSumO++;
                if(board[row][col] == 'x') rowSumX++;
            }
            if (rowSumO == 3) {
                return 10;
            } else if (rowSumX == 3) {
                return -15;
            }
            rowSumX = 0;
            rowSumO = 0;
        }

        rowSumX = 0;
        rowSumO = 0;

        // Check columns for winner.
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if(board[row][col] == 'o') rowSumO++;
                if(board[row][col] == 'x') rowSumX++;
            }
            if (rowSumO == 3) {
                return 10;
            } else if (rowSumX == 3) {
                return -15;
            }
            rowSumX = 0;
            rowSumO = 0;
        }

        // Check diagonals for winner.
        // Top-left to bottom-right diagonal.
        rowSumX = 0;
        rowSumO = 0;
        for (int i = 0; i < 3; i++) {
            if(board[i][i] == 'x') rowSumX++;
            if(board[i][i] == 'o') rowSumO++;
        }
        if (rowSumO == 3) {
            return 10;
        } else if (rowSumX == 3) {
            return -15;
        }
        rowSumX = 0;
        rowSumO = 0;
        // Top-right to bottom-left diagonal.
        for (int i = 0; i < 2; i++) {
            if(board[i][2-i] == 'x') rowSumX++;
            if(board[i][2-i] == 'o') rowSumO++;
        }
        if (rowSumO == 3) {
            return 10;
        } else if (rowSumX == 3) {
            return -15;
        }

        return 0;
    }
}
