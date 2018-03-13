/*
package com.tenmaker.backupwd.util;

import com.nutshell.uhaou.util.kuaidi.Kuaidi;
import com.nutshell.uhaou.util.kuaidi.KuaidiData;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class KuaidiUtil {

	public static String getDelivery(String kd_com, String kd_no) {
		if (StringUtil.isEmpty(kd_no)) {
			return null;
		}
		try {
			// 64a4812c65bf1f9f
			// http://api.kuaidi100.com/api?id=[]&com=[]&nu=[]&valicode=[]&show=[0|1|2|3]&muti=[0|1]&order=[desc|asc]
			// "http://www.kuaidi100.com/query?type="+kd_com+"&postid="+kd_no
			// http://www.kuaidi100.com/applyurl?key=[]&com=[]&nu=[]
			// 305963788759
			URL url = new URL(
					"http://www.kuaidi100.com/applyurl?key=64a4812c65bf1f9f&com="
							+ kd_com + "&nu=" + kd_no);
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			*/
/*
			 * if (type == null) { type = con.getContentType(); }
			 * 
			 * if (type == null || type.trim().length() == 0 ||
			 * type.trim().indexOf("text/html") < 0){ return null; }
			 * 
			 * if (type.indexOf("charset=") > 0) { charSet =
			 * type.substring(type.indexOf("charset=") + 8); }
			 *//*


			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					String newContent = new String(b, 0, numRead, "UTF-8");
					content += newContent;
				}
			}
			*/
/*
			 * System.out.println(content); ObjectMapper om = new
			 * ObjectMapper(); Kuaidi kd = om.readValue(content, Kuaidi.class);
			 *//*

			*/
/*
			 * for (int i = 0; i < kd.getData().size(); i++) { KuaidiData kdata=
			 * kd.getData().get(i); System.out.println(kdata.getContext()); }
			 *//*

			urlStream.close();
			return content;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Kuaidi getEntityDelivery(String kd_com, String kd_no) {
		if (StringUtil.isEmpty(kd_no)) {
			return null;
		}
		try {
			// 64a4812c65bf1f9f
			// http://api.kuaidi100.com/api?id=[]&com=[]&nu=[]&valicode=[]&show=[0|1|2|3]&muti=[0|1]&order=[desc|asc]
			// "http://www.kuaidi100.com/query?type="+kd_com+"&postid="+kd_no
			// http://www.kuaidi100.com/applyurl?key=[]&com=[]&nu=[]
			// 305963788759
			URL url = new URL("http://www.kuaidi100.com/query?type=" + kd_com
					+ "&postid=" + kd_no);
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null) {
				type = con.getContentType();
			}

			if (type == null || type.trim().length() == 0
					|| type.trim().indexOf("text/html") < 0) {
				return null;
			}

			if (type.indexOf("charset=") > 0) {
				charSet = type.substring(type.indexOf("charset=") + 8);
			}

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					String newContent = new String(b, 0, numRead, "UTF-8");
					content += newContent;
				}
			}
			System.out.println(content);
			ObjectMapper om = new ObjectMapper();
			Kuaidi kd = om.readValue(content, Kuaidi.class);
			for (int i = 0; i < kd.getData().size(); i++) {
				KuaidiData kdata = kd.getData().get(i);
				System.out.println(kdata.getContext());
			}
			return kd;
			*/
/*
			 * urlStream.close(); return content;
			 *//*

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// 688901713627
		// 305963788759
		String com = "shunfeng";
		String nu = "589860534900";

		//KuaidiUtil.getEntityDelivery(com, nu);
		System.out.println(KuaidiUtil.getDelivery(com, nu));
	}
}
*/
