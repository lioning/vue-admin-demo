package com.in28minutes.login;

import java.util.ArrayList;
import java.util.Map;

import com.in28minutes.db.ExecuteSQL;
import com.in28minutes.util.DBUtil;

public class LoginService {
	private ExecuteSQL db = new ExecuteSQL();
	private DBUtil util = new DBUtil();
	
	public boolean isUserValid(String user, String password) {
		String sql = "SELECT * FROM  user WHERE `user_name`='" + user + "' AND `user_pwd`='" + password + "' ";
		System.out.println("LoginService, sql:" + sql);
		ArrayList<Map<String, Object>> res = db.execute_query(sql);
		
		if (res.size() > 0) {
			return true;
		}
		
		return false;
		
//		if (user != null && user.equals("1") && password != null && password.equals("1"))
//			return true;
//
//		return false;
	}
	
	public ArrayList<Map<String, Object>> SearchUser(String user, String password) {
		String sql = "SELECT * FROM  user WHERE `user_name`='" + user + "' AND `user_pwd`='" + password + "' ";
		System.out.println("LoginService, sql:" + sql);
		ArrayList<Map<String, Object>> res = db.execute_query(sql);
		
		return res;
	}

}
