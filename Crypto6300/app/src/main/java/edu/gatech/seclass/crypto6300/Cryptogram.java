package edu.gatech.seclass.crypto6300;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Cryptogram implements Serializable {

    private Application context;
    private String plainText;
    private String encodedPhrase;
    private String encodingLetters;
    private String title;
    private String hint;
    private Date creationDate = new Date();
    private Player creator;
    private boolean isDisabled = false;
    private int numberOfCompletedGame = 0;
    private int numberOfWins = 0;
    private List<Player> attemptedBy = new ArrayList<>();


    public Cryptogram(Application context) {
        this.context = context;
    }

    // Used to create a new cryptogram
    public String create(Player creator, String title, String plainText, String encoding){
        this.title = title;
        this.creator = creator;
        this.plainText = plainText;
        this.encodingLetters = encoding;
        this.creator = creator;
        creator.addPoints(5);
        //this.encodedPhrase
        return title;
    }




    // Checks if a player has attempted this this Cryptogram
    public boolean hasAttempted(Player p){
        for(Player player: attemptedBy){
            if (player.getUsername().equalsIgnoreCase(p.getUsername())) return true;
        }
        return false;
    }

    // Player's attempt to solve the game
    public void attempt(Player p){
        if (!hasAttempted(p)){
            attemptedBy.add(p);
        }
    }


    // Getters and setters


    public Application getContext() {
        return context;
    }

    public void setContext(Application context) {
        this.context = context;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getEncodedPhrase() {
        return encodedPhrase;
    }

    public void setEncodedPhrase(String encodedPhrase) {
        this.encodedPhrase = encodedPhrase;
    }

    public String getEncodingLetters() {
        return encodingLetters;
    }

    public void setEncodingLetters(String encodingLetters) {
        this.encodingLetters = encodingLetters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Player getCreator() {
        return creator;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public int getNumberOfCompletedGame() {
        return numberOfCompletedGame;
    }

    public void setNumberOfCompletedGame(int numberOfCompletedGame) {
        this.numberOfCompletedGame = numberOfCompletedGame;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public List<Player> getAttemptedBy() {
        return attemptedBy;
    }

    public void setAttemptedBy(List<Player> attemptedBy) {
        this.attemptedBy = attemptedBy;
    }
}
