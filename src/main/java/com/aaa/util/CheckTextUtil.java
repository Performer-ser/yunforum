package com.aaa.util;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckTextUtil {
	 private static final String URL = "https://aip.baidubce.com/rest/2.0/antispam/v2/spam";
	 
	 public static JSONObject check(String content){
		 	//获取access_token
	        String access_token = getAuth();
	        Map<String,String> parameters = new HashMap<String,String>();
	        //添加调用参数
	        parameters.put("access_token",access_token);
	        parameters.put("command_no", "500071");
	        parameters.put("content",content);
	        //调用文本审核接口
	        String result = HttpUtil.doPost(URL, parameters);
	        JSONObject jsonobject=JSONObject.fromObject(result);
	        //JSONObject json = jsonobject.getJSONObject(result);
	        return jsonobject;
	    }

	 private static String getAuth() {
	        // 官网获取的 API Key 更新为你注册的
	        String clientId = "gK4DadWGmu3vFHHgpnFuoCFq";
	        // 官网获取的 Secret Key 更新为你注册的
	        String clientSecret = "j7GZfbBxBiNMzymjI9QUzkDqVndwniGb";
	        return getAuth(clientId, clientSecret);
	 }

	private static String getAuth(String clientId, String clientSecret) {
		 String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
	        String getAccessTokenUrl = authHost
	                // 1. grant_type为固定参数
	                + "grant_type=client_credentials"
	                // 2. 官网获取的 API Key
	                + "&client_id=" + clientId
	                // 3. 官网获取的 Secret Key
	                + "&client_secret=" + clientSecret;
	        try {
	            URL realUrl = new URL(getAccessTokenUrl);
	            // 打开和URL之间的连接
	            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.err.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String result = "";
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	            /**
	             * 返回结果示例
	             */
	            System.err.println("result:" + result);
	            //JSONObject jsonObject = new JSONObject();
	            JSONObject jsonObject = JSONObject.fromObject(result);
	            String access_token = jsonObject.getString("access_token");
	            return access_token;
	        } catch (Exception e) {
	            System.err.printf("获取token失败！");
	            e.printStackTrace(System.err);
	        }
	        return null;
	}

}
