package com.example.hsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	EditText user;
	EditText pass ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// Implementing the EditText from the xmliiiiiii
		user = (EditText)findViewById(R.id.inputName);
		pass = (EditText)findViewById(R.id.inputPass);
		
		// TODO do not activate keyboard on startup
		user.clearFocus();
		pass.clearFocus();
		
	}
	
	/**
	 * Reaction of onClick from Login
	 * 
	 * @param v the button view
	 */
	public void onClick_Login(View v){
		// Checks if username and pass are true 
		if(user.getText().toString().equals("ormir") && pass.getText().toString().equals("admin")){
			startActivityForResult(new Intent(this, MainActivity.class), 1);
		}
	}

}
