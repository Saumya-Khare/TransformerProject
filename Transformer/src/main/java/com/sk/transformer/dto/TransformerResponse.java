package com.sk.transformer.dto;

public class TransformerResponse {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TransformerResponse [message=" + message + "]";
	}
	
	
}
