package com.self.service.Impl;
import com.self.exception.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
	public void deleteBook(int isbn) throws BookNotFoundException {
		if (books.isEmpty()) {
			return;
		}
		if (!books.containsKey(isbn))
			throw new BookNotFoundException();
		else
			books.remove(isbn);
	}

	@Override
	public void updateBook(Book book) throws BookNotFoundException{
		if (books.isEmpty()) {
			return;
		}
		if (!books.containsKey(book.getIsbn()))
			throw new BookNotFoundException();
		else
			books.replace(book.getIsbn(), book);
		
	}

	@Override
	public Book getBook(int isbn) throws BookNotFoundException {//isnb로 책찾기 
		if (books.containsKey(isbn))
			return books.get(isbn);
		else {
			throw new BookNotFoundException();
		}
	}


	public ArrayList<Book> getAllBookForList()
	{

		ArrayList<Book> temp = new ArrayList<Book>();
		
		// values 쓰는것보다 이렇게 하는게 정확하다.
		Set<Integer> set = books.keySet();
		for(Integer no:set) {
			temp.add(books.get(no));
		}
		
		
		return temp;
	}
	@Override
	public HashMap<Integer, Book> getAllBook()
	{//모든 책 정보 반환하기 
		
		return books;
	}

	@Override
	public int getNumberOfBooks() {//등록된 책 숫자 
		 return books.size();
	}
	

	@Override
	public HashMap<Integer, Book> searchBookByTitle(String title) throws BookNotFoundException
	{//제목으로 책찾기 
		
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
	
	public HashMap<Integer, Book> searchBookByPrice(double min, double max) throws BookNotFoundException
	{
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
	public double getSumPriceOfBooks() 
	{//모든 책의 가격 합치기~
		double sum = 0;
		for(Book b :books.values()) {
			sum += b.getPrice();
		}
		return sum;
	}

	@Override
	public double getAvgPriceOFBooks() {//모든 책 가격평균 구하기 
		
		return getSumPriceOfBooks()/books.size();
	}


	@Override
	public HashMap<Integer, Book> searchNovelByGenre(String genre)
	{
		
		int i=0;
		HashMap<Integer, Book> findBook = new HashMap<Integer, Book>();
		for (Map.Entry<Integer, Book> b : books.entrySet()) {
			if (b.getValue() instanceof Novel) {
				 Novel n = (Novel) b.getValue();
				if (containString(n.getGenre(), genre))
					findBook.put(i++, b.getValue());
			}
		}
			
		return findBook;
	}

	@Override
	public HashMap<Integer, Book> getStarOfMagazines(String starName) throws BookNotFoundException {
		
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