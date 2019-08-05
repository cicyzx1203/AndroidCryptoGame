package edu.gatech.seclass.crypto6300;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application implements Serializable {
    private List<Cryptogram> cryptogramList = new ArrayList<>();
    private List<Player> playerList = new ArrayList<>();

    // gets a cryptogram by title
    public Cryptogram getCryptogram(String title){
        for(Cryptogram cryptogram: cryptogramList){
            if (cryptogram.getTitle().equalsIgnoreCase(title)) return cryptogram;
        }
        return null;
    }


    // Checks if title is unique
    public boolean isTitleUnique(String title){
        return  getCryptogram(title)==null;
    }

    // creates a new Game
    public Game createGame(Player p, int betAmount, Cryptogram cryptogram){
        Game game = new Game();
        game.setCryptogram(cryptogram);
        game.setPlayer(p);
        game.setBetAmount(betAmount);
        return game;
    }

    // Gets a random cryptogram from the list of cryptograms that has no been attempted by this player
    public Cryptogram rand(Player p){
        // Check if there are playable cryptograms
        List<Cryptogram> playableCryptogram = new ArrayList<>();
        for (Cryptogram cryptogram: getAllCryptogram()){
            if(!cryptogram.getCreator().equals(p) && !cryptogram.hasAttempted(p) && !cryptogram.isDisabled()){
                playableCryptogram.add(cryptogram);
            }
        }


        if (playableCryptogram.isEmpty()){
            return null;
        }
        else{
            Random rand = new Random();
            return playableCryptogram.get(rand.nextInt(playableCryptogram.size()));
        }
    }

    // Saves cryptogram to list
    public void saveCryptogram(Cryptogram cryptogram){
        cryptogramList.add(cryptogram);
    }

    // Gets a player from the player list. Can also be used for login to get player with matching details
    public boolean login(String username, String email){
        for(Player player: playerList){
            if (player.getUsername().equalsIgnoreCase(username) && player.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }

    // Gets a player by username
    public Player getPlayer(String username){
        for(Player player: playerList){
            if (player.getUsername().equalsIgnoreCase(username)) {
                return player;
            }
        }
        return null;
    }

    // Check if user name is unique
    public boolean isUsernameUnique(String username){
        return getPlayer(username) == null;
    }

    public Player createPlayer(String username,String email){
        Player player = new Player(this);
        player.setEmail(email);
        player.setUsername(username);

        return  player;
    }

    public void savePlayer(Player player){
        playerList.add(player);
    }



    // Getters and setters


    public List<Cryptogram> getAllCryptogram() {
        return cryptogramList;
    }

    public void setCryptogramList(List<Cryptogram> cryptogramList) {
        this.cryptogramList = cryptogramList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
