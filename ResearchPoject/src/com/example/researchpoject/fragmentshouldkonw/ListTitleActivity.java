package com.example.researchpoject.fragmentshouldkonw;

import android.app.Fragment;
import android.util.Log;

public class ListTitleActivity extends SingleFragmentActivity {
    private ListTitleFragment mListFragment;
    private static final String TAG="ListTitleActivity";
	@Override
	protected Fragment createFragment() {
		Log.e(TAG, "Fragment createFragment");
		mListFragment=new ListTitleFragment();
		return mListFragment;
	}

}
