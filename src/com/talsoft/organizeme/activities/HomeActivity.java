package com.talsoft.organizeme.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
	private Intent taskListIntent;
	private Intent tagListIntent;
	
	
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
				taskListIntent = new Intent(HomeActivity.this, TaskListActivity.class);
				startActivity(taskListIntent);
			}
		});
		
		
		// event on noteButton
		noteButton.setOnClickListener(new OnClickListener() 
		{	
			public void onClick(View v) 
			{
				Toast.makeText(HomeActivity.this, "noteButton OK", Toast.LENGTH_SHORT).show();
				tagListIntent = new Intent(HomeActivity.this, TagListActivity.class);
				startActivity(tagListIntent);
			}
		});
		
		
		// event on disconnectButton
		disconnectButton.setOnClickListener(new OnClickListener() 
		{	
			public void onClick(View v) 
			{
				showDisconnectDialog();
			}
		});
		
	}
	
	public void showDisconnectDialog()
	{
		DisconnectDialogFragment dis = new DisconnectDialogFragment();
		dis.show(getFragmentManager(), "disconnectDialog");
	}
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK)) 
		{
			showDisconnectDialog();
			
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	@SuppressLint("ValidFragment")
	public class DisconnectDialogFragment  extends DialogFragment
	{
		@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) 
		{
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage("Voulez-vous vous déconnecter ?")
	        
	               .setPositiveButton("Déconnecter", new DialogInterface.OnClickListener() 
	               {
	                   public void onClick(DialogInterface dialog, int id) 
	                   {
	                	   logInIntent = new Intent(HomeActivity.this, LogInActivity.class);
	       					startActivity(logInIntent);
	       					finish();
	                   }
	               })
	               .setNegativeButton("Annuler", new DialogInterface.OnClickListener() 
	               {
	                   public void onClick(DialogInterface dialog, int id) 
	                   {
	                       // User cancelled the dialog
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
}
