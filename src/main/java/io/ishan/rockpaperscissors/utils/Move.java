package io.ishan.rockpaperscissors.utils;

public enum Move {
    ROCK("rock"), PAPER("paper"), SCISSORS("scissors");

    private String type;

    Move(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}