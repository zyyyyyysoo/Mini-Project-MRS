package com.mrs.dto;

public class Movie {
	private int movie_code;
	private String m_name;
	private String d_name;
	private String genre;
	private String company;
	private String grade;
	private int capacity;
	private static final int D_CAPACITY = 50;
	
	public Movie(int movie_code, String m_name, String d_name, String genre, String company, String grade, int capacity) {
		super();
		this.movie_code = movie_code;
		this.m_name = m_name;
		this.d_name = d_name;
		this.genre = genre;
		this.company = company;
		this.grade = grade;
		this.capacity = capacity;
	}
	public Movie(int movie_code, String m_name, String d_name, String genre, String company, String grade) {
		this(movie_code, m_name, d_name, genre, company, grade, D_CAPACITY);
	}
	
	public int getMovie_code() {
		return movie_code;
	}
	public void setMovie_code(int movie_code) {
		this.movie_code = movie_code;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Movie [movie_code=" + movie_code + ", m_name=" + m_name + ", d_name=" + d_name + ", genre=" + genre + ", company="
				+ company + ", grade=" + grade + "]";
	}
	
	

}
