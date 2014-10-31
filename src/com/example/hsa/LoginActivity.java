package com.example.hsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	EditText user;
	EditText pass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		user = (EditText)findViewById(R.id.inputName);
		pass = (EditText)findViewById(R.id.inputPass);
		
		user.clearFocus();
		pass.clearFocus();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void onClick_Login(View v){
		
		
		if(user.getText().toString().equals("ormir") && pass.getText().toString().equals("admin")){
			startActivityForResult(new Intent(this, MainActivity.class), 1);
		}
	}

}
