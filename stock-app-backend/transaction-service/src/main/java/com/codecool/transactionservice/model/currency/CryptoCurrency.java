package com.codecool.transactionservice.model.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class CryptoCurrency{

	@JsonProperty("data")
	private List<CurrencyDetails> data;

	@JsonProperty("status")
	private Status status;

	public void setData(List<CurrencyDetails> data){
		this.data = data;
	}

	public List<CurrencyDetails> getData(){
		return data;
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
			"CryptoCurrency{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
