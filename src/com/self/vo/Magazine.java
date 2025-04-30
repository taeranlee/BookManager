package com.self.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Magazine extends Book{
	private String coverStar;
	private HashMap<String, String> interviewStar;
	private int month;
	
	public Magazine() {}

	public Magazine(int isbn, String title,int month,String author, String publisher, double price,String coverStar , HashMap<String, String> interviewStar) {
		super(isbn,title,author,publisher,price);
		this.coverStar = coverStar;
		this.interviewStar = interviewStar;
		this.month = month;
	}
	
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	public String getCoverStar() {
		return coverStar;
	}

	public void setCoverStar(String coverStar) {
		this.coverStar = coverStar;
	}

	public HashMap<String, String> getInterviewStar() {
		return interviewStar;
	}

	public void setInterviewStar(HashMap<String, String> interviewStar) {
		this.interviewStar = interviewStar;
	}

	@Override
	public String toString() {
		return super.toString()+"Magazine [coverStar=" + coverStar + ", interviewStar=" + interviewStar + ", month="
				+ month + "]";
	}
	
	public int compareTo(Book b) {
		
		// 1. 제목(title) 오름차순 비교
		int titleCompare = this.getTitle().compareTo(b.getTitle());
		if (titleCompare != 0) {
		        return titleCompare;
		    }
		
		// 2. 제목이 같으면 month 오름차순 비교
		if (b instanceof Magazine) {
		    Magazine m = (Magazine) b;
		    return ~Integer.compare(this.month, m.getMonth());
		}
		return super.compareTo(b);
		
	}

	
	
	
	
	

}
