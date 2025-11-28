package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();
        game.join(player);
        game.start();

        Board board = game.board();
        board.placeToken(3, 3, new GoldToken(2.0));
        board.placeToken(5, 5, new GoldToken(1.5));
        board.placeToken(2, 6, new GoldToken(0.5));
        board.placeToken(4, 4, new PyriteToken());
        board.placeToken(6, 2, new PyriteToken());

        Scanner sc = new Scanner(System.in);
        PlayerToken token = player.token();

        while (true) {
            board.display();
            System.out.println("Gold: " + player.gold() + " oz");
            System.out.print("Move (W,S,A,D): ");

            String s = sc.nextLine();
            if (s.length() == 0) {
                continue;
            }
            s = s.toUpperCase();

            try {
                switch (s) {
                    case "A":
                        token.move(PlayerToken.Move.LEFT);
                        break;
                    case "D":
                        token.move(PlayerToken.Move.RIGHT);
                        break;
                    case "W":
                        token.move(PlayerToken.Move.UP);
                        break;
                    case "S":
                        token.move(PlayerToken.Move.DOWN);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot move outside the board!");
            }

            System.out.println();
        }
    }
}
