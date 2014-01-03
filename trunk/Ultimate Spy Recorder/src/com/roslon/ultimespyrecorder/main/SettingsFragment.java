package com.roslon.ultimespyrecorder.main;

import com.roslong.ultimespyrecorder.util.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class SettingsFragment extends Fragment implements OnClickListener, OnCheckedChangeListener, OnTimeChangedListener, OnFocusChangeListener{

	private Switch swi;
	private TimePicker tp;
	private CheckBox cb;
	private MyNPicker mnp;
	public SettingsFragment(){super();}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main_settings, container, false);

		swi = (Switch) v.findViewById(R.id.switch1);       
		tp = (TimePicker) v.findViewById(R.id.timePicker1);
		cb = (CheckBox) v.findViewById(R.id.checkBox1);
		mnp = (MyNPicker)v.findViewById(R.id.Picker1);


		swi.setOnCheckedChangeListener(this);
		tp.setOnTimeChangedListener(this);
		cb.setOnClickListener(this);
		mnp.valueText.setOnFocusChangeListener(this);
		
		TextView tv = (TextView) v.findViewById(R.id.section_label);
		tv.setText(getArguments().getString("msg"));

		return v;
	}

	public static SettingsFragment newInstance(String text) {

		SettingsFragment f = new SettingsFragment();
		Bundle b = new Bundle();
		b.putString("msg", text);

		f.setArguments(b);

		return f;
	}
	@Override
	public void onClick(View target) {
		switch(target.getId()){
		case R.id.checkBox1:
			Toast.makeText(target.getContext().getApplicationContext(), "checkBox", Toast.LENGTH_LONG).show();
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch(buttonView.getId()){

		case R.id.switch1:
			Toast.makeText(buttonView.getContext().getApplicationContext(), "The Switch is " + (isChecked ? "on" : "off"),
					Toast.LENGTH_SHORT).show();
			if(isChecked) {
				//do stuff when Switch is ON
			} else {
				//do stuff when Switch if OFF
			}
			break;
		}

	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		switch(view.getId()){
		case R.id.timePicker1:
			Toast.makeText(view.getContext().getApplicationContext(), "timePicker1", Toast.LENGTH_LONG).show();
			break;
		}

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		
			Toast.makeText(v.getContext().getApplicationContext(), "Picker1", Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		this.getActivity().startService(new Intent(Constants.ACTION_STOP2));

	}

	private void startUSRService() {
		this.getActivity().startService(new Intent(Constants.ACTION_PLAY2));
	}

	private void stopUSRService() {
		this.getActivity().startService(new Intent(Constants.ACTION_STOP2));
	}

}