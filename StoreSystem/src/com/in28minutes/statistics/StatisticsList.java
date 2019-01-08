package com.in28minutes.statistics;

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

@WebServlet(urlPatterns = "/statistics_month.do")
public class StatisticsList extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String year = request.getParameter("year");
		String store = request.getParameter("store");
		System.out.println("year:" + year + "sore:" + store);
		
		if ( store == null || "".equals(store) || year == null || "".equals(year) ) {
			System.out.println("statistics para error");
			util.send_http_json("[]", response);
			return;
		}
				
		String cond = "";
		if (request.getParameter("name") != null && ! request.getParameter("name").equals("")) {
			cond = " WHERE supplier_name LIKE '%" + request.getParameter("name") + "%'";
		}

		String sql = "SELECT * FROM sales_month WHERE `store_store_no`='" + store + "' AND `year`='" + year + "'";
		ArrayList<Map<String, Object>> res = db.execute_query(sql);
		System.out.println("statistics sql:" + sql);
		System.out.println("statistics res:" + res);
		
		if (res.size() == 0) {
			System.out.println("statistics para no Data");
			util.send_http_json("[]", response);
			return;
		}

		System.out.println(util.gen_moth_data(res));
		String json = "[" + String.join(",", util.gen_moth_data(res)) + "]";

		util.send_http_json(json, response);

	}
}
