package edu.io.player;

import edu.io.token.*;

public class Player {
    private PlayerToken token;
    public final Gold gold;
    private final Shed shed;

    public Player() {
        this.gold = new Gold();
        this.shed = new Shed();
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public double gold() {
        return gold.amount();
    }

    public void gainGold(double amount) {
        gold.gain(amount);
    }

    public void loseGold(double amount) {
        gold.lose(amount);
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            Tool tool = shed.getTool();
            double amount = goldToken.amount();
            
            if (tool instanceof PickaxeToken pickaxe) {
                pickaxe.useWith(goldToken)
                    .ifWorking(() -> {
                        gainGold(amount * pickaxe.gainFactor());
                    })
                    .ifBroken(() -> {
                        gainGold(amount);
                        shed.dropTool();
                    })
                    .ifIdle(() -> {
                        gainGold(amount);
                    });
            } else {
                gainGold(amount);
            }
            System.out.println("GOLD!");
        } else if (token instanceof PickaxeToken pickaxeToken) {
            shed.add(pickaxeToken);
        } else if (token instanceof AnvilToken anvilToken) {
            if (shed.getTool() instanceof Repairable tool) {
                tool.repair();
            }
        }
    }
}

