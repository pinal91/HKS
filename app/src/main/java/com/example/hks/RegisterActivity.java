package com.example.hks;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;






import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity{
  Button btnsubmit;
  EditText e1,e2,e3,e4,e5;
  String name,username,email,mobile,password;
  String gen = null;
 // DatabaseHelper dbhe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		Spinner spn;
		
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
		
		btnsubmit=(Button)findViewById(R.id.btnsubmit);
		e1=(EditText)findViewById(R.id.name);
		e2=(EditText)findViewById(R.id.username);
		e3=(EditText)findViewById(R.id.email);
		e4=(EditText)findViewById(R.id.mobile);
		e5=(EditText)findViewById(R.id.password);
		
		spn=(Spinner)findViewById(R.id.spinner1);
		
		List<String> gender = new ArrayList<String>();
		
		gender.add("MALE");
		gender.add("FEMALE");
		
		final ArrayAdapter<String> ar = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, gender);
		
		spn.setAdapter(ar);
		
		spn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				gen = ar.getItem(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		//dbhe = new DatabaseHelper(this);
		
		btnsubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 name=e1.getText().toString();
				 username=e2.getText().toString();
				 email=e3.getText().toString();
				 mobile=e4.getText().toString();
				 password=e5.getText().toString();
				 
				

					Insert i=new Insert();
					i.execute();
					
				   Intent i1 = new Intent(RegisterActivity.this, MainActivity.class);
				   startActivity(i1);
				 
				
			}
		});
	}
	
	class Insert extends AsyncTask<Void, Void, Void>{

		ProgressDialog pd;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pd=new ProgressDialog(RegisterActivity.this);
			pd.setMessage("please wait");
			pd.setCancelable(true);
			pd.setIndeterminate(false);
			pd.show();

		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			List<NameValuePair> nameValuePair=new ArrayList<NameValuePair>();
			
			nameValuePair.add(new BasicNameValuePair("um_name", name));
			nameValuePair.add(new BasicNameValuePair("um_username", username));
			nameValuePair.add(new BasicNameValuePair("um_mobile", mobile));
			nameValuePair.add(new BasicNameValuePair("um_email", email));
			nameValuePair.add(new BasicNameValuePair("um_password",password));
			nameValuePair.add(new BasicNameValuePair("um_gender", gen));
			
			
			Log.d("system out", "request");
			
			Json js=new Json();
			
			String url=js.insertdata("http://192.168.1.232/hks_admin/production/insert.php", nameValuePair);
			
			Log.d("System Out", "res of add to cart: "+ url);
			
			/*List<NameValuePair> namevaluepair=new ArrayList<NameValuePair>();
			
			namevaluepair.add(new BasicNameValuePair("fname",fname.getText().toString()));
			namevaluepair.add(new BasicNameValuePair("lname",lname.getText().toString()));
			namevaluepair.add(new BasicNameValuePair("email",email.getText().toString()));

			Log.d("Syatem out","Requesting...");

			Json js=new Json();
			
			String r=js.insertdata("http://10.0.2.2/project/insert.php", namevaluepair);
			
			Log.d("System Out", "res of add to cart: "+ r);*/
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			
			if(pd.isShowing()){
				pd.dismiss();
				
				Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();
			}
			super.onPostExecute(result);
		}

		
		
	}
	}

	

	

	
