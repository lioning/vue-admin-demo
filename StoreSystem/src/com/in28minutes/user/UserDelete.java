package com.in28minutes.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in28minutes.db.ExecuteSQL;
import com.in28minutes.util.DBUtil;

@WebServlet(urlPatterns = "/user_delete.do")
public class UserDelete extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");

		String sql = "DELETE FROM user WHERE `user_name` = '" + name + "'";
		try {
			db.execute_udate(sql);
		} catch (Exception e) {
			// ���� Class.forName ����
			e.printStackTrace();
		} finally {
//			util.send_http_json("{\"msg\":\"��������\", \"code\":500 " + "}", response);
		}
		System.out.println(sql);
	}
}
