package com.self.vo;

import java.util.ArrayList;
import java.util.List;

public class Novel extends Book{
	private List<String> ganre;
	private int series;
	
	public Novel() {}

	public Novel(int isbn, String title, String author, String publisher, double price, int series) {
		super(isbn,title,author,publisher,price);
		this.ganre = new ArrayList<String>();
		this.series = series;
	}

	public List<String> getGanre() {
		return ganre;
	}

	public void setGanre(List<String> ganre) {
		this.ganre = ganre;
	}

	public int getSeries() {
		return series;
	}

	public void publishSeries(int series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return "Novel [ganre=" + ganre + ", series=" + series + "]";
	}
	
	

}
