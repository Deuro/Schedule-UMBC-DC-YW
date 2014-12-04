package com.example.appointmentadvisor;

import java.io.BufferedReader;
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
import android.content.Intent;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ScheduleActivity extends Activity {
	String userName,password;
	boolean flag;
	ArrayList<Timeslot> myTimes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		myTimes = new ArrayList<Timeslot>();
		setContentView(R.layout.activity_schedule);
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    try {
		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
		            openFileInput(message)));
		    String inputString;
		    StringBuffer stringBuffer = new StringBuffer();                
		    while ((inputString = inputReader.readLine()) != null) {
		    	if(inputReader.readLine().equals("t"))
		    	{flag = true;}
		    	else if(inputReader.readLine().equals("f"))
		    	{flag = false;}
		        myTimes.add(new Timeslot(inputString,flag,inputReader.readLine()));
		    }

			//TextView tv = (TextView)findViewById(R.id.textView1);  

		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	   // TextView tv = (TextView)findViewById(R.id.textView1);  
	    String str="";
	    for(int i =0;i<myTimes.size();i++)
	    {
	    	str += myTimes.get(i).toString();
	    }
	    RadioGroup radioGroup = (RadioGroup)findViewById(R.id.group);

	    for (int i = 0; i < radioGroup .getChildCount(); i++) {
	    		if(myTimes.get(i).isOpen())
	            {((RadioButton) radioGroup.getChildAt(i)).setText(myTimes.get(i).getTime());
	            }
	    	}
	   // tv.setText(str);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}
	
	
}
