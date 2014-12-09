package com.example.appointmentadvisor;
/**
 * The main menu of appointment advisor
 */
import java.io.FileOutputStream;
import java.util.ArrayList;

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
	//extra values to push
	public final static String EXTRA_PROFESSOR = "com.example.appointmentadvisor.professor";
	public final static String EXTRA_STANDING = "com.example.appointmentadvisor.standing";
	public final static String EXTRA_DAYS = "com.example.appointmentadvisor.days";
	
	//instance variables
	String professorSelected ="";
	String standingSelected="";
	
	//on opening the activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//override stuff
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//instantiate spinners
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
		
		
		
	}
	
	//method for when something is selected in a dropdown menu
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected
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
	
	//what happends when somenoe trys to check the days
	public void schedule(View view){
		//make an array for the days
		int length=0;
		if (professorSelected.equals("Lupoli"))
		{
			length=2;
		}
		if (professorSelected.equals("Kuber"))
		{
			length=3;
		}
		if (professorSelected.equals("Yesha"))
		{
			length=2;
		}
		if (professorSelected.equals("Park"))
		{
			length=2;
		}
		
		
		String[] days = new String[length];
		if (professorSelected.equals("Lupoli"))
		{
			days[0]="Monday";
			days[1]="Wednesday";
		}
		if (professorSelected.equals("Kuber"))
		{
			days[0]="Monday";
			days[1]="Tuesday";
			days[2]="Wednesday";
		}
		if (professorSelected.equals("Yesha"))
		{
			days[0]="Tuesday";
			days[1]="Thursday";
		}
		if (professorSelected.equals("Park"))
		{
			days[0]="Monday";
			days[1]="Friday";
		}
		//start the menu activity
		Intent intent = new Intent(this,MenuActivity.class);
		intent.putExtra(EXTRA_PROFESSOR, professorSelected);
		intent.putExtra(EXTRA_STANDING, standingSelected);
		intent.putExtra(EXTRA_DAYS,days);
		startActivity(intent);
	}
	
	//starts the check activity when the button is pressed
	public void check(View view)
	{
		Intent intent = new Intent(this,CheckActivity.class);
		startActivity(intent);
	}
	
	//resets the files
	public void reset(View view)
	{
		
		Timeslot ts3 = new Timeslot("1:00",true,"SENIOR");
		Timeslot ts4 = new Timeslot("1:30",true,"SENIOR");
		Timeslot ts5 = new Timeslot("2:00",true,"JUNIOR");
		Timeslot ts6 = new Timeslot("2:30",true,"JUNIOR");
		Timeslot ts7 = new Timeslot("3:00",true,"SOPHMORE");
		
		Timeslot ts1 = new Timeslot("1:00",true,"JUNIOR");
		Timeslot ts2 = new Timeslot("1:30",true,"JUNIOR");
		Timeslot ts8 = new Timeslot("2:00",true,"SOPHMORE");
		Timeslot ts9 = new Timeslot("2:30",true,"SOPHMORE");
		Timeslot ts10 = new Timeslot("3:00",true,"FRESHMAN");
		
		Timeslot ts11 = new Timeslot("5:00",true,"FRESHMAN");
		Timeslot ts12 = new Timeslot("5:30",true,"FRESHMAN");
		
		Timeslot ts13 = new Timeslot("2:00",true,"SENIOR");
		Timeslot ts14 = new Timeslot("2:10",true,"SENIOR");
		Timeslot ts15 = new Timeslot("2:20",true,"SENIOR");
		Timeslot ts16 = new Timeslot("2:30",true,"JUNIOR");
		Timeslot ts17 = new Timeslot("2:40",true,"JUNIOR");
		Timeslot ts18 = new Timeslot("2:50",true,"JUNIOR");
		Timeslot ts19 = new Timeslot("3:00",true,"JUNIOR");
		
		Timeslot ts20 = new Timeslot("2:00",true,"SOPHMORE");
		Timeslot ts21 = new Timeslot("2:10",true,"SOPHMORE");
		Timeslot ts22 = new Timeslot("2:20",true,"SOPHMORE");
		Timeslot ts23 = new Timeslot("2:30",true,"SOPHMORE");
		Timeslot ts24 = new Timeslot("2:40",true,"FRESHMAN");
		Timeslot ts25 = new Timeslot("2:50",true,"FRESHMAN");
		Timeslot ts26 = new Timeslot("3:00",true,"FRESHMAN");
		FileOutputStream outputStream;
		
		String lupoliDay1 = ts13.toString() + ts14.toString() + ts15.toString()
				+ ts16.toString() + ts17.toString() + ts18.toString()+ ts19.toString();
		String lupoliDay2 =  ts20.toString() + ts21.toString() + ts22.toString()
				+ ts23.toString() + ts24.toString() + ts25.toString()+ ts26.toString();
		
		String yeshaDay1 = ts13.toString() + ts14.toString() + ts15.toString()
				+ ts16.toString() + ts17.toString() + ts18.toString()+ ts19.toString();
		String yeshaDay2 =  ts20.toString() + ts21.toString() + ts22.toString()
				+ ts23.toString() + ts24.toString() + ts25.toString()+ ts26.toString();
		
		String kuberDay1 = ts3.toString() + ts4.toString() + ts5.toString()
				+ts6.toString()+ts7.toString();
		
		String kuberDay2 = ts1.toString()+ts2.toString() + ts8.toString()
				+ts9.toString()+ts10.toString();
		
		String parkDay1 = ts13.toString() + ts14.toString() + ts15.toString()
				+ ts16.toString() + ts17.toString() + ts18.toString()+ ts19.toString();
		String parkDay2 =  ts20.toString() + ts21.toString() + ts22.toString()
				+ ts23.toString() + ts24.toString() + ts25.toString()+ ts26.toString();
	/*	String allTimes = ts1.toString() + ts2.toString() + ts3.toString()
				+ ts4.toString() + ts5.toString()  + ts6.toString() + ts7.toString()
				+ ts8.toString() + ts9.toString()  + ts10.toString() + ts11.toString()
				+ ts12.toString();*/
		try {
			  outputStream = openFileOutput("user", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write("You have nothing scheduled!".getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("LupoliMonday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(lupoliDay1.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("LupoliWednesday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(lupoliDay2.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		
		try {
			  outputStream = openFileOutput("KuberMonday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(kuberDay1.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("KuberTuesday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(kuberDay1.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("KuberWednesday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(kuberDay2.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("ParkMonday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(parkDay1.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("ParkFriday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(parkDay2.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("YeshaTuesday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(yeshaDay1.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		try {
			  outputStream = openFileOutput("YeshaThursday", Context.MODE_PRIVATE| Context.MODE_WORLD_READABLE);
			  outputStream.write(yeshaDay2.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}

}
