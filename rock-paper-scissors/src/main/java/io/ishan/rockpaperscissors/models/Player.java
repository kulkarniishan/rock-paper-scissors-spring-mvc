package io.ishan.rockpaperscissors.models;

import io.ishan.rockpaperscissors.utils.Move;

public class Player {
    String name;
    String id;
    int score = 0;
    boolean hasPlayed = false;
    Move move;

    public Player(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public int addScore(int Score) {
        if (!hasPlayed) {
            this.score += Score;
            this.hasPlayed = true;
        }
        return this.score;
    }
}
