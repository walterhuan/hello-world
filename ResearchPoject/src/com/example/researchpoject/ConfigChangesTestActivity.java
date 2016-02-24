package com.example.researchpoject;

import java.util.ArrayList;

import com.example.researchpoject.asynctask.LoadDataAsyncTask;
import com.example.researchpoject.dialogfragment.LoadingDialogSavedFragment;
import com.example.researchpoject.utils.UtilInterfaces.GetDatasListener;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.res.Configuration; 
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

/**
 *     <activity  
            android:name=".ConfigChangesTestActivity"  
            android:configChanges="screenSize|orientation" >  
        </activity>  
 * 低版本的API只需要加入orientation，而高版本的则需要加入screenSize。 
 * @author Walter
 *
 */
public class ConfigChangesTestActivity extends ListActivity implements GetDatasListener{
	private static final String TAG = "ConfigChangesTestActivity";
	private ListAdapter mAdapter;
	private ArrayList<String> mDatas;
	private DialogFragment mLoadingDialog;
	private LoadDataAsyncTask mLoadataAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		initData(savedInstanceState);
	}

	
	private void initData(Bundle savedInstanceState) {
		mLoadingDialog=new LoadingDialogSavedFragment();
		mLoadingDialog.show(getFragmentManager(), "LoadingDialog");
		mLoadataAsyncTask=new LoadDataAsyncTask(this, mDatas, mLoadingDialog);
		mLoadataAsyncTask.execute();
		
	}
	
	private void initAdapter(){
		mAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
		setListAdapter(mAdapter);
	}


	@Override
	public void putDatas(ArrayList<String> mDatas) {
		this.mDatas=mDatas;
		initAdapter();
	}
	
	 /** 
     * 当配置发生变化时，不会重新启动Activity。但是会回调此方法，用户自行进行对屏幕旋转后进行处理 
     */  
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE) {
			Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();  
		}else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
			Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();  
		}
	}
	
}
