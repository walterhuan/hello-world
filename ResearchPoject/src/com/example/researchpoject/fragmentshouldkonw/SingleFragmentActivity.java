package com.example.researchpoject.fragmentshouldkonw;

import com.example.researchpoject.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public abstract class SingleFragmentActivity extends FragmentActivity {
	protected abstract Fragment createFragment();
	private static final String TAG="SingleFragmentActivity";
	@Override
	protected void onCreate(Bundle arg0) {
		Log.e(TAG, "onCreate");
		super.onCreate(arg0);
		setContentView(R.layout.activity_single_fragment);
		FragmentManager fm=getFragmentManager();
		Fragment fragment=fm.findFragmentById(R.id.id_fragment_container);
		
		if (fragment==null) {
			Log.e(TAG, "fragment=null  called");
			fragment=createFragment();
			fm.beginTransaction().add(R.id.id_fragment_container, fragment).commit();
		}
		
	}
}
