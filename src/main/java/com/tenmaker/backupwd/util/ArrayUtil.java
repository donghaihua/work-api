package com.tenmaker.backupwd.util;

public class ArrayUtil {
	public static Double[] maoPao(Object[] volArray) {
		Double[] tempArray = new Double[volArray.length];
		for (int i = volArray.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				Double vol_1 = NumberUtil.parseDoubleFromStr(volArray[j]
						.toString().trim());
				Double vol_2 = NumberUtil.parseDoubleFromStr(volArray[j + 1]
						.toString().trim());
				Double temp = 0D;
				if (vol_1 > vol_2) {
					temp = vol_2;
					tempArray[j] = temp;
					tempArray[j + 1] = vol_1;
				} else {
					tempArray[j] = vol_1;
					tempArray[j + 1] = vol_2;
				}
			}
		}
		return tempArray;
	}
}
