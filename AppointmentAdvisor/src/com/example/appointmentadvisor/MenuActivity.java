package com.example.appointmentadvisor;
/**
 * displays a menu for the user to select the day to schedule
 * dynamically populates depending on the professor
 */
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MenuActivity extends Activity {
	//extra variables
	public final static String EXTRA_FILENAME = "com.example.appointmentadvisor.menu.filename";
	public final static String EXTRA_PROFESSOR = "com.example.appointmentadvisor.menu.professor";
	public final static String EXTRA_STANDING = "com.example.appointmentadvisor.menu.standing";
	public final static String EXTRA_DAYS = "com.example.appointmentadvisor.menu.days";
	public final static String EXTRA_DAY_SELECTED = "com.example.appointmentadvisor.menu.dayselected";
	//instance variables
	String[] days;
	String daySelected="";
	String professor;
	String classStanding;
	String filename;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_menu);
		//get data from the intent
		Intent intent = getIntent();
		days = intent.getStringArrayExtra(MainActivity.EXTRA_DAYS);
		professor = intent.getStringExtra(MainActivity.EXTRA_PROFESSOR);
		classStanding = intent.getStringExtra(MainActivity.EXTRA_STANDING);
		
		RadioGroup radioButtons = (RadioGroup) findViewById(R.id.radiodays);
		
		//populate the radiogroup with radio buttons
		for(int i=0;i<days.length;i++)
		{
			RadioButton myRadioButton = new RadioButton(this);
			myRadioButton.setId(2 * i * 3);
			myRadioButton.setText(days[i]);
			myRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton button,
						boolean isChecked) 
				{
					daySelected = (String) button.getText();
					
					
				}
				});
			radioButtons.addView(myRadioButton);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	//start scheduling
	//send data to schedule activity
	public void schedule(View view){
		String filename = professor+daySelected;
		Intent intent = new Intent(this,ScheduleActivity.class);
		intent.putExtra(EXTRA_PROFESSOR, professor);
		intent.putExtra(EXTRA_STANDING, classStanding);
		intent.putExtra(EXTRA_FILENAME, filename);
		intent.putExtra(EXTRA_DAY_SELECTED, daySelected);
		
		startActivity(intent);
	}

}
