package com.in28minutes.store;

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

@WebServlet(urlPatterns = "/store_delete.do")
public class StoreDelete extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String no = request.getParameter("no");

//DELETE FROM 表名称 WHERE 列名称 = 值 
		String sql = "DELETE FROM store WHERE `store_no` = '" + no + "'";
		try {
			db.execute_udate(sql);
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
//			util.send_http_json("{\"msg\":\"操作出错\", \"code\":500 " + "}", response);
		}
		System.out.println(sql);
	}
}
