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
	public void insertBook(Book book) { //책추가 
		if(idx == MAX_COUNT) {
			System.out.println("더 이상 책을 추가할 수 없습니다. ");
		    return;
		}else {
			books[idx++]=book;
			System.out.println(book.getTitle()+"책이 등록되었습니다 . ");
			numberOfBook++;
		}
	}

	@Override
	public void deleteBook(int isnb) {//책 삭제 
		int ridx = -1;
		for(int i=0;i<idx;i++) {
			if(books[i].getIsbn()==isnb) {
				ridx = i;
				break;
			}
		}if(ridx ==-1) {
			System.out.println("삭제할 책이 존재하지 않습니다. ");
			return;
		}
		for(int i=ridx;i<idx-1;i++) {
			if(ridx==MAX_COUNT-1) {
				books[ridx]=null;
			}else{
				books[i]=books[i+1];
			}
		}
		idx--;
		
	}

	@Override
	public void updateBook(Book book) {//책의 정보 수정 
		for(Book b:books) {
			if(b==null)break;
			if(b.getIsbn()==book.getIsbn()) {
				b.setAuthor(b.getAuthor());
				b.setPrice(b.getPrice());
				b.setPublisher(b.getPublisher());
				b.setTitle(b.getTitle());
				if(b instanceof Magazine) {
					((Magazine)b).setCoverStar(((Magazine)b).getCoverStar());
					((Magazine)b).setInterviewStar(((Magazine)b).getInterviewStar());
				}else if(b instanceof Novel){
					((Novel)b).setGanre(((Novel)b).getGanre());
					((Novel)b).publishSeries(((Novel)b).getSeries());
				}
				
			}
		}
		
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
	public Book[] getAllBook() {//모든 책 정보 반환하기 
		Book[] bookInfo = new Book[idx];
		System.arraycopy(books, 0, bookInfo, 0, idx);
		return bookInfo;
	}

	@Override
	public int getNumberOfBooks() {//등록된 책 숫자 
		int bookCount =0;
		for(int i =0;i<idx;i++) {
			if(books[i]!=null) {
				bookCount++;
			}
		}
		return bookCount;
	}

	@Override
	public Book[] searchBookByTitle(String title) {//제목으로 책찾기 
		Book[] findTitle = new Book[idx];
		int i=0;
		for(Book b:books) {
			if(b.getTitle().equals(title)) {
				findTitle[i]=b;
				idx++;
			}
		}
		return findTitle;
	}

	@Override
	public double getSumPriceOfBooks() {//모든 책의 가격 합치기~
		double sum=0;
		for(int i =0;i<idx;i++) {
			if(books[i]!=null) {
				sum += books[i].getPrice();
			}
		}
		return sum;
	}

	@Override
	public double getAvgPriceOFBooks() {//모든 책 가격평균 구하기 
		
		return getSumPriceOfBooks()/idx;
	}

	@Override
	public Book[] getstarOfMagazines(String starName) {//특정 스타가 인터뷰한 매거진들 찾기 
		int i =0;
		Book[] tempMagazines = new Book[idx];
		for(Book b:books) {
			if(b instanceof Magazine) {
				if(((Magazine)b).getCoverStar().equals(starName)){
					tempMagazines[i]=b;
					i++;
				}else if(containString(((Magazine)b).getInterviewStar(), starName)){
					tempMagazines[i]=b;
					i++;
				}
			}
		}
		if(i==0)System.out.println("해당 star가 인터뷰한 매거진이 없습니다. ");
		return tempMagazines;
	}
	
	public boolean containString(String[] strings,String string) {
		for(String s:strings) {
			if(s!=null && s.equals(string)) {
				return true;
			}
		}
		return false;
	}
		

	@Override
	public Book[] searchBookByPrice(double min, double max) {//최대값 최소값 받고 그 중간 가격들의 책들 다 반환 
		Book[] tempPrice = new Book[idx];  // 임시 배열 크기 지정
	    int i = 0;

	    // 가격 범위에 맞는 책들을 배열에 추가
	    for (int j = 0; j < idx; j++) {
	        if (books[j] != null && books[j].getPrice() >= min && books[j].getPrice() <= max) {
	            tempPrice[i++] = books[j];  // 범위에 맞는 책을 배열에 저장
	        }
	    }

	    // 범위에 맞는 책이 하나도 없다면 빈 배열 반환
	    if (i == 0) {
	        System.out.println("가격 범위에 맞는 책이 없습니다.");
	        return new Book[0];  // 빈 배열 반환
	    }

	    // 책 배열 크기 맞추기
	    Book[] result = new Book[i];
	    System.arraycopy(tempPrice, 0, result, 0, i);  // tempPrice 배열을 실제 크기에 맞게 복사

	    return result;  // 가격 범위에 맞는 책 배열 반환
	}

	@Override
	public Book[] sortMagazineByMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book[] searchNovelByGenre() {
		// TODO Auto-generated method stub
		return null;
	}


}//class
