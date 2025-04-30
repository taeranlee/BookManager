package com.self.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.self.vo.Book;

public interface BookManager {
	void insertBook(Book book);
	void deleteBook(int isbn);
	void updateBook(Book book);
	Book getBook(int isbn);
	HashMap<Integer, Book> getAllBook();
	int getNumberOfBooks();
	HashMap<Integer, Book> searchBookByTitle(String title);
	HashMap<Integer, Book> searchBookByPrice(double min,double max);
	double getSumPriceOfBooks();
	double getAvgPriceOFBooks();
	
	HashMap<Integer, Book> searchNovelByGenre(String genre);
	//특정 인터뷰 star가 나온 magazine들 찾기 
	HashMap<Integer, Book> getStarOfMagazines(String starName);
	
}
