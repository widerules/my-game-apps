package com.roslon.ultimespyrecorder.main;


import com.roslong.ultimespyrecorder.util.Constants;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RecorderFragment extends Fragment {
	 // Intent for starting the IntentService 
   
    
	private Button btnRecStart;
	private Button btnRecStop;
	public static int stato_service = 0;
	public RecorderFragment(){super();}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main_recorder, container, false);

		TextView tv = (TextView) v.findViewById(R.id.section_label);
		tv.setText(getArguments().getString("msg"));

		btnRecStart = (Button) v.findViewById(R.id.start_rec);       
		btnRecStop = (Button) v.findViewById(R.id.stop_rec);
	
		btnRecStop.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {

				Toast.makeText(v.getContext().getApplicationContext(), "Stop Recording", Toast.LENGTH_LONG).show();
				stato_service = 2;
				btnRecStart.setBackgroundColor(Color.parseColor("#0d4b2b"));
				btnRecStop.setBackgroundColor(Color.parseColor("#4b0d2d"));
				stopRecorderService();
				

			}

		});

		btnRecStart.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Toast.makeText(v.getContext().getApplicationContext(), "Start Recording", Toast.LENGTH_LONG).show();
				stato_service = 1;
				btnRecStop.setBackgroundColor(Color.parseColor("#0d4b2b"));
				btnRecStart.setBackgroundColor(Color.parseColor("#4b0d2d"));
				startRecorderService();

			}

		});

		return v;
	}

	public static RecorderFragment newInstance(String text) {

		RecorderFragment f = new RecorderFragment();
		Bundle b = new Bundle();
		b.putString("msg", text);

		f.setArguments(b);

		return f;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
	}
	
	private void startRecorderService() {
		this.getActivity().startService(new Intent(Constants.ACTION_PLAY));
	}

	private void stopRecorderService() {
		this.getActivity().startService(new Intent(Constants.ACTION_STOP));
	}
}