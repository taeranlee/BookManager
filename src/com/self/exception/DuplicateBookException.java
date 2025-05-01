package com.self.exception;

public class DuplicateBookException extends UserException {
	
	public DuplicateBookException() {
		super("책이 중복됩니다.");
	}
    public DuplicateBookException(String message) {
        super(message);
    }
}
