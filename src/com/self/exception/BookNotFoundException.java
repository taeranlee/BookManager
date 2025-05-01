package com.self.exception;

public class BookNotFoundException extends Exception {
	public BookNotFoundException() {
		super("책을 찾지 못 했습니다.");
	}
}