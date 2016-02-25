package com.example.researchpoject;

import com.example.researchpoject.fragment.CreateOnceFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 当屏幕发生旋转，Activity发生重新启动，默认的Activity中的Fragment也会跟着Activity重新创建；
 * 这样造成当旋转的时候，本身存在的Fragment会重新启动
 * ，然后当执行Activity的onCreate时，又会再次实例化一个新的Fragment，这就是出现的原因。
 * 
 * 通过检查onCreate的参数Bundle savedInstanceState就可以判断，当前是否发生Activity的重新创建
 */
public class FragmentCreateOnceActivity extends Activity {
	private static final String TAG = "FragmentCreateOnceActivity";
    private CreateOnceFragment mFOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.activity_jump_fragment_use);
    //	Log.e(TAG, "savedInstanceState="+savedInstanceState);
    	if (savedInstanceState==null) {
			mFOne=new CreateOnceFragment();
			FragmentManager fm=getFragmentManager();
			FragmentTransaction ft=fm.beginTransaction();
			ft.add(R.id.id_content, mFOne, "ONE");
			ft.commit();
		}
    	super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();  
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
}
