package io.ishan.rockpaperscissors.controllers;

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "site is Up";
    private final String SITE_IS_DOWN = "site is Down";
    private final String URL_IS_INCORRECT = "Incorrect URL";

    @GetMapping("/check")
    public String getUrlStatus(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObject = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if (responseCodeCategory == 3 || responseCodeCategory == 2) {
                returnMessage = SITE_IS_UP;
            } else {
                returnMessage = SITE_IS_DOWN;
            }
            return returnMessage;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            return URL_IS_INCORRECT;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return SITE_IS_DOWN;
        }
    }
}
