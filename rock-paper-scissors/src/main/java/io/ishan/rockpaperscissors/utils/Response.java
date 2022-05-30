package io.ishan.rockpaperscissors.utils;

import java.util.HashMap;
import java.util.Map;

public class Response {
    String userId = "";
    String gameId = "";

    Response(String userId, String gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public static Map<String, String> createSuccessResponse(String response[][]) {

        Map<String, String> responsehm = new HashMap<String, String>();

        for (int i = 0; i < response.length; i++) {
            responsehm.put(response[i][0], response[i][1]);
        }

        return responsehm;
    }

    public static Map<String, String> HTTP500() {

        Map<String, String> responsehm = new HashMap<String, String>();
        responsehm.put("msg","Internal Server Error!");
        return responsehm;
    }
}
