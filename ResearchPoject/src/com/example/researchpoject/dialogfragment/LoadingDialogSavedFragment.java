package com.example.researchpoject.dialogfragment;

import com.example.researchpoject.R;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class LoadingDialogSavedFragment extends DialogFragment {
	private String msg = "Loading";

	public void setMessage(String msg) {
		this.msg = msg;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater=getActivity().getLayoutInflater();
		View view=inflater.inflate(R.layout.fragment_login_dialog_saved, null);
		TextView title=(TextView)view.findViewById(R.id.id_dialog_loading_msg);
		title.setText(msg);
		Dialog dialog=new Dialog(getActivity(), R.style.dialog);
		dialog.setContentView(view);
		return dialog;
	}
}
