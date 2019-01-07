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
			// 注册 JDBC 驱动器
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");

			// 打开一个连接
			conn = ds.getConnection();

			// 执行 SQL 查询
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			ResultSetMetaData md = rs.getMetaData(); // 获得结果集结构信息,元数据
			int columnCount = md.getColumnCount(); // 获得列数
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				res.add(rowData);
			}

			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 最后是用于关闭资源的块
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
            // 注册 JDBC 驱动器
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");

            // 打开一个连接
            conn = ds.getConnection();

            // 执行 SQL 查询
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
            	res = rs.getInt(1);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 最后是用于关闭资源的块
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
			// 注册 JDBC 驱动器
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");

			// 打开一个连接
			conn = ds.getConnection();

			// 执行 SQL 查询
			stmt = conn.createStatement();
			res = stmt.executeUpdate(sql);

			// 完成后关闭
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 最后是用于关闭资源的块
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
