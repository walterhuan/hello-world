package com.example.researchpoject;

import java.util.List;

import com.example.researchpoject.asynctask.MyAsyncTask;
import com.example.researchpoject.fragment.OtherRetainedFragment;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class FixProblemActivity extends ListActivity {

	private static final String TAG="FixProblemActivity";
	private ListAdapter mAdapter;
	private List<String> mDatas;
	private OtherRetainedFragment dataFragment;
	private MyAsyncTask mMyTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		FragmentManager fm=getFragmentManager();
		dataFragment=(OtherRetainedFragment)fm.findFragmentByTag("data");
		if (dataFragment==null) {
			dataFragment=new OtherRetainedFragment();
			fm.beginTransaction().add(dataFragment, "data").commit();
		}
		mMyTask=dataFragment.getData();
		if (mMyTask!=null) {
			mMyTask.setActivity(this);
		}else{
			mMyTask=new MyAsyncTask(this);
			dataFragment.setData(mMyTask);
			mMyTask.execute();
		}
	}
	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		Log.e(TAG, "onRestoreInstanceState");
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mMyTask.setActivity(null);
		super.onSaveInstanceState(outState);
		Log.e(TAG, "onSaveInstanceState");
		
	}
	@Override
	protected void onDestroy() {
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}
	/**
	 * 回调
	 */
	public void onTaskCompleted(){
		mDatas=mMyTask.getItems();
		mAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
		setListAdapter(mAdapter);
	}
}
