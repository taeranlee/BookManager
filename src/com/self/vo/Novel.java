package com.self.vo;

import java.util.HashMap;

public class Novel extends Book{
	private HashMap<Integer, String> genre;
	private int series;
	
	public Novel() {}

	public Novel(int isbn, String title, String author, String publisher, double price, int series, HashMap<Integer, String> genre) {
		super(isbn,title,author,publisher,price);
		this.genre = genre;
		this.series = series;
	}

	public HashMap<Integer, String> getGenre() {
		return genre;
	}

	public void setGenre(HashMap<Integer, String>  genre) {
		this.genre = genre;
	}

	public int getSeries() {
		return series;
	}

	public void publishSeries(int series) {
		this.series = series;
	}
	
	public int compareTo(Book b) {
		return this.getTitle().compareTo(b.getTitle());  // title 기준으로 오름차순 정렬
	}

	@Override
	public String toString() {
		return super.toString()+"Novel [genre=" + genre + ", series=" + series + "]";
	}
}