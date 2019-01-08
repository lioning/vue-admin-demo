package com.in28minutes.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in28minutes.todo.TodoService;
import com.in28minutes.util.DBUtil;

/*

 // Method descriptor #15 ()V
 public void init() throws javax.servlet.ServletException;

 // Method descriptor #37 (Ljavax/servlet/ServletRequest;Ljavax/servlet/ServletResponse;)V
 public void service(javax.servlet.ServletRequest arg0, javax.servlet.ServletResponse arg1) throws javax.servlet.ServletException, java.io.IOException;

 // Method descriptor #15 ()V
 public void destroy();

 */
@WebServlet(urlPatterns = "/do_login.do")
public class LoginRealService extends HttpServlet {

	private LoginService userValidationService = new LoginService();
	private TodoService todoService = new TodoService();
	private DBUtil util = new DBUtil();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String resJSON = "[{id:1, name:‘数据1’},{id:2, name:‘数据2’}]";
		out.print(resJSON);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		String password = request.getParameter("password");

		boolean isUserValid = userValidationService.isUserValid(name, password);

		if (isUserValid) {
			String type = userValidationService.SearchUser(name, password).get(0).get("user_type").toString();

			HashMap<String, String> name_map = new HashMap<String, String>();
			HashMap<String, Object> user = new HashMap<String, Object>();
			user.put("name", name);
			user.put("avatar", type);

			HashMap<String, Object> res = new HashMap<String, Object>();
			res.put("msg", "success");
			res.put("code", "200");
			res.put("user", util.gen_json_raw(user, name_map));

			util.send_http_json(util.gen_json_raw(res, name_map), response);
		} else {
			util.send_msg_json("用户名或密码错误!！", 500, response);
		}
	}

}