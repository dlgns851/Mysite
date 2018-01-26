package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.javaex.conn.DbConnect;
import com.javaex.vo.UserVo;

public class UserDao {

	public UserVo getUser(String email, String password) {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserVo vo = null;
		try {
			String query = "select no,name from users where email = ? and password=?";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while (rs.next()) { // rs는 값이 들어가지않을경우 시작도안함
				vo = new UserVo();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
			}

			// authorList.toString();
			// System.out.println(authorId+" "+authorName+" "+authorDesc);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		return vo;
	}

	public List<UserVo> getListAll() {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo vo = null;

		List<UserVo> userList = new ArrayList<UserVo>();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from users";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setPassword(rs.getString(4));
				vo.setGender(rs.getString(5));

				userList.add(vo);

				// authorList.toString();
				// System.out.println(authorId+" "+authorName+" "+authorDesc);
			}

		} catch (SQLException e) {
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
		return userList;
	}

	public void insert(UserVo userVo) {

		Connection con = new DbConnect().getCon(); // 드라이버로드, 디비연동
		PreparedStatement pstmt = null;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into users\r\n" + "values (seq_users_no.nextval, ?,?,?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getEmail());
			pstmt.setString(3, userVo.getPassword());
			pstmt.setString(4, userVo.getGender());

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 저장완료");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {

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
	
	public void modify(UserVo newUserVo) {
		
		
		Connection con = new DbConnect().getCon(); // 드라이버로드, 디비연동
		PreparedStatement pstmt = null;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update USERS \r\n" + 
					"set NAME=?,email=?,PASSWORD=?,gender=?\r\n" + 
					"where NO=?";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, newUserVo.getName());
			pstmt.setString(2, newUserVo.getEmail());
			pstmt.setString(3, newUserVo.getPassword());
			pstmt.setString(4, newUserVo.getGender());
			pstmt.setInt(5, newUserVo.getNo());

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 저장완료");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {

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

}
