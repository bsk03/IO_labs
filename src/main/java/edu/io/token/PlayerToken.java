package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token {

    public enum Move {
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    private int col;
    private int row;
    private final Board board;

    public PlayerToken(Board board, int col, int row) {
        super(Label.PLAYER_TOKEN_LABEL);

        if (col < 0 || col >= board.getSize() || row < 0 || row >= board.getSize()) {
            throw new IllegalArgumentException("Player outside board");
        }

        this.col = col;
        this.row = row;
        this.board = board;

        board.placeToken(col, row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }

    public void move(Move dir) {
        int newCol = col;
        int newRow = row;

        switch (dir) {
            case LEFT -> newCol--;
            case RIGHT -> newCol++;
            case UP -> newRow--;
            case DOWN -> newRow++;
            case NONE -> { return; }
        }

        if (newCol < 0 || newCol >= board.getSize() || newRow < 0 || newRow >= board.getSize()) {
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        board.placeToken(col, row, new EmptyToken());
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this);
    }
}
