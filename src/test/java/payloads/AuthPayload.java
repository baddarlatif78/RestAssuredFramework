package payloads;

import java.util.HashMap;
import java.util.Map;

import config.ConfigReader;

public class AuthPayload {

    public static Map<String, String> createAuthPayload() {

        Map<String, String> payload = new HashMap<>();

        payload.put(
                "username",
                ConfigReader.getProperty("username"));

        payload.put(
                "password",
                ConfigReader.getProperty("password"));

        return payload;
    }
}

