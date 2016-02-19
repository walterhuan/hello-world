package com.example.researchpoject;

import com.example.researchpoject.dialogfragment.EditNameDialogFragment;
import com.example.researchpoject.dialogfragment.LoginDialogFragment;
import com.example.researchpoject.dialogfragment.LoginDialogFragment.LoginInputListener;
import com.example.researchpoject.utils.UtilsConstant;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class FragmentDialogActivity extends Activity implements LoginInputListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment_dialog_use);

	}
	public void BtnOnClicks(View view){
		switch (view.getId()) {
		case R.id.id_fmd_activity_btn_one:
			EditNameDialogFragment editNameDialog=new EditNameDialogFragment();
			editNameDialog.show(getFragmentManager(), "EditNameDialog");
			break;

		case R.id.id_fmd_activity_btn_two:
			LoginDialogFragment dialog= new LoginDialogFragment();
			dialog.show(getFragmentManager(), "loginDialog");
			break;
			
		case R.id.id_fmd_activity_btn_three:
			showDialogInDifferentScreen(view);
			break;
		}
		
	}
	
	public void showDialogInDifferentScreen(View view){
		FragmentManager fm=getFragmentManager();
		EditNameDialogFragment newFragment=new EditNameDialogFragment();
		boolean mIsLargeLayout="yes".equals(getResources().getString(R.string.large_layout))?true:false;
		Log.d(UtilsConstant.TAG,"mIsLargeLayout="+ mIsLargeLayout);
		if (mIsLargeLayout) {
			 // The device is using a large layout, so show the fragment as a  
            // dialog  
			newFragment.show(fm, "dialog");
			
		}else{  
			FragmentTransaction ft=fm.beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.replace(R.id.id_ly, newFragment).commit();
		}
	}
	
	@Override
	public void onLoginInputComplete(String username, String password) {
		Toast.makeText(this, "账号:"+username+"  密码:"+password, Toast.LENGTH_SHORT).show();
	}
	
}
