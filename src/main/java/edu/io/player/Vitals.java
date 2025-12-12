package edu.io.player;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vitals {
    private int hydration;
    private Runnable onDeathCallback;

    public Vitals() {
        this.hydration = 100;
        this.onDeathCallback = () -> {};
    }

    public int hydration() {
        return hydration;
    }

    public void dehydrate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        
        boolean wasAlive = isAlive();
        hydration = Math.max(0, hydration - amount);
        
        if (wasAlive && !isAlive()) {
            onDeathCallback.run();
        }
    }

    public void hydrate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        hydration = Math.min(100, hydration + amount);
    }

    public boolean isAlive() {
        return hydration > 0;
    }

    public void setOnDeathHandler(@NotNull Runnable callback) {
        onDeathCallback = Objects.requireNonNull(callback, "callback cannot be null");
    }
}

