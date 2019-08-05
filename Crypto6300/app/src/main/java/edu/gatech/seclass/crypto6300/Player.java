package edu.gatech.seclass.crypto6300;

import java.io.Serializable;

public class Player implements Serializable {
    private String  username;
    private String email;
    private int points = 20;
    private int cryptogramsAttempted = 0;
    private Application context;
    private Game currentGame;

    public Player (Application context){
        this.context = context;
    }

    public int addPoints(int p) {
        points += p;
        if (points < 0) points = 0;
        return points;
    }

    // GETTERS & SETTERS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAttempts() {
        return cryptogramsAttempted;
    }

    public void setCryptogramsAttempted(int cryptogramsAttempted) {
        this.cryptogramsAttempted = cryptogramsAttempted;
    }

    public int getCryptogramsAttempted() {
        return cryptogramsAttempted;
    }

    public Application getContext() {
        return context;
    }

    public void setContext(Application context) {
        this.context = context;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
