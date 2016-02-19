package com.example.researchpoject;

import com.example.researchpoject.fragment.EditNameDialogFragment;
import com.example.researchpoject.fragment.LoginDialogFragment;
import com.example.researchpoject.fragment.LoginDialogFragment.LoginInputListener;

import android.app.Activity;
import android.os.Bundle;
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
		}
		
	}
	@Override
	public void onLoginInputComplete(String username, String password) {
		Toast.makeText(this, "账号:"+username+"  密码:"+password, Toast.LENGTH_SHORT).show();
	}
	
}
