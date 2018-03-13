package com.tenmaker.backupwd.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	/**
	 * 使用Base64加密算法加密字符串 return
	 */
	public static String encodeStr(String str) {
		byte[] b = str.getBytes();
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	/**
	 * 使用Base64加密 return
	 */
	public static String decodeStr(String encodeStr) {
		byte[] b = encodeStr.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b);
		return s;
	}
}
