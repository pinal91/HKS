package com.example.hks;




import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.provider.Telephony.TextBasedSmsColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {
     
	TextView e1,e2;
	ListView l1;
	ImageView i1;
	PreferenceHelper pf;
	Context context;
	Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		b1=(Button)findViewById(R.id.btndoctor);
		b2=(Button)findViewById(R.id.btngroceries);
		TextView t1=(TextView)findViewById(R.id.textView3);
		t1.setText(pf.getPreferences(HomeActivity.this,"rm_name"));
		 i1 = (ImageView) findViewById(R.id.slide_img);
		i1.setBackgroundResource(R.drawable.sliding_image);

		AnimationDrawable anim = (AnimationDrawable) i1.getBackground();
		anim.start();
		
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(HomeActivity.this, ListviewActivity.class);
				i.putExtra("my", "1");
				startActivity(i);
			}
			
			
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(HomeActivity.this, ListviewActivity.class);
				i1.putExtra("my", "2");
				startActivity(i1);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
