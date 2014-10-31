package com.example.hsa;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class UnterrichtActivity extends Activity {

	TextView tv;
	String fstr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unterricht);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.unterricht, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClick_Unterricht_Fach(View v) {
		RelativeLayout rl = (RelativeLayout)v;
		tv = (TextView) rl.getChildAt(0);
		fstr = tv.getText().toString();
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText("Das letzte Thema, das Unterrichtet wurde");
		
		tv.startAnimation(in);
		new Handler().postDelayed(new Runnable()
		{
		   @Override
		   public void run()
		   {
		     // your code here
			   tv.startAnimation(out);
			   tv.setTextSize(50f);
				tv.setText(fstr);
				tv.startAnimation(in);
		   }
		}, 4000/* 1sec delay */);
		
	}
	
	public void onClick_Unterricht_Fach_Quick(View v) {
		LinearLayout parent = (LinearLayout) v.getParent();
		RelativeLayout rl = (RelativeLayout) parent.getChildAt(0);
		tv = (TextView) rl.getChildAt(0);
		fstr = tv.getText().toString();
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText("Uebung 3 und 4 bei Seite 149");
		
		tv.startAnimation(in);
		new Handler().postDelayed(new Runnable()
		{
		   @Override
		   public void run()
		   {
		     // your code here
			   tv.startAnimation(out);
			   tv.setTextSize(50f);
				tv.setText(fstr);
				tv.startAnimation(in);
		   }
		}, 4000/* 1sec delay */);
	}

}
