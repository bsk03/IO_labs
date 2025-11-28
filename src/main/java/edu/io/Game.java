package edu.io;

import edu.io.token.PlayerToken;

public class Game {
    private final Board board;

    public Game() {
        this.board = new Board();
    }

    public Board board() {
        return board;
    }

    public void join(Player player) {
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
    }

    public void start() {
        
    }
}

