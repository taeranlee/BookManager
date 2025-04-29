package com.self.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import com.self.service.BookManager;
import com.self.vo.Book;
import com.self.vo.Magazine;
import com.self.vo.Novel;

public class BookManagerImpl implements BookManager {
	private List<Book> books;
	
	private static BookManagerImpl service = new BookManagerImpl();
	
	private BookManagerImpl() {
		this.books = new ArrayList<Book>();
	}
	
	public static BookManagerImpl getInstance() {
		return service;
	}

	@Override
	public void insertBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(int isnb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book getBook(int isnb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfBooks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Book> searchBookByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> searchBookByPrice(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getSumPriceOfBooks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAvgPriceOFBooks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Book> sortMagazineByMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> searchNovelByGenre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getStarOfMagazines(String starName) {
		// TODO Auto-generated method stub
		return null;
	}

	
}//class