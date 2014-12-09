package com.example.appointmentadvisor;
/**
 * simple class to check your appointment
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class CheckActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		try {
		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
		            openFileInput("user")));
		    String inputString;
		    StringBuffer stringBuffer = new StringBuffer();                
		    while ((inputString = inputReader.readLine()) != null) {
		        stringBuffer.append(inputString + "\n");
		    }

			TextView tv = (TextView)findViewById(R.id.textViewCheck);  

		    tv.setText(stringBuffer.toString());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check, menu);
		return true;
	}

}
