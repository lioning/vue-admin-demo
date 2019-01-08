package com.in28minutes.product;

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

@WebServlet(urlPatterns = "/product_listpage.do")
public class ProductList extends HttpServlet {
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
			cond = " WHERE product_name LIKE '%" + request.getParameter("name") + "%'";
		}

		String sql = "SELECT * FROM product " + cond + "limit " + (pageNo - 1) * pageSize + ", " + pageSize;
		ArrayList<Map<String, Object>> res = db.execute_query(sql);

		HashMap<String, String> name_map = new HashMap<String, String>();
		name_map.put("product_no", "no");
		name_map.put("product_name", "name");
		name_map.put("product_price", "price");
		name_map.put("product_price_vip", "price_vip");
		name_map.put("product_serial", "serial");
		name_map.put("product_discount", "discount");
		name_map.put("product_supplier_no", "supplier");
		name_map.put("product_unit", "unit");

		int total = db.execute_query_count("SELECT COUNT(*) FROM product" + cond);
		util.send_data_json( util.gen_json_array(res, name_map), total, response);
		System.out.println(util.gen_json_array(res, name_map));

//		String json =  "{" + util.gen_json(res, name_map) + ", \"total\" : " + total + "}";
//		util.send_http_json(json, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
