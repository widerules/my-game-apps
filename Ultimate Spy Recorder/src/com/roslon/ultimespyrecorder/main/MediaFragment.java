package com.roslon.ultimespyrecorder.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.roslong.ultimespyrecorder.util.SongsManager;

public class MediaFragment extends Fragment implements OnClickListener,OnCompletionListener {

	public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	ListView objectListView;
	ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();
	private Button btnPlay;
	private Button btnPause;
	private Button btnStop;
	private Button btnShare;
	private static  int statop = 0;
	// Media Player
	private SongsManager plm ;
	private static  MediaPlayer mp;
	private static int selez=0, poz=0;
	private StableArrayAdapter adapter;
	private  ArrayList<String> list_namea = new ArrayList<String>();
	public MediaFragment(){super();}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main_player, container, false);



		// All player buttons
		btnPlay = (Button) v.findViewById(R.id.playbutton);       
		btnPause = (Button) v.findViewById(R.id.pausebutton);
		btnStop = (Button) v.findViewById(R.id.stopbutton);
		objectListView = (ListView)v.findViewById(R.id.playList);
		btnShare = (Button) v.findViewById(R.id.shareact);


		btnPlay.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnShare.setOnClickListener(this);
		
		// Mediaplayer
		createMediaPlayerIfNeeded();
		// Getting all songs list

		plm= new SongsManager();
		// get all songs from sdcard



		//get selected song
		objectListView.setOnItemClickListener (new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				selez=arg2;


			}});

		objectListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				final String strFile =songsList.get(arg2).get("songPath"); 

				AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
				adb.setTitle("Delete?");
				adb.setMessage("Are you sure you want to delete " + arg2);

				adb.setNegativeButton("Cancel", null);
				adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						File file = new File(strFile);
						boolean deleted = file.delete();
						adapter.clear();

						refreshList();
						adapter.notifyDataSetChanged();

					}});
				adb.show();

				return true;
			}

		});

		// create playlist
		refreshList();

		return v;
	}

	public static MediaFragment newInstance(String text) {

		MediaFragment f = new MediaFragment();
		Bundle b = new Bundle();
		b.putString("msg", text);

		f.setArguments(b);

		return f;
	}
	public void  playSong(int songIndex){
		// Play song
		try {

			btnPlay.setVisibility(View.GONE);
			btnPause.setVisibility(View.VISIBLE);
			btnPause.setClickable(true);
			btnPlay.setClickable(false);
			if(statop == 0 )
			{
				mp.reset();
				mp.setDataSource(songsList.get(selez).get("songPath"));
				mp.prepare();
				mp.start();	
				statop=1;
			}else if (statop == 2){
				mp.seekTo(poz);
				mp.start();
				statop=1;}


		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void  pauseSong(int songIndex){
		// Play song
		try {

			if(statop == 1){
				btnPause.setClickable(false);
				btnPlay.setVisibility(View.VISIBLE);
				btnPause.setVisibility(View.GONE);
				btnPlay.setClickable(true);
				statop=2;
				poz=mp.getCurrentPosition();
				mp.pause();
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void  stopSong(int songIndex){
		// Play song
		try {

			if(statop!=0 || statop==1){

				mp.stop();
				mp.reset();	
				btnPause.setClickable(false);
				btnPlay.setVisibility(View.VISIBLE);
				btnPause.setVisibility(View.GONE);
				btnPlay.setClickable(true);
				poz=0;
				statop=0;}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}



	@Override
	public void onDestroy(){
		super.onDestroy();
		mp.stop();
		mp.release();
		mp=null;
		selez=0;
		poz=0;
	}

	@Override
	public void onClick(View target) {


		switch(target.getId()){

		case R.id.playbutton:
			btnPlay.setBackgroundColor(Color.parseColor("#4b0d2d"));
			btnStop.setBackgroundColor(Color.parseColor("#0d4b2b"));
			playSong(selez);
			break;
		case R.id.pausebutton:
			btnPause.setBackgroundColor(Color.parseColor("#4b0d2d"));
			btnStop.setBackgroundColor(Color.parseColor("#0d4b2b"));
			pauseSong(selez);
			break;

		case R.id.stopbutton:
			btnPause.setBackgroundColor(Color.parseColor("#0d4b2b"));
			btnPlay.setBackgroundColor(Color.parseColor("#0d4b2b"));
			btnStop.setBackgroundColor(Color.parseColor("#4b0d2d"));
			stopSong(selez);
			break;
		case R.id.shareact:
			Intent shInt = createShareIntent(selez);
			startActivity(Intent.createChooser(shInt, "How do you want to share?"));
			break;

		
		}
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

	@Override
	public void onCompletion(MediaPlayer mp) {

		Log.i("Completion Listener","Song Complete");
		//mp.stop();
		mp.reset();
		try {
			mp.setDataSource(songsList.get(selez).get("songPath"));
			mp.prepare();
			mp.start();		
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Makes sure the media player exists and has been reset. This will create the media player
	 * if needed, or reset the existing media player if one already exists.
	 */
	void createMediaPlayerIfNeeded() {
		if (mp == null) {
			mp = new MediaPlayer();
			mp.setWakeMode(this.getActivity().getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
			mp.setOnCompletionListener(this);

		}
		else
			mp.reset();
	}

	private Intent createShareIntent(int songIndex) {

		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		shareIntent.setType("image/*");
		Log.d("sono", songsList.get(selez).get("songPath"));
		// For a file in shared storage.  For data in private storage, use a ContentProvider.

		shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+ songsList.get(songIndex).get("songPath")));
		return shareIntent;
	} 

	public void refreshList(){
		songsList.clear();
		songsList = plm.getPlayList();
		if(songsList.isEmpty())
		{ HashMap<String, String> pl= new HashMap<String, String> ();
		pl.put("songTitle","songTitle");
		songsList.add(pl);}

		String key= "songTitle";
		list_namea.clear();

		for (int i = 0; i < songsList.size(); ++i) {
			// creating new HashMap
			HashMap<String, String> song = songsList.get(i);
			Log.d("lista",songsList.get(i).get(key));
			// adding HashList to ArrayList
			songsListData.add(song);
			list_namea.add(songsList.get(i).get(key));
		}



		adapter = new StableArrayAdapter(this.getActivity(),
				android.R.layout.simple_list_item_1, list_namea);

		objectListView.setAdapter(adapter);



	}
}