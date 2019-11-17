package com.codecool.stockapp.model.Currencies;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.Map;

@Generated("com.robohorse.robopojogenerator")
public class SingleCurrency{

	@JsonProperty("data")
	private Map<Integer, CurrencyDetails> data;

	@JsonProperty("status")
	private Status status;

	public Map<Integer, CurrencyDetails> getData() {
		return data;
	}

	public void setData(Map<Integer, CurrencyDetails> data) {
		this.data = data;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"SingleCurrency{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
