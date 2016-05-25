package com.example.hks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;

import android.util.Log;

public class Json {
	
	InputStream is=null;
	StringBuilder sb;
	String line=null;
	String strResponse;
	static String json = "";
	 
	
	public String insertdata(String url,List<NameValuePair> params) {
		
		try {
		HttpClient httpclient=new DefaultHttpClient();
		HttpPost httpPost=new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(params));
		
		HttpResponse httpResponse=httpclient.execute(httpPost);
		HttpEntity httpEntity=httpResponse.getEntity();
		is=httpEntity.getContent();
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"), 8);
			
			StringBuilder sb=new StringBuilder();
			String line=null;
			
			while((line=reader.readLine())!=null){
				sb.append(line+"\n");
				
				
			}
			is.close();
			
			strResponse=sb.toString();
			Log.e("JSON", json);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Log.e("buffered error","cannot convert"+ e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strResponse;
		
	}
	
	
	
	
	 public String getJSONFromUrl(String url) {

			
			try {
				
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				
				 strResponse =sb.toString();
				Log.e("JSON", json);
			} catch (Exception e) {
				Log.e("Buffer Error", "Error converting result " + e.toString());
			}


			return strResponse;

		}
}