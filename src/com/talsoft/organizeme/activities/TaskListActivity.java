package com.talsoft.organizeme.activities;



import java.util.List;

import org.joda.time.DateTime;

import android.R.integer;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.common.collect.Lists;
import com.talsoft.organizeme.R;
import com.talsoft.organizeme.adapters.TaskAdapter;
import com.talsoft.organizeme.datas.OrganizeMeDataBase;
import com.talsoft.organizeme.models.Task;

public class TaskListActivity extends ListActivity
{
	
	private TaskAdapter taskAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_list);
		
		/*List<Task> fakeTasks = Lists.newArrayList();
		
		for(int i=1; i<=20; i++)
		{
			Task task = new Task();
			task.setGoal("goal"+i);
			task.setPlace("place"+i);
			task.setBeginDate(new DateTime());
			task.setEndDate(new DateTime());
			
			fakeTasks.add(task);
		}*/
		
		taskAdapter = new TaskAdapter(TaskListActivity.this,OrganizeMeDataBase.getInstance().getTasks());
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
