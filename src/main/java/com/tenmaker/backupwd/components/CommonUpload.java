/*
package com.tenmaker.backupwd.components;

import com.qiniu.api.auth.AuthException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUpload {
	private static Logger log = LoggerFactory.getLogger(CommonUpload.class);
	*/
/**
	 * 
	 * @param mfile
	 * @param dirName
	 * @param filePath 这个是用来压缩图片后的图片存储路径，然后把压缩后的图片传到七牛上
	 * @return
	 *//*

	public static Map<String, Object> kindeditor_UploadImage(MultipartFile mfile, String dirName, String filePath) {
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		// extMap.put("flash", "swf,flv");
		// extMap.put("media",
		// "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		// extMap.put("file",
		// "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		//long maxSize = 5000000;

		if (mfile == null) {
			return getError("请选择要上传的图片");
		}

		String fileName = mfile.getOriginalFilename();
		// 检查文件大小
		*/
/*if (mfile.getSize() > maxSize) {
			return getError("上传文件大小超过限制");
		}*//*

		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		List<String> extList = Arrays.asList(extMap.get(dirName).split(","));
		if (!extList.contains(fileExt)) {
			return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式");
		}

		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确");
		}

		*/
/*//*
/ 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}*//*


		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	        String key = df.format(new Date()) + "_" + new Random().nextInt(1000);
			File file = new File(key);
			mfile.transferTo(file);
			Qiniu.uploadFile(file, key, filePath);
			// 上传成功
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("error", 0);
			msg.put("url", CommonConst.QN_IMG_SITE + key);
			return msg;
		} catch (AuthException e) {
			log.error("kindeditor上传文件失败, AuthException", e);
			return getError("上传文件失败");
		} catch (JSONException e) {
			log.error("kindeditor上传文件失败, JSONException", e);
			return getError("上传文件失败");
		} catch (Exception e) {
			log.error("kindeditor上传文件失败", e);
			return getError("上传文件失败");
		}
	}

	private static Map<String, Object> getError(String message) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("error", 1);
		msg.put("message", message);
		return msg;
	}

	public static Map<String, Object> uploadImageToTempPath(
            MultipartFile multipartFile, String filePath)
			throws IOException, Exception {

		*/
/*File filePath = new File(fullTempPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}*//*


		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 判断图片大小是否大于2M
		if (multipartFile.getSize() > (1024 * 2000)) {
			resultMap.put("success", 1);
			resultMap.put("message", "图片不能大于2M");
			resultMap.put("error", CommonConst.TOO_BIG);
			return resultMap;
		}

		// 保存相对路径到数据库 图片写入服务器
		if (multipartFile != null && !multipartFile.isEmpty()) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	        String key = df.format(new Date()) + "_" + new Random().nextInt(1000);
			File file = new File(key);
			multipartFile.transferTo(file);
			Qiniu.uploadFile(file, key, filePath);
			resultMap.put("success", 0);
			resultMap.put("imgSrc", CommonConst.QN_IMG_SITE + key);
			
			*/
/*//*
/ 获取图片的文件名
			String fileName = multipartFile.getOriginalFilename();
			// 获取图片的扩展名
			String extensionName = fileName
					.substring(fileName.lastIndexOf(".") + 1);
			// JPG,GIF,PNG,JPEG,BMP
			String extName = "JPG,GIF,PNG,JPEG,BMP";

			if (extName.indexOf(extensionName.toUpperCase()) < 0) {
				resultMap.put("success", 2);
				resultMap.put("message", "扩展名不符合");
				return resultMap;
			}

			// 新的图片文件名 = 获取时间戳+"."图片扩展名
			String newFileName = String.valueOf(System.currentTimeMillis());
			String jsPath = tempPath + newFileName;
			String realPath = fullTempPath + newFileName;
			 构建文件目录 
			File newFile = new File(realPath);
			if (newFile.exists()) {
				newFile.delete();
			}
			
			 * FileOutputStream out = new FileOutputStream(realPath); // 写入文件
			 * out.write(file.getBytes()); out.flush(); out.close();
			 
			System.out.println(new String(multipartFile.getOriginalFilename()
					.getBytes("ISO-8859-1"), "UTF-8"));
			Thumbnails.of(multipartFile.getInputStream()).scale(1f)
					.outputQuality(0.4f).outputFormat("jpg").toFile(realPath + ".jpg");
			
			jsPath += ".jpg";
			resultMap.put("success", 0);
			resultMap.put("imgSrc", jsPath);*//*

		} else {
			resultMap.put("success", 3);
			resultMap.put("message", "请选择要上传的图片");
		}
		return resultMap;
	}

}
*/
