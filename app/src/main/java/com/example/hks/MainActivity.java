package com.example.hks;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hks.MainActivity;


import com.example.hks.R;



import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {
  Button b1;
  
  EditText e1;
  EditText e2;
  JSONObject strjson;
  JSONObject jsonObject;
  JSONParser js;
  String strresponce;
	
ArrayList<HashMap<String, String>> data;
StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	

	ProgressDialog pd;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView t1=(TextView)findViewById(R.id.reg);
		t1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(MainActivity.this, RegisterActivity.class);
				startActivity(i);
			}
		});
		
		b1=(Button)findViewById(R.id.btnlogin);
		
		e1=(EditText)findViewById(R.id.etxtemail);
		e2=(EditText)findViewById(R.id.etxtpassword);
		
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StrictMode.setThreadPolicy(policy); 
	         	new getcar().execute();
				
	         	
				
			}
		});
   
		
		
				
		}
	
	class getcar extends AsyncTask<Void, Void, Void>{
		PreferenceHelper pf;
		String sname;
		String spass;
		String rm_name;
		String cat ;
		JSONObject jobj;
		

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pd = new ProgressDialog(MainActivity.this);
			pd.setMessage("fetching data...");
			pd.setIndeterminate(false);
			pd.setCancelable(true);
			pd.show();
		} 

		@SuppressWarnings("static-access")
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//BasicNameValuePair nm = new BasicNameValuePair("user_id", pf.getPreferences(carlist.this, "user_id"));
			String email = e1.getText().toString();
			String password = e2.getText().toString();
			
			BasicNameValuePair nm = new BasicNameValuePair("um_email", email);
			BasicNameValuePair nm1 = new BasicNameValuePair("um_password", password);
			
			ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(nm);
			param.add(nm1);
			
			js = new JSONParser();
			data = new ArrayList<HashMap<String,String>>();
			
			
			 strresponce = js.getJSONFromUrl("http://192.168.1.232/hks_admin/production/login.php", param);
			Log.d("database", "product comes"+ strresponce);
			JSONObject jobjResponse;  
			try {
				jobjResponse = new JSONObject(strresponce);
				JSONArray jArrayProdList = new JSONArray();
				jArrayProdList = jobjResponse.getJSONArray("user");
				for (int i = 0; i < jArrayProdList.length(); i++) {
					HashMap<String,String> map = new HashMap<String, String>();
					jobj = jArrayProdList.getJSONObject(i);
					//cat = jobj.getString("catag");
					
//					rm_id = jobj.getString("cust_id");
					rm_name = jobj.getString("um_email");
//					
//					
//					map.put( "rm_id", rm_id );
					map.put( "rm_name", rm_name );
					
					
					
					data.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			/*if(pd.isShowing()){
				pd.dismiss();
				
			}
			*/

			 if (pd.isShowing()) {
	                pd.dismiss();
	              //  Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();
	                if (strresponce.contains("[]")){

	                	
	                	Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_LONG).show();
	                }else {
	                    PreferenceHelper.SavePreferences(MainActivity.this,"rm_name",rm_name);
	                   
//	                    PreferenceHelper.SavePreferences(Login.this,"rm_id",rm_id);

	                   


	                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
	                    startActivity(i);
	                }
		
			 }
		}
	

	
}
	}
 
		
			
			 
		
 

