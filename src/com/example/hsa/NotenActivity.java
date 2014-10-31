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
		
		setTitle("Noten Liste");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.noten, menu);
		return true;
	}

	public void onClick_Noten_Fach(View v) {
		startActivityForResult(new Intent(this, NotenFachActivity.class), 1);
	}
	
	public void onClick_Noten_Fach_Quick(View v) {
		LinearLayout parent = (LinearLayout) v.getParent();
		RelativeLayout rl = (RelativeLayout) parent.getChildAt(0);
		tv = (TextView) rl.getChildAt(0);
		fstr = tv.getText().toString();
		
		final Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		tv.startAnimation(out);
		tv.setTextSize(30f);
		tv.setText("+, +, +, 9, 9, 9, 9, 9, 9, 9, 9, +");
		
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
