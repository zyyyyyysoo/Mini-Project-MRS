package com.mrs.exception;

public class InvalidTransactionException extends Exception{
	public InvalidTransactionException(){
		this("시간이 지난 예매는 취소할 수 없습니다.");
	}
	public InvalidTransactionException(String message){
		super(message);
	}
}
