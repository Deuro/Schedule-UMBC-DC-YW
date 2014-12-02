package com.example.appointmentadvisor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnItemSelectedListener  {
	 public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	String professorSelected ="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Spinner spinner = (Spinner) findViewById(R.id.professors);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.professors_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		OnItemSelectedListener select;
		spinner.setOnItemSelectedListener(this);
		
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        Object selection =  parent.getItemAtPosition(pos);
        professorSelected = (String) selection;
        	
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
		intent.putExtra(EXTRA_MESSAGE, professorSelected);
		startActivity(intent);
	}

}
