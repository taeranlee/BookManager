package com.self.exception;

public class GenreNotFoundException extends UserException {
	public GenreNotFoundException() {
		super("Gerne를 찾지 못 했습니다.");
	}
    public GenreNotFoundException(String message) {
        super(message);
    }
}