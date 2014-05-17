package com.talsoft.organizeme.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.talsoft.organizeme.R;

public class LogInActivity extends Activity
{
	
	private EditText login;
	private EditText password;
	private Button connectButton;
	
	private Intent homeIntent;
	
	public final static String FAKE_LOGIN_AND_PASSWORD = "ADMIN";
	public final static String WELCOME_MSG = "Bienvenu sur Organize me";
	public final static String BAD_LOGIN_OR_PASSWORD = "Votre login ou votre mot de passe est incorrect";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in_activity);
		
		login = (EditText) findViewById(R.id.loginEditText);
		password = (EditText) findViewById(R.id.passwordEditText);
		connectButton = (Button) findViewById(R.id.connectButton);
		
		
		
		connectButton.setOnClickListener(new OnClickListener() 
		{	
			public void onClick(View v) 
			{
				String scannedLogin = login.getText().toString();
				String scannedPassword = password.getText().toString();
				
				if(scannedLogin.equalsIgnoreCase(FAKE_LOGIN_AND_PASSWORD) && scannedPassword.equalsIgnoreCase(FAKE_LOGIN_AND_PASSWORD))
				{
					Toast.makeText(LogInActivity.this, WELCOME_MSG, Toast.LENGTH_LONG).show();
					
					homeIntent = new Intent(LogInActivity.this, HomeActivity.class);
					startActivity(homeIntent);
					finish();
				}
				else
				{
					Toast.makeText(LogInActivity.this, BAD_LOGIN_OR_PASSWORD, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
