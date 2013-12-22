
package com.roslon.ultimespyrecorder.main;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import com.roslong.ultimespyrecorder.util.SongsManager;


public class USRService extends IntentService {
	// Used to write to the system log from this class.
	public static final String LOG_TAG = "USRService";
	private static  MediaRecorder mRecorder = null;
	// Defines and instantiates an object for handling status updates.
//	private BroadcastNotifier mBroadcaster = new BroadcastNotifier(this);

	/**
	 * An IntentService must always have a constructor that calls the super constructor. The
	 * string supplied to the super constructor is used to give a name to the IntentService's
	 * background thread.
	 */
	public USRService() {

		super("USRService");
	}

	/**
	 * In an IntentService, onHandleIntent is run on a background thread.  As it
	 * runs, it broadcasts its current status using the LocalBroadcastManager.
	 * @param workIntent The Intent that starts the IntentService. This Intent contains the
	 * URL of the web site from which the RSS parser gets data.
	 */
	@Override
	protected void onHandleIntent(Intent workIntent) {
		// Gets a URL to read from the incoming Intent's "data" value
		String localString = workIntent.getDataString();
		Log.d(LOG_TAG, localString);
		if(RecorderFragment.stato_service==2)
		{stopRecording();Log.d(LOG_TAG, "stop");
	    }
		if(RecorderFragment.stato_service==1){startRecording();
		Log.d(LOG_TAG, "start");}
		// Reports that the service is about to connect to the RSS feed
		//mBroadcaster.broadcastIntentWithState(Constants.RECORD_ACTION_WAIT);

	}


	private void startRecording() {
		SongsManager.mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/UltimaSpyRecorder";
		SongsManager.mFileName += "/MyRecord";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		String timeStamp = dateFormat.format(date).toString();
		SongsManager.mFileName+=timeStamp+".mp3";
		if (mRecorder==null){
			mRecorder = new MediaRecorder();
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mRecorder.setOutputFile(SongsManager.mFileName);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		}
		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e("voice service", "prepare() failed");
		}

		mRecorder.start();
	}

	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}
}
