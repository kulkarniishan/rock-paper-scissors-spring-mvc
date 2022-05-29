package io.ishan.rockpaperscissors.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.ishan.rockpaperscissors.interfaces.IGame;
import io.ishan.rockpaperscissors.utils.Generator;
import io.ishan.rockpaperscissors.utils.Move;

public class Game implements IGame {
    String id = "";
    String winnerId = "";
    String creatorId = "";
    boolean started = false;
    boolean ended = false;
    String winnerName;
    String WinnerId;
    String WinnerScore;
    HashMap<String, Player> players;

    public String getId() {
        return this.id;
    }

    public Map<String, String> createGame(String creatorName, int numberOfPlayers) {
        String gameId = Generator.generateRandomPassword(10);
        this.id = gameId;

        this.players = new HashMap<String, Player>();
        String userId = Generator.generateRandomPassword(10);
        this.players.put(userId, new Player(userId, creatorName));

        Map<String, String> response = new HashMap<String, String>();
        response.put("userId", userId);
        response.put("gameId", this.id);
        return response;
    }

    public Map<String, String> startGame(String id) {
        Map<String, String> response = new HashMap<String, String>();

        if (id.equals(this.creatorId)) {
            this.started = true;
            response.put("status", "success");
        } else {
            response.put("status", "failure");
        }
        return response;
    }

    public Map<String, String> joinGame(String name) {
        String userId = Generator.generateRandomPassword(10);
        this.players.put(userId, new Player(userId, name));

        Map<String, String> response = new HashMap<String, String>();
        response.put("userId", userId);
        response.put("msg", "success");
        return response;
    }

    public Map<String, String> playGame(String move, String playerId) {
        Player player = players.get(playerId);
        player.hasPlayed = true;
        player.move = Move.valueOf(move.toLowerCase());

        Map<String, String> response = new HashMap<String, String>();
        response.put("msg", "success");
        response.put("msg", "success");
        return response;
    }

    public Map<String, String> endGame(String id) {
        if (id.equals(this.creatorId)) {
            this.ended = true;
            for (Map.Entry<String, Player> me : players.entrySet()) {
                int score = 0;
                for (Map.Entry<String, Player> other : players.entrySet()) {
                    if (!(me.getKey()).equals(other.getKey())) {
                        if ((me.getValue().move == Move.PAPER && other.getValue().move == Move.ROCK)
                                || (me.getValue().move == Move.SCISSORS && other.getValue().move == Move.PAPER)
                                || (me.getValue().move == Move.ROCK && other.getValue().move == Move.SCISSORS)) {
                            score++;
                        }
                    }
                }
                me.getValue().addScore(score);
            }
        }
        Map<String, String> response = new HashMap<String, String>();
        response.put("msg", "success");
        response.put("msg", "success");
        return response;
    }

    // public Map<String, String> generateresults() {
    // for (Map.Entry<String, Player> other : players.entrySet()) {
    // }
    // }
}
