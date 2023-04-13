package com.geekster.fetchDataUsingJava1.fetchDataUsingJava;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetchData {

    public static void main(String[] args) throws Exception {

        URL getUrl = new URL(" https://api.zippopotam.us/us/33162");
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.setRequestMethod("GET");


        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonResponseData = new StringBuilder();
            String readLine;


            while ((readLine = in.readLine()) != null) {
                jsonResponseData.append(readLine);
            }

            in.close();
            JSONObject masterData = new JSONObject(jsonResponseData.toString());

            System.out.println("Post code=> " + masterData.get("post code"));
            System.out.println("Country=> " + masterData.get("country"));
            System.out.println("Country abbreviation=> " + masterData.get("country abbreviation"));

            JSONArray jsonArray = masterData.getJSONArray("places");
            JSONObject firstPlaceObj = jsonArray.getJSONObject(0);

            System.out.println("place name=> " + firstPlaceObj.get("place name"));
            System.out.println("longitude=> " + firstPlaceObj.get("longitude"));
            System.out.println("state abbreviation=> " + firstPlaceObj.get("state abbreviation"));
            System.out.println("state => " + firstPlaceObj.get("state"));
            System.out.println("latitude=> " + firstPlaceObj.get("latitude"));
        } else {
            System.out.println("This is not valid URL- " + responseCode);
        }


    }
}
