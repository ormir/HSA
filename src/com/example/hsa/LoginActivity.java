package com.example.hsa;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hsa.JSONParser;

import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
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
	private static String url_student_detail = "http://app.htl-shkoder.com.dd24526.kasserver.com/android_connect/get_login.php";

	// studens JSONArray
	JSONArray student = null;
	String [][] studentStringArray = null;
	int success = 0;
	
	// Shared Preferences variable
	SharedPreferences sharedPrefs;
	SharedPreferences.Editor editSharedPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// TODO Remove restricion for HTTPGet to run in main thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		// initialise shared preferences
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);;
		editSharedPrefs = sharedPrefs.edit();
		
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
		
		// Pass to MD5 Hash
		inputPassword = eTxtPass.getText().toString();
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(inputPassword.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			while(hashtext.length() < 32 ){
				  hashtext = "0"+hashtext;
			}
			inputPassword = hashtext;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("name", inputUser));
						params.add(new BasicNameValuePair("password", inputPassword));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jParser.makeHttpRequest(
								url_student_detail, "GET", params);
						
						if( json != null){
							// json success tag
							success = json.getInt("success");
							if (success == 1) {
								
//								Toast.makeText(getApplicationContext(), "Loggin successful", 
//										   Toast.LENGTH_SHORT).show();
								
								// Remove 'success' entry from JSON Array
								json.remove("success");
								
								JSONArray jsNames = json.names();
								JSONArray[] response = new JSONArray[jsNames.length()];
								String responseName = "";
								for(int i = 0; i< jsNames.length(); i++){
									String strResponse = "";
									String setName = jsNames.getString(i);
									responseName += setName + ",";
									response[i] = json.getJSONArray(setName);
									for(int j = 0; j< response[i].length(); j++){
										strResponse += response[i].getString(j) + ",";
									}
									sharedPrefs.edit().putString(setName, strResponse).commit();
									//Log.d("RESPONSE STR", strResponse);
								}
								sharedPrefs.edit().putString("login_name", responseName).commit();
								//Save responce to shared preferences
							} else {
								Toast.makeText(getApplicationContext(), "Username or Password incorrect. Please try again.", 
										   Toast.LENGTH_SHORT).show();
							}
						}else {
							Toast.makeText(getApplicationContext(), "Check network connection", 
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
			
			// Check if data were fetched to start activity
			if(success == 1){
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
		}
	}

}
