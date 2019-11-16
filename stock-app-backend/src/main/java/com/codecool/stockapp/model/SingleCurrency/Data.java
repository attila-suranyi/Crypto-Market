package com.codecool.stockapp.model.SingleCurrency;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Data{

	@JsonProperty("1")
	private CurrencyDetails currencyDetails;

	public void setCurrencyDetails(CurrencyDetails currencyDetails){
		this.currencyDetails = currencyDetails;
	}

	public CurrencyDetails getCurrencyDetails(){
		return currencyDetails;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"1 = '" + currencyDetails + '\'' +
			"}";
		}
}
