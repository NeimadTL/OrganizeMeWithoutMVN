package com.talsoft.organizeme.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.datas.OrganizeMeDataBase;
import com.talsoft.organizeme.models.Tag;

public class CreateTagActivity  extends Activity
{
	
	private EditText tagName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_tag);

		tagName = (EditText) findViewById(R.id.tagNameEditText);	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.create_tag_activity_action, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle presses on the action bar items
	    switch (item.getItemId()) 
	    {
	        case R.id.action_create_tag:
	            
	        	//create the task 
	        	Tag toCreate = new Tag();
	        	
	        	//set fields
	        	toCreate.setName(tagName.getText().toString());
	        	
	        	//add task created
	        	OrganizeMeDataBase.addTag(toCreate);
	        	
	        	//TODO: this line might genrate a bug, activity is started but not stopped TO CHECK
	        	startActivity(new Intent(CreateTagActivity.this, TagListActivity.class));
	        	
	        	finish();
	            return true;
	        	
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
