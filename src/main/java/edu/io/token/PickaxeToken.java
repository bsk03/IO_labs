package edu.io.token;

public class PickaxeToken extends Token implements Tool, Repairable {
    private final double gainFactor;
    private final int initialDurability;
    private int durability;
    private Token targetToken;

    public PickaxeToken() {
        this(1.5);
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0) {
            throw new IllegalArgumentException("Gain factor must be positive");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("Durability must be positive");
        }
        this.gainFactor = gainFactor;
        this.initialDurability = durability;
        this.durability = durability;
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    public void use() {
        if (durability > 0) {
            durability--;
        }
    }

    @Override
    public boolean isBroken() {
        return durability <= 0;
    }

    @Override
    public void repair() {
        this.durability = initialDurability;
    }

    @Override
    public Tool useWith(Token withToken) {
        this.targetToken = withToken;
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        if (targetToken instanceof GoldToken && !isBroken()) {
            action.run();
            use();
        }
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) {
        if (targetToken instanceof GoldToken && isBroken()) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        if (!(targetToken instanceof GoldToken)) {
            action.run();
        }
        return this;
    }
}

