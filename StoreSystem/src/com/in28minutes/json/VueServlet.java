package com.in28minutes.json;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = "/json")
public class VueServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setHeader("content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String resJSON = "[{id:1, name:¡®±¼³Û¡¯},{id:2, name:¡®±¦Âí¡¯}]";
		out.print(resJSON);
		out.flush();
		out.close();
	}
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}