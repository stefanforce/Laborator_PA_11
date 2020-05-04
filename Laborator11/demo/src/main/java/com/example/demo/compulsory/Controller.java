package com.example.demo.compulsory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class Controller {

    private final Game game=new Game();
    private static int id=0;
    //Request-ul pentru Get
    @GetMapping("/players")
    public List<Player> getPlayers() {
        return game.getPlayerList();
    }

    //Request-ul pentru Post
    @PostMapping
    public int addPlayer(@RequestParam String name){
        id=id+1;
        Player player=new Player(id, name);
        game.getPlayerList().add(player);
        return player.getId();
    }


    //Request-ul pentru Put
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(
            @PathVariable int id, @RequestParam String name) {
        Player player=game.findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player not found", HttpStatus.NOT_FOUND); //or GONE
        }
        player.setName(name);
        return new ResponseEntity<>( "Player updated successfully", HttpStatus.OK);
    }

    //Request-ul pentru Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        Player player = game.findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player not found", HttpStatus.GONE);
        }
        game.getPlayerList().remove(player);
        return new ResponseEntity<>("Player removed", HttpStatus.OK);
    }
    }

