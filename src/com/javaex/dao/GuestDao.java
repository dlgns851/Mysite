package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.conn.DbConnect;
import com.javaex.vo.GuestVo;


public class GuestDao {

	
public void insert(GuestVo vo) {
		
		
		Connection con = new DbConnect().getCon(); //드라이버로드, 디비연동
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO guestbook VALUES (seq_guestbook_no.nextval, ? ,? , ?,sysdate)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 저장완료");
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
	}

public void delete(String pass) {
	
	
	Connection con = new DbConnect().getCon(); //드라이버로드, 디비연동
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {			
		// 3. SQL문 준비 / 바인딩 / 실행
		String sql= "delete from guestbook where password=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, pass);
		
		int count = pstmt.executeUpdate();

		// 4.결과처리
		System.out.println(count + "건 저장완료");
	}  catch (SQLException e) {
		System.out.println("error:" + e);
	} finally {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
}

public void delete2(String pass,int no) { //삭제에 해당하는 번호의 테이블의 비밀번호와 사용자입력비밀번호가 맞을경우 삭제
	
	
	Connection con = new DbConnect().getCon(); //드라이버로드, 디비연동
	PreparedStatement pstmt = null;
	//PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	String tPass=null;

	try {
		String sql2="select password from guestbook where no="+no;
		pstmt = con.prepareStatement(sql2);
		rs = pstmt.executeQuery();
		int count=0;
		while(rs.next()) {
		tPass = rs.getString(1);
		
		if(pass.equals(tPass))  {	
			// 3. SQL문 준비 / 바인딩 / 실행
			String sql= "delete from guestbook where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			count = pstmt.executeUpdate();
			
		}
		}
		
		

		// 4.결과처리
		System.out.println(count + "건 삭제완료");
	}  catch (SQLException e) {
		System.out.println("error:" + e);
	} finally {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
}


	
	public List<GuestVo> getListAll() {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<GuestVo> gulist = new ArrayList<GuestVo>();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
				String query = "select * from guestbook";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				GuestVo vo = new GuestVo();
				
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegDate(rs.getString(5));

				gulist.add(vo);
				
				//authorList.toString();
				//System.out.println(authorId+" "+authorName+" "+authorDesc);
			}
			
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		
		return gulist;
	}
}
