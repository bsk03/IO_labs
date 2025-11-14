package edu.io;

public class Main {
    private static final String GOLD_TOKEN_LABEL = "💰︎";
    private static final String PLAYER_TOKEN_LABEL = "웃";

    public static void main(String[] args) {
        Board board = new Board();

        Token gold = new Token(GOLD_TOKEN_LABEL);
        Token player = new Token(PLAYER_TOKEN_LABEL);

        board.placeToken(1, 2, gold);
        board.placeToken(4, 5, player);

        board.display();
    }
}