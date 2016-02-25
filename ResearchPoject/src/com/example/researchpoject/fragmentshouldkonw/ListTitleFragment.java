package com.example.researchpoject.fragmentshouldkonw;

import java.util.Arrays;
import java.util.List;


import android.R;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListTitleFragment extends ListFragment {
	public static final String TAG="ListTitleFragment";
	public static final int REQUEST_DETAIL = 0x110;
	private List<String> mTitles = Arrays.asList("Hello", "World", "Android","Hello", "World", "Android","World","World","World","Hello", "Hello", "Hello", "Android","Hello","Hello","Hello","Hello","Hello","Hello","Hello","Hello","Hello","Hello");
	private int mCurrentPos ; 
	private ArrayAdapter<String> mAdapter ; 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.e(TAG, "onActivityCreated"+"   bundle="+savedInstanceState+"   adpter="+mAdapter);
		super.onActivityCreated(savedInstanceState);
		setListAdapter(mAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, mTitles));
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	    Log.e(TAG,"position="+position);

	}

}
