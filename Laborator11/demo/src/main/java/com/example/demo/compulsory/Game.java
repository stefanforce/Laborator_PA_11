package com.example.demo.compulsory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> playerList;

    public Game(){
        playerList=new ArrayList<>();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }


    public Player findById(int id){
        return playerList.stream()
                .filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}