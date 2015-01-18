package com.example.hsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NotenActivity extends Activity {

	TextView tv;
	String fstr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noten);

		// Set ActionBar title
		setTitle("Noten Liste");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.noten, menu);
		return true;
	}

	/**
	 * Starts the activity that shows grate for the selected subject
	 * 
	 * @param v
	 */
	public void onClick_Noten_Fach(View v) {
		startActivityForResult(new Intent(this, NotenFachActivity.class), 1);
	}

	/**
	 * Quick view of the grates for the selected subject
	 * 
	 * @param v
	 */
	public void onClick_Noten_Fach_Quick(View v) {
		LinearLayout parent = (LinearLayout) v.getParent();
		RelativeLayout rl = (RelativeLayout) parent.getChildAt(0);
		tv = (TextView) rl.getChildAt(0);
		fstr = tv.getText().toString();

		// Load the animation
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);

		// Start animation
		tv.startAnimation(out);
		tv.setTextSize(30f);
		// TODO Get data from database
		// Context rollback
		tv.setText("+, +, +, 9, 9, 9, 9, 9, 9, 9, 9, +");
		tv.startAnimation(in);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				// Start animation
				tv.startAnimation(out);
				tv.setTextSize(50f);
				// Text rollback
				tv.setText(fstr);
				tv.startAnimation(in);
			}
		}, 4000/* 1sec delay */);
	}
}
