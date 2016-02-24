package com.example.researchpoject.fragment;

import com.example.researchpoject.asynctask.MyAsyncTask;

import android.app.Fragment;
import android.os.Bundle;

public class OtherRetainedFragment extends Fragment {

	//保存一个异步的任务
	private MyAsyncTask myAsyncTask;
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	public void setData(MyAsyncTask myAsyncTask){
		this.myAsyncTask=myAsyncTask;
	}
	
	public MyAsyncTask getData(){
		return myAsyncTask;
	}
	
}
