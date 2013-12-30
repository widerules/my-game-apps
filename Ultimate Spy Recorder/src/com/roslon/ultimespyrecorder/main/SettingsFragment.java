package com.roslon.ultimespyrecorder.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SettingsFragment extends Fragment implements OnClickListener{

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
		

		swi.setOnClickListener(this);
		tp.setOnClickListener(this);
		cb.setOnClickListener(this);
		mnp.setOnClickListener(this);
        
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

		case R.id.switch1:
			Toast.makeText(target.getContext().getApplicationContext(), "Switch", Toast.LENGTH_LONG).show();
			break;
		case R.id.timePicker1:
			Toast.makeText(target.getContext().getApplicationContext(), "timePick", Toast.LENGTH_LONG).show();
			break;

		case R.id.checkBox1:
			Toast.makeText(target.getContext().getApplicationContext(), "CheckBox", Toast.LENGTH_LONG).show();
			break;
		case R.id.Picker1:
			Toast.makeText(target.getContext().getApplicationContext(), "Picker1", Toast.LENGTH_LONG).show();
			break;
		
		}
	}
	
}