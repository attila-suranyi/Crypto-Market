package com.codecool.stockapp.model.entity.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Status{

	@JsonProperty("error_message")
	private String errorMessage;

	@JsonProperty("elapsed")
	private int elapsed;

	@JsonProperty("credit_count")
	private int creditCount;

	@JsonProperty("error_code")
	private int errorCode;

	@JsonProperty("timestamp")
	private String timestamp;

	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage(){
		return errorMessage;
	}

	public void setElapsed(int elapsed){
		this.elapsed = elapsed;
	}

	public int getElapsed(){
		return elapsed;
	}

	public void setCreditCount(int creditCount){
		this.creditCount = creditCount;
	}

	public int getCreditCount(){
		return creditCount;
	}

	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}

	public int getErrorCode(){
		return errorCode;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"Status{" + 
			"error_message = '" + errorMessage + '\'' + 
			",elapsed = '" + elapsed + '\'' + 
			",credit_count = '" + creditCount + '\'' + 
			",error_code = '" + errorCode + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}