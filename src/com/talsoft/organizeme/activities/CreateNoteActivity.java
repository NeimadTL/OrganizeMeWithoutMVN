package com.talsoft.organizeme.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.datas.OrganizeMeDataBase;
import com.talsoft.organizeme.models.Note;

public class CreateNoteActivity extends Activity 
{
	
	public static final int REQUEST_IMAGE_CAPTURE = 1;
	public static final int REQUEST_AUDIO_CAPTURE = 2;
	
	private EditText noteTitle;
	private Button takePhotoBtn;
	private Note toCreate;
	private Button takeAudioRecBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_note);

		//set title of this activity
		setTitle("Créer une note");
		
		toCreate = new Note();
				
		noteTitle = (EditText) findViewById(R.id.noteTitleEditText);
		takePhotoBtn = (Button) findViewById(R.id.takePhotoButton);
		takeAudioRecBtn = (Button) findViewById(R.id.takeAudioRecButton);
		
		
		takePhotoBtn.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent intentForPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				if(intentForPhoto.resolveActivity(getPackageManager()) != null)
				{
					startActivityForResult(intentForPhoto, REQUEST_IMAGE_CAPTURE);
				}
			}
		});
		
		
		takeAudioRecBtn.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent intentForAudio = new Intent(CreateNoteActivity.this, AudioRecordingActivity.class);
						
				if(intentForAudio.resolveActivity(getPackageManager()) != null)
				{
					startActivityForResult(intentForAudio, REQUEST_AUDIO_CAPTURE);
				}
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(data == null)
			return;
		
		Bundle extras = data.getExtras();
		
		if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
		{	
			Bitmap imageBitMap = (Bitmap) extras.get("data");	
			toCreate.setPhoto(imageBitMap);	
		}
		
		
		if(requestCode == REQUEST_AUDIO_CAPTURE && resultCode == RESULT_OK)
		{
			String audioFileName = (String) extras.get("audioFileName");	
			toCreate.setAudioFileName(audioFileName);	
		}
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
	        	/*Note toCreate = new Note();*/
	        	
	        	//set fields
	        	toCreate.setTitle(noteTitle.getText().toString());
	        	
	        	//get tag's name to put the note inside
	        	String tagName = this.getIntent().getStringExtra("tagName");
	        	
	        	//finish set fields
	        	toCreate.setTagName(tagName);
	        	
	        	
	        	//add task created
	        	OrganizeMeDataBase.addNote(toCreate);
	        	
	        	toCreate = null;//delete reference
	        	
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
