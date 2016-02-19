package com.example.researchpoject.fragment;

import com.example.researchpoject.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class JumpOneFragment extends Fragment implements OnClickListener{
	private Button mBtn;
	/**
	 * 设置按钮点击的回调
	 */
	public interface FOneBtnClickListener{
		void onFOneBtnClick();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_junp_one, container, false);
		mBtn=(Button)view.findViewById(R.id.id_fgtjump_btn_one);
		mBtn.setText("Button In FirstFragment    ");
		mBtn.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		if(getActivity() instanceof FOneBtnClickListener){
			((FOneBtnClickListener)getActivity()).onFOneBtnClick();
		}
		
	}
	
	
	/**
	 * 跳转时用的replace和将事物添加到回退栈(ft.addToBackStack(null);) 实例不被销毁但视图被销毁
	 */
//	@Override
//	public void onClick(View v) {
//		JumpTwoFragment fTwo=new JumpTwoFragment();
//		FragmentManager fm=getFragmentManager();
//		FragmentTransaction ft=fm.beginTransaction();
//		ft.replace(R.id.id_content, fTwo, "two");
//		ft.addToBackStack(null);
//		ft.commit();
//		
//	}
	
	
	
}
