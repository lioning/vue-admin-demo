package com.in28minutes.supplier;

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

@WebServlet(urlPatterns = "/supplier_add.do")
public class SupplierAdd extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String no = request.getParameter("no");
		String phone = request.getParameter("phone");

		String q_sql = "SELECT * FROM supplier WHERE supplier_no='" + no + "' ";
		ArrayList<Map<String, Object>> q_res = db.execute_query(q_sql);
		if (q_res.size() > 0) {
			util.send_http_json("{\"msg\":\"供应商编号已存在\", \"code\":500 " + "}", response);
			return;
		}

		String sql = "INSERT into supplier (`supplier_name`, `supplier_addr`, `supplier_phone`, `supplier_no`) VALUES ('"
				+ name + "', '" + addr + "', '" + phone + "', '" + no + "' )";

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
