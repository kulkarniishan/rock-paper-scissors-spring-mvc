package io.ishan.rockpaperscissors.models;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.ishan.rockpaperscissors.interfaces.IGame;
import io.ishan.rockpaperscissors.utils.Generator;
import io.ishan.rockpaperscissors.utils.Move;

public class Game implements IGame {
    public String id = "";
    public String winnerId = "";
    public String creatorId = "";
    public boolean started = false;
    public boolean ended = false;
    public String winnerName;
    public String WinnerId;
    public String WinnerScore;
    public HashMap<String, Player> players;

    public String getId() {
        return this.id;
    }

    public Map<String, String> createGame(String playerName, int numberOfPlayers) {
        String gameId = Generator.generateRandomPassword(10);
        this.id = gameId;

        this.players = new HashMap<String, Player>();
        String playerId = Generator.generateRandomPassword(10);
        this.creatorId = playerId;

        this.players.put(playerId, new Player(playerId, playerName));

        Map<String, String> response = new HashMap<String, String>();
        response.put("playerId", playerId);
        response.put("gameId", this.id);
        return response;
    }

    public Map<String, String> startGame(String id) {
        Map<String, String> response = new HashMap<String, String>();
        System.out.println(this.creatorId);

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
        Map<String, String> response = new HashMap<String, String>();

        Player player = players.get(playerId);
        if (!player.hasPlayed && !this.ended) {
            player.hasPlayed = true;
            player.move = Move.valueOf(move.toLowerCase());

            response.put("msg", "successfully played " + move);
            response.put("status", "success");

        } else {
            response.put("msg", "Either the game is ended or you have already used your move");
            response.put("status", "error");
        }

        return response;
    }

    public Map<String, String> endGame(String id) {
        Map<String, String> response = new HashMap<String, String>();

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
            response.put("msg", "Ended the game!");
            response.put("status", "success");
        } else {
            response.put("msg", "Only the creator can end the game!");
            response.put("status", "error");
        }
        return response;
    }

    public Map<String, String> generateresults() {
        Map<String, String> response = new HashMap<String, String>();

        if (this.started == false) {
            response.put("msg", "The game has not yet started!");
            response.put("status", "error");
        } else {
            for (Map.Entry<String, Player> other : players.entrySet()) {
            }
        }
        return response;
    }
}
