package com.example.hsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Ormir G.");
		getActionBar().setIcon(R.drawable.ic_gear_red);
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick_Main_Studenplan(View v) {
		startActivityForResult(new Intent(this, StundenplanActivity.class), 1);
	}
	
	public void onClick_Main_Studenplan_Quick(View v) {
		tv = (TextView)findViewById(R.id.textView1);
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText("AM, E, GG-AL, AL, D, WIR, ITP2");
		
		tv.startAnimation(in);
		new Handler().postDelayed(new Runnable()
		{
		   @Override
		   public void run()
		   {
		     // your code here
			   	tv.startAnimation(out);
			   	tv.setTextSize(50f);
			   	tv.setText("Stundenplan");
				tv.startAnimation(in);
		   }
		}, 4000/* 1sec delay */);
		
	}
	
	public void onClick_Main_Noten(View v) {
		startActivityForResult(new Intent(this, NotenActivity.class), 1);
	}
	
	public void onClick_Main_Noten_Quick(View v) {
tv = (TextView)findViewById(R.id.textView2);
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText("9, 10, 10, 8, 10, 9, 10, 9, 10, 8, 10");
		
		tv.startAnimation(in);
		new Handler().postDelayed(new Runnable(){
		   @Override
		   public void run()
		   {
		     // your code here
			   tv.startAnimation(out);
			   tv.setTextSize(50f);
				tv.setText("Noten");
				tv.startAnimation(in);
		   }
		}, 4000/* 1sec delay */);
	}
	
	public void onClick_Main_Unterrichtsstoff(View v) {
		startActivityForResult(new Intent(this, UnterrichtActivity.class), 1);
	}
	
	public void onClick_Main_Unterrichtsstoff_Quick(View v) {
		tv = (TextView)findViewById(R.id.textView3);
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText("AL, D, AM");
		
		tv.startAnimation(in);
		new Handler().postDelayed(new Runnable(){
		   @Override
		   public void run()
		   {
		     // your code here
			   tv.startAnimation(out);
			   tv.setTextSize(50f);
				tv.setText("Unterrichtsstoff");
				tv.startAnimation(in);
		   }
		}, 4000/* 1sec delay */);
	}
	
	public void onClick_Main_Supplierplan(View v) {
		startActivityForResult(new Intent(this, SupplierActivity.class), 1);
	}
	
	public void onClick_Main_Supplierplan_Quick(View v) {
tv = (TextView)findViewById(R.id.textView4);
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText("MAV, SCG, NUF, NUF");
		
		tv.startAnimation(in);
		new Handler().postDelayed(new Runnable()
		{
		   @Override
		   public void run()
		   {
		     // your code here
			   tv.startAnimation(out);
			   tv.setTextSize(50f);
				tv.setText("Supplierplan");
				tv.startAnimation(in);
		   }
		}, 4000/* 1sec delay */);
	}
	
	public void onClick_Main_Terminen(View v) {
		startActivityForResult(new Intent(this, TerminenActivity.class), 1);
	}
	
	public void onClick_Main_Terminen_Quick(View v) {
tv = (TextView)findViewById(R.id.textView5);
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText("DA Praesentation, D Matura ...");
		
		tv.startAnimation(in);
		new Handler().postDelayed(new Runnable(){
		   @Override
		   public void run()
		   {
		     // your code here
			   tv.startAnimation(out);
			   tv.setTextSize(50f);
				tv.setText("Terminen");
				tv.startAnimation(in);
		   }
		}, 4000/* 1sec delay */);
	}
	
	public void onClick_Settings(MenuItem v) {
		startActivityForResult(new Intent(this, SettingActivity
				.class), 1);
	}
}
