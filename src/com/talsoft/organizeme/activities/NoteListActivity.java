package com.talsoft.organizeme.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.adapters.NoteListAdapter;
import com.talsoft.organizeme.datas.OrganizeMeDataBase;

public class NoteListActivity extends ListActivity
{
	private NoteListAdapter noteAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_list);
		
		//set title of this activity
		setTitle("Mes notes");
		
		//get previous' tag's name
		String tagName = this.getIntent().getStringExtra("tagName");
		
		noteAdapter = new NoteListAdapter(NoteListActivity.this, OrganizeMeDataBase.getAllNotesWithTagName(tagName));
		setListAdapter(noteAdapter);	
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.note_list_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle presses on the action bar items
	    switch (item.getItemId()) 
	    {
	        case R.id.action_add_note:
	            
	        	Intent i = new Intent(NoteListActivity.this, CreateNoteActivity.class);
	        	
	        	//get previous' tag's name
	    		String tagName = this.getIntent().getStringExtra("tagName");	
	    		
	    		//put in the intent to star activity
    			i.putExtra("tagName", tagName);
	    			
	    		// launch activity for create a note	    		
	        	startActivity(i);
	        	
	        	finish();
	            return true;
	      
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
