package com.javaex.util;

public class Paging {

		 public int w_size = 10; //보여질 글 개수
		 public int p_size = 5;  //보여질 페이지 개수
		 public int writing_Count = 0;//글 총 개수
		 
		 
		 public int cur_Page = 0;//현재 페이지 
		 
		 
		 public Paging() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Paging(int w_size, int p_size, int writing_Count,  int cur_Page) {
		  super();
		  this.w_size = w_size;
		  this.p_size = p_size;
		  this.writing_Count = writing_Count;
		  this.cur_Page = cur_Page;
		 }
		
		
		 
		 public int getW_size() {
			return w_size;
		}

		public void setW_size(int w_size) {
			this.w_size = w_size;
		}

		public int getP_size() {
			return p_size;
		}

		public void setP_size(int p_size) {
			this.p_size = p_size;
		}

		public int getWriting_Count() {
			return writing_Count;
		}

		public void setWriting_Count(int writing_Count) {
			this.writing_Count = writing_Count;
		}

		public int getCur_Page() {
			return cur_Page;
		}

		public void setCur_Page(int cur_Page) {
			this.cur_Page = cur_Page;
		}

		public int getPage_Count() //총 페이지 개수
		 {
		  return ( (writing_Count - 1) / w_size) + 1;
		 }
		 
		 public int getPage_Start() //페이지 시작번호 
		 { int i=( ( cur_Page - 1 ) / p_size ) * p_size + 1;
		  return i;
		 }
		 
		 public int getPage_End() //페이지 끝번호 
		 {
		  return Math.min( getPage_Start() + p_size - 1 , getPage_Count() );
		 }
		 
		 public boolean isPre() //pre표시 여부 
		 {
		  return getPage_Start() != 1;
		 }
		 
		 public boolean isNext() //next표시 여부
		 {
		  return getPage_End() < getPage_Count();
		 }
		 
		 public int getWriting_Start() //페이지 내에서 글 시작번호
		 {
		  return getWriting_End() - w_size + 1;
		 }
		 
		 public int getWriting_End() //페이지 내에서 글 끝번호 
		 {
		  return cur_Page * w_size;
		 }
}
