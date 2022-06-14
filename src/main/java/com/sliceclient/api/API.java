package com.sliceclient.api;

import com.sliceclient.Slice;
import com.sliceclient.util.HardwareUtil;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@Getter @Setter
public class API {

    public static String API_URL = "https://api.sliceclient.com/";

    /**
     * Checks if a user is authenticated with the server
     * **/
    public static void sendAuthRequest() {
        try {
            URL url = new URL(API_URL + "checkAuth/" + HardwareUtil.getHardwareID());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.connect();
            JSONObject json = new JSONObject(Objects.requireNonNull(readResponse(connection)));

            boolean success = json.getBoolean("status");
            Slice.INSTANCE.setAuthorized(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String readResponse(HttpURLConnection connection) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception ignored) {}
        return null;
    }
}
