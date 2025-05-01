package com.self.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
	public void insertBook(Book book) {
		if (books.containsKey(book.getIsbn()))
			System.out.println("이미 등록된 책 입니다.");
		else
			books.put(book.getIsbn(), book);
	}

	@Override
	public void deleteBook(int isbn) {
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
		}
		if (!books.containsKey(isbn))
			System.out.println("등록되지 않은 책 번호입니다.");
		else
			books.remove(isbn);
	}

	@Override
	public void updateBook(Book book) {
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return ;
		}
		if (!books.containsKey(book.getIsbn()))
			System.out.println("등록되지 않은 책 번호입니다.");
		else
			books.replace(book.getIsbn(), book);
		
	}

	@Override
	public Book getBook(int isbn) {//isnb로 책찾기 
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return null;
		}
		if (books.containsKey(isbn))
			return books.get(isbn);
		else {
			System.out.println("등록되지 않은 번호의 책입니다.");
			return null;
		}
	}


	@Override
	public HashMap<Integer, Book> getAllBook() {//모든 책 정보 반환하기 
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return null;
		}
		return books;
	}

	@Override
	public int getNumberOfBooks() {//등록된 책 숫자 
		 return books.size();
	}
	

	@Override
	public HashMap<Integer, Book> searchBookByTitle(String title) {//제목으로 책찾기 
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return null;
		}
		HashMap<Integer, Book> findTitle = new HashMap<Integer,Book>();
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		for (Book b : books.values()) 
			temp.put(b.getTitle(), b.getIsbn());
		if (!temp.containsKey(title)) {
			System.out.println("찾으시는 제목의 책이 없습니다.");
			return null;
		}
		else {
			for(Book b : books.values()) {
				if (b.getIsbn() == temp.get(title)) {
					findTitle.put(b.getIsbn(), b);
				}
			}
		}
		return findTitle;
	}
	
	public HashMap<Integer, Book> searchBookByPrice(double min, double max)
	{
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return null;
		}
		HashMap<Integer, Book> findTitle = new HashMap<Integer, Book>();
		for(Book b: books.values()) {
			if(b.getPrice() >= min && b.getPrice() <= max) {
				findTitle.put(b.getIsbn() ,b);
			}
		}
		return findTitle;
	}



	@Override
	public double getSumPriceOfBooks() {//모든 책의 가격 합치기~
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return 0.0;
		}
		double sum = 0;
		for(Book b :books.values()) {
			sum += b.getPrice();
		}
		return sum;
	}

	@Override
	public double getAvgPriceOFBooks() {//모든 책 가격평균 구하기 
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return 0.0;
		}
		return getSumPriceOfBooks()/books.size();
	}


	@Override
	public HashMap<Integer, Book> searchNovelByGenre(String genre) {
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return null;
		}
//		HashMap<Integer, Book> findBook = new HashMap<Integer, Book>();
//		for (Book b : books.values()) {
//			if (b instanceof Novel) {
//				if (((Novel) b).getGenre().containsKey(genre))
//					findBook.put(b.getIsbn(), b);
//			}
//		}
		return null;
	}

	@Override
	public HashMap<Integer, Book> getStarOfMagazines(String starName) {
		if (books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return null;
		}
		HashMap<Integer, Book> tempMagazines = new HashMap<Integer, Book>();
//	    for (Book b : books.values()) {
//	        if (b instanceof Magazine) {
//	            Magazine m = (Magazine) b;
//	            if (starName.equals(m.getCoverStar()) 
//	                    || containString(m.getInterviewStar(), starName)) {
//	                tempMagazines.put(b.getIsbn(), b);
//	            }
//	        }
//	    }
//
//	    if (tempMagazines.isEmpty()) {
//	        System.out.println("해당 스타가 인터뷰한 매거진이 없습니다.");
//	    }
//	    
	    return null;
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