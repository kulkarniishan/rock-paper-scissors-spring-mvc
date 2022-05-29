package io.ishan.rockpaperscissors.models;

public class Response {
    String userId = "";
    String gameId = "";

    Response(String userId, String gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }
}
