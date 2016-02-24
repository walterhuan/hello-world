package com.example.researchpoject;

import java.util.ArrayList;

import com.example.researchpoject.asynctask.LoadDataAsyncTask;
import com.example.researchpoject.dialogfragment.LoadingDialogSavedFragment;
import com.example.researchpoject.utils.UtilInterfaces.GetDatasListener;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.app.PendingIntent.OnFinished;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
/**
 * 
 * 界面为一个ListView，onCreate中启动一个异步任务去加载数据，这里使用Thread.sleep模拟了一个耗时操作；
 * 当用户旋转屏幕发生重新启动时，会onSaveInstanceState中进行数据的存储，在onCreate中对数据进行恢复，
 * 免去了不必要的再加载一遍。
 * 运行结果：
 * 当正常加载数据完成之后，用户不断进行旋转屏幕，log会不断打出：
 * onSaveInstanceState->onDestroy->onCreate->onRestoreInstanceState，
 * 验证我们的确是重新启动了，但是我们没有再次去进行数据加载。
 * 如果在加载的时候，进行旋转，则会发生错误，异常退出
 * （退出原因：dialog.dismiss()时发生NullPointException，因为与当前对话框绑定的FragmentManager为null，
 * 又有兴趣的可以去Debug，这个不是关键）。
 *
 */
public class SavedInstanceStateUsingActivity extends ListActivity implements GetDatasListener{
	private static final String TAG = "SavedInstanceStateUsingActivity";  
	private ListAdapter mAdapter;
	private ArrayList<String> mDatas;
	private DialogFragment mLoadingDialog;
	private LoadDataAsyncTask mLoadAsyncTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");
		initData(savedInstanceState);
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(Bundle savedInstanceState){
		if(savedInstanceState!=null){
			mDatas=savedInstanceState.getStringArrayList("mDatas");
		}
		if (mDatas==null) {
			mLoadingDialog=new LoadingDialogSavedFragment();
			mLoadingDialog.show(getFragmentManager(), "LoadingDialogSavedFragment");
			mLoadAsyncTask=new LoadDataAsyncTask(this, mDatas, mLoadingDialog);
            mLoadAsyncTask.execute();
		}else{
			initAdapter();
		}
	}

	public void initAdapter() {
		mAdapter=new ArrayAdapter<String>(
				SavedInstanceStateUsingActivity.this, android.R.layout.simple_list_item_1, mDatas);
		setListAdapter(mAdapter);
		
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle state) {
		Log.e(TAG, "onRestoreInstanceState");
		super.onRestoreInstanceState(state);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.e(TAG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
		outState.putSerializable("mDatas", mDatas);
	}
	
	@Override
	protected void onDestroy() {
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}
 
	@Override
	public void finish() {
		Log.e(TAG, "finish");
		super.finish();
	}

	@Override
	public void putDatas(ArrayList<String> mDatas) {
		this.mDatas=mDatas;
		initAdapter();
	}


}
