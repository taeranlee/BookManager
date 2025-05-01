package com.self.exception;

public class BookNotFoundException extends UserException {
	public BookNotFoundException() {
		super("책을 찾지 못 했습니다.");
	}
}