package com.infygo.user.exception;

public class AlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(String message) {
		super(message);
	}
	
}
