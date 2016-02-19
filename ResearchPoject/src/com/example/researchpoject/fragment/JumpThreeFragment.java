package com.example.researchpoject.fragment;

import com.example.researchpoject.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class JumpThreeFragment extends Fragment {
	private Button mbtn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_junp_three, container, false);
		mbtn=(Button)view.findViewById(R.id.id_fgtjump_btn_three);
		mbtn.setText("Third Fragment Button");
		return view;
	}
}
