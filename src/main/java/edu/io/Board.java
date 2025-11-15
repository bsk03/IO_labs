package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {
    private final int size;
    private final Token[][] grid;



    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }

    public Board() {
        this(8);
    }

    public record Coords(int col, int row) {}

    public int getSize() {
        return size;
    }

    public void clean() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new EmptyToken();
            }
        }
    }

    public void placeToken(int col, int row, Token token) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            grid[row][col] = token;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Invalid coordinates: col=%d, row=%d. Board size is %d.", col, row, size)
            );
        }
    }

    public Token peekToken(int col, int row) {
        return grid[row][col];
    }


    public Token square(int col, int row) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            return grid[row][col];
        }
        throw new IndexOutOfBoundsException(
                String.format("Coordinates out of bounds: col=%d, row=%d, size=%d", col, row, size)
        );
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