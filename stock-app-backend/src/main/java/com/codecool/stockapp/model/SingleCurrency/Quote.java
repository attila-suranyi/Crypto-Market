package com.codecool.stockapp.model.SingleCurrency;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Quote{

	@JsonProperty("USD")
	private USD uSD;

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
			"uSD = '" + uSD + '\'' + 
			"}";
		}
}