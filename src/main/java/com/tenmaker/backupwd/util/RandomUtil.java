package com.tenmaker.backupwd.util;

import java.security.SecureRandom;

public class RandomUtil {
	/**
	 * 每位允许的字符
	 */
	private static final String POSSIBLE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String POSSIBLE_NUMBERS = "659472831";
	private static final String POSSIBLE_CHARACTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 生产一个指定长度的随机字符串
	 *
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS
					.length())));
		}
		return sb.toString();
	}

	/**
	 * 9位毫秒+5位随机数
	 *
	 * @param length 默认是5
	 * @return
	 */
	public static String generateOrderNo(int length) {
		StringBuilder sb = new StringBuilder(length);
		SecureRandom random = new SecureRandom();
		String time = new Long(System.currentTimeMillis()).toString();
		sb.append(time.substring(4));
		for (int i = 0; i < length; i++) {
			sb.insert(0, POSSIBLE_NUMBERS.charAt(random.nextInt(POSSIBLE_NUMBERS.length())));
		}
		return sb.toString();
	}

	public static String generateOrderNo() {
		StringBuilder sb = new StringBuilder(14);
		SecureRandom random = new SecureRandom();
		String time = new Long(System.currentTimeMillis()).toString();
		sb.append(time.substring(4));
		for (int i = 0; i < 5; i++) {
			sb.insert(0, (POSSIBLE_NUMBERS.charAt(random.nextInt(POSSIBLE_NUMBERS.length()))));
		}
		return sb.toString();
	}

	public static String generateSpreadCode() {
		StringBuilder sb = new StringBuilder(10);
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < 10; i++) {
			sb.append(POSSIBLE_CHARACTER.charAt(random.nextInt(POSSIBLE_CHARACTER.length())));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(generateOrderNo());
	}
}
