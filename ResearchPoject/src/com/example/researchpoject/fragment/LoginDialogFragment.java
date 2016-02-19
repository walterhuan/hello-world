package com.example.researchpoject.fragment;

import com.example.researchpoject.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
