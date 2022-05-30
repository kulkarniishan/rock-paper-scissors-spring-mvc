package io.ishan.rockpaperscissors.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.ishan.rockpaperscissors.models.Game;
import io.ishan.rockpaperscissors.utils.Response;

@RestController
@RequestMapping("/api")
public class GameController {

    public HashMap<String, Game> games = new HashMap<String, Game>();

    @GetMapping("/create")
    public Map<String, String> createGame(@RequestParam String name, @RequestParam int numberOfPlayers) {
        try {
            Game game = new Game();
            Map<String, String> response = game.createGame(name, numberOfPlayers);
            this.games.put(game.getId(), game);

            System.out.println(game.creatorId);

            return response;
        } catch (Exception e) {
            return Response.HTTP500();
        }
    }

    @GetMapping("/start")
    public Map<String, String> startGame(@RequestParam String gameId, @RequestParam String playerId) {
        try {
            Game game = this.games.get(gameId);
            Map<String, String> response = game.startGame(playerId);
            return response;
        } catch (Exception e) {
            return Response.HTTP500();
        }
    }

    @GetMapping("/play")
    public Map<String, String> playGame(@RequestParam String playerId, @RequestParam String gameId,
            @RequestParam String move) {
        try {
            Game game = this.games.get(gameId);
            Map<String, String> response = game.playGame(move, playerId);
            return response;
        } catch (Exception e) {
            return Response.HTTP500();
        }
    }

    @GetMapping("/end")
    public Map<String, String> endGame(@RequestParam String playerId, @RequestParam String gameId) {
        try {
            Game game = this.games.get(gameId);
            Map<String, String> response = game.endGame(playerId);
            return response;
        } catch (Exception e) {
            return Response.HTTP500();
        }
    }

    @GetMapping("/result")
    public Map<String, String> endGame(@RequestParam String gameId) {
        try {
            Game game = this.games.get(gameId);
            Map<String, String> response = game.generateresults();
            return response;
        } catch (Exception e) {
            return Response.HTTP500();
        }
    }

}
