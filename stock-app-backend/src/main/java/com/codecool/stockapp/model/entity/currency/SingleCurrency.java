package com.codecool.stockapp.model.entity.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.Map;

@Generated("com.robohorse.robopojogenerator")
public class SingleCurrency{

	@JsonProperty("data")
	private Map<Long, CurrencyDetails> data;

	@JsonProperty("status")
	private Status status;

	public Map<Long, CurrencyDetails> getData() {
		return data;
	}

	public void setData(Map<Long, CurrencyDetails> data) {
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
