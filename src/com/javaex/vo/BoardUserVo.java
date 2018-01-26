package com.javaex.vo;

public class BoardUserVo {

	
	int no;
	String title;
	String name;
	int count;
	String date;
	public BoardUserVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardUserVo(int no, String title, String name, int count, String date) {
		super();
		this.no = no;
		this.title = title;
		this.name = name;
		this.count = count;
		this.date = date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
