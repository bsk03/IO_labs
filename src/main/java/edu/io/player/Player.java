package edu.io.player;

import edu.io.token.*;

public class Player {
    private PlayerToken token;
    public final Gold gold;
    public final Vitals vitals;
    private final Shed shed;

    public Player() {
        this.gold = new Gold();
        this.vitals = new Vitals();
        this.shed = new Shed();
    }

    public void assignToken(PlayerToken token) {
        if (token == null) {
            throw new NullPointerException("Token cannot be null");
        }
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
        if (token == null) {
            throw new NullPointerException("Token cannot be null");
        }
        
        if (!vitals.isAlive()) {
            throw new IllegalStateException("player is dead");
        }
        
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
            vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
            System.out.println("GOLD!");
        } else if (token instanceof PickaxeToken pickaxeToken) {
            shed.add(pickaxeToken);
        } else if (token instanceof AnvilToken anvilToken) {
            if (shed.getTool() instanceof Repairable tool) {
                tool.repair();
            }
            vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
        } else if (token instanceof WaterToken waterToken) {
            vitals.hydrate(waterToken.amount());
        } else {
            vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
        }
    }
}

