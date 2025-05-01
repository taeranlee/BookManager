package com.self.service.Impl;
import com.self.exception.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import com.self.service.BookManager;
import com.self.vo.Book;
import com.self.vo.Magazine;
import com.self.vo.Novel;

public class BookManagerImpl implements BookManager {
	private HashMap<Integer, Book> books;
	
	private static BookManagerImpl service = new BookManagerImpl();
	
	private BookManagerImpl() {
		this.books = new HashMap<Integer, Book>();
	}
	
	public static BookManagerImpl getInstance() {
		return service;
	}

	@Override
	public void insertBook(Book book) throws DuplicateBookException {
		if (books.containsKey(book.getIsbn()))
			throw new DuplicateBookException();
		else
			books.put(book.getIsbn(), book);
	}

	@Override
	public void deleteBook(int isbn) throws EmptyBookException, BookNotFoundException {
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		if (!books.containsKey(isbn))
			throw new BookNotFoundException();
		else
			books.remove(isbn);
	}

	@Override
	public void updateBook(Book book) throws EmptyBookException, BookNotFoundException{
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		if (!books.containsKey(book.getIsbn()))
			throw new BookNotFoundException();
		else
			books.replace(book.getIsbn(), book);
		
	}

	@Override
	public Book getBook(int isbn) throws EmptyBookException, BookNotFoundException {//isnb로 책찾기 
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		if (books.containsKey(isbn))
			return books.get(isbn);
		else {
			throw new BookNotFoundException();
		}
	}


	@Override
	public HashMap<Integer, Book> getAllBook() throws EmptyBookException
	{//모든 책 정보 반환하기 
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		return books;
	}

	@Override
	public int getNumberOfBooks() {//등록된 책 숫자 
		 return books.size();
	}
	

	@Override
	public HashMap<Integer, Book> searchBookByTitle(String title) throws EmptyBookException, BookNotFoundException
	{//제목으로 책찾기 
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		HashMap<Integer, Book> findTitle = new HashMap<Integer,Book>();
		for (Map.Entry<Integer, Book> b : books.entrySet()) {
			if (b.getValue().getTitle().equals(title)) {
				findTitle.put(b.getKey(), b.getValue());
			}	
		}
		if (findTitle.isEmpty()) {
			throw new BookNotFoundException();
		}
		return findTitle;
	}
	
	public HashMap<Integer, Book> searchBookByPrice(double min, double max) throws EmptyBookException, BookNotFoundException
	{
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		HashMap<Integer, Book> findTitle = new HashMap<Integer, Book>();
		for(Book b: books.values()) {
			if(b.getPrice() >= min && b.getPrice() <= max) {
				findTitle.put(b.getIsbn() ,b);
			}
		}
		if(findTitle.isEmpty()) {
			throw new BookNotFoundException();
		}
		return findTitle;
	}



	@Override
	public double getSumPriceOfBooks() throws EmptyBookException 
	{//모든 책의 가격 합치기~
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		double sum = 0;
		for(Book b :books.values()) {
			sum += b.getPrice();
		}
		return sum;
	}

	@Override
	public double getAvgPriceOFBooks() throws EmptyBookException  {//모든 책 가격평균 구하기 
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		return getSumPriceOfBooks()/books.size();
	}


	@Override
	public HashMap<Integer, Book> searchNovelByGenre(String genre) throws EmptyBookException,GenreNotFoundException
	{
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		int i=0;
		HashMap<Integer, Book> findBook = new HashMap<Integer, Book>();
		for (Map.Entry<Integer, Book> b : books.entrySet()) {
			if (b.getValue() instanceof Novel) {
				 Novel n = (Novel) b.getValue();
				if (containString(n.getGenre(), genre))
					findBook.put(i++, b.getValue());
			}
		}
		if (findBook.isEmpty()) {
			throw new GenreNotFoundException();
		}
			
		return findBook;
	}

	@Override
	public HashMap<Integer, Book> getStarOfMagazines(String starName) throws EmptyBookException, BookNotFoundException {
		if (books.isEmpty()) {
			throw new EmptyBookException();
		}
		HashMap<Integer, Book> findBook = new HashMap<Integer, Book>();
	    for (Map.Entry<Integer, Book> b : books.entrySet()) {
	        if (b.getValue() instanceof Magazine) {
	            Magazine m = (Magazine) b.getValue();
	            if (m.getCoverStar().equals(starName) 
	                    || containString(m.getInterviewStar(), starName)) {
	                findBook.put(b.getKey(), b.getValue());
	            }
	        }
	    }

	    if (findBook.isEmpty()) {
	        throw new BookNotFoundException();
	    }
	    
	    return findBook;
	}
	
	public boolean containString(HashMap<Integer, String> strings, String target) {
	    if (strings == null) {
	        return false;
	    }
	    for (String s : strings.values()) {
	        if (target.equals(s)) {
	            return true;
	        }
	    }
	    return false;
	}

	
}//class