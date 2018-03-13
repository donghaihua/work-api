/*
package com.tenmaker.backupwd.components;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import net.coobird.thumbnailator.Thumbnails;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Qiniu {
	public static String getUpToken() throws AuthException, JSONException {
		Config.ACCESS_KEY = "d6xO5kaPpzOdxtBJB5rQVzc7IVTLdIMtD3bOsSnI";
        Config.SECRET_KEY = "z4jHXEROLKHxBWe8-GJRjgQI69ZCkUxEm7BOCkYT";
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        String bucketName = "uhaou";
        PutPolicy putPolicy = new PutPolicy(bucketName);
        return putPolicy.token(mac);
	}
	
	*/
/**
	 * 先把原文件传到后台，在后台磁盘内经过压缩后，再上传到七牛
	 * @param file
	 * @param key
	 * @param file_to_path 压缩后存放的位置
	 * @throws AuthException
	 * @throws JSONException
	 * @throws IOException
	 *//*

	public static void uploadFile(File file, String key, String file_to_path) throws AuthException, JSONException, IOException {
        File filePath = new File(file_to_path);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }
        String uptoken = Qiniu.getUpToken();
        PutExtra extra = new PutExtra();
        String file_path = file_to_path + key + ".jpg"; 
        Thumbnails.of(file).scale(1f).outputQuality(0.4f).outputFormat("jpg").toFile(file_path);
        File file_thumb = new File(file_path);
        PutRet ret = IoApi.putFile(uptoken, key, file_thumb, extra);
        if(file_thumb.exists()) {
        	file_thumb.delete();
        }
        System.out.println("KEY:" + ret.getKey());
        System.out.println("RESPONSE:" + ret.getResponse());
        System.out.println("STATUS_CODE:" + ret.getStatusCode());
	}

    */
/**
     * 直接上传文件（流）
     * @param file
     * @param key
     * @param file_to_path
     * @throws AuthException
     * @throws JSONException
     * @throws IOException
     *//*

    public static void uploadFile(MultipartFile file, String key, String file_to_path) throws AuthException, JSONException, IOException {
        File filePath = new File(file_to_path);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }
        String uptoken = Qiniu.getUpToken();
        PutExtra extra = new PutExtra();
        PutRet ret = IoApi.Put(uptoken, key, file.getInputStream(), extra);
        System.out.println("KEY:" + ret.getKey());
        System.out.println("RESPONSE:" + ret.getResponse());
        System.out.println("STATUS_CODE:" + ret.getStatusCode());
    }
}
*/
