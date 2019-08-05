package edu.gatech.seclass.crypto6300;

import java.io.Serializable;

public class Game implements Serializable {
    private int attemptsLeft = 5;
    private int betAmount;
    private boolean hintUsed = false;
    private Cryptogram cryptogram;
    private Player player;


    public int reduceAttempts(){
        attemptsLeft--;
        return attemptsLeft;
    }

    // Getters and Setters
    public Cryptogram getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(Cryptogram cryptogram) {
        this.cryptogram = cryptogram;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmmount) {
        this.betAmount = betAmmount;
    }

    public boolean isHintUsed() {
        return hintUsed;
    }

    public void setHintUsed(boolean hintUsed) {
        this.hintUsed = hintUsed;
    }
}
