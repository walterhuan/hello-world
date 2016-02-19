package com.example.researchpoject.dialogfragment;

import com.example.researchpoject.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class EditNameDialogFragment extends DialogFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 去掉Dialog的标题
		if ("yes".equals(getResources().getString(R.string.large_layout))) {
			getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		View view = inflater.inflate(R.layout.fragment_edit_name, container,
				false);//不设置为False容易出问题 当把dialog当做view直接放layout里面replace
		return view;
	}
}
