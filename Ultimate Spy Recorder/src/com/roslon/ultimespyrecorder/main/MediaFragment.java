package com.roslon.ultimespyrecorder.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.roslong.ultimespyrecorder.util.SongsManager;

import com.roslong.ultimespyrecorder.util.Utilities;

public class MediaFragment extends Fragment implements OnClickListener {
	
	public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	ListView objectListView;
	ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();
    private Button btnPlay;
    private Button btnPause;
    private Button btnStop;
   
    private TextView songTitleLabel;
  
    // Media Player
    private  MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();;
    private SongsManager songManager;
    private Utilities utils;
	
	
	
	public MediaFragment(){super();}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main_player, container, false);

		TextView tv = (TextView) v.findViewById(R.id.section_label);
		tv.setText(getArguments().getString("msg"));

		// All player buttons
        btnPlay = (Button) v.findViewById(R.id.playbutton);       
        btnPause = (Button) v.findViewById(R.id.pausebutton);
        btnStop = (Button) v.findViewById(R.id.stopbutton);
        objectListView = (ListView)v.findViewById(R.id.playList);
        songTitleLabel = (TextView) v.findViewById(R.id.songTitle);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
       
        // Mediaplayer
        mp = new MediaPlayer();
      
    // Getting all songs list

        SongsManager plm = new SongsManager();
        // get all songs from sdcard
        songsList = plm.getPlayList();
        if(songsList.isEmpty())
        { HashMap<String, String> pl= new HashMap<String, String> ();
        pl.put("songTitle","songTitle");
		songsList.add(pl);}
        // looping through playlist
        String key= "songTitle";
        final ArrayList<String> list_namea = new ArrayList<String>();
        for (int i = 0; i < songsList.size(); i++) {
            // creating new HashMap
            HashMap<String, String> song = songsList.get(i);
            Log.d("lista",songsList.get(i).get(key));
            // adding HashList to ArrayList
            songsListData.add(song);
            list_namea.add(songsList.get(i).get(key));
        }
  
 
       
     
        final StableArrayAdapter adapter = new StableArrayAdapter(this.getActivity(),
            android.R.layout.simple_list_item_1, list_namea);
        objectListView.setAdapter(adapter);
 
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
	            mp.reset();
	          //  mp.setDataSource(songsList.get(songIndex).get("songPath"));
	          //  mp.prepare();
	            mp.start();
	            // Displaying Song title
	            String songTitle = songsList.get(songIndex).get("songTitle");
	            songTitleLabel.setText(songTitle);
	 
	            
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } 
	    }
	  
	  @Override
	     public void onDestroy(){
	     super.onDestroy();
	        mp.release();
	     }

	@Override
	public void onClick(View target) {
		  if (target == btnPlay)
			  ;         //  startService(new Intent(MusicService.ACTION_PLAY));
	        else if (target == btnPlay)
	            ;//startService(new Intent(MusicService.ACTION_PAUSE));
	        else if (target == btnStop)
	           ;// startService(new Intent(MusicService.ACTION_SKIP));
	     
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
	
}