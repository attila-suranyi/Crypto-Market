package com.codecool.stockapp.model; /**
 * This example uses the Apache HTTPComponents library. 
 */

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CryptoAPIService {

    @Autowired
    private RemoteURLReader remoteURLReader;

    public String getPrice(String symbol) throws IOException, URISyntaxException, JSONException {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("start","1"));
        parameters.add(new BasicNameValuePair("limit","1"));
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        String result = remoteURLReader.makeAPICall(uri, parameters);
        System.out.println(result);
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        return jsonArray.getJSONObject(0).getJSONObject("quote").getJSONObject("USD").getString("price");
    }
    public String getTopCurrencies(int top) throws IOException, URISyntaxException, JSONException {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("start","1"));
        parameters.add(new BasicNameValuePair("limit",String.valueOf(top)));
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        String result = remoteURLReader.makeAPICall(uri, parameters);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.toString();

    }

}