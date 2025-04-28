package com.self.service;

import com.self.vo.Book;

public interface BookManager {
	void insertBook(Book book);
	void deleteBook(int isnb);
	void updateBook(Book book);
	Book getBook(int isnb);
	Book[] getAllBook();
	int getNumberOfBooks();
	Book[] searchBookByTitle(String title);
	Book[] searchBookByPrice(double min,double max);
	double getSumPriceOfBooks();
	double getAvgPriceOFBooks();
	
	Book[] sortMagazineByMonth();
	Book[] searchNovelByGenre();
	//특정 인터뷰 star가 나온 magazine들 찾기 
	Book[] getstarOfMagazines(String starName);
	
	
	

}
