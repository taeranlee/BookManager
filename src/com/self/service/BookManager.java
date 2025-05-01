package com.self.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.self.exception.*;
import com.self.vo.Book;

public interface BookManager {
	void insertBook(Book book) throws DuplicateBookException;
	void deleteBook(int isbn) throws  BookNotFoundException;
	void updateBook(Book book) throws BookNotFoundException;
	Book getBook(int isbn) throws  BookNotFoundException;
	HashMap<Integer, Book> getAllBook();
	ArrayList<Book> getAllBookForList();
	int getNumberOfBooks();
	HashMap<Integer, Book> searchBookByTitle(String title) throws BookNotFoundException;
	HashMap<Integer, Book> searchBookByPrice(double min,double max) throws  BookNotFoundException;
	double getSumPriceOfBooks() ;
	double getAvgPriceOFBooks();
	
	HashMap<Integer, Book> searchNovelByGenre(String genre);
	//특정 인터뷰 star가 나온 magazine들 찾기 
	HashMap<Integer, Book> getStarOfMagazines(String starName) throws BookNotFoundException;
	
}
