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

@WebServlet(urlPatterns = "/supplier_edit.do")
public class SupplierEdit extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String no = request.getParameter("no");
		String phone = request.getParameter("phone");

		String sql = "UPDATE supplier SET `supplier_name`='" + name + "', `supplier_addr`='" + addr
				+ "', `supplier_phone`='" + phone + "' WHERE `supplier_no` = '" + no + "'";
		try {
			db.execute_udate(sql);
			util.send_http_json("{\"msg\":\"success\", \"code\":200 " + "}", response);
		} catch (Exception e) {
			// ���� Class.forName ����
			e.printStackTrace();
		} finally {
//			util.send_http_json("{\"msg\":\"��������\", \"code\":500 " + "}", response);
		}
//		System.out.println(sql);
//		System.out.printf("recieved, name:%s, addr:%s, no:%s", name, addr, no);
	}
}
