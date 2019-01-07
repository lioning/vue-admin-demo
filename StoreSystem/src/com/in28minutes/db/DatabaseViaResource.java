package com.in28minutes.db;

import java.io.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.sql.*;

@WebServlet(urlPatterns = "/dbtest2.so")
public class DatabaseViaResource extends HttpServlet {
	private ExecuteSQL db = new ExecuteSQL();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String title = "���ݿ���";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n");
		
		String sql;
		sql = "SELECT id, first, last, age FROM Employees";
		ArrayList<Map<String, Object>> rs = db.execute_query(sql);

		for (int i = 0; i < rs.size(); i++) {
			// ͨ���ֶμ���
			int id = (int) rs.get(i).get("id");
			int age = (int) rs.get(i).get("age");
			String first = (String) rs.get(i).get("first");
			String last = (String) rs.get(i).get("last");

			// �������
			out.println("ID: " + id + "<br>");
			out.println(", Age: " + age + "<br>");
			out.println(", First: " + first + "<br>");
			out.println(", Last: " + last + "<br>");
		}
		out.println("</body></html>");

	}

	public void doGet_bak(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// JDBC ���������ƺ����ݿ�� URL

		// ������Ӧ��������
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "���ݿ���";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n");
		Connection conn = null;
		Statement stmt = null;
		Context ctx = null;

		try {
			// ע�� JDBC ������
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");

			// ��һ������
			conn = ds.getConnection();

			// ִ�� SQL ��ѯ
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// չ����������ݿ�
			while (rs.next()) {
				// ͨ���ֶμ���
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// �������
				out.println("ID: " + id + "<br>");
				out.println(", Age: " + age + "<br>");
				out.println(", First: " + first + "<br>");
				out.println(", Last: " + last + "<br>");
			}
			out.println("</body></html>");

			// ��ɺ�ر�
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// ���� JDBC ����
			se.printStackTrace();
		} catch (Exception e) {
			// ���� Class.forName ����
			e.printStackTrace();
		} finally {
			// ��������ڹر���Դ�Ŀ�
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
