package com.self.service;

import java.util.List;

import com.self.vo.Book;

public interface BookManager {
	void insertBook(Book book);
	void deleteBook(int isnb);
	void updateBook(Book book);
	Book getBook(int isnb);
	List<Book> getAllBook();
	int getNumberOfBooks();
	List<Book> searchBookByTitle(String title);
	List<Book> searchBookByPrice(double min,double max);
	double getSumPriceOfBooks();
	double getAvgPriceOFBooks();
	
	List<Book> searchNovelByGenre(String genre);
	//특정 인터뷰 star가 나온 magazine들 찾기 
	List<Book> getStarOfMagazines(String starName);
	
}
