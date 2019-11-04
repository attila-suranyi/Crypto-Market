package com.codecool.stockapp.model; /**
 * This example uses the Apache HTTPComponents library. 
 */

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CryptoAPIService {

    @Autowired
    private RemoteURLReader remoteURLReader;

    public String getCryptoCurrencies(String symbol) throws IOException, URISyntaxException {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        //parameters.add(new BasicNameValuePair("symbol",symbol));
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        return remoteURLReader.makeAPICall(uri, parameters);
    }

}