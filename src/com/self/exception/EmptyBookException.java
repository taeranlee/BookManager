package com.self.exception;

public class EmptyBookException extends UserException {
	public EmptyBookException() { 
		super("책이 비어있습니다.");
		}
    public EmptyBookException(String message) {
        super(message);
    }
}
