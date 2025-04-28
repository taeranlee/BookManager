package com.self.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Magazine extends Book{
	private String coverStar;
	private List<String> interviewStar;
	private int month;
	
	public Magazine() {}

	public Magazine(int isbn, String title,int month,String author, String publisher, double price,String coverStar) {
		super(isbn,title,author,publisher,price);
		this.coverStar = coverStar;
		this.interviewStar = new ArrayList<String>();
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

	public List<String> getInterviewStar() {
		return interviewStar;
	}

	public void setInterviewStar(List<String> interviewStar) {
		this.interviewStar = interviewStar;
	}

	@Override
	public String toString() {
		return "Magazine [coverStar=" + coverStar + ", interviewStar=" + interviewStar + ", month="
				+ month + "]";
	}

	
	
	
	
	

}
