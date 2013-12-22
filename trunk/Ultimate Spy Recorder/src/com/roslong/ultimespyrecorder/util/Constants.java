package com.roslong.ultimespyrecorder.util;
import java.util.Locale;

/**
 *
 * Constants used by multiple classes in this package
 */
public final class Constants {

    // Set to true to turn on verbose logging
    public static final boolean LOGV = false;
    
    // Set to true to turn on debug logging
    public static final boolean LOGD = true;

    // Custom actions
    
    public static final String STOP =
            "com.roslong.ultimespyrecorder.ACTION_START_REC";

    public static final String START=
            "com.roslong.ultimespyrecorder.ACTION_STOP_REC";
    
    // Defines a custom Intent action
    public static final String BROADCAST_ACTION = "com.roslong.ultimespyrecorder.BROADCAST";

    // Defines the key for the status "extra" in an Intent
    public static final String EXTENDED_DATA_STATUS = "com.roslong.ultimespyrecorder.STATUS";

    // Defines the key for the log "extra" in an Intent
    public static final String EXTENDED_STATUS_LOG = "com.roslong.ultimespyrecorder.LOG";
    

    
    // Status values to broadcast to the Activity

    // The download is starting
    public static final int RECORD_ACTION_STARTED = 0;

    // The background thread is connecting to the RSS feed
    public static final int RECORD_ACTION_WAIT = 1;

    // The background thread is parsing the RSS feed
    public static final int RECORD_ACTION_STOPPED = 2;
    
    // The background thread is done
    public static final int RECORD_ACTION_COMPLETE = 3;

    // The background thread is doing logging
    public static final int STATE_LOG = -1;

    public static final CharSequence BLANK = " ";
}