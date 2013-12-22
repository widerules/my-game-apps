package com.roslong.ultimespyrecorder.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Environment;

public class SongsManager {
    public static String mFileName = "empty";
	// SDCard Path
    final String MEDIA_PATH =Environment.getExternalStorageDirectory().getAbsolutePath()+"/UltimaSpyRecorder";
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
 
    // Constructor
    public SongsManager(){
    	
    }
 
    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */
    public ArrayList<HashMap<String, String>> getPlayList(){
        File home = new File(MEDIA_PATH);
        
       if(!canReadFromExternalStorage())
    	  return null;
       if(!prepareRecorder())
   		return null;
       
        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                HashMap<String, String> song = new HashMap<String, String>();
                song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
                song.put("songPath", file.getPath());
 
                // Adding each song to SongList
                songsList.add(song);
            }
        }
        // return songs list array
        return songsList;
    }
 
    /**
     * Class to filter files which are having .mp3 extension
     * */
    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
    
    
	public boolean prepareRecorder() {
		if(canReadFromExternalStorage()){
			File folder = new File(MEDIA_PATH);
			if(!folder.exists())
				folder.mkdir();
			return true;

		}
		else{
			//Toast.makeText(getApplicationContext(), "SD CARD NOT FOUND", Toast.LENGTH_LONG).show();
			return false;
		}
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
}