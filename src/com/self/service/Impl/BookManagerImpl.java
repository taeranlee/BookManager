package com.self.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
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
		for (Book b : books) {
			if (b.getIsbn() == book.getIsbn()) {
				System.out.println("이미 등록된 책입니다.");
				return ;
			}
		}
		books.add(book);
	}

	@Override
	public void deleteBook(int isbn) {
		for (Book b : books) {
			if (b.getIsbn() == isbn) {
				books.removeIf(book -> book.getIsbn() == isbn);
				return;
			}
		}
		System.out.println("지우려는 책이 등록되어있지 않습니다.");
	}

	@Override
	public void updateBook(Book book) {
		for (int i=0;i<books.size()-1;i++) {
			if (book.getIsbn() == books.get(i).getIsbn()) {
				books.set(i, book);
				return;
			}
		}
		System.out.println("업데이트 하려는 책이 등록되어있지 않습니다.");
	}

	@Override
	public Book getBook(int isnb) {//isnb로 책찾기 
		Book findIsnb = null;
		for(Book b:books) {
			if(b.getIsbn()==isnb) {
				findIsnb=b;
			}
		}
		return findIsnb;
	}


	@Override
	public List<Book> getAllBook() {//모든 책 정보 반환하기 

		return books;
	}

	@Override
	public int getNumberOfBooks() {//등록된 책 숫자 
		 return books.size();
	}
	

	@Override
	public List<Book> searchBookByTitle(String title) {//제목으로 책찾기 
		List<Book> findTitle = new ArrayList<Book>();
		int idx = -1;
		int sidx = -1;
		for(Book b:books) {
			if(b.getTitle().equals(title)) {
				findTitle.add(b);
			}
		}
		return findTitle;
	}
	
	public List<Book> searchBookByPrice(double min, double max)
	{
		List<Book> findTitle = new ArrayList<Book>();
		for(Book b:books) {
			if(b.getPrice() >= min && b.getPrice() <= max) {
				findTitle.add(b);
			}
		}
		return findTitle;
		
	}



	@Override
	public double getSumPriceOfBooks() {//모든 책의 가격 합치기~
		double sum = 0;
		for(Book b :books) {
			
			sum += b.getPrice();

		}
		return sum;
	}

	@Override
	public double getAvgPriceOFBooks() {//모든 책 가격평균 구하기 
		
		return getSumPriceOfBooks()/books.size();
	}


	@Override
	public List<Book> searchNovelByGenre(String genre) {
		List<Book> temp = new ArrayList<Book>();
		for (Book b : books) {
			if (b instanceof Novel) {
				for (String s : ((Novel)b).getGanre()) {
					if (s.equals(genre))
						temp.add(b);
				}
			}
		}
		return temp;
	}

	@Override
	public List<Book> getStarOfMagazines(String starName) {
	    List<Book> tempMagazines = new ArrayList<>();

	    for (Book b : books) {
	        if (b instanceof Magazine) {
	            Magazine m = (Magazine) b;
	            // 표지 스타가 일치하거나 인터뷰 스타 목록에 포함되어 있으면 추가
	            if (starName.equals(m.getCoverStar()) 
	                    || containString(m.getInterviewStar(), starName)) {
	                tempMagazines.add(b);
	            }
	        }
	    }

	    if (tempMagazines.isEmpty()) {
	        System.out.println("해당 스타가 인터뷰한 매거진이 없습니다.");
	    }
	    
	    return tempMagazines;
	}
	
	public boolean containString(List<String> strings, String target) {
	    if (strings == null) {
	        return false;
	    }
	    for (String s : strings) {
	        if (target.equals(s)) {
	            return true;
	        }
	    }
	    return false;
	}

	
}//class