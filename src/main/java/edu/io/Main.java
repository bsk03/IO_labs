package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board = new Board(8);
        PlayerToken player = new PlayerToken(board, 3, 1);

        board.placeToken(3,3, new GoldToken());

        Scanner sc = new Scanner(System.in);


        while (true) {
            board.display();
            System.out.print("Move (W,S,A,D):");

            String s = sc.nextLine().trim().toUpperCase();
            if (s.isEmpty()) continue;

            try {
                switch (s) {
                    case "A" -> player.move(PlayerToken.Move.LEFT);
                    case "D" -> player.move(PlayerToken.Move.RIGHT);
                    case "W" -> player.move(PlayerToken.Move.UP);
                    case "S" -> player.move(PlayerToken.Move.DOWN);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Border");
            }

            System.out.println();
        }
    }
}
