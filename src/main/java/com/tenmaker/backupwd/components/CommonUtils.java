package com.tenmaker.backupwd.components;


import com.tenmaker.backupwd.util.PageUtil;
import com.tenmaker.backupwd.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
	public static Map<String, Object> error(String errorMsg) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", 1);
		resultMap.put("errorMsg", errorMsg);
		return resultMap;
	}

	public static Map<String, Object> getError(Integer errorCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", 1);
		resultMap.put("error", errorCode);
		return resultMap;
	}
	
	public static Map<String, Object> getError() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", 1);
		resultMap.put("error", CommonConst.FAILURE);
		return resultMap;
	}

	public static Map<String, Object> getSuccess() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", 0);
		return resultMap;
	}

	public static Map<String, Object> getSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", 0);
		resultMap.put("datas", data);
		return resultMap;
	}

	public static Map<String, Object> getSuccess(PageUtil page) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", 0);
		resultMap.put("datas", page.getData());
		resultMap.put("totalCount", page.getTotalCount());
		resultMap.put("lastPage", page.getLastPage());
		return resultMap;
	}

	public static boolean validateLong(Long val) {
		if (val == null || val < 0) {
			return false;
		}
		return true;
	}

	public static boolean validateInteger(Integer val) {
		if (val == null || val < 0) {
			return false;
		}
		return true;
	}
	
	public static String getRealPath(String path) {
		if(!path.endsWith("\\")) {
			path += "/";
		}
		return path;
	}
	
	public static String getRootPath(HttpServletRequest request) {
		HttpSession session = CommonUtils.getSession(request);
		String path = session.getServletContext().getRealPath("/");
		if(!path.endsWith("\\")) {
			path += "/";
		}
		return path;
	}
	
	public static String escapeHtml(String value) {
		if(StringUtil.isNotEmpty(value)) {
			value = value.replaceAll("<script", "").replaceAll("</script", "");   
	        value = value.replaceAll("\\(", "（").replaceAll("\\)", "）");   
	        value = value.replaceAll("eval\\((.*)\\)", "");
	        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
	        return value;
		} else {
			return null;
		}
    }
	
	public static HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return session;
		} else {
			return request.getSession(true);
		}
	}
}
