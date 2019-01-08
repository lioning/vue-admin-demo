package com.in28minutes.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in28minutes.util.DBUtil;

@WebServlet(urlPatterns = "/do_logout.do")
public class LogoutServlet extends HttpServlet {
	private DBUtil util = new DBUtil();
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		request.getSession().invalidate();
		util.send_msg_json("success", 200, response);
		System.out.println("Logout");
//		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
//				request, response);
	}
}