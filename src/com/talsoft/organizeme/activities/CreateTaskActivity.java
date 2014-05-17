package com.talsoft.organizeme.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.pickers.TimePickerFragment;

public class CreateTaskActivity extends Activity
{
	
	private EditText goal;
	private EditText place;
	private TextView fromTextView;
	private TextView fromTimeTextView;
	private TextView fromDateTextView;
	private TextView toTextView;
	private TextView toTimeTextView;
	private TextView toDateTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_task);
		
		goal = (EditText) findViewById(R.id.goalEditText);
		place = (EditText) findViewById(R.id.placeEditText);
		
		fromTextView = (TextView) findViewById(R.id.fromTextView);
		fromDateTextView = (TextView) findViewById(R.id.fromDateTextView);
		fromTimeTextView = (TextView) findViewById(R.id.fromTimeTextView);
		
		toTextView = (TextView) findViewById(R.id.toTextView);
		toDateTextView = (TextView) findViewById(R.id.toDateTextView);
		toTimeTextView = (TextView) findViewById(R.id.toTimeTextView);
		
	
	}
	
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void showTimePickerDialog(View v) 
	{
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
	}
}
