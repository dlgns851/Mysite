package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.javaex.conn.DbConnect;
import com.javaex.util.Paging;
import com.javaex.vo.BoardUserVo;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

public class BoardDao {

	
	public List<BoardVo> getListAll() {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;

		List<BoardVo> boardList = new ArrayList<BoardVo>();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
		
			String query = "select bo.no,bo.title,us.name,bo.hit,bo.reg_date,bo.content,bo.user_no\r\n" + 
						   "from board bo,users us\r\n" + 
						   "where bo.user_no = us.no \r\n" + 
						   "order by bo.reg_date desc";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setCount(rs.getInt(4));
				vo.setDate(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setUserNo(rs.getInt(7));
			
				

				boardList.add(vo);

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
		return boardList;
	}
	
	
	public List<BoardVo> getListAll(String searchWord) {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;

		List<BoardVo> boardList = new ArrayList<BoardVo>();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
		
			String query = "select bo.no,bo.title,us.name,bo.hit,bo.reg_date,bo.content,bo.user_no\r\n" + 
						   "from board bo,users us\r\n" + 
						   "where bo.user_no = us.no \r\n" +
						   "and bo.title like ? \r\n" +
						   "order by bo.reg_date desc";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+searchWord+"%");
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setCount(rs.getInt(4));
				vo.setDate(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setUserNo(rs.getInt(7));
			
				

				boardList.add(vo);

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
		return boardList;
	}
	
	public List<BoardVo> getListAll(Paging paging) {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;

		List<BoardVo> boardList = new ArrayList<BoardVo>();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
		
			String query = "select  no,title,name,hit,reg_date,content,user_no\r\n" + 
					"from\r\n" + 
					"(\r\n" + 
					"select rownum rnum,no,title,name,hit,reg_date,content,user_no \r\n" + 
					"from(\r\n" + 
					"select bo.no,bo.title,us.name,bo.hit,bo.reg_date,bo.content,bo.user_no\r\n" + 
					"from BOARD bo, users us\r\n" + 
					"where bo.user_no=us.no\r\n" + 
					"order by reg_date) )\r\n" + 
					"where rnum >=? and rnum <= ?";
			
		
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, paging.getWriting_Start()); //
			pstmt.setInt(2, paging.getWriting_End());
			//pstmt.setString(1, "%"+searchWord+"%");
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setCount(rs.getInt(4));
				vo.setDate(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setUserNo(rs.getInt(7));
			
				

				boardList.add(vo);

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
		return boardList;
	}
	
	
	
	
	public int getUserNo(int no) {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		int userNo=0;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select user_no from board where no=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			

			// 4.결과처리
			while (rs.next()) {
				userNo=rs.getInt(1);
				
				

				

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
		return userNo;
	}
	
	
	public BoardVo view(int no) {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;


		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select title,content,user_no from board where no=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			

			// 4.결과처리
			while (rs.next()) {
				vo = new BoardVo();
				
				vo.setTitle(rs.getString(1));
				
				vo.setContent(rs.getString(2));
				vo.setUserNo(rs.getInt(3));
				
				vo.setNo(no);
				

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
		return vo;
	}
	
	public void delete(int no) {
		//서블릿에서 session의 네임을 boardVo에 넣어줌 
		Connection con = new DbConnect().getCon(); // 드라이버로드, 디비연동
		PreparedStatement pstmt = null;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from board where no=?"; //title,name,content 순
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1,no);
		

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
	
	public void upHit(int no,int hit) {
		//서블릿에서 session의 네임을 boardVo에 넣어줌 
		Connection con = new DbConnect().getCon(); // 드라이버로드, 디비연동
		PreparedStatement pstmt = null;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update board\r\n" + 
					"set hit=?\r\n" + 
					"where no=?"; //title,name,content 순
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1,hit);
			pstmt.setInt(2,no);
		

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
	
	public void modify(BoardVo vo) {
		//서블릿에서 session의 네임을 boardVo에 넣어줌 
		Connection con = new DbConnect().getCon(); // 드라이버로드, 디비연동
		PreparedStatement pstmt = null;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update board\r\n" + 
					"set title=?,content=?\r\n" + 
					"where no=?"; //title,name,content 순
			pstmt = con.prepareStatement(query);

			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContent());
			pstmt.setInt(3,vo.getNo());
		

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
	
	public int countRow() {
		Connection con = new DbConnect().getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		int countRow=0;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select count(*)\r\n" + 
					" from board";
			pstmt = con.prepareStatement(query);
		
			rs = pstmt.executeQuery();
			
			

			// 4.결과처리
			while (rs.next()) {
				countRow=rs.getInt(1);
				

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
		return countRow;
	}
	
	public void insert(BoardVo boardVo,int userVo) {
		//서블릿에서 session의 네임을 boardVo에 넣어줌 
		Connection con = new DbConnect().getCon(); // 드라이버로드, 디비연동
		PreparedStatement pstmt = null;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into board\r\n" + 
					"values(seq_board_no.nextval,?,0,sysdate,?,?)"; //title,name,userno 순
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, boardVo.getTitle());
			
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, userVo);
		

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
