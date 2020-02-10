package com.example.npuzzle;

import java.util.Random;

public class Board {


    private int[][] board;

    private int height;
    private int width;

    private int numberofmoves;
    private int y_empty;
    private int x_empty;
    private char lastMove;

    Board(int h, int w) {

        height = h;
        width = w;
        numberofmoves = 0;

        board = new int[h][w];
        x_empty = h - 1;
        y_empty = w - 1;
        FinalBoard();
    }

    int getValue(int row, int col) {
        return board[row][col];
    }

    private void FinalBoard() {
        int k = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ++k;
                if (k == height * width) {
                    k = -1;
                    setY_empty(i);
                    setX_empty(j);
                }
                board[i][j] = k;
            }
        }
    }


    private int getHeight() {
        return height;
    }

    private void setY_empty(int y_empty) {
        this.y_empty = y_empty;
    }

    private void setX_empty(int x_empty) {
        this.x_empty = x_empty;
    }

    private int getWidth() {

        return width;
    }

    public int numberOfMoves() {
        return numberofmoves;
    }

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

    void shuffle() {
        char[] moves = {'r', 'l', 'u', 'd'};
        char randomMove;
        Random random = new Random();

        for (int i = 0; i < 200; i++) {
            randomMove = moves[random.nextInt(4)];

            if (lastMove == 'r' && randomMove == 'l') {
                --i;
            }
            if (lastMove == 'l' && randomMove == 'r') {
                --i;
            }
            if (lastMove == 'd' && randomMove == 'u') {
                --i;
            }
            if (lastMove == 'u' && randomMove == 'd') {
                --i;
            }

            if (!move(randomMove)) {
                --i;
            }
        }
    }

    private boolean move(char move) {

        switch (move) {
            case 'u':
                if (y_empty != 0) {
                    board[y_empty][x_empty] = board[y_empty - 1][x_empty];
                    board[y_empty - 1][x_empty] = -1;
                    --y_empty;
                    ++numberofmoves;
                    lastMove = move;
                    return true;
                }
                break;
            case 'd':
                if (y_empty != getHeight() - 1) {
                    board[y_empty][x_empty] = board[y_empty + 1][x_empty];
                    board[y_empty + 1][x_empty] = -1;
                    ++y_empty;
                    ++numberofmoves;
                    lastMove = move;
                    return true;
                }
                break;
            case 'r':
                if (x_empty != getWidth() - 1) {
                    board[y_empty][x_empty] = board[y_empty][x_empty + 1];
                    board[y_empty][x_empty + 1] = -1;
                    ++x_empty;
                    ++numberofmoves;
                    lastMove = move;
                    return true;
                }
                break;
            case 'l':
                if (x_empty != 0) {
                    board[y_empty][x_empty] = board[y_empty][x_empty - 1];
                    board[y_empty][x_empty - 1] = -1;
                    --x_empty;
                    ++numberofmoves;
                    lastMove = move;
                    return true;
                }
                break;
        }
        return false;
    }
}
