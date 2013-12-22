package com.roslon.ultimespyrecorder.main;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RecorderFragment extends Fragment {
	 // Intent for starting the IntentService 
    private static Intent mServiceIntent;
    
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
		mServiceIntent =  new Intent(getActivity(), USRService.class).setData(Uri.parse(String.valueOf(stato_service)));
		
		btnRecStop.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {

				Toast.makeText(v.getContext().getApplicationContext(), "Stop Recording", Toast.LENGTH_LONG).show();
				stato_service = 2;
			

			}

		});

		btnRecStart.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Toast.makeText(v.getContext().getApplicationContext(), "Start Recording", Toast.LENGTH_LONG).show();
				stato_service = 1;
				

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
		this.getActivity().getApplicationContext().stopService(mServiceIntent);
	}
}