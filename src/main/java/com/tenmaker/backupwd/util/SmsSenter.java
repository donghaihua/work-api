package com.tenmaker.backupwd.util;

import com.tenmaker.backupwd.components.CommonConst;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SuppressWarnings("deprecation")
public class SmsSenter {

	public HttpURLConnection httpURLConnection;
	public static Logger logger = LoggerFactory.getLogger(SmsSenter.class.getName());

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		sendCaptcha("13675175526", "221104");
	}

	public static void sendSms(String telephone, String content)
			throws ClientProtocolException, IOException {
		String url = "http://www.smsadmin.cn/smsmarketing/wwwroot/api/post_send/";
		String uid = CommonConst.SMS_NAME;
		String pwd = CommonConst.SMS_PWD;

		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("uid", uid));
		nvps.add(new BasicNameValuePair("pwd", pwd));
		nvps.add(new BasicNameValuePair("mobile", telephone));
		nvps.add(new BasicNameValuePair("msg", content));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "GB2312"));
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httpPost);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		System.out.println(response.getStatusLine() + "发送时间："
				+ sdf.format(Calendar.getInstance().getTime()));
	}

	public static void sendCaptcha(String mobile, String captcha)
			throws ClientProtocolException, IOException {
		String url = "http://www.smsadmin.cn/smsmarketing/wwwroot/api/post_send/";
		String uid = CommonConst.SMS_NAME;
		String pwd = CommonConst.SMS_PWD;
		String msg = "您的验证码:" + captcha + CommonConst.BI_SIGN;

		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("uid", uid));
		nvps.add(new BasicNameValuePair("pwd", pwd));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("msg", msg));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "GB2312"));
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httpPost);

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			logger.info(" SmsSernter 发送手机验证码 ，手机号{},验证码：{} 其它："+ EntityUtils.toString(entity, "GB2312") + "发送时间："
					+ sdf.format(Calendar.getInstance().getTime()),mobile, captcha );
		}

		/*
		 * logger.info(response.getStatusLine());
		 * System.out.println(response.getStatusLine());
		 * 
		 * Header[] headers = response.getAllHeaders(); for (int i = 0; i <
		 * headers.length; i++){ System.out.println(headers[i].getName() + ":" +
		 * headers[i].getValue()); logger.info(headers[i].getName() + ":" +
		 * headers[i].getValue()); }
		 * 
		 * HttpEntity entity = response.getEntity(); if (entity != null) {
		 * logger.info(EntityUtils.toString(entity, "GB2312"));
		 * System.out.println(EntityUtils.toString(entity, "GB2312")); }
		 */
	}
}
