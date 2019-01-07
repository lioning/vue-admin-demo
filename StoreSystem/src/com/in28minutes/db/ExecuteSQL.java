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

public class ExecuteSQL {
	public ArrayList<Map<String, Object>> execute_query(String sql) {
		Connection conn = null;
		Statement stmt  = null;
		Context ctx     = null;
		ArrayList<Map<String, Object>> res = new ArrayList<Map<String, Object>>();

		try {
			// ע�� JDBC ������
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");

			// ��һ������
			conn = ds.getConnection();

			// ִ�� SQL ��ѯ
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			ResultSetMetaData md = rs.getMetaData(); // ��ý�����ṹ��Ϣ,Ԫ����
			int columnCount = md.getColumnCount(); // �������
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				res.add(rowData);
			}

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
		return res;
	}
	

    public int execute_query_count(String sql) {
        Connection conn = null;
        Statement stmt  = null;
        Context ctx     = null;
        int res = 0;

        try {
            // ע�� JDBC ������
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");

            // ��һ������
            conn = ds.getConnection();

            // ִ�� SQL ��ѯ
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
            	res = rs.getInt(1);
            }

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
        return res;
    }

	
	public int execute_udate(String sql) {
		Connection conn = null;
		Statement stmt  = null;
		Context ctx     = null;
		int res = 0;

		try {
			// ע�� JDBC ������
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");

			// ��һ������
			conn = ds.getConnection();

			// ִ�� SQL ��ѯ
			stmt = conn.createStatement();
			res = stmt.executeUpdate(sql);

			// ��ɺ�ر�
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
		return res;
	}
}
