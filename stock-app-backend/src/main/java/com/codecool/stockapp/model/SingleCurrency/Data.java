package com.codecool.stockapp.model.SingleCurrency;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Data{

	@JsonProperty("1")
	private JsonMember1 jsonMember1;

	public void setJsonMember1(JsonMember1 jsonMember1){
		this.jsonMember1 = jsonMember1;
	}

	public JsonMember1 getJsonMember1(){
		return jsonMember1;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"1 = '" + jsonMember1 + '\'' + 
			"}";
		}
}