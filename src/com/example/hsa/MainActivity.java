package com.example.hsa;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	Animation in;
	Animation out;
	
	// Shared Preferences variable
	SharedPreferences sharedPrefs;
	Set<String> shredResponse = null;
	
	// Quick show text
	private String timetableQuickString = "";
	private String gradesQuickString = "Loading ...";
	private String materialQuickString = "Loading ...";
	private String absentQuickString = "Loading ...";
	private String schedulesQuickString = "Loading ...";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// TODO Remove restricion for HTTPGet to run in main thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		// TODO set username as Title
		//setTitle(getIntent().getStringExtra("student_name"));
		getActionBar().setIcon(R.drawable.ic_gear_red);
		
		// Load animation for the faddein fadeout effect
		in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String loginResponse =  sharedPrefs.getString("login_name", null);
		String[] arrNames = loginResponse.split(",");
		
		Map<String, String[]> assoc = new HashMap<String, String[]>();
		
		for(String arrName : arrNames){
			String[] mainElement =  sharedPrefs.getString(arrName, null).split(",");
			assoc.put(arrName, mainElement);
		}
		
		String[] test = assoc.get(arrNames[0]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	//TODO include the following two methodes in a switch function
	/**
	 * Open Activity when category selected
	 * 
	 * @param v category view
	 */
	public void onClick_Main_Studenplan(View v) {
		startActivityForResult(new Intent(this, StundenplanActivity.class), 1);
	}
	
	/**
	 * Animate when Quick Function selected
	 * @param v
	 */
	public void onClick_Main_Studenplan_Quick(View v) {
		tv = (TextView)findViewById(R.id.textView1);
		
		// Set out as start animation
		//tv.startAnimation(out);
		// TODO fixed text size
		tv.setTextSize(30f);
		// TODO Get data from database
		tv.setText(timetableQuickString);
		
		// Start animation
		//tv.startAnimation(in);
		new Handler().postDelayed(new Runnable() {
		   @Override
		   public void run() {
			   // Start animation
			   tv.startAnimation(out);
			   tv.setTextSize(50f);
			   // Context rollback
			   tv.setText("Stundenplan");
			   tv.startAnimation(in);
		   }
		}, 4000/* 4sec delay */);
		
	}
	
	/**
	 * Open Activity when category selected
	 * 
	 * @param v category view
	 */
	public void onClick_Main_Noten(View v) {
		startActivityForResult(new Intent(this, NotenActivity.class), 1);
	}
	
	public void onClick_Main_Noten_Quick(View v) {
		tv = (TextView)findViewById(R.id.textView2);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText(gradesQuickString);
		
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
	
	/**
	 * Open Activity when category selected
	 * 
	 * @param v category view
	 */
	public void onClick_Main_Unterrichtsstoff(View v) {
		startActivityForResult(new Intent(this, UnterrichtActivity.class), 1);
	}
	
	public void onClick_Main_Unterrichtsstoff_Quick(View v) {
		tv = (TextView)findViewById(R.id.textView3);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText(materialQuickString);
		
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
	
	/**
	 * Open Activity when category selected
	 * 
	 * @param v category view
	 */
	public void onClick_Main_Supplierplan(View v) {
		startActivityForResult(new Intent(this, SupplierActivity.class), 1);
	}
	
	public void onClick_Main_Supplierplan_Quick(View v) {
		tv = (TextView)findViewById(R.id.textView4);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText(absentQuickString);
		
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
	
	/**
	 * Open Activity when category selected
	 * 
	 * @param v category view
	 */
	public void onClick_Main_Terminen(View v) {
		startActivityForResult(new Intent(this, TerminenActivity.class), 1);
	}
	
	public void onClick_Main_Terminen_Quick(View v) {
		tv = (TextView)findViewById(R.id.textView5);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText(schedulesQuickString);
		
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
