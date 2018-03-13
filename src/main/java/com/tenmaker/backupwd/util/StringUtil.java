package com.tenmaker.backupwd.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static String convertObjectToString(Object obj) {
		if(obj == null) {
			return null;
		} else {
			return obj.toString();
		}
	}

	/**
	 * 包装sql查询的精确查找的字符串
	 */
	public static String sqlExactString(String str) {
		if (str != null) {
			str = "'" + str + "'";
		}
		return str;
	}

	/**
	 * 包装sql查询的模糊查找的字符串
	 */
	public static String sqlLikeString(String str) {
		if (str != null) {
			str = "'%" + str + "%'";
		}
		return str;
	}

	/**
	 * 去空字符串
	 */
	public static String trim(String str) {
		if (str != null && !"".equals(str)) {
			return str.trim();
		} else {
			return null;
		}
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 */
	public static String removeBlank(String str) {// String
													// str="I am a, I am Hello ok, \n new line ffdsa!";
		// System.out.println("before:"+str);
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		String after = m.replaceAll("");
		// System.out.println("after:"+after);
		return after;
	}

	/**
	 * @param line
	 *            母体字符串
	 * @param specifiedStr
	 *            指定的字符串之前，只插入匹配到的第一个
	 * @param content
	 *            要插入的字符串
	 * @return
	 * @author yhy at 2012-11-05 在指定字符串之前插入一段String
	 */
	public static String insertBefore(String line, String specifiedStr,
			String content) {
		int n = line.indexOf(specifiedStr);// 获得字母b第一次出现时的下标
		if (n != -1) {
			StringBuffer sb = new StringBuffer(line);
			StringBuffer insertedSB = sb.insert(n, content);
			line = insertedSB.toString();
		}
		return line;
	}

	/**
	 * @param line
	 *            母体字符串
	 * @param specifiedStr
	 *            指定的字符串之后，只插入匹配到的第一个
	 * @param content
	 *            要插入的字符串
	 * @return
	 * @author yhy at 2012-11-05 在指定字符串之后插入一段String
	 */
	public static String insertAfter(String line, String specifiedStr,
			String content) {
		line = line != null ? line.toUpperCase() : null;
		int n = line.indexOf(specifiedStr.toUpperCase());// 获得字母b第一次出现时的下标
		if (n != -1) {
			StringBuffer sb = new StringBuffer(line);
			StringBuffer insertedSB = sb.insert(n + specifiedStr.length(),
					content);
			line = insertedSB.toString();
		}
		return line;
	}

	/**
	 * @param line
	 *            母体字符串
	 * @param tableName
	 *            表名字符串
	 * @param condition
	 *            插入在WHERE后的条件字符串 --- 需自带AND或OR等，即完整的条件关联
	 * @return
	 * @author yhy at 2012-11-06 给HQL拼接条件, 前提HQL语句已有WHERE 1=1
	 */
	public static String addWhereContidion(String line, String tableName,
			String condition) {
		line = isNotEmpty(line) ? line.trim() : null;
		tableName = isNotEmpty(tableName) ? tableName.trim() : null;
		condition = isNotEmpty(condition) ? condition.trim() : null;

		String whereStr = "WHERE 1=1";
		int i = line.toUpperCase().indexOf(whereStr);// 获得WHERE字符第一次出现时的下标
		if (i != -1) {
			StringBuffer sb = new StringBuffer(line);
			if (isNotEmpty(tableName)
					&& !line.substring(0, i).contains(tableName)) {// 如果该表名在HQL中WHERE之前不存在，则加在WHERE前面
				sb = sb.insert(i, ", " + tableName + " ");
				line = sb.toString();
				i = line.toUpperCase().indexOf(whereStr);
			}
			if (isNotEmpty(condition) && !line.substring(i).contains(condition)) {// 如果该条件在HQL中WHERE之后不存在，则加在WHERE后面
			// int j = line.toUpperCase().indexOf("AND");
			// if(isEmpty(line.substring(i, j))) {//如果WHERE和AND之间没货，中间就插入" 1=1 "
			//
			// }

				sb = sb.insert(i + whereStr.length(), " " + condition + " ");
			}
			line = sb.toString();
		}
		return line;
	}

	/**
	 * @param line
	 *            母体字符串
	 * @param targetTableName
	 *            要被Join的表名，即紧跟在其后Join之
	 * @param joinTableStr
	 *            Join的字符串，形如 left outer join Xxx x ON a.id=x.a_id
	 * @param condition
	 *            插入在WHERE后的条件字符串 --- 需自带AND或OR等，即完整的条件关联
	 * @return
	 * @author yhy at 2012-11-07 给HQL拼接Join的条件, 前提HQL语句已有WHERE 1=1
	 */
	public static String addJoinContidion(String line, String targetTableName,
			String joinTableStr, String condition) {
		line = isNotEmpty(line) ? line.trim() : null;
		targetTableName = isNotEmpty(targetTableName) ? targetTableName.trim()
				: null;
		joinTableStr = isNotEmpty(joinTableStr) ? joinTableStr.trim() : null;
		condition = isNotEmpty(condition) ? condition.trim() : null;

		String whereStr = "WHERE 1=1";
		int t = line.toUpperCase().indexOf(targetTableName.toUpperCase());// 获得targetTableName字符第一次出现时的下标
		int i = line.toUpperCase().indexOf(whereStr);// 获得WHERE字符第一次出现时的下标
		if (t != -1) {
			StringBuffer sb = new StringBuffer(line);
			if (isNotEmpty(targetTableName)
					&& !line.substring(0, t).contains(targetTableName)) {// 如果该表名在HQL中WHERE之前不存在，则加在WHERE前面
				sb = sb.insert(t + targetTableName.length(), " " + joinTableStr
						+ " ");
				line = sb.toString();
				i = line.toUpperCase().indexOf(whereStr);
			}
			if (isNotEmpty(condition) && !line.substring(i).contains(condition)) {// 如果该条件在HQL中WHERE之后不存在，则加在WHERE后面
				sb = sb.insert(i + whereStr.length(), " " + condition + " ");
			}
			line = sb.toString();
		}
		return line;
	}

	/**
	 * 将数组各项值连接成字串
	 */
	public static String join(String glue, Object[] pieces) {
		String rtn = null;
		if (pieces != null) {
			StringBuffer sb = new StringBuffer();
			if (pieces.length > 0)
				sb.append(pieces[0]);
			if (pieces.length > 1)
				for (int i = 1; i < pieces.length; i++) {
					sb.append(glue);
					sb.append(pieces[i]);
				}
			rtn = sb.toString();
		}
		return rtn;
	}

	/**
	 * 将数组list各项值连接成字串
	 */
	public static String join(String glue, List pieces) {
		String rtn = null;
		if (pieces != null) {
			int size = pieces.size();
			StringBuffer sb = new StringBuffer();
			if (size > 0)
				sb.append(pieces.get(0));
			if (size > 1)
				for (int i = 1; i < size; i++) {
					sb.append(glue);
					sb.append(pieces.get(i));
				}
			rtn = sb.toString();
		}
		return rtn;
	}

	/**
	 * 将一个数字id组成的字符串数组转成一个Long型的数组
	 */
	public static Long[] convertIdsToLongArr(String ids) {
		if (StringUtil.isEmpty(ids))
			return null;
		String[] idStrArr = ids.split(",");
		if (idStrArr == null || idStrArr.length < 0)
			return null;

		Long[] idLongArr = new Long[idStrArr.length];// 首先进行数组长度的定义
		for (int i = 0; i < idStrArr.length; i++) {// 把值赋予数组
			idLongArr[i] = NumberUtil.parseLongFromStr(idStrArr[i]);
		}

		return idLongArr;
	}

	public static String getIntSortByString(String str) {
		if (str == null)
			return "0";
		else {
			String[] temp = str.split(",");
			List tmpList = new ArrayList();
			int k = 0;

			if (temp != null)
				k = temp.length;
			for (int i = 0; i < k; i++) {
				tmpList.add(temp[i]);
			}

			k = tmpList.size();
			StringBuffer sb = new StringBuffer();
			sb.append("0");
			for (int i = 0; i < k; i++) {
				if (tmpList.get(i) != null && !tmpList.get(i).equals("")) {
					sb.append(",");
					sb.append(tmpList.get(i));
				}
			}
			return sb.toString();
		}
	}

	public static String getStringSortByString(String str) {
		if (str == null)
			return "''";
		else {
			String[] temp = str.split(",");
			List tmpList = new ArrayList();
			int k = 0;

			if (temp != null)
				k = temp.length;
			for (int i = 0; i < k; i++) {
				tmpList.add(temp[i]);
			}

			k = tmpList.size();
			StringBuffer sb = new StringBuffer();
			sb.append("''");
			for (int i = 0; i < k; i++) {
				if (tmpList.get(i) != null && !tmpList.get(i).equals("")) {
					sb.append(",'");
					sb.append(tmpList.get(i));
					sb.append("'");
				}
			}
			return sb.toString();
		}
	}

	/**
	 * 判断字串str是否在 strArr 中。如果在，那么返回 true。否则返回false
	 */
	public static boolean in_array(String str, String[] strArr) {
		boolean returnflag = false;
		if (strArr != null) {
			for (int j = 0; j < strArr.length; j++) {
				if (strArr[j].equals(str)) {
					returnflag = true;
					break;
				}
			}
		}
		return returnflag;
	}

	public static boolean in_array(int i, int[] intArr) {
		boolean returnflag = false;
		if (intArr != null) {
			for (int j = 0; j < intArr.length; j++) {
				if (intArr[j] == i) {
					returnflag = true;
					break;
				}
			}
		}
		return returnflag;
	}

	public static boolean in_array(int i, String str) {
		boolean returnflag = false;
		if (str != null || !str.equals("")) {
			String[] strArr = str.split(",");
			for (int j = 0; j < strArr.length; j++) {
				if (ObjectToInt(strArr[j]) == i) {
					returnflag = true;
					break;
				}
			}
		}
		return returnflag;
	}

	public static boolean in_array(String i, String str) {
		boolean returnflag = false;
		if (str != null || !str.equals("")) {
			String[] strArr = str.split(",");
			String strI = String.valueOf(i);
			for (int j = 0; j < strArr.length; j++) {
				if (String.valueOf(strArr[j]).equals(strI)) {
					returnflag = true;
					break;
				}
			}
		}
		return returnflag;
	}

	/**
	 * 判断字串 str 是否坐落在 strArr数组中
	 */
	public static boolean in_index_of_array(String str, String[] strArr) {
		boolean returnflag = false;
		if (strArr != null) {
			for (int j = 0; j < strArr.length; j++) {
				if (strArr[j] != null) {
					if (strArr[j].indexOf(str) > 0) {
						returnflag = true;
						break;
					}
				}
			}
		}
		return returnflag;
	}

	/**
	 * 判断字串 str 是否坐落在 strArr字符串中
	 */
	public static boolean in_index_of_str(Object strCheck, String strOldArr) {
		boolean returnflag = false;
		if (strOldArr != null) {
			// try
			{
				String str = String.valueOf(strCheck);
				String[] strArr = strOldArr.split(",");
				if (strArr != null) {

					for (int j = 0; j < strArr.length; j++) {
						if (strArr[j] != null) {
							if (strArr[j].equals(str)) {
								returnflag = true;
								break;
							}
						}
					}
				}
			}
			// catch (Exception e)
			{

			}
		}
		return returnflag;
	}

	/**
	 * 函数名：Fs_getRandom 函数功能：取得指定长度的随机数或随机字串。 输入参数说明： $length:表示返回的随机数的长度 $type:
	 * 0表示是整数随机字符串； 1表示大小写混合的字符和数字组合的随机字符串 2表示只有小写字母和数字混合的字符串。
	 * 函数返回说明：函数返回以逗号为间隔的父类id的字符串。 调用方法示例说明：$random=getrandom(8,0);返回8位随机数字串。
	 * 作者：xdju 时间：2003.9.10 版本：1.0
	 */
	public static String getRandom(int intLength, int intType) {
		String returnval = "";
		Random random = new Random();
		random.setSeed(Math.round(Math.random() * 1000000000));
		switch (intType) {
		case 0: // 整数
		{
			for (int i = 0; i < intLength; i++) {
				returnval += random.nextInt(10);
			}
		}
			break;
		case 1: // 字符串(大小写混合)和整数
		{
			for (int i = 0; i < intLength; i++) {
				int j = random.nextInt(1000000000);
				switch (j % 3) {
				case 0:
					returnval += (char) (j % 10 + 48);
					break;
				case 1:
					returnval += (char) (j % 26 + 65);
					break;
				case 2:
					returnval += (char) (j % 26 + 97);
					break;
				}
			}
		}
			break;
		case 2: // 字符串(小写)和整数
		{
			for (int i = 0; i < intLength; i++) {
				int j = random.nextInt(1000000000);
				switch (j % 2) {
				case 0:
					returnval += (char) (j % 10 + 48);
					break;
				case 1:
					returnval += (char) (j % 26 + 97);
					break;
				}
			}
		}
			break;
		case 3: // 字符串(大写)和整数
		{
			for (int i = 0; i < intLength; i++) {
				int j = random.nextInt(1000000000);
				switch (j % 2) {
				case 0:
					returnval += (char) (j % 10 + 48);
					break;
				case 1:
					returnval += (char) (j % 26 + 65);
					break;
				}
			}
		}
			break;
		case 4: // 字符串(大写)
		{
			for (int i = 0; i < intLength; i++) {
				int j = random.nextInt(1000000000);
				returnval += (char) (j % 26 + 65);
			}
		}
			break;
		case 5: // 字符串(小写)
		{
			for (int i = 0; i < intLength; i++) {
				int j = random.nextInt(1000000000);
				returnval += (char) (j % 26 + 97);
			}
		}
			break;
		}// //switch(type)
		return returnval;
	}

	/**
	 * 替代linde中的oldString为newString
	 *
	 * @param newString
	 *            the String that will replace all instances of oldString
	 * @return a String will all instances of oldString replaced by newString
	 * @参数 line 需要做替代的字符串
	 * @参数 oldString the String that should be replaced by newString
	 */
	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 判断是否为整数
	 */
	public static boolean isInteger(String input) {
		boolean result = false;
		try {
			Integer.parseInt(input);
			result = true;
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}

	/**
	 * 判断给定字符串是否全部由数字组成
	 */
	public static boolean isMadeUpOfNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 由字符串前后用逗号","隔开的字符串得到整形数组
	 */
	public static int[] getIntAryByString(String input) {
		int[] result = new int[0];

		if (input != null && !input.equals("") && !input.equals(",")) {

			String[] temp = input.split(",");
			int k = 0;
			ArrayList tmpList = new ArrayList();
			if (temp != null)
				k = temp.length;
			for (int i = 0; i < k; i++) {
				if (isInteger(temp[i])) {
					tmpList.add(temp[i]);
				}
			}

			if (tmpList != null) {
				int len = tmpList.size();
				result = new int[len];
				for (int i = 0; i < len; i++) {
					result[i] = Integer.parseInt((String) tmpList.get(i));
				}
			}
		}
		return result;
	}

	/**
	 * 得到部分字符串
	 */
	public static String substring(String sourceStr, int start, int end) {
		String result = "";
		int strLen = 0;
		if (sourceStr != null) {
			strLen = sourceStr.length();
		}
		start = (start > strLen) ? strLen : start;
		end = (strLen > end) ? end : strLen;
		if (sourceStr != null) {
			result = sourceStr.substring(start, end);
		}
		return result;
	}

	/**
	 * 得到部分字符串
	 */
	public static int substring(int sourceNum, int start, int end) {
		String result = "";
		int strLen = 0;
		String sourceStr = sourceNum + "";

		if (sourceStr != null) {
			strLen = sourceStr.length();
		}

		start = (start > strLen) ? strLen : start;
		end = (strLen > end) ? end : strLen;
		if (sourceStr != null) {
			result = sourceStr.substring(start, end);
		}
		return (new Integer(result)).intValue();
	}

	public static int ObjectToInt(Object str) {
		try {
			String strTmp = "";
			if (str != null)
				strTmp = String.valueOf(str);
			return Integer.parseInt(strTmp);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static String ObjectToString(Object str) {
		try {
			String strTmp = "";
			if (str != null)
				strTmp = String.valueOf(str);
			return strTmp;
		} catch (Exception e) {
			return "";
		}
	}

	public static float ObjectToFloat(Object str) {
		try {
			String strTmp = "";
			if (str != null)
				strTmp = String.valueOf(str);
			return Float.parseFloat(strTmp);
		} catch (Exception e) {
			return 0;
		}
	}

	public static double ObjectToDouble(Object str) {
		try {
			String strTmp = "";
			if (str != null)
				strTmp = String.valueOf(str);
			return Double.parseDouble(strTmp);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 得到固定位数的值如1变为01成两位
	 */
	public static String getZeroFill(String str, int intLen) {
		String returnVal;
		str = str.trim();
		returnVal = str;
		int thisLength = str.length();
		for (int j = thisLength; j < intLen; j++)
			returnVal = "0" + returnVal;

		return returnVal;
	}

	/**
	 * 得到指定编码格式的字符长度
	 */
	public static int getStrLength(String txt, String charset) {
		try {
			return txt.getBytes(charset).length;
		} catch (UnsupportedEncodingException ex) {
			return txt.length();
		}
	}

	/**
	 * 得到字符串的
	 */
	public static String encodeURL(String url, String charset) {
		if (url != null && url.length() > 0) {
			try {
				return URLEncoder.encode(url, charset);
			} catch (UnsupportedEncodingException ex) {
				return url;
			}
		}
		return url;
	}

	public static String replaceSpecChar(String str) {
		str = replace(str, "\\", "\\\\");
		str = replace(str, "\'", "\\'");
		return str;
	}

	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		else if (str.trim().equals(""))
			return true;
		else if ("null".equals(str))
			return true;
		return false;
	}

	public static boolean isNotEmpty(String str) {
		if (str == null)
			return false;
		else if (str.trim().equals(""))
			return false;
		else if ("null".equals(str))
			return false;
		return true;
	}

	public static Object htmlSpecChars(Object str) {
		Object rtn = str;
		if ((rtn != null)
				&& (rtn.getClass().getName().equals(String.class.getName()))) {
			String strTmp = (String) str;
			if (!strTmp.equals("")) {
				strTmp = strTmp.replaceAll("&", "&amp;");
				strTmp = strTmp.replaceAll("\"", "&quot;");
				strTmp = strTmp.replaceAll("'", "&#039;");
				strTmp = strTmp.replaceAll("<", "&lt;");
				strTmp = strTmp.replaceAll(">", "&gt;");
				rtn = strTmp;
			}
		}
		return rtn;
	}

	public static Object nl2br(Object str) {
		Object rtn = str;
		if ((rtn != null)
				&& (rtn.getClass().getName().equals(String.class.getName()))) {
			String strTmp = (String) str;
			if (!strTmp.equals("")) {
				strTmp = strTmp.replaceAll("\n", "<br/>");
				strTmp = strTmp.replaceAll(" ", "&nbsp;");
				rtn = strTmp;
			}
		}
		return rtn;
	}

	public static String replaceStr(String str, String separator,
			String replacestr, int maxLine, String lastStr) {
		if (isEmpty(str))
			return "";
		String[] strList = str.split(separator);
		StringBuffer sb = new StringBuffer();
		int blankLine = 0;
		for (int i = 0; i < strList.length; i++) {
			if (maxLine != 0 && i - blankLine > maxLine - 1) {
				sb.append(lastStr);
				break;
			}
			if (!isEmpty(strList[i])) {
				sb.append(strList[i]);
				if (i + 1 < strList.length)
					sb.append(replacestr);
			} else {
				blankLine++;
			}
		}
		return sb.toString();
	}

	/**
	 * @param inputList
	 *            输入的List值
	 * @param keyIn
	 *            输入的键值数据
	 * @param finedValue
	 *            输入的查找对应值数据，个数与keyIn的个数同
	 * @param keyOut
	 *            返回Map中的键值对
	 * @return 由输入的条件等到一组满足的条件的Map
	 * @author 2005-3-26 12:03:07 edusaj
	 */
	public static Map getFindMapFromListByKey(List inputList, String[] keyIn,
			String[] finedValue, String[] keyOut) {
		Map rtnMap = null;
		if (inputList != null) {
			int k = inputList.size();
			Map tempMap = null;
			for (int i = 0; i < k; i++) {
				tempMap = (HashMap) inputList.get(i);
				if (tempMap != null) {
					boolean existsFind = false;
					boolean checkExistsFind = true;

					/** 输入的参数与会值都满足 */
					if (keyIn != null && finedValue != null
							&& keyIn.length == finedValue.length) {
						int m = keyIn.length;
						for (int n = 0; n < m; n++) {
							checkExistsFind = checkExistsFind
									&& tempMap.containsKey(keyIn[n])
									&& (tempMap.get(keyIn[n]).toString())
											.equals(finedValue[n]);
						}
					}

					existsFind = checkExistsFind;

					if (existsFind) {
						rtnMap = new HashMap();
						if (keyOut != null) {
							int s = keyOut.length;
							rtnMap.put(keyIn, finedValue);
							for (int j = 0; j < s; j++) {
								rtnMap.put(keyOut[j], tempMap.get(keyOut[j]));
							}
						}
						/** 如果存在，则退出 */
						break;
					}

				}
			}
		}
		return rtnMap;
	}

	/**
	 * @param inputList
	 *            输入的List值
	 * @param keyIn
	 *            输入的键值
	 * @return 返回由键值得到的值以逗号","隔开的字符串
	 * @author 2005-3-26 12:03:43 edusaj
	 */
	public static String getSplitOutByList(List inputList, Object keyIn) {
		String rtn = "0";
		if (inputList != null) {
			StringBuffer out = new StringBuffer();

			int k = inputList.size();
			Map tempMap = null;
			for (int i = 0; i < k; i++) {
				tempMap = (HashMap) inputList.get(i);
				if (tempMap != null && tempMap.containsKey(keyIn)) {
					out.append("," + tempMap.get(keyIn));
				}
			}

			if (out != null) {
				rtn += out.toString();
			}
		}
		return rtn;
	}

	public static int getTotalCount(Object str) {
		int intRtn = 0;
		if (str != null) {
			try {
				String strTmp = String.valueOf(str);
				char[] tempChar = strTmp.toCharArray();
				for (int kk = 0; (kk < tempChar.length); kk++) {
					intRtn += String.valueOf(tempChar[kk]).getBytes().length;
				}
			} catch (Exception e) {

			}
		}
		return intRtn;
	}

	/**
	 * 比较2个字符串 strSource == strCompare return 0 strSource > strCompare return >0
	 * strSource < strCompare return <0
	 */
	public static int getCompareTo(Object strSource, Object strCompare) {
		try {
			String strTmp1 = "", strTmp2 = "";
			if (strSource != null)
				strTmp1 = String.valueOf(strSource);
			if (strCompare != null)
				strTmp2 = String.valueOf(strCompare);
			return strTmp1.compareTo(strTmp2);
			// return Integer.parseInt(strTmp);
		} catch (NumberFormatException e) {
			return 2;
		}
	}

	public static void main(String[] args) {
		System.out.println(getIntAryByString("1,,,").length);
		// System.out.println(in_index_of_str("11","1,2,3,4"));
	}
}