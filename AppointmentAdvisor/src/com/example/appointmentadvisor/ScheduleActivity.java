package com.example.appointmentadvisor;
/**
 * Appointment Advisor
 * Dylan Chu, Yu-Dean Wang
 * 
 * This activity populates a radio group with timeslots and lets the user schedule an appointment
 * Then is will go to a confirmation screen
 */
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;




import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class ScheduleActivity extends Activity {
	//instance variables
	public static final String EXTRA_PROFESSOR = "com.example.appointmentadvisor.ScheduleActivity.professor";
	String timeslotSelected;
	boolean flag;
	String classStanding="";
	String professor="";
	String daySelected="";
	ArrayList<Timeslot> myTimes;
	String filename;
	
	//onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//instantiate variables
		timeslotSelected = "";
		filename="";
		myTimes = new ArrayList<Timeslot>();
		setContentView(R.layout.activity_schedule);
		// Get data from the intent
		Intent intent = getIntent();
		classStanding=intent.getStringExtra(MenuActivity.EXTRA_STANDING);
		professor = intent.getStringExtra(MenuActivity.EXTRA_PROFESSOR);
	    filename = intent.getStringExtra(MenuActivity.EXTRA_FILENAME);
	    daySelected = intent.getStringExtra(MenuActivity.EXTRA_DAY_SELECTED);
	    
	    //read the file and populate the timeslot arraylist with open classes
	    try {
		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
		            openFileInput(filename)));
		    String inputString;
		    flag = true;         
		    while ((inputString = inputReader.readLine()) != null) {
		    	String avail = inputReader.readLine();
		    	String minClass = inputReader.readLine();
		    	if(avail.equals("t"))
		    	{
		    		flag = true;
		    		myTimes.add(new Timeslot(inputString,flag,minClass));
		    	}
		    	else if(avail.equals("f"))
		    	{
		    		flag=false;
		    		myTimes.add(new Timeslot(inputString,flag,minClass));
		    	}
		    	
		       
		    }
		    inputReader.close();
			

		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    
	    //populate the radiogroup with radio buttons
	    String str="";
	    for(int i =0;i<myTimes.size();i++)
	    {
	    	str += myTimes.get(i).toString();
	    }
	    RadioGroup radioGroup = (RadioGroup)findViewById(R.id.group);
	    //check the class standing to display the proper timeslots
	    for (int i = 0; i < myTimes.size(); i++) {
	    	
	    	if((classStanding.equals("SENIOR"))
	    			||
	    				(classStanding.equals("JUNIOR")
	    						&& !(myTimes.get(i).getminClass().equals("SENIOR")))
	    			||
	    				(classStanding.equals("SOPHMORE")
	    						&& ((myTimes.get(i).getminClass().equals("SOPHMORE"))
	    								||myTimes.get(i).getminClass().equals("FRESHMAN")))
	    			||
	    				(classStanding.equals("FRESHMAN")
	    						&&(myTimes.get(i).getminClass().equals("FRESHMAN")))
	    								
	    			
	    			)
	    	{
	    		//only display open timeslots
	    		if(myTimes.get(i).isOpen())
	            {
	    			RadioButton myRadioButton = new RadioButton(this);
	    			myRadioButton.setId(i);
	    			myRadioButton.setText(myTimes.get(i).getTime());
	    			myRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener(){

						@Override
						public void onCheckedChanged(CompoundButton button,
								boolean isChecked) 
						{
							timeslotSelected = (String) button.getText();
							
							
						}
	    				});
	    			radioGroup.addView(myRadioButton);
	    	}
	    		
	    			
	            }
	    	}

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}
	
	//write to the user file and rewrite the source file to close the timeslot
	public void schedule(View view){
		for(int i = 0;i<myTimes.size();i++)
		{
			if(timeslotSelected.equals(myTimes.get(i).getTime()))
			{
				myTimes.get(i).setisOpen(false);
			}
		}
		String timeString ="";
		String userString = "Your appointment is on "+ daySelected + 
			" at " + timeslotSelected + " with Professor "+ professor;
		for(int i=0;i<myTimes.size();i++){
			timeString += myTimes.get(i).toString();
		}
		//rewrite the professor file
		try {
			  FileOutputStream outputStream;
			  outputStream = openFileOutput(filename, Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(timeString.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		//write to user file
		try {
			  FileOutputStream outputStream;
			  outputStream = openFileOutput("user", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(userString.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		
		//send a confirmation message
		Intent intent = new Intent(this,ConfirmActivity.class);
		intent.putExtra(EXTRA_PROFESSOR, professor);
		startActivity(intent);
	}
}
