package com.example.hks;



import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class ListviewActivity extends ActionBarActivity {
	JSONParser js;
	ProgressDialog pd;
    EditText etSearch;
	ListView l1;
	ArrayList<HashMap<String, String>> data;
	//ListView l1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		 l1 = (ListView)findViewById(R.id.listnew);
		    new getfaculty().execute();
		
		
	}

	public class getfaculty extends AsyncTask<Void, Void, Void>{
		String imagepath;
		Intent i = getIntent();
		String s = i.getStringExtra("my");
		
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pd = new ProgressDialog(ListviewActivity.this);
			pd.setMessage("fetching data...");
			pd.setIndeterminate(false);
			pd.setCancelable(true);
			pd.show();
		}

		@Override 
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//BasicNameValuePair nm = new BasicNameValuePair("product_catagory_id", "1");
			//ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
			//param.add(nm);
			js = new JSONParser(); 
			data = new ArrayList<HashMap<String,String>>();
			String strresponce = js.getJSONFromUrl("http://192.168.1.232/hks_admin/production/productdata.php?cate_id="+s);
			Log.d("database", "product comes"+ strresponce);
			//String strresponce = js.getJSONFromUrl("http://10.0.2.2/gentella-master/sc_table.php?sc_id="+s);
			//Log.d("database", "product comes"+ strresponce);
			JSONObject jobjResponse;
			try {
				jobjResponse = new JSONObject(strresponce);
				JSONArray jArrayProdList = new JSONArray();
				jArrayProdList = jobjResponse.getJSONArray("catagory");
				for (int i = 0; i < jArrayProdList.length(); i++) {
					HashMap<String,String> map = new HashMap<String, String>();
					JSONObject jobj = jArrayProdList.getJSONObject(i);   
					
					
					imagepath = jobj.getString("imagepath");
					String id = jobj.getString("subcat_id");
					String pid = jobj.getString("subcat_name");
//					String pcode = jobj.getString("product_code");
//					String pname = jobj.getString("product_nm");
//					String pprice = jobj.getString("selling_price");
//					String pdesc1 = jobj.getString("desc_1");
//					String pdesc2 = jobj.getString("desc_2");
//					String poffers = jobj.getString("offers");
				
				
					
					
					
					map.put("pid", pid);
					map.put("id", id);
					map.put("imagepath", imagepath);
//					map.put( "pcode", pcode );
//					map.put( "pname", pname );
//					map.put( "pprice", pprice );
//					map.put( "pdesc1", pdesc1 );
//					map.put( "pdesc2", pdesc2 );
//					map.put( "poffers", poffers );
//					
					
					
					
					
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
			if(pd.isShowing()){
				pd.dismiss();
				
			}
			ListSingleActivity adapter = new ListSingleActivity(ListviewActivity.this,data );
		l1.setAdapter(adapter);
		
		}
		
	}
	
	 
}
