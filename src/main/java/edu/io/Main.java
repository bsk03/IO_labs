package edu.io;

import edu.io.player.Player;
import edu.io.token.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();
        game.join(player);
        game.start();

        player.vitals.setOnDeathHandler(() -> {
            System.out.println("Zmarłeś z pragnienia");
        });

        Board board = game.board();
        
        board.placeToken(3, 3, new GoldToken(2.0));
        board.placeToken(5, 5, new GoldToken(1.5));
        board.placeToken(2, 6, new GoldToken(0.5));
        board.placeToken(4, 4, new PyriteToken());
        board.placeToken(6, 2, new PyriteToken());
        

        board.placeToken(2, 2, new PickaxeToken());
        board.placeToken(7, 7, new PickaxeToken(2.0));
        board.placeToken(4, 6, new AnvilToken());
        
        board.placeToken(1, 1, new WaterToken(15));
        board.placeToken(6, 6, new WaterToken(20));
        board.placeToken(3, 7, new WaterToken(10));

        Scanner sc = new Scanner(System.in);
        PlayerToken token = player.token();



        while (true) {
            board.display();
            
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.printf("│ Złoto: %-8.1f oz              │%n", player.gold());
            System.out.printf("│ Nawodnienie: %-3d%% ", player.vitals.hydration());
            
            if (player.vitals.hydration() > 50) {
                System.out.print("[█████████] ✓");
            } else if (player.vitals.hydration() > 25) {
                System.out.print("[████░░░░░] ⚠");
            } else if (player.vitals.hydration() > 0) {
                System.out.print("[██░░░░░░░] ☠");
            } else {
                System.out.print("[░░░░░░░░░] ✗");
            }
            System.out.println(" │");
            System.out.println("└─────────────────────────────────┘");
            
            if (player.vitals.hydration() <= 20 && player.vitals.hydration() > 0) {
                System.out.println("UWAGA: Krytyczny poziom nawodnienia!");
            }
            
            if (!player.vitals.isAlive()) {
                System.out.println("\n Jesteś martwy. Nie możesz się poruszać.");
                System.out.println("Końcowy wynik: " + String.format("%.1f", player.gold()) + " oz złota");
                break;
            }
            
            System.out.print("\nMove (W,S,A,D) lub Q aby wyjść: ");

            String s = sc.nextLine();
            if (s.length() == 0) {
                continue;
            }
            s = s.toUpperCase();
            
            if (s.equals("Q")) {
                System.out.println("\nKońcowy wynik: " + String.format("%.1f", player.gold()) + " oz złota");
                System.out.println("Poziom nawodnienia: " + player.vitals.hydration() + "%");
                System.out.println("\nDziękujemy za grę!");
                break;
            }

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
                    default:
                        System.out.println("Nieznana komenda. Użyj W,S,A,D lub Q.");
                        continue;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Nie można wyjść poza planszę!");
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }
        
        sc.close();
    }
}
