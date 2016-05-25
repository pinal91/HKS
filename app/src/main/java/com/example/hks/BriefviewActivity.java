package com.example.hks;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BriefviewActivity extends ActionBarActivity {

	
	TextView t1,t2,t3,t4,t6,lati,longii;
	ListView l1;
	ImageView i1;
	PreferenceHelper pf;
	Button b1,b2;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.briefview);
		
	t1=(TextView)findViewById(R.id.txtname);
	t2=(TextView)findViewById(R.id.txtpersonname);
	t3=(TextView)findViewById(R.id.txtaddress);
	t4=(TextView)findViewById(R.id.txtmobile1);
	t6=(TextView)findViewById(R.id.txtemail);
		lati=(TextView)findViewById(R.id.lati);
		longii=(TextView)findViewById(R.id.longii);

	b1=(Button)findViewById(R.id.btnlocation);
	b2=(Button)findViewById(R.id.btncancel);
	
	Intent i = getIntent();
	String des = i.getStringExtra("pid");
	String id = i.getStringExtra("id");
	String contact = i.getStringExtra("contact");
	String email = i.getStringExtra("email");
	String address = i.getStringExtra("address");

		final String leti = i.getStringExtra("leti");
		final String longi = i.getStringExtra("longi");
	t1.setText(id);
	t2.setText(des);
	t3.setText(address);
	t4.setText(contact);
	t6.setText(email);

		lati.setText(leti);
		longii.setText(longi);
	
	
	i1 = (ImageView) findViewById(R.id.slide_img);
	i1.setBackgroundResource(R.drawable.slide_img);

	AnimationDrawable anim = (AnimationDrawable) i1.getBackground();
	anim.start();

	b1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + leti + "," + longi));
			startActivity(intent);
			
		}
	});
	
	b2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.briefview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
