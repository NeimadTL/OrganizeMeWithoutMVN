package com.talsoft.organizeme.activities;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.adapters.TagListAdapter;
import com.talsoft.organizeme.datas.OrganizeMeDataBase;
import com.talsoft.organizeme.models.Tag;

public class TagListActivity extends ListActivity
{
	
	private TagListAdapter tagAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_list);
		
		tagAdapter = new TagListAdapter(TagListActivity.this,OrganizeMeDataBase.getAllTags());
		setListAdapter(tagAdapter);
		
		//registerForContextMenu(getListView());	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.tag_list_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle presses on the action bar items
	    switch (item.getItemId()) 
	    {
	        case R.id.action_add_tag:
	            // open activity for create a tag
	        	startActivity(new Intent(TagListActivity.this, CreateTagActivity.class));
	        	finish();
	            return true;
	      
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
