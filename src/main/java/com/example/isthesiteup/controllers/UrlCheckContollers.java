package com.example.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckContollers {

    private final String IS_SITE_UP = "Site is up !";
    private final String IS_SITE_DOWN = "Site is down !";
    private final String INCOORECT_URL = "The URL is incorrect";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int response = conn.getResponseCode() / 100;

            if (response == 2 || response == 3) {
                returnMessage = IS_SITE_UP;
            } else {
                returnMessage = IS_SITE_DOWN;
            }

        } catch (MalformedURLException e) {
            returnMessage = INCOORECT_URL;
        } catch (IOException e) {
            returnMessage = IS_SITE_DOWN;
        }

        return returnMessage;
    }

}
