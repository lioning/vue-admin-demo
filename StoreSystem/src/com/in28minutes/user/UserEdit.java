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

@WebServlet(urlPatterns = "/user_edit.do")
public class UserEdit extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String pwd  = request.getParameter("pwd");
		String pwd_confirm = request.getParameter("pwd_confirm");

		System.out.printf("user_edit recieved, name:%s, phone:%s, pwd:%s, pwd_confirm:%s\n", name, phone, pwd, pwd_confirm);

		String pwd_sql = "";
		if (pwd != null || pwd_confirm != null) {
			if (pwd != null && ! pwd.equals(pwd_confirm) || pwd_confirm != null && ! pwd_confirm.equals(pwd)) {
				util.send_msg_json("两次键入的密码不一致！", 502, response);
				return;
			}
			pwd_sql = ", `user_pwd` = '" + pwd + "'";
		}

		String sql = "UPDATE user SET `user_phone`='" + phone + "'" + pwd_sql + " WHERE `user_name` = '" + name + "'";
		try {
			db.execute_udate(sql);
			util.send_http_json("{\"msg\":\"success\", \"code\":200 " + "}", response);
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
			util.send_msg_json("操作出错", 500, response);
		} finally {
//			util.send_http_json("{\"msg\":\"操作出错\", \"code\":500 " + "}", response);
		}
		System.out.println("User_edit, sql:" + sql);

	}
}
