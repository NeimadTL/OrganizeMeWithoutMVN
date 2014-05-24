package com.talsoft.organizeme.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
	
	
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		Toast.makeText(TagListActivity.this, "onListItemClick()", Toast.LENGTH_SHORT).show();
		
		Tag tag = (Tag) l.getItemAtPosition(position);
		String tagNameValue = tag.getName();
		
		Intent i = new Intent(TagListActivity.this, NoteListActivity.class);
		i.putExtra("tagName", tagNameValue);
		startActivity(i);
		
    	finish();
		
	}
}
