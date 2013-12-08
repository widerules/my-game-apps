package com.roslon.ultimespyrecorder.main;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainUSR extends FragmentActivity {


	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_usr);

		
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());


		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_usr, (android.view.Menu) menu);
		
		//menu.add(R.drawable.ic_menu_name);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch ( item.getItemId() ) {
		case R.id.action_recorder:
			mViewPager.setCurrentItem(0); 
			mSectionsPagerAdapter.instantiateItem(mViewPager,0);
			return true;

		case R.id.action_player:
			mViewPager.setCurrentItem(1); 
			mSectionsPagerAdapter.instantiateItem(mViewPager,1);
			return true;

		case R.id.action_settings:
			mViewPager.setCurrentItem(2); 
			mSectionsPagerAdapter.instantiateItem(mViewPager,2);
			
			return true;
		}

		return false;

	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch(position) {

			case 0: return RecorderFragment.newInstance("RecorderFragment, Instance 1");
			
			case 1: return MediaFragment.newInstance("MediaFragment, Instance 1");
			
			case 2: return SettingsFragment.newInstance("SettingsFragment, Instance 1");
			
			default: return RecorderFragment.newInstance("RecorderFragment, Default");
			}

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}



}
