package com.example.hks;

import java.util.ArrayList;
import java.util.HashMap;


import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListSingleActivity extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> result = new HashMap<String, String>();
	DisplayImageOptions options;
	
	ListSingleActivity(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		this.data = arraylist;
		
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheOnDisc(true).cacheInMemory(true)
		.imageScaleType(ImageScaleType.EXACTLY)
		.displayer(new FadeInBitmapDisplayer(300)).build();

ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
		context).defaultDisplayImageOptions(defaultOptions)
		.memoryCache(new WeakMemoryCache())
		.discCacheSize(100 * 1024 * 1024).build();

ImageLoader.getInstance().init(config);
// END - UNIVERSAL IMAGE LOADER SETUPuto-generated method stub
imageLoader = ImageLoader.getInstance();
options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.build();
		
		
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		TextView pcode, ppname, ppprice, ppcatagory;
		ImageView fac_img;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.list_single, parent, false);
		result = data.get(position);
		
		 pcode = (TextView)itemView.findViewById(R.id.txt);
		ppname = (TextView)itemView.findViewById(R.id.textView1);
//		ppprice = (TextView)itemView.findViewById(R.id.txtpprice);
		//ppcatagory = (TextView)itemView.findViewById(R.id.txtcatagory);
		//fexperience = (TextView)itemView.findViewById(R.id.txtfacexperience);
		
		fac_img = (ImageView) itemView.findViewById(R.id.img);
		
		
		pcode.setText(result.get("pid"));
		ppname.setText(result.get("id"));
//		ppprice.setText(result.get("pprice"));
		//ppcatagory.setText(result.get("pcagory"));
	//	fexperience.setText(result.get("six"));
		
	imageLoader.displayImage(result.get("imagepath"), fac_img, options);
		
		itemView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HashMap<String, String> detailmap = new HashMap<String, String>();
				detailmap = data.get(position);
				Intent i = new Intent(context, ProductlistActivity.class);
				i.putExtra("pid", detailmap.get("pid"));
			    i.putExtra("id", detailmap.get("id"));
//				i.putExtra("pname", detailmap.get("pname"));
//				
//				i.putExtra("pprice", detailmap.get("pprice"));
//				i.putExtra("pdesc1", detailmap.get("pdesc1"));
//				i.putExtra("pdesc2", detailmap.get("pdesc2"));
//				i.putExtra("poffers", detailmap.get("poffers"));
//				i.putExtra("imagepath", detailmap.get("imagepath"));
//				
				context.startActivity(i);
				
			}
		});
		
		return itemView;
	}

     
	
}
