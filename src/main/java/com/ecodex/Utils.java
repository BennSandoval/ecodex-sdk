package com.ecodex;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by bennsandoval on 8/19/18.
 */
public class Utils {

    public static String getAccesToken(String server, String rfc) throws IOException {
        URL obj = new URL(server + "/token?version=2");
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) obj.openConnection();
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setDoOutput(true);

        DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
        dataOutputStream.writeBytes("rfc=" + rfc + "&grant_type=authorization_token");
        dataOutputStream.flush();
        dataOutputStream.close();

        int responseCode = httpsURLConnection.getResponseCode();
        if(responseCode == 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            String accessToken = jsonObject.getString("access_token");

            return accessToken;
        }

        throw new NullPointerException("Invalid response trying to get access_token");
    }

    public static String getClaveAlta(String server, String token, String accessToken) throws IOException {
        URL obj = new URL(server + "/api/certificados/clave");

        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) obj.openConnection();
        httpsURLConnection.setRequestMethod("GET");
        httpsURLConnection.setRequestProperty("X-Auth-Token", token);
        httpsURLConnection.setRequestProperty("Authorization", "bearer " + accessToken);

        int responseCode = httpsURLConnection.getResponseCode();
        if(responseCode == 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            String clave = jsonObject.getString("clave");
            return clave;
        }
        throw new NullPointerException("Invalid response trying to get clave");
    }

}
