package com.mrs.exception;

public class RecordNotFoundException extends Exception{
	public RecordNotFoundException(){
		this("Not exist data");
	}
	public RecordNotFoundException(String message){
		super(message);
	}
}
