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

@WebServlet(urlPatterns = "/store_add.do")
public class StoreAdd extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String no = request.getParameter("no");
		
		String q_sql = "SELECT * FROM store WHERE store_no='" + no + "' ";
		ArrayList<Map<String, Object>> q_res = db.execute_query(q_sql);
		if (q_res.size() > 0) {
			util.send_http_json("{\"msg\":\"�ŵ����Ѵ���\", \"code\":500 " + "}", response);
			return;
		}

		String sql = "INSERT into store (`store_name`, `store_addr`, `store_no`) VALUES ('" + name + "', '"
				+ addr + "', '" + no + "' )";
		util.send_http_json("{\"msg\":\"success\", \"code\":200 " + "}", response);
		try {
			db.execute_udate(sql);
		}catch (Exception e) {
			// ���� Class.forName ����
			e.printStackTrace();
		}finally {
//			util.send_http_json("{\"msg\":\"��������\", \"code\":500 " + "}", response);
		}
//		System.out.println(sql);
	}
}
