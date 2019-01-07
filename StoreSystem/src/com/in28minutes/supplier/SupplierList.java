package com.in28minutes.supplier;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(urlPatterns = "/supplier_listpage.do")
public class SupplierList extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int pageNo = 1;
		int pageSize = 20;
		if (request.getParameter("page") != null) {
			pageNo = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("size") != null) {
			pageSize = Integer.parseInt(request.getParameter("size"));
		}
		
		String cond = "";
		if (request.getParameter("name") != null && ! request.getParameter("name").equals("")) {
			cond = " WHERE supplier_name LIKE '%" + request.getParameter("name") + "%'";
		}

		String sql = "SELECT * FROM supplier " + cond + "limit " + (pageNo - 1) * pageSize + ", " + pageSize;
		ArrayList<Map<String, Object>> res = db.execute_query(sql);

		HashMap<String, String> name_map = new HashMap<String, String>();
		name_map.put("supplier_name", "name");
		name_map.put("supplier_addr", "addr");
		name_map.put("supplier_no", "no");
		name_map.put("supplier_phone", "phone");

		int total = db.execute_query_count("SELECT COUNT(*) FROM supplier" + cond);
		String json =  "{" + util.gen_json(res, name_map) + ", \"total\" : " + total + "}";

		util.send_http_json(json, response);
//		System.out.println(sql);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
