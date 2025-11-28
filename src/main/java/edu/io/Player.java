package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Player {
    private PlayerToken token;
    private double gold;

    public Player() {
        this.gold = 0.0;
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public double gold() {
        return gold;
    }

    public void gainGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Gold amount cannot be negative");
        }
        this.gold += amount;
    }

    public void loseGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Gold amount cannot be negative");
        }
        if (this.gold < amount) {
            throw new IllegalArgumentException("Cannot lose more gold than player has");
        }
        this.gold -= amount;
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            gainGold(goldToken.amount());
            System.out.println("GOLD!");
        }
    }
}

