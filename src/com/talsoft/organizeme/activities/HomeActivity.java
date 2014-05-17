package com.talsoft.organizeme.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.talsoft.organizeme.R;

public class HomeActivity extends Activity
{
	private Button taskButton;
	private Button noteButton;
	private Button disconnectButton;
	private Intent logInIntent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// bind object from activity_home.xml to this activity
		taskButton = (Button) findViewById(R.id.taskButton);
		noteButton = (Button) findViewById(R.id.noteButton);
		disconnectButton = (Button) findViewById(R.id.disconnectButton);
		
		
		// event on taskButton
		taskButton.setOnClickListener(new OnClickListener() 
		{	
			public void onClick(View v) 
			{
				Toast.makeText(HomeActivity.this, "taskButton OK", Toast.LENGTH_SHORT).show();			
			}
		});
		
		
		// event on noteButton
		noteButton.setOnClickListener(new OnClickListener() 
		{	
			public void onClick(View v) 
			{
				Toast.makeText(HomeActivity.this, "noteButton OK", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		// event on disconnectButton
		disconnectButton.setOnClickListener(new OnClickListener() 
		{	
			public void onClick(View v) 
			{
				logInIntent = new Intent(HomeActivity.this, LogInActivity.class);
				startActivity(logInIntent);
				finish();
			}
		});
		
	}
}
