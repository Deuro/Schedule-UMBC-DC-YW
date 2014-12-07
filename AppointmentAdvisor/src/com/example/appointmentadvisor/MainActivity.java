package com.example.appointmentadvisor;

import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnItemSelectedListener  {
	public final static String EXTRA_PROFESSOR = "com.example.appointmentadvisor.professor";
	public final static String EXTRA_STANDING = "com.example.appointmentadvisor.standing";
	String professorSelected ="";
	String standingSelected="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Spinner spinner1 = (Spinner) findViewById(R.id.professors);
		Spinner spinner2 = (Spinner) findViewById(R.id.standing);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
		        R.array.professors_array, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
		        R.array.standing_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		spinner1.setAdapter(adapter1);
		spinner2.setAdapter(adapter2);
		OnItemSelectedListener select;
		spinner1.setOnItemSelectedListener(this);
		spinner2.setOnItemSelectedListener(this);
		FileOutputStream outputStream;
		Timeslot ts1 = new Timeslot("12:00",true,"SENIOR");
		Timeslot ts2 = new Timeslot("12:30",true,"SENIOR");
		Timeslot ts3 = new Timeslot("1:00",true,"SENIOR");
		Timeslot ts4 = new Timeslot("1:30",true,"JUNIOR");
		Timeslot ts5 = new Timeslot("2:00",true,"JUNIOR");
		Timeslot ts6 = new Timeslot("2:30",true,"JUNIOR");
		Timeslot ts7 = new Timeslot("3:00",true,"SOPHMORE");
		Timeslot ts8 = new Timeslot("3:30",true,"SOPHMORE");
		Timeslot ts9 = new Timeslot("4:00",true,"SOPHMORE");
		Timeslot ts10 = new Timeslot("4:30",true,"FRESHMAN");
		Timeslot ts11 = new Timeslot("5:00",true,"FRESHMAN");
		Timeslot ts12 = new Timeslot("5:30",true,"FRESHMAN");
		
		String allTimes = ts1.toString() + ts2.toString() + ts3.toString()
				+ ts4.toString() + ts5.toString()  + ts6.toString() + ts7.toString()
				+ ts8.toString() + ts9.toString()  + ts10.toString() + ts11.toString()
				+ ts12.toString();
		
		
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
		View professorsDropDown = findViewById(R.id.professors);
		View classStanding = findViewById(R.id.standing);
        Object selection =  parent.getItemAtPosition(pos);
        if(((View)parent).equals(professorsDropDown))
        	{professorSelected = (String) selection;}
        else if(((View)parent).equals(classStanding))
        	{standingSelected=(String) selection;}
        	
    }
    
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void schedule(View view){
		
		Intent intent = new Intent(this,ScheduleActivity.class);
		intent.putExtra(EXTRA_PROFESSOR, professorSelected);
		intent.putExtra(EXTRA_STANDING, standingSelected);
		startActivity(intent);
	}
	
	public void reset(View view)
	{
		Timeslot ts1 = new Timeslot("12:00",true,"SENIOR");
		Timeslot ts2 = new Timeslot("12:30",true,"SENIOR");
		Timeslot ts3 = new Timeslot("1:00",true,"SENIOR");
		Timeslot ts4 = new Timeslot("1:30",true,"JUNIOR");
		Timeslot ts5 = new Timeslot("2:00",true,"JUNIOR");
		Timeslot ts6 = new Timeslot("2:30",true,"JUNIOR");
		Timeslot ts7 = new Timeslot("3:00",true,"SOPHMORE");
		Timeslot ts8 = new Timeslot("3:30",true,"SOPHMORE");
		Timeslot ts9 = new Timeslot("4:00",true,"SOPHMORE");
		Timeslot ts10 = new Timeslot("4:30",true,"FRESHMAN");
		Timeslot ts11 = new Timeslot("5:00",true,"FRESHMAN");
		Timeslot ts12 = new Timeslot("5:30",true,"FRESHMAN");
		FileOutputStream outputStream;
		String allTimes = ts1.toString() + ts2.toString() + ts3.toString()
				+ ts4.toString() + ts5.toString()  + ts6.toString() + ts7.toString()
				+ ts8.toString() + ts9.toString()  + ts10.toString() + ts11.toString()
				+ ts12.toString();
		try {
			  outputStream = openFileOutput("Lupoli", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(allTimes.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("Kuber", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(allTimes.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("Park", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(allTimes.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("Yesha", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(allTimes.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}

}
