package com.codecool.currencyservice.model.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Quote{

	@JsonProperty("BTC")
	private BTC bTC;

	@JsonProperty("USD")
	private USD uSD;

	public void setBTC(BTC bTC){
		this.bTC = bTC;
	}

	public BTC getBTC(){
		return bTC;
	}

	public void setUSD(USD uSD){
		this.uSD = uSD;
	}

	public USD getUSD(){
		return uSD;
	}

	@Override
 	public String toString(){
		return 
			"Quote{" + 
			"bTC = '" + bTC + '\'' + 
			",uSD = '" + uSD + '\'' + 
			"}";
		}
}
