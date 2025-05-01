package com.self.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.self.exception.*;
import com.self.vo.Book;

public interface BookManager {
	void insertBook(Book book) throws DuplicateBookException;
	void deleteBook(int isbn) throws EmptyBookException, BookNotFoundException;
	void updateBook(Book book) throws EmptyBookException, BookNotFoundException;
	Book getBook(int isbn) throws EmptyBookException, BookNotFoundException;
	HashMap<Integer, Book> getAllBook() throws EmptyBookException;
	int getNumberOfBooks();
	HashMap<Integer, Book> searchBookByTitle(String title) throws EmptyBookException, BookNotFoundException;
	HashMap<Integer, Book> searchBookByPrice(double min,double max) throws EmptyBookException, BookNotFoundException;
	double getSumPriceOfBooks() throws EmptyBookException;
	double getAvgPriceOFBooks() throws EmptyBookException;
	
	HashMap<Integer, Book> searchNovelByGenre(String genre) throws EmptyBookException, GenreNotFoundException;
	//특정 인터뷰 star가 나온 magazine들 찾기 
	HashMap<Integer, Book> getStarOfMagazines(String starName) throws EmptyBookException, BookNotFoundException;
	
}
