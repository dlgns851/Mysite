package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("user 진입");
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");
		
		if(actionName.equals("joinform")) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
		}
		else if(actionName.equals("join")) {
			UserVo userVo = new UserVo();
			UserDao dao = new UserDao();
			String name =request.getParameter("name");
			String email =request.getParameter("email");
			String password =request.getParameter("password");
			String gender =request.getParameter("gender");
			
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			dao.insert(userVo);
			
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
		}
		
		else if(actionName.equals("loginform")) {
			System.out.println("로긴폼 메소드 진입");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
		}
		
		else if(actionName.equals("login")) {
			System.out.println("로긴메소드진입");
			
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			UserVo userVo= new UserDao().getUser(email, password);
			
			
			if(userVo==null) {
				System.out.println("로그인실패");
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&flag=1");
		
				
			
			}
			else {
				System.out.println("로그인성공");
				HttpSession session = request.getSession();
				
				System.out.println("세션 만들기전 userVo확인 "+userVo.toString());
				session.setAttribute("authUser", userVo);
				
				WebUtil.redirect(request, response, "/mysite/main");
			}
			/*List<UserVo> userList = new UserDao().getListAll();
			
			for(UserVo userVo : userList) {
				
				if( userVo.getEmail().equals(request.getParameter("email")))
				
					if(userVo.getPassword().equals(request.getParameter("password")))
						System.out.println("로그인성공");*/
				
				//로그인 석세스 폼 만들고 로그인세션 할당 
			}
		
		else if (actionName.equals("logout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			response.sendRedirect("/mysite/main");
		}
		else if(actionName.equals("modify")) {
			System.out.println("modify if문 접근");
			HttpSession session = request.getSession();

			UserVo newUserVo= new UserVo();
			UserVo userVo=(UserVo)session.getAttribute("authUser");
			newUserVo.setNo(userVo.getNo());
			newUserVo.setName(request.getParameter("name"));
			newUserVo.setEmail(request.getParameter("email"));
			newUserVo.setPassword(request.getParameter("password"));
			newUserVo.setGender(request.getParameter("gender"));
			

			UserDao dao = new UserDao();
			dao.modify(newUserVo);
			session.setAttribute("authUser", newUserVo);
			
			WebUtil.redirect(request, response, "/mysite/main");
		}
		else if(actionName.equals("modifyform")) {
			HttpSession session =request.getSession();
			UserVo userVo =  (UserVo) session.getAttribute("authUser");
			if(userVo==null) {
				WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			}
			else		
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
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
