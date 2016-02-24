package com.example.researchpoject.asynctask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.researchpoject.FixProblemActivity;
import com.example.researchpoject.dialogfragment.LoadingDialogSavedFragment;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

	private FixProblemActivity activity;
	/**
	 * 是否完成
	 */
	private boolean isCompleted;
	/**
	 * 进度框
	 */
	private LoadingDialogSavedFragment mLoadingDialog;
	private List<String> items;
	
	public MyAsyncTask(FixProblemActivity activity){
		this.activity=activity;
	}
	/**
	 * 开始时，显示加载框
	 */
	@Override
	protected void onPreExecute() {
		mLoadingDialog=new LoadingDialogSavedFragment();
		mLoadingDialog.show(activity.getFragmentManager(), "LOADING");
		super.onPreExecute();
	}
	/**
	 * 加载数据
	 */
	@Override
	protected Void doInBackground(Void... params) {
		items=loadingData();
		return null;
	}

	/**
	 * 加载完成回调当前的Activity
	 * 
	 */
	@Override
	protected void onPostExecute(Void result) {
		isCompleted=true;
		notifyActivityTaskCompleted();
		if (mLoadingDialog!=null) {
			mLoadingDialog.dismiss();
		}
		super.onPostExecute(result);
	}
	
	public List<String> getItems(){
		return items;
	}
	
	
	private List<String> loadingData(){
		try {
			Thread.sleep(6000);
		} catch (Exception e) {
			
		}
		return new ArrayList<String>(Arrays.asList("通过Fragment保存大量数据",  
                "onSaveInstanceState保存数据",  
                "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",  
                "Spark"));
		
	}
	/**
	 * 设置Activity 因为Activity会一直变化
	 */
	public void setActivity(FixProblemActivity activity){
		//如果上一个Activity销毁，将与上一个Activity绑定的DialogFragment销毁
		if (activity==null) {
			mLoadingDialog.dismiss();
		}
		//设置当前的Activity
		this.activity=activity;
		//开启一个与当前Activity绑定的等待框
		if (activity!=null & !isCompleted) {
			mLoadingDialog=new LoadingDialogSavedFragment();
			mLoadingDialog.show(activity.getFragmentManager(), "LOADING");
		}
		//如果完成，通知Activity
		if (isCompleted) {
			notifyActivityTaskCompleted();
		}
	}
	
	
	private void notifyActivityTaskCompleted(){
		if (null!=activity) {
			activity.onTaskCompleted();
		}
	}
}
