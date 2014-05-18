package com.talsoft.organizeme.activities;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimeParserBucket;

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
	        	DateTime dt = DateTime.parse(fromDateTextView.getText().toString(),DateTimeFormat.forPattern("EEEE dd MMMM yyyy"));
	        	DateTime dt1 = DateTime.parse(fromTimeTextView.getText().toString(),DateTimeFormat.forPattern("HH:mm"));
	        	
	        	DateTime beginDateTime = new DateTime();
	        	beginDateTime.withDayOfWeek(dt.getDayOfWeek())
	        				 .withDayOfMonth(dt.getDayOfMonth())
	        				 .withMonthOfYear(dt.getMonthOfYear())
	        				 .withYear(dt.getYear())
	        				 .withHourOfDay(dt1.getHourOfDay())
	        				 .withMinuteOfHour(dt1.getMinuteOfHour());
	        	toCreate.setBeginDate(beginDateTime);
	        	
	        	
	        	//END PART
	        	DateTime dt2 = DateTime.parse(toDateTextView.getText().toString(),DateTimeFormat.forPattern("EEEE dd MMMM yyyy"));
	        	DateTime dt3 = DateTime.parse(toTimeTextView.getText().toString(),DateTimeFormat.forPattern("HH:mm"));
	        	
	        	DateTime endDateTime = new DateTime();
	        	beginDateTime.withDayOfWeek(dt2.getDayOfWeek())
	        				 .withDayOfMonth(dt2.getDayOfMonth())
	        				 .withMonthOfYear(dt2.getMonthOfYear())
	        				 .withYear(dt2.getYear())
	        				 .withHourOfDay(dt3.getHourOfDay())
	        				 .withMinuteOfHour(dt3.getMinuteOfHour());
	        	toCreate.setEndDate(endDateTime);
	        	
	        	OrganizeMeDataBase.getInstance().getTasks().add(toCreate);
	        
	        	
	        	
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
	
	public void showDatePickerDialog(View v) 
	{
		fromDateTextView = (TextView) v;
		toDateTextView = (TextView) v;
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	
	
	@SuppressLint("ValidFragment")
	public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener 
	{

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
			DateTime date = new DateTime(year, month, day, 0, 0);
			DateTimeFormatter formatter = DateTimeFormat.forPattern("EEEE dd MMMM yyyy");
			formatter.withLocale(Locale.FRENCH);
			fromDateTextView.setText(formatter.print(date));
		}
	}
		
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void showTimePickerDialog(View v) 
	{
		fromTimeTextView = (TextView) v;
		toTimeTextView = (TextView) v;
		TimePickerFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");	    
	}
	
	
	@SuppressLint("ValidFragment")
	public  class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener 
	{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) 
        {
            // Use the current time as the default values for the picker
            /*final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);*/
            
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
        	/*fromTimeTextView.setText(hourOfDay +":"+ minute);
        	toTimeTextView.setText(hourOfDay +":"+ minute);*/
        	
        	DateTime date = new DateTime(0, 1, 1, hourOfDay, minute);
        	DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        	fromTimeTextView.setText(formatter.print(date));
        	toTimeTextView.setText(formatter.print(date));
        }
    }
	
	
}
