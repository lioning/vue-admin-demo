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

@WebServlet(urlPatterns = "/supplier_list.do")
public class SupplierListAll extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String sql = "SELECT supplier_name, supplier_no FROM supplier";
		ArrayList<Map<String, Object>> res = db.execute_query(sql);

		HashMap<String, String> name_map = new HashMap<String, String>();
		name_map.put("supplier_name", "label");
		name_map.put("supplier_no", "value");

		util.send_data_json(util.gen_json_array(res, name_map), 0, response);
//		System.out.println(sql);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
