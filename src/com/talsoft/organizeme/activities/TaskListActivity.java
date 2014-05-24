package com.talsoft.organizeme.activities;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.adapters.TaskListAdapter;
import com.talsoft.organizeme.datas.OrganizeMeDataBase;

public class TaskListActivity extends ListActivity
{
	
	private TaskListAdapter taskAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_list);
		
		//set title of this activity
		setTitle("Mes tâches");
		
		taskAdapter = new TaskListAdapter(TaskListActivity.this,OrganizeMeDataBase.getAllTasks());
		setListAdapter(taskAdapter);
		
		//registerForContextMenu(getListView());	
	}
	
	
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
	        	finish();
	            return true;
	      
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
