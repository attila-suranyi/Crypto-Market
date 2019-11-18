package com.codecool.stockapp.model.entity.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class USD{

	@JsonProperty("percent_change_1h")
	private double percentChange1h;

	@JsonProperty("last_updated")
	private String lastUpdated;

	@JsonProperty("percent_change_24h")
	private double percentChange24h;

	@JsonProperty("market_cap")
	private long marketCap;

	@JsonProperty("price")
	private double price;

	@JsonProperty("volume_24h")
	private long volume24h;

	@JsonProperty("percent_change_7d")
	private double percentChange7d;

	public void setPercentChange1h(double percentChange1h){
		this.percentChange1h = percentChange1h;
	}

	public double getPercentChange1h(){
		return percentChange1h;
	}

	public void setLastUpdated(String lastUpdated){
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdated(){
		return lastUpdated;
	}

	public void setPercentChange24h(double percentChange24h){
		this.percentChange24h = percentChange24h;
	}

	public double getPercentChange24h(){
		return percentChange24h;
	}

	public void setMarketCap(long marketCap){
		this.marketCap = marketCap;
	}

	public long getMarketCap(){
		return marketCap;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setVolume24h(long volume24h){
		this.volume24h = volume24h;
	}

	public long getVolume24h(){
		return volume24h;
	}

	public void setPercentChange7d(double percentChange7d){
		this.percentChange7d = percentChange7d;
	}

	public double getPercentChange7d(){
		return percentChange7d;
	}

	@Override
 	public String toString(){
		return 
			"USD{" + 
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