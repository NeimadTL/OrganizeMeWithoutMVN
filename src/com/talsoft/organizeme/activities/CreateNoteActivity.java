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
import com.talsoft.organizeme.models.Note;

public class CreateNoteActivity extends Activity 
{
	
	private EditText noteTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_note);

		noteTitle = (EditText) findViewById(R.id.noteTitleEditText);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.create_note_activity_action, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle presses on the action bar items
	    switch (item.getItemId()) 
	    {
	        case R.id.action_create_note:
	            
	        	//create the task 
	        	Note toCreate = new Note();
	        	
	        	//set fields
	        	toCreate.setTitle(noteTitle.getText().toString());
	        	
	        	//get tag's name to put the note inside
	        	String tagName = this.getIntent().getStringExtra("tagName");
	        	
	        	//finish set fields
	        	toCreate.setTagName(tagName);
	        	
	        	
	        	//add task created
	        	OrganizeMeDataBase.addNote(toCreate);
	        	
	        	//put tag's name in intent for launch "NoteListActivity";
	        	Intent intent  = new Intent(CreateNoteActivity.this, NoteListActivity.class);
	        	intent.putExtra("tagName",tagName);
	        	
	        	//TODO: this line might genrate a bug, activity is started but not stopped TO CHECK
	        	startActivity(intent);
	        	
	        	finish();
	            return true;
	        	
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
