
package com.roslon.ultimespyrecorder.main;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;

import org.apache.http.HttpStatus;
import org.xmlpull.v1.XmlPullParserException;

import com.roslong.ultimespyrecorder.util.Constants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Vector;


public class USRService extends IntentService {
    // Used to write to the system log from this class.
    public static final String LOG_TAG = "RSSPullService";

    // Defines and instantiates an object for handling status updates.
    private BroadcastNotifier mBroadcaster = new BroadcastNotifier(this);

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

        
                // Reports that the service is about to connect to the RSS feed
                mBroadcaster.broadcastIntentWithState(Constants.RECORD_ACTION_WAIT);

                // Gets a response code from the RSS server
                int responseCode = 0;

                switch (responseCode) {

                    // If the response is OK
                    case HttpStatus.SC_OK:

                        // Reports that the service is parsing
                        mBroadcaster.broadcastIntentWithState(Constants.RECORD_ACTION_WAIT);

                  
                        // Reports that the service is now writing data to the content provider.
                        mBroadcaster.broadcastIntentWithState(Constants.RECORD_ACTION_STARTED);

                // Reports that the feed retrieval is complete.
                mBroadcaster.broadcastIntentWithState(Constants.RECORD_ACTION_COMPLETE);
            }
                
      
    }

}
