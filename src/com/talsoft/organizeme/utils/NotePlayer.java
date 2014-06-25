package com.talsoft.organizeme.utils;
import java.io.IOException;

import android.media.MediaPlayer;

import com.talsoft.organizeme.models.Note;


public class NotePlayer 
{

	private MediaPlayer mPlayer;
	
	
	public NotePlayer(){}
	
	
	public void onPlay(boolean start, Note note) 
    {
        if (start) 
        {
            startPlaying(note);
        } 
        else 
        {
        	stopPlaying();
        }
    }
	
	
	
	private void startPlaying(Note note) 
	{
		mPlayer = new MediaPlayer();
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
    }
}
