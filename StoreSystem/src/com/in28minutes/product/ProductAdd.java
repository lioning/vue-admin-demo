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

@WebServlet(urlPatterns = "/product_add.do")
public class ProductAdd extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HashMap<String, String> name_map = new HashMap<String, String>();
		name_map.put("product_no", "no");
		name_map.put("product_name", "name");
		name_map.put("product_price", "price");
		name_map.put("product_price_vip", "price_vip");
		name_map.put("product_serial", "serial");
		name_map.put("product_discount", "discount");
		name_map.put("product_supplier_no", "supplier");
		name_map.put("product_unit", "unit");

		String[] paraNames = name_map.values().toArray(new String[0]);
		String[] colNames = name_map.keySet().toArray(new String[0]);

		HashMap<String, String> paras = util.get_para_default(request, paraNames);
		paras.put("supplier", "1");
		String q_sql = "SELECT * FROM product WHERE product_no='" + paras.get("no") + "' ";
		ArrayList<Map<String, Object>> q_res = db.execute_query(q_sql);
		if (q_res.size() > 0) {
			util.send_msg_json("商品编号已存在", 500, response);
			return;
		}

		String sql = "INSERT into product (" + util.get_insert_cols(colNames) + ") VALUES ("
				+ util.get_insert_vals(paras, colNames, name_map) + ")";
	    System.out.println("product add set_sql:" + sql);

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
//		System.out.println(sql);
	}
}
