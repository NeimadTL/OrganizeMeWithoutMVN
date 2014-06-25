package com.talsoft.organizeme.activities;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.datas.OrganizeMeDataBase;
import com.talsoft.organizeme.models.Task;


@SuppressLint("ValidFragment")
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
		
		//set title of this activity
		setTitle("Créer une tâche");
		
		goal = (EditText) findViewById(R.id.goalEditText);
		place = (EditText) findViewById(R.id.placeEditText);
		
		fromTextView = (TextView) findViewById(R.id.fromTextView);
		fromDateTextView = (TextView) findViewById(R.id.fromDateTextView);
		fromTimeTextView = (TextView) findViewById(R.id.fromTimeTextView);
		
		toTextView = (TextView) findViewById(R.id.toTextView);
		toDateTextView = (TextView) findViewById(R.id.toDateTextView);
		toTimeTextView = (TextView) findViewById(R.id.toTimeTextView);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.create_task_activity_action, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle presses on the action bar items
	    switch (item.getItemId()) 
	    {
	        case R.id.action_create_task:
	            
	        	//create the task 
	        	Task toCreate = new Task();
	        	
	        	//set fields  
	        	toCreate.setGoal(goal.getText().toString());
	        	toCreate.setPlace(place.getText().toString());
	        	
	        	//BEGIN PART
	        	DateTime fromDate = DateTime.parse(fromDateTextView.getText().toString(),DateTimeFormat.forPattern("EEEE dd MMMM yyyy"));
	        	DateTime fromTime = DateTime.parse(fromTimeTextView.getText().toString(),DateTimeFormat.forPattern("HH:mm"));
	        	DateTime beginDateTime = new DateTime(fromDate.getYear(), fromDate.getMonthOfYear(), 
	        										  fromDate.getDayOfMonth(), fromTime.getHourOfDay(),
	        										  fromTime.getMinuteOfHour());

	        	//END PART
	        	DateTime toDate = DateTime.parse(toDateTextView.getText().toString(),DateTimeFormat.forPattern("EEEE dd MMMM yyyy"));
	        	DateTime toTime = DateTime.parse(toTimeTextView.getText().toString(),DateTimeFormat.forPattern("HH:mm"));
	        	DateTime endDateTime = new DateTime(toDate.getYear(), toDate.getMonthOfYear(), 
	        										toDate.getDayOfMonth(), toTime.getHourOfDay(), 
	        										toTime.getMinuteOfHour());
	        	
	        	//finish set field
	        	toCreate.setBeginDate(beginDateTime);
	        	toCreate.setEndDate(endDateTime);
	        	
	        	//add task created
	        	OrganizeMeDataBase.addTask(toCreate);
	        
	        	
	        	
	        	//TODO: this line might genrate a bug, activity is started but not stopped TO CHECK
	        	startActivity(new Intent(CreateTaskActivity.this, TaskListActivity.class)); 
	        	
	        	finish();
	            return true;
	      
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK)) 
		{
			startActivity(new Intent(CreateTaskActivity.this, TaskListActivity.class));
        	finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}

	
	
	public void showDatePickerDialogForFromDate(View v) 
	{
		fromDateTextView = (TextView) v;
		DatePickerFragment newFragment = new DatePickerFragment();
	    newFragment.isFromDate = true;
	    newFragment.show(getFragmentManager(), "toDatePicker");
	}
	
	
	public void showDatePickerDialogForToDate(View v) 
	{
		toDateTextView = (TextView) v;
		DatePickerFragment newFragment = new DatePickerFragment();
		newFragment.isFromDate = false;
	    newFragment.show(getFragmentManager(), "toDatePicker");
	}
	
	
	
	
	public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener 
	{
		public boolean isFromDate;
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) 
		{
			// Use the current date as the default date in the picker
			DateTime date = new DateTime();
			int year = date.getYear();
			int month = date.getMonthOfYear();
			int day = date.getDayOfMonth();
			
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		public void onDateSet(DatePicker view, int year, int month, int day) 
		{
			// Do something with the date chosen by the user
			DateTime date = new DateTime(year, month , day, 0, 0);
			DateTimeFormatter formatter = DateTimeFormat.forPattern("EEEE dd MMMM yyyy");
			formatter.withLocale(Locale.FRENCH);
		
			if(isFromDate)
			{
				fromDateTextView.setText(formatter.print(date));
			}
			else
			{
				toDateTextView.setText(formatter.print(date));
			}

			
		}
	}
		
	
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void showTimePickerDialogForFromTime(View v) 
	{
		fromTimeTextView = (TextView) v;
		TimePickerFragment newFragment = new TimePickerFragment();
		newFragment.isFromTime = true;
	    newFragment.show(getFragmentManager(), "toTimePicker");
	    
	}
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void showTimePickerDialogForToTime(View v) 
	{
		toTimeTextView = (TextView) v;
		TimePickerFragment newFragment = new TimePickerFragment();
		newFragment.isFromTime = false;
	    newFragment.show(getFragmentManager(), "toTimePicker");
	    
	}
	
	
	public  class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener 
	{
		public boolean isFromTime;
		
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) 
        {
            // Use the current time as the default values for the picker
            DateTime date = new DateTime();
            int hour = date.getHourOfDay();
            int minute = date.getMinuteOfHour();

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            // Do something with the time chosen by the user        	
        	DateTime date = new DateTime(0, 1, 1, hourOfDay, minute);
        	DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        	
        
        	if(isFromTime)
        	{
        		fromTimeTextView.setText(formatter.print(date));
        	}
        	else
        	{
        		toTimeTextView.setText(formatter.print(date));
        	}
        }
    }
	
	
}
