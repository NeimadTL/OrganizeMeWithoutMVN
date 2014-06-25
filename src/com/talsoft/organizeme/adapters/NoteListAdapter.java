package com.talsoft.organizeme.adapters;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.models.Note;
import com.talsoft.organizeme.utils.NotePlayer;

public class NoteListAdapter  extends BaseAdapter
{
	private Context context;
	private List<Note> notes;
	private LayoutInflater inflater;
	private MediaPlayer mPlayer;
	
	
	public NoteListAdapter(Context context, List<Note> notes)
	{
		this.context = context;
		this.notes = notes;
		this.inflater = LayoutInflater.from(context);
		mPlayer = new MediaPlayer(); 
	}


	@Override
	public int getCount() 
	{
		return notes.size();
	}


	@Override
	public Object getItem(int position) 
	{
		return notes.get(position);
	}


	@Override
	public long getItemId(int position) 
	{
		return 0;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		final Note note = (Note) getItem(position);
		
		
		convertView = inflater.inflate(R.layout.list_view_note,null);
		
		TextView noteTitle = (TextView) convertView.findViewById(R.id.noteTitleTextView);
		noteTitle.setText(note.getTitle());
		
		if(note.getPhoto() == null)
		{
			convertView.findViewById(R.id.photoView).setVisibility(View.INVISIBLE);
		}
		else
		{
			ImageView photoView = (ImageView) convertView.findViewById(R.id.photoView);
			photoView.setImageBitmap(note.getPhoto());
		}
		
		if(note.getAudioFileName() == null || note.getAudioFileName().isEmpty())
		{
			convertView.findViewById(R.id.playButton).setVisibility(View.INVISIBLE);
		}
		else
		{
			final Button  playBtn = (Button) convertView.findViewById(R.id.playButton);
			playBtn.setOnClickListener(new OnClickListener() 
			{
				NotePlayer player = new NotePlayer();
				boolean startPlaying = true;
				
				@Override
				public void onClick(View v) 
				{
					//startPlaying(note);
					player.onPlay(startPlaying,note);
					
					if (startPlaying) 
			        {
						playBtn.setText("Stop");
			        } 
			        else 
			        {
			        	playBtn.setText("Play");
			        }
			        
					startPlaying = !startPlaying;
				}
			});
			
		
		}
		
		
		
		
		return convertView;
	}
	
	
	/*private void startPlaying(Note note) 
	{
        try 
        {
            mPlayer.setDataSource(note.getAudioFileName());
            mPlayer.prepare();
            mPlayer.start();
        }
        catch (IOException e) 
        {
           // Log.e(LOG_TAG, "prepare() failed");
        }
    }
	
	
    private void stopPlaying() 
    {
        mPlayer.release();
        mPlayer = null;
    }*/
	
}
