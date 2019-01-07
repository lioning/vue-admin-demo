package com.in28minutes.db;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

//public class DatabaseAccess extends HttpServlet {
//
//}

@WebServlet(urlPatterns = "/dbtest.so") 
public class DatabaseAccess extends HttpServlet{
    
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
				request, response);
	}
  public void doGet(HttpServletRequest request,
		  	HttpServletResponse response) throws ServletException, IOException
  {
      // JDBC ���������ƺ����ݿ�� URL
      final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
      final String DB_URL="jdbc:mysql://localhost/TEST?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
//      ������url��"jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&characterEncoding=utf-8&useSSL=true"
//      ����&serverTimezone=GMT%2B8��ע�⣺GMT%2B8����GMT+8ʱ��

      //  ���ݿ���û��������룬��Ҫ�����Լ�������
      final String USER = "root";
      final String PASS = "root";

      // ������Ӧ��������
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "���ݿ���";
      String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +          "transitional//en\">\n";
         out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor=\"#f0f0f0\">\n" +
         "<h1 align=\"center\">" + title + "</h1>\n");
      Connection conn = null;
      Statement stmt = null;
      try{
         // ע�� JDBC ������
//         Class.forName("com.mysql.jdbc.Driver");
         Class.forName("com.mysql.cj.jdbc.Driver");
         
         // ��һ������
         conn = DriverManager.getConnection(DB_URL,USER,PASS);

         // ִ�� SQL ��ѯ
         stmt = conn.createStatement();
         String sql;
         sql = "SELECT id, first, last, age FROM Employees";
         ResultSet rs = stmt.executeQuery(sql);

         // չ����������ݿ�
         while(rs.next()){
            // ͨ���ֶμ���
            int id  = rs.getInt("id");
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
      }catch(SQLException se){
         // ���� JDBC ����
         se.printStackTrace();
      }catch(Exception e){
         // ���� Class.forName ����
         e.printStackTrace();
      }finally{
         // ��������ڹر���Դ�Ŀ�
         try{
            if(stmt!=null)
               stmt.close();
         }catch(SQLException se2){
         }
         try{
            if(conn!=null)
            conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }
      }
   }
} 