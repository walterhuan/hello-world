package com.example.researchpoject.asynctask;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.researchpoject.utils.UtilInterfaces.GetDatasListener;


import android.app.DialogFragment;
import android.content.Context;
import android.os.AsyncTask;



public class LoadDataAsyncTask extends AsyncTask<Void, Void, Void> {

	private ArrayList<String> mDatas;
	private DialogFragment mLoadingDialog;
	private Context mctx;
	public LoadDataAsyncTask(Context mctx,ArrayList<String> mDatas,DialogFragment mLoadingDialog){
		super();
		this.mDatas=mDatas;
		this.mLoadingDialog=mLoadingDialog;
		this.mctx=mctx;
	}
	
	/**
	 * 模拟耗时操作
	 */
	private ArrayList<String> generateTimeConsumingDatas(){
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			
		}
		return new ArrayList<String>(Arrays.asList("通过Fragment保存大量数据",  
                "onSaveInstanceState保存数据",  
                "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",  
                "Spark"));
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		mDatas=generateTimeConsumingDatas();
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		mLoadingDialog.dismiss();
		//((SavedInstanceStateUsingActivity)mctx).initAdapter(mDatas);
		GetDatasListener getDatasListener=(GetDatasListener)mctx;
		getDatasListener.putDatas(mDatas);
	}

}
