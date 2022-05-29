package io.ishan.rockpaperscissors.interfaces;

import java.util.Map;

public interface IGame {
    public Map<String, String> createGame(String creatorName, int numberOfPlayers);

    public Map<String, String> joinGame(String name);

    public Map<String, String> endGame(String id);
}
