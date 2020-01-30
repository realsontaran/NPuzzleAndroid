package com.example.npuzzle;

public class Board {


    private int[][] board;

    private int height = 0;
    private int width = 0;

    private int numberofmoves = 0;
    private int y_empty;
    private int x_empty;

    public Board(int h, int w) {

        board = new int[h][w];
        x_empty = h - 1;
        y_empty = w - 1;
        initFinalBoard(h, w);
    }

    /**
     * This method creates final board
     *
     * @param h height
     * @param w width
     */
    private void initFinalBoard(int h, int w) {
        int k = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                ++k;
                if (k == h * w) {
                    k = -1;
                }
                board[i][j] = k;
            }
        }
    }

    public int getXEmpty() {
        return x_empty;
    }

    public int getYEmpty() {
        return y_empty;
    }

    /**
     * Getter method for height
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter method for height
     *
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Setter method for width
     *
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter method for width
     *
     * @return the width
     */
    public int getWidth() {

        return width;
    }


    /**
     * Returns the number of steps (moves) this board made.
     *
     * @return int
     */
    public int numberOfMoves() {
        return numberofmoves;
    }


    /**
     * Makes a move according to the given char parameter.
     *
     * @param move parameter
     * @return boolean
     */

    public boolean move(char move) {
        ++numberofmoves;
        return false;
    }

    /**
     * Returns true if the board is a solution
     *
     * @return boolean
     */

    public boolean isSolved() {
        int k = 0;
        boolean isIt = true;

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                k++;
                if (k == (getHeight() * getWidth())) {
                    k = -1;
                }
                if (board[i][j] != k) {
                    isIt = false;
                }
            }
        }
        return isIt;
    }
}
