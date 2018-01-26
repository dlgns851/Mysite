package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

/**
 * Servlet implementation class GuestCont
 */
@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuestBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		/* WebUtil.forward(request, response, "/WEB-INF/list.jsp"); */

		String a = request.getParameter("a");

		if (a.equals("add")) {

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			GuestVo vo = new GuestVo(1, name, password, content, "date");
			GuestDao dao = new GuestDao();
			dao.insert(vo);

			response.sendRedirect("/mysite/gb?a=list");

		} else if (a.equals("list")) {
			GuestDao dao = new GuestDao();
			List<GuestVo> guestList = dao.getListAll();

			request.setAttribute("guestList", guestList);

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		}

		else if (a.equals("delete")) {
			System.out.println("delete 진입 ");
			String no = request.getParameter("no");
			System.out.println(no+"파라미터값 확인 ");
			int no2I = Integer.parseInt(no);
			String password = request.getParameter("password");
			GuestDao dao = new GuestDao();
			dao.delete2(password, no2I);//delete2 자체에서 판별후 삭제 해주는데  dao에선 데이터만 꺼내오고 if문여기서해주는게날듯
			response.sendRedirect("/mysite/gb?a=list");
		} 
		else if (a.equals("deleteform")) {

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
