package com.self.service.test;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.self.exception.BookNotFoundException;
import com.self.exception.DuplicateBookException;
import com.self.exception.EmptyBookException;
import com.self.exception.GenreNotFoundException;
import com.self.service.Impl.BookManagerImpl;
import com.self.vo.Book;
import com.self.vo.Magazine;
import com.self.vo.Novel;

public class BookManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookManagerImpl service = BookManagerImpl.getInstance();
		
		try {
		service.insertBook(new Novel(11, "해리포터", "조앤롤링", "민음사", 32.0, 8, new HashMap<>(Map.of(11, "판타지"))));
		// service.insertBook(new Novel(11, "해리포터", "조앤롤링", "민음사", 32.0, 8, new HashMap<>(Map.of(11, "판타지"))));
		service.insertBook(new Novel(12, "룬과 친구들", "박경자", "초록", 15.3, 8, new HashMap<>(Map.of(11, "판타지", 22, "어드벤처"))));
		service.insertBook(new Magazine(21, "Vogue", 4, "BB", "b", 12.3, "jennie", new HashMap<>(Map.of(0, "suzy",1, "IU",2, "WOODZ"))));
		//service.insertBook(new Magazine(22, "GQ", 4, "CC", "c", 13.8, "taeyong", Arrays.asList("Doyoung", "Mark")));
		service.insertBook(new Magazine(23, "Elle", 4, "DD", "d", 11.6, "rosé", new HashMap<>(Map.of(3, "Jisoo", 4,"Lisa"))));
		service.insertBook(new Magazine(24, "Vogue", 9, "BB", "b", 14.2, "jennie", new HashMap<>(Map.of(5,"suzy", 6, "IU", 7,"WOODZ"))));
		service.insertBook(new Magazine(25, "GQ", 9, "CC", "c", 15.3, "taeyong", new HashMap<>(Map.of(8, "Doyoung", 9, "Mark"))));
		service.insertBook(new Magazine(26, "Harper's Bazaar", 12, "EE", "e", 17.8, "sana", new HashMap<>(Map.of(10,"Momo", 11,"Mina"))));
		service.insertBook(new Magazine(27, "Singles", 12, "FF", "f", 10.5, "haerin", new HashMap<>(Map.of(12,"Minji",13, "Danielle"))));
		service.insertBook(new Magazine(28, "Elle", 1, "DD", "d", 11.5, "rosé", new HashMap<>(Map.of(14,"Lisa",15, "Jisoo"))));
		service.insertBook(new Magazine(29, "Arena Homme", 1, "GG", "g", 13.7, "jungkook", new HashMap<>(Map.of(16, "RM", 17, "Jin",18, "SUGA"))));
		service.insertBook(new Magazine(30, "Cosmopolitan", 6, "HH", "h", 13.4, "seulgi", new HashMap<>(Map.of(19, "Wendy",20, "Joy"))));
		service.insertBook(new Magazine(31, "W Korea", 6, "II", "i", 16.0, "karina", new HashMap<>(Map.of(21, "Winter", 22, "NingNing"))));
		}catch (DuplicateBookException e) {
			System.out.println(e.getMessage());
		}
        
		System.out.println("=== [1] 전체 책 목록 출력 ===");
		try {
        printBooks(service.getAllBook());
		}catch (EmptyBookException e) {
			System.out.println(e.getMessage());
		}
       

        // 2. 특정 ISBN 책 삭제
        System.out.println("\n=== [2] ISBN 24번 책 삭제 후 목록 출력 ===");
        
        try {
        	 service.deleteBook(24);
             printBooks(service.getAllBook());
        }catch (EmptyBookException e) {
			System.out.println(e.getMessage());
		}catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}

        // 3. 책 개수 출력
        System.out.println("\n=== [3] 등록된 책 수 ===");
        System.out.println(service.getNumberOfBooks() + "권");

        // 4. 평균 가격 출력
        System.out.println("\n=== [4] 책들의 평균 가격 ===");
        try {
        	System.out.printf("%.2f원\n", service.getAvgPriceOFBooks());
		} catch (EmptyBookException e) {
			System.out.println(e.getMessage());
		}
        

        // 5. 가격 총합 출력
        System.out.println("\n=== [5] 책들의 가격 총합 ===");
        try {
        	System.out.printf("%.2f원\n", service.getSumPriceOfBooks());
		} catch (EmptyBookException e) {
			System.out.println(e.getMessage());
		}
        

        // 6. 가격 범위로 책 검색
        System.out.println("\n=== [6] 가격이 10~30만원 사이인 책 검색 ===");
        try {
        	HashMap<Integer, Book> foundBooks = service.searchBookByPrice(10.0, 30.0);
            printBooks(foundBooks);
		} catch (EmptyBookException e) {
			System.out.println(e.getMessage());
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
        


        // 7. 매거진 월별 정렬
        System.out.println("\n=== [9] 매거진 월(month)순 정렬 후 전체 출력 ===");
        try {
        	ArrayList<Book> sortList = service.getAllBookForList();
	        printMagazine(sortList);
	        System.out.println("*********sort 후************");
	        Collections.sort(sortList);
	        printMagazine(sortList);
        } catch(EmptyBookException e) {
        	System.out.println(e.getMessage());
        }
        
        // 8. 특정 스타가 인터뷰한 매거진 찾기
         System.out.println("\n=== [7] 인터뷰 스타가 'jennie'인 매거진 검색 ===");
         try {
        	 HashMap<Integer, Book> magazines = service.getStarOfMagazines("jennie");
             printMagazine(magazines);
		} catch (EmptyBookException e) {
			System.out.println(e.getMessage());
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
         

        // 9. 특정 장르 소설 검색
        System.out.println("\n=== [8] '판타지' 장르 소설 검색 ===");
        try {
        	 HashMap<Integer, Book> novels = service.searchNovelByGenre("판타지");
             printNovel(novels);
		} catch (EmptyBookException e) {
			System.out.println(e.getMessage());
		} catch (GenreNotFoundException e) {
			System.out.println(e.getMessage());
		} 
       
        
        //10. Novel Title 기준 오름차순 정렬 
//        System.out.println("\n=== [9] Novel Title 기준 오름차순 정렬 ===");
//        List<Book> allBooks = service.getAllBook();
//        Collections.sort(service.getAllBook()); 
//        printNovel(service.getAllBook());// Novel 클래스에서 Comparable을 구현했으므로 기본 정렬 기준으로 정렬
        //11. title 검색
        System.out.println("\n=== [9] 타이틀 제목의 책 검색 ===");
        try {
        	 System.out.println(service.searchBookByTitle("룬과 친구"));
		} catch (EmptyBookException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (BookNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
       
	
	}
	
	private static void printMagazine(HashMap<Integer, Book> books) {
        if (books != null && !books.isEmpty()) {
            for (Book b : books.values()) {
            	if (b instanceof Magazine)
            		System.out.println(b);
            }
        } else {
            System.out.println("결과가 없습니다.");
        }
    }
	
	private static void printNovel(HashMap<Integer, Book> books) {
        if (books != null && !books.isEmpty()) {
            for (Book b : books.values()) {
            	if (b instanceof Novel)
            		System.out.println(b);
            }
        } else {
            System.out.println("결과가 없습니다.");
        }
    }
	
    // 리스트를 출력하는 공통 메서드
	private static void printMagazine(ArrayList<Book> books) {
		if(books != null && !books.isEmpty()) {
			for(Book b : books) {
				if(b instanceof Magazine) {
					System.out.println(b);
				}
			}
		}
	}
    private static void printBooks(HashMap<Integer, Book> foundBooks) {
        if (foundBooks != null && !foundBooks.isEmpty()) {
            for (Book b : foundBooks.values()) {
            	System.out.println(b);
            }
        } else {
            System.out.println("결과가 없습니다.");
        }
    }

}//class
