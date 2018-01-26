package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");
		
		if(actionName.equals("list")) {
			
			//board 리스트를 받아와서 list.jsp 포워드 
			
			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.getListAll();
			request.setAttribute("list", list);
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
				
		}
		
		else if(actionName.equals("writeform")) {			
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
				
		}
		
		else if(actionName.equals("write")) {
			
			HttpSession session =request.getSession();
			UserVo userVo =  (UserVo) session.getAttribute("authUser");
			if(userVo==null) {
				System.out.println("로그인 안됐음 write차단");
				WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			}
			else {

				System.out.println("로그인 됐음");

			//dao에서 게시글 작성 후 list.jsp로 리다이렉트
			BoardVo boardVo = new BoardVo();
			BoardDao dao = new BoardDao();
			

			boardVo.setTitle(request.getParameter("title"));
			boardVo.setName(userVo.getName());
			boardVo.setContent(request.getParameter("content"));
			System.out.println(boardVo.toString());
			
			
			dao.insert(boardVo,userVo.getNo());
			
			WebUtil.redirect(request, response, "/mysite/board?a=list");
			}
		}
		
	else if(actionName.equals("view")) {
			
			//boardno 값을 받아온상태 .
			
			BoardDao dao = new BoardDao();
			BoardVo vo = dao.view(Integer.parseInt(request.getParameter("no")));
			
			request.setAttribute("view", vo);
			
			
			
			//조회수 1 증가  보드no와 조회수값 필요 
			
			int count=Integer.parseInt(request.getParameter("count"));
			
			dao.upHit(vo.getNo(),count+1);
			
			
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
		
			
			
		}
		
	else if(actionName.equals("modifyform")) {
		
		//boardno 값을 받아온상태 .
		
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.view(Integer.parseInt(request.getParameter("no")));
		
		request.setAttribute("view", vo);
		
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
	
		
		
	}
	else if(actionName.equals("modify")) {
		
		//boardno 값을 받아온상태 .
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		
		vo.setNo(Integer.parseInt(request.getParameter("no")));
		vo.setContent(request.getParameter("content"));
		vo.setTitle(request.getParameter("title"));

		//request.setAttribute("view", vo);
		dao.modify(vo);
		WebUtil.redirect(request, response, "/mysite/board?a=list");

	
		
		
	}
		
	else if(actionName.equals("delete")) {
		HttpSession session =request.getSession();
		UserVo vo= (UserVo)session.getAttribute("authUser");
		
		if(vo==null) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
		}
		else	{	
		
			
		//getparam으로 no 받아서 (보드번호) 이에해당하는 userno 받아옴
			// 세션의 no와 userno 비교후 맞으면 딜리트 
			
		BoardDao dao = new BoardDao();
		int userNo= dao.getUserNo(Integer.parseInt(request.getParameter("no"))); //게시글 userno //getuserno구현
		
		if(userNo==vo.getNo()) //선택된 게시글 작성자와 로그인된 사용자이름 비교 
		dao.delete(Integer.parseInt(request.getParameter("no")));
		
		
		WebUtil.redirect(request, response, "/mysite/board?a=list");
	
		}
		
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
