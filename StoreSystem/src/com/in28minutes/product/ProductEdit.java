package com.in28minutes.product;

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

@WebServlet(urlPatterns = "/product_edit.do")
public class ProductEdit extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HashMap<String, String> name_map = new HashMap<String, String>();
//		name_map.put("product_no", "no");
		name_map.put("name", "product_name");
		name_map.put("price", "product_price");
		name_map.put("price_vip", "product_price_vip");
		name_map.put("serial", "product_serial");
		name_map.put("discount", "product_discount");
		name_map.put("supplier", "product_supplier");
		name_map.put("unit", "product_unit");

		String[] paraNames = name_map.keySet().toArray(new String[0]);
		String[] colNames = name_map.values().toArray(new String[0]);
		HashMap<String, String> paras = util.get_para_default(request, paraNames);

		String no = request.getParameter("no");
		String sql = "UPDATE product SET " + util.gen_set_sql(paras, name_map) + "WHERE `product_no` = '" + no + "'";
		;
//		String sql = "UPDATE product SET (" + util.get_insert_cols(colNames) + ") VALUES ("
//				+ util.get_insert_vals(paras, colNames, name_map) + ") WHERE `product_no` = '" + no + "'";
		try {
			db.execute_udate(sql);
//			util.send_http_json("{\"msg\":\"success\", \"code\":200 " + "}", response);
			util.send_msg_json("success", 200, response);
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
//			util.send_http_json("{\"msg\":\"操作出错\", \"code\":500 " + "}", response);
		}
//		System.out.println("product_edit, sql:" + sql);
	}
}
