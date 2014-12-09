package com.example.appointmentadvisor;
/**
 * simple class to confirm that the user scheduled properly
 * sends user back to main menu
 */
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ConfirmActivity extends Activity {
	String professor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm);
		Intent intent = getIntent();
		professor = intent.getStringExtra(ScheduleActivity.EXTRA_PROFESSOR);
		TextView tv = (TextView) findViewById(R.id.confirmText);
		tv.setText("Thank for scheduling your appointment with Professor "+professor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirm, menu);
		return true;
	}
	
	public void mainmenu(View view)
	{
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}

}
