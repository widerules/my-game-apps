
package com.roslon.ultimespyrecorder.main;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.roslong.ultimespyrecorder.util.Constants;
import com.roslong.ultimespyrecorder.util.SongsManager;


public class USRService extends Service {
	// Used to write to the system log from this class.
	public static final String LOG_TAG = "USRService";
	public static boolean STARTED = false;
	private MediaRecorder mRecorder = null;


	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(LOG_TAG, "created");
		checkUSRService();

	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		//stopUSRService(true);

	}


	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}



	private void startRecording() {
		SongsManager.mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/UltimaSpyRecorder";
		SongsManager.mFileName  += "/MyRecord";
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
		SongsManager.mFileName +=timeStamp+".mp3";

		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mRecorder.setAudioEncodingBitRate(16);
		mRecorder.setAudioSamplingRate(44100);
		mRecorder.setOutputFile(SongsManager.mFileName);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e("voice service", "prepare() failed");
		}

		mRecorder.start();
	}

	private void stopRecording() {
	//	mRecorder.stop();
		relaxResources(true);

	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String action = intent.getAction();
		if (action.equals(Constants.ACTION_PLAY2))startRecording();
		else if (action.equals(Constants.ACTION_STOP2)) stopRecording();
		else if (action.equals(Constants.ACTION_STOPSERVICE2)) stopUSRService(true);


		return START_NOT_STICKY; // Means we started the service, but don't want it to
		// restart in case it's killed.
	}

	public void checkUSRService() {
		if(canReadFromExternalStorage()){
			File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SpyVoiceRecorder");
			if(!folder.exists())
				folder.mkdir();

		}
		else{
			Toast.makeText(getApplicationContext(), "SD CARD NOT FOUND", Toast.LENGTH_LONG).show();
		}
	}    

	void stopUSRService(boolean force) {
		// let go of all resources...
		stopRecording();
		// service is no longer necessary. Will be started again if needed.
		stopSelf();

	}

	private boolean canReadFromExternalStorage() {
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return false;
		} else {
			return false;
		}
	}

	void relaxResources(boolean releaseMediaPlayer) {
		// stop being a foreground service
		stopForeground(true);
		// stop and release the Media Player, if it's available
		if (releaseMediaPlayer && mRecorder != null) {
			mRecorder.stop();
			mRecorder.reset();
			mRecorder.release();
			mRecorder = null;
		}

	}

}
