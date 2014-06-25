package com.talsoft.organizeme.activities;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.talsoft.organizeme.R;

public class AudioRecordingActivity extends Activity 
{
	
	private Button startnStopRecBtn;
	private Button recOKBtn;
    private MediaRecorder mRecorder;
    String audioFileName;
    private static int audioNoteCounter = 0;
    
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio_recording);
		
		setTitle("Enregistrement audio");
	
		
		startnStopRecBtn = (Button) findViewById(R.id.startnStopRecButton);
		recOKBtn = (Button) findViewById(R.id.recOKButton);
		
		startnStopRecBtn.setOnClickListener(new OnClickListener() 
		{
			boolean mStartRecording = true;
			
			@Override
			public void onClick(View v) 
			{
				onRecord(mStartRecording);
				
		        if (mStartRecording) 
		        {
		        	startnStopRecBtn.setText("Arrêter l'enregistrement audio");
		        } 
		        else 
		        {
		        	startnStopRecBtn.setText("Débuter l'enregistrement audio");
		        }
		        
		        mStartRecording = !mStartRecording;
			}
		});
		
		
		
		recOKBtn.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent result  = new Intent();
				result.putExtra("audioFileName", audioFileName);
				setResult(RESULT_OK, result);
				finish();
			}
		});
	}
	
	private void onRecord(boolean start) 
    {
        if (start) 
        {
            startRecording();
            Toast.makeText(AudioRecordingActivity.this, "Vous pouvez maintenant parler...", Toast.LENGTH_LONG).show();
            recOKBtn.setEnabled(false);
        } 
        else 
        {
            stopRecording();
            Toast.makeText(AudioRecordingActivity.this, "Enregistrement terminé.", Toast.LENGTH_LONG).show();
            recOKBtn.setEnabled(true);
        }
    }
	
	
	private void startRecording() 
	{
		audioFileName = Environment.getExternalStorageDirectory().getAbsolutePath(); 
		audioFileName += "/audiorecord" + audioNoteCounter + ".3gp";
		audioNoteCounter++;
		
		mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(audioFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try 
        {
            mRecorder.prepare();
        } 
        catch (IOException e) 
        {
           // Log.e(LOG_TAG, "prepare() failed");
        }
        
        try
        {
        	mRecorder.start();
        }
        catch(IllegalStateException ise)
        {
        	 //Log.e("ISException", ise.getMessage());
        }
    }
	
	 
	private void stopRecording() 
	{
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
	
	
	@Override
    public void onPause() 
	{
		super.onPause();
		if (mRecorder != null) 
		{
			mRecorder.release();
			mRecorder = null;
		}
    }

}
