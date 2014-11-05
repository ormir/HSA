package com.example.hsa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hsa.JSONParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText eTxtUser; // edit Text User
	EditText eTxtPass ; // edit Text Password
	JSONParser jParser = new JSONParser();
	
	// input variables
	private String inputUser, inputPassword;

	// Progress Dialog
	private ProgressDialog pDialog;

	// url to get all products list
	private static String url_student_detail = "http://app.htl-shkoder.com.dd24526.kasserver.com/android_connect/student_login.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_STUDENT = "student";
	private static final String TAG_STUDENT_ID = "student_id";
	private static final String TAG_NAME = "name";

	// studens JSONArray
	JSONArray student = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// TODO Remove restricion for HTTPGet to run in main thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		// Implementing the EditText from the xml
		eTxtUser = (EditText)findViewById(R.id.inputName);
		eTxtPass = (EditText)findViewById(R.id.inputPass);
		
		// TODO do not activate keyboard on startup
		eTxtUser.clearFocus();
		eTxtPass.clearFocus();
		
	}
	
	/**
	 * Reaction of onClick from Login
	 * 
	 * @param v the button view
	 */
	public void onClick_Login(View v){
		inputUser = eTxtUser.getText().toString();
		inputPassword = eTxtPass.getText().toString();
		
		// Loading student in Background Thread
		new GetStudentDetails().execute();
	}
	
	/**
	 * Background Async Task to Get complete student details
	 * */
	class GetStudentDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(LoginActivity.this);
			pDialog.setMessage("Logging in ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Getting product details in background thread
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					// Check for success tag
					int success;
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair(TAG_NAME, inputUser));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jParser.makeHttpRequest(
								url_student_detail, "GET", params);

						// check your log for json response
						//Log.d("Single Student Details", json.toString());

						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray studentObj = json
									.getJSONArray(TAG_STUDENT); // JSON Array

							// get first product object from JSON Array
							JSONObject student = studentObj.getJSONObject(0);

							// product with this pid found
							// display product data in EditText
							// Checks if username and pass are true 
							if(eTxtPass.getText().toString().equals("pass")){
								startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra(TAG_STUDENT_ID, student.getString(TAG_STUDENT_ID)));
							}
						} else {
							Toast.makeText(getApplicationContext(), "Username incorrect. Please try again.", 
									   Toast.LENGTH_SHORT).show();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			pDialog.dismiss();
		}
	}

}
