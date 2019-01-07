package com.in28minutes.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBUtil {
	/*
	 * Input: Table Output: "res":"["k1":"v1", "k2":2]"
	 */
	public String gen_json(ArrayList<Map<String, Object>> tbl, HashMap<String, String> name_map) {
//		String res = "{\"res\":[";
		String res = "\"res\":[";
		ArrayList<String> rows = new ArrayList<String>();

		for (int i = 0; i < tbl.size(); i++) {
			ArrayList<String> row = new ArrayList<String>();

			for (Map.Entry<String, Object> entry : tbl.get(i).entrySet()) {
				String v = "";
				try {
					v = entry.getValue().toString();
				} catch (Exception e) {
				}
				row.add("\"" + name_map.getOrDefault(entry.getKey().toString(), entry.getKey().toString()) + "\":\"" + v
						+ "\"");

			}
			rows.add("{" + String.join(",", row) + "}");
		}
//		res += String.join(",", rows) + "], \"total\":" + tbl.size() + "}";
		res += String.join(",", rows) + "]";
		return res;
	}

	/*
	 * Input: Table Output: ["k1":"v1", "k2":2]
	 */
	public String gen_json_array (ArrayList<Map<String, Object>> tbl, HashMap<String, String> name_map) {

		String res = "[";
		ArrayList<String> rows = new ArrayList<String>();

		for (int i = 0; i < tbl.size(); i++) {
			rows.add(this.gen_json_raw(tbl.get(i), name_map));
		}
		res += String.join(",", rows) + "]";
		return res;
	}

	/*
	 * Input: Rowdata Output: {"k1":"v1", "k2":2}
	 */
	public String gen_json_raw(Map<String, Object> source, HashMap<String, String> name_map) {

		ArrayList<String> tokens = new ArrayList<String>();

		for (Map.Entry<String, Object> entry : source.entrySet()) {
			String v = "";
			String k = "";

			try {
				k = entry.getKey().toString();
				k = name_map.getOrDefault(k, k);
				v = entry.getValue().toString();
			} catch (Exception e) {
			}
			if (v.matches("[0-9]{1,}") || v.startsWith("{")) {
				tokens.add("\"" + k + "\":" + v);
			} else {
				tokens.add("\"" + k + "\":\"" + v + "\"");
			}
		}

		return "{" + String.join(",", tokens) + "}";
	}

	/*
	 * Input: Rowdata Output: {"k1":"v1", "k2":2} No name map
	 */
	public String gen_json_ori(Map<String, Object> source) {

		ArrayList<String> tokens = new ArrayList<String>();

		for (Map.Entry<String, Object> entry : source.entrySet()) {
			String v = "";
			String k = "";

			try {
				k = entry.getKey().toString();
				v = entry.getValue().toString();
			} catch (Exception e) {
			}
			if (v.matches("[0-9]{1,}") || v.startsWith("{") || v.startsWith("[") ) {
				tokens.add("\"" + k + "\":" + v);
			} else {
				tokens.add("\"" + k + "\":\"" + v + "\"");
			}
		}

		return "{" + String.join(",", tokens) + "}";
	}

	/*
	 * Input: Rowdata 
	 * Output: "k1"='v1', "k2" ='2'
	 */
	public String gen_set_sql(HashMap<String, String> paras, Map<String, String> name_map) {
		ArrayList<String> tokens = new ArrayList<String>();

		for (Entry<String, String> entry : paras.entrySet()) {
			String k = "", v = "";
			try {
				k = entry.getKey().toString();
				k = name_map.getOrDefault(k,  k);
				v = entry.getValue().toString();
			} catch (Exception e) {

			}
			tokens.add("`" + k + "`='" + v + "'");
		}
		return " " + String.join(",", tokens) + " ";
	}

	/*
	 * 从 request 中获取参数，参数名称由 source 指定 Input: column name Output: HashMap "k1":"v1",
	 * "k2":"2"
	 */
	public HashMap<String, String> get_para_default(HttpServletRequest request, String[] source) {
		HashMap<String, String> res = new HashMap<String, String>();

		for (int i = 0; i < source.length; i++) {
			String k = source[i];
			String v = "";
			if (request.getParameter(k) != null) {
				v = request.getParameter(k);
			}
			res.put(k, v);
		}
		return res;
	}

	/*
	 * Input: [name1, name2] Output: `name1`, `name2`
	 */
	public String get_insert_cols(String[] colNames) {
		ArrayList<String> tokens = new ArrayList<String>();

		for (int i = 0; i < colNames.length; i++) {
			tokens.add("`" + colNames[i] + "`");
		}
		return String.join(",", tokens);
	}

	/*
	 * Input: HashMap name1:1, name2:2, [name1, name2] Output: 'name1', 'name2'
	 */
	public String get_insert_vals(HashMap<String, String> paras, String[] colNames, HashMap<String, String> name_map) {
		ArrayList<String> tokens = new ArrayList<String>();

		for (int i = 0; i < colNames.length; i++) {
			String name = name_map.getOrDefault(colNames[i], colNames[i]);
			tokens.add("'" + paras.getOrDefault(name, "") + "'");
		}
		return String.join(",", tokens);
	}

	public void send_http_json(String json, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

	public void send_data_json(String data, int count, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("res", data);
		res.put("total", count);

		this.send_http_json(this.gen_json_ori(res), response);
	}
	
	public void send_msg_json(String msg, int code, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("msg", msg);
		res.put("code", code);

		this.send_http_json(this.gen_json_ori(res), response);
	}
}
