package com.mrs.exception;

public class DuplicateException extends Exception{
	public DuplicateException(){
		this("이미 있는 회원이십니다. ");
	}
	public DuplicateException(String message){
		super(message);
	}
}
