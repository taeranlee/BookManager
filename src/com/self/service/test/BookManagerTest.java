package com.self.service.test;

import java.lang.management.GarbageCollectorMXBean;

import com.self.service.Impl.BookManagerImpl;
import com.self.vo.Book;
import com.self.vo.Magazine;
import com.self.vo.Novel;

public class BookManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookManagerImpl service = BookManagerImpl.getInstance();
		
		service.insertBook(new Novel(11, "해리포터", "조앤롤링", "민음사", 32.0, "판타지",7 ));
		service.insertBook(new Novel(12, "룬과 친구들", "박경자", "초록", 15.3, "판타지",8 ));
		service.insertBook(new Novel(13, "궤도의 밖에서 ", "조앤롤링", "민음사", 24.1, "판타지",1 ));
		service.insertBook(new Novel(14, "비행운 ", "32", "비트", 22.0, "스릴러",1 ));
		service.insertBook(new Magazine(21, "Vogue", "BB", "b", 12.3, "jennie", new String[] {"suzy","IU","WOODZ"}));
		service.insertBook(new Magazine(22, "Bite", "DE", "h", 15.7, "IU", new String[] {"YK","EUJIN","Mark"}));
		
		for(Book b:service.getAllBook()) {
			System.out.println(b);
		}
		
		
		service.deleteBook(14);//isnb로 책 삭제 
		
		for(Book b:service.getAllBook()) {
			System.out.println(b);
		}//모든 책 출력 
		
		for(Book b:service.getAllBook()) {
			System.out.println(service.getNumberOfBooks());
			break;
		}//저장된 책 갯수 
		
		for(Book b:service.getAllBook()) {
			System.out.println(service.getAvgPriceOFBooks());
			break;
		}//책들의 평균가격 
		
		for(Book b:service.getAllBook()) {
			System.out.println(service.getSumPriceOfBooks());
			break;
		}//책들의 모든 가격 
		
		Book[] foundBooks = service.searchBookByPrice(20.0, 30.0);
		 for (Book b : foundBooks) {
	            if (b != null) {
	                System.out.println(b.getTitle() + " - " + b.getPrice() + "원");
	            }
	     }
		
		
		Book[] magazines = service.getstarOfMagazines("jennie");
		if (magazines != null) {
            for (Book b : magazines) {
                if (b != null) {
                    System.out.println(b.getTitle());  // 매거진 제목 출력
                }
            }
        }//jennie가 있는 매거진 모드 출력 
		
		Book[] seriesBooks = service.findGanreByPublisher("민음사", "판타지");
		if(seriesBooks!=null) {
			for(Book b:seriesBooks) {
				if(b != null) {
					System.out.println(b.getTitle());
				}
			}
		}//민음사에서 나온 판타지 작품들중 시리즈물만 출력 

	}//main

}//class
