package com.in28minutes.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in28minutes.db.ExecuteSQL;
import com.in28minutes.util.DBUtil;

@WebServlet(urlPatterns = "/user_add.do")
public class UserAdd extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String pwd_confirm = request.getParameter("pwd_confirm");
		String phone = request.getParameter("phone");
		String addr = "";

		System.out.println(
				"UserAdd doPost Hit" + "name:" + name + ", pwd:" + pwd + ", pwd2:" + pwd_confirm + ", phone:" + phone);

		if (pwd.equals(pwd_confirm) == false) {
			util.send_http_json("{\"msg\":\"再次键入的密码不一致！\", \"code\":501 " + "}", response);
			return;
		}

		String q_sql = "SELECT * FROM store WHERE `user_name`='" + name + "' ";
		ArrayList<Map<String, Object>> q_res = db.execute_query(q_sql);
		if (q_res.size() > 0) {
			util.send_http_json("{\"msg\":\"用户已存在\", \"code\":502 " + "}", response);
			return;
		}
		// YYYY-MM-DD HH:MM:SS
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String sql = "INSERT into user (`user_name`, `user_pwd`, `user_phone`, `user_addr`, `user_sign_date`) VALUES ('" + name
				+ "', '" + pwd + "', '" + phone + "', '" + addr + "', '" + df.format(new Date()) + "' )";

		try {
			db.execute_udate(sql);
			util.send_http_json("{\"msg\":\"success\", \"code\":200 " + "}", response);
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
//			util.send_http_json("{\"msg\":\"操作出错\", \"code\":500 " + "}", response);
		}
		System.out.println(sql);
	}
}
