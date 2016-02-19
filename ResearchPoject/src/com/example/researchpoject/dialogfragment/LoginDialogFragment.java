package com.example.researchpoject.dialogfragment;

import com.example.researchpoject.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
/**
 * 传统的new AlertDialog在屏幕旋转时，第一不会保存用户输入的值，第二还会报异常，
 * 因为Activity销毁前不允许对话框未关闭。
 * 而通过DialogFragment实现的对话框则可以完全不必考虑旋转的问题。
 * @author Walter
 *
 */
public class LoginDialogFragment extends DialogFragment {
	
	private EditText mUserName;
	private EditText mPassWord;
	
	public interface LoginInputListener{
		void onLoginInputComplete(String username,String password);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		// Get the layout inflater  
		LayoutInflater inflater=getActivity().getLayoutInflater();
		View view=inflater.inflate(R.layout.fragment_login_dialog, null);
		mUserName=(EditText)view.findViewById(R.id.id_txt_username);
		mPassWord=(EditText)view.findViewById(R.id.id_txt_password);
		// Inflate and set the layout for the dialog  
        // Pass null as the parent view because its going in the dialog layout  
		builder.setView(view)
		           // Add action buttons  
		           .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				LoginInputListener listener=(LoginInputListener)getActivity();
				listener.onLoginInputComplete(mUserName.getText().toString(),
						                                        mPassWord.getText().toString());
			}
		}).setNegativeButton("Cancel", null);
		
		return builder.show();
	}
}
