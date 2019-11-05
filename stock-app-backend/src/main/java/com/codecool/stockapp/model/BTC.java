package com.codecool.stockapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class BTC{

	@JsonProperty("percent_change_1h")
	private int percentChange1h;

	@JsonProperty("last_updated")
	private String lastUpdated;

	@JsonProperty("percent_change_24h")
	private int percentChange24h;

	@JsonProperty("market_cap")
	private int marketCap;

	@JsonProperty("price")
	private int price;

	@JsonProperty("volume_24h")
	private int volume24h;

	@JsonProperty("percent_change_7d")
	private int percentChange7d;

	public void setPercentChange1h(int percentChange1h){
		this.percentChange1h = percentChange1h;
	}

	public int getPercentChange1h(){
		return percentChange1h;
	}

	public void setLastUpdated(String lastUpdated){
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdated(){
		return lastUpdated;
	}

	public void setPercentChange24h(int percentChange24h){
		this.percentChange24h = percentChange24h;
	}

	public int getPercentChange24h(){
		return percentChange24h;
	}

	public void setMarketCap(int marketCap){
		this.marketCap = marketCap;
	}

	public int getMarketCap(){
		return marketCap;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setVolume24h(int volume24h){
		this.volume24h = volume24h;
	}

	public int getVolume24h(){
		return volume24h;
	}

	public void setPercentChange7d(int percentChange7d){
		this.percentChange7d = percentChange7d;
	}

	public int getPercentChange7d(){
		return percentChange7d;
	}

	@Override
 	public String toString(){
		return 
			"BTC{" + 
			"percent_change_1h = '" + percentChange1h + '\'' + 
			",last_updated = '" + lastUpdated + '\'' + 
			",percent_change_24h = '" + percentChange24h + '\'' + 
			",market_cap = '" + marketCap + '\'' + 
			",price = '" + price + '\'' + 
			",volume_24h = '" + volume24h + '\'' + 
			",percent_change_7d = '" + percentChange7d + '\'' + 
			"}";
		}
}