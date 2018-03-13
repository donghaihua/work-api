package com.tenmaker.backupwd.util;

import java.io.File;

public class FileUtil {
	public static boolean move(String oldFilePath, String newPath,
			String fileName, boolean isDelete) {
		File oldFile = new File(oldFilePath);// 带文件名称
		File newFile = new File(newPath);
		if (oldFile.isDirectory()) {
			return false;
		}
		if (oldFile.exists()) {
			if (newFile.exists() == false) {
				newFile.mkdirs();
			}
			newFile = new File(newPath + fileName);
			oldFile.renameTo(newFile);
			if (isDelete) {
				oldFile.delete();
			}
			return true;
		}
		return false;
	}
}
