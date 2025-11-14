package edu.io;

public class Board {
    private static final String EMPTY_TOKEN_LABEL = "\u30FB";
    
    public final int size;
    public final Token[][] grid;

    public Board() {
        this(8);
    }

    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }

    public void clean() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Token(EMPTY_TOKEN_LABEL);
            }
        }
    }

    public void placeToken(int col, int row, Token token) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            grid[row][col] = token;
        }
    }

    public Token square(int col, int row) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            return grid[row][col];
        }
        return null;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}