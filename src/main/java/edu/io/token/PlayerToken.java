package edu.io.token;

import edu.io.Board;
import edu.io.player.Player;

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
    private final Player player;

    public PlayerToken(Player player, Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.player = player;
        this.board = board;
        
        Board.Coords coords = board.getAvailableSquare();
        this.col = coords.col();
        this.row = coords.row();
        
        board.placeToken(col, row, this);
    }

    public PlayerToken(Player player, Board board, int col, int row) {
        super(Label.PLAYER_TOKEN_LABEL);

        if (col < 0 || col >= board.size() || row < 0 || row >= board.size()) {
            throw new IllegalArgumentException("Player outside board");
        }

        this.player = player;
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

        if (newCol < 0 || newCol >= board.size() || newRow < 0 || newRow >= board.size()) {
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        var token = board.peekToken(newCol, newRow);
        player.interactWithToken(token);

        board.placeToken(col, row, new EmptyToken());

        col = newCol;
        row = newRow;

        board.placeToken(col, row, this);
    }
}
