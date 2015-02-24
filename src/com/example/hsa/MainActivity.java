package com.example.hsa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hsa.LoginActivity.GetStudentDetails;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView tv;
	Animation in;
	Animation out;
	String student_id;
	
	// url to get all products list
	private static String url_main = "http://app.htl-shkoder.com.dd24526.kasserver.com/android_connect/";
	private static String url_timetable_student = url_main + "get_timetable_student.php";
	private static String url_get_main_grade = url_main + "get_main_grade.php";
	private static String url_get_main_grade_quick = url_main + "get_main_grade_quick.php";
	
	JSONParser jParser = new JSONParser();
	
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_STUDENT = "student";
	private static final String TAG_STUDENT_ID = "student_id";
	private static final String TAG_DAY_WEEK = "day_week";
	private static final String TAG_LESSON_SEQUENCE_ID = "lesson_sequence_id";
	private static final String TAG_NAME = "name";
	private static final String TAG_SUBJECT_ACR = "subject_acr";
	private static final String TAG_TEACHER_ACR = "teacher_acr";
		
	// studens JSONArray
	JSONArray student = null;
	
	// Quick show text
	private String timetableQuickString = "Loading ...";
	private String gradesQuickString = "Loading ...";
	private String materialQuickString = "Loading ...";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		student_id = getIntent().getStringExtra(TAG_STUDENT_ID);
		
		// TODO Remove restricion for HTTPGet to run in main thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		// TODO set username as Title
		setTitle(getIntent().getStringExtra("student_name"));
		getActionBar().setIcon(R.drawable.ic_gear_red);
		
		// Load animation for the faddein fadeout effect
		in = AnimationUtils.loadAnimation(this, R.anim.fadein);
		out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		
		// Loading products in Background Thread
		new GetTimetableDetails().execute();
		new GetGradeMain().execute();
		new GetGradeQuick().execute();
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
	
	/**
	 * Background Async Task to Get complete student details
	 * */
	class GetTimetableDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
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
						params.add(new BasicNameValuePair(TAG_DAY_WEEK, "we"));
						params.add(new BasicNameValuePair(TAG_NAME, "5a"));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jParser.makeHttpRequest(
								url_timetable_student, "GET", params);

						// check your log for json response
						//Log.d("Single Student Details", json.toString());

						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray studentObj = json
									.getJSONArray(TAG_STUDENT); // JSON Array
							
							// get first product object from JSON Array
							//Loop through all timetable elements
							for(int i = 0; i < studentObj.length(); i++){
								JSONObject arrayStudent = studentObj.getJSONObject(i);
								// product with this pid found
								// display product data in EditText
								if(i == 6){
									((TextView)findViewById(R.id.main_stundenplan_quick_text)).setText(arrayStudent.getString(TAG_SUBJECT_ACR).toUpperCase());
								}
								
								// Add comma to all elements beside the last one
								if(i==(studentObj.length()-1)){
									timetableQuickString +=  arrayStudent.getString(TAG_SUBJECT_ACR).toUpperCase();
								} else{
									timetableQuickString +=  arrayStudent.getString(TAG_SUBJECT_ACR).toUpperCase()+", ";
								}
							}
						} else {
							Toast.makeText(getApplicationContext(), "Timetable not feched", 
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
			//Toast.makeText(getApplicationContext(), "Timetable fech finished.", 
				//	   Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Background Async Task to Get complete student details
	 * */
	class GetGradeMain extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
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
						params.add(new BasicNameValuePair("name", getIntent().getStringExtra("student_name")));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jParser.makeHttpRequest(
								url_get_main_grade, "GET", params);

						// check your log for json response
						//Log.d("Single Student Details", json.toString());

						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray studentObj = json
									.getJSONArray(TAG_STUDENT); // JSON Array
							
							gradesQuickString = "";
							
							// get first product object from JSON Array
							//Loop through all timetable elements
							for(int i = 0; i < studentObj.length(); i++){
								JSONObject arrayStudent = studentObj.getJSONObject(i);
								String avgGrade = arrayStudent.getString("avgGrade").toUpperCase();
								
								// Add comma to all elements beside the last one
								if(i==(studentObj.length()-1)){
									gradesQuickString +=  avgGrade;
								} else{
									gradesQuickString +=  avgGrade + ", ";
								}
							}
						} else {
							Toast.makeText(getApplicationContext(), "Grades not feched", 
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
			//Toast.makeText(getApplicationContext(), "Timetable fech finished.", 
				//	   Toast.LENGTH_SHORT).show();
		}
	}
	
	class GetGradeQuick extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
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
						params.add(new BasicNameValuePair("name", getIntent().getStringExtra("student_name")));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jParser.makeHttpRequest(
								url_get_main_grade_quick, "GET", params);

						// check your log for json response
						//Log.d("Single Student Details", json.toString());

						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray studentObj = json
									.getJSONArray(TAG_STUDENT); // JSON Array
							

							// get first product object from JSON Array
							//Loop through all timetable elements
							for(int i = 0; i < studentObj.length(); i++){
								JSONObject arrayStudent = studentObj.getJSONObject(i);
								
								((TextView)findViewById(R.id.txtMainNotenQuick)).setText(arrayStudent.getString("avgGrade"));
							}
						} else {
							Toast.makeText(getApplicationContext(), "Grades not feched", 
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
			//Toast.makeText(getApplicationContext(), "Timetable fech finished.", 
				//	   Toast.LENGTH_SHORT).show();
		}
	}
}
