package com.talsoft.organizeme.activities;



import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.talsoft.organizeme.R;

public class TaskListActivity extends Activity
{
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.task_list_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle presses on the action bar items
	    switch (item.getItemId()) 
	    {
	        case R.id.action_add_task:
	            // open activity for create a task
	        	startActivity(new Intent(TaskListActivity.this, CreateTaskActivity.class));
	            return true;
	      
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
