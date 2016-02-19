package com.example.researchpoject.fragment;

import com.example.researchpoject.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class JumpTwoFragment extends Fragment implements OnClickListener {
	private Button mBtn;
	private FTwoBtnClickListener fTwoBtnClickListener;
    public interface FTwoBtnClickListener{
    	void onFTwoBtnClick();
    }
    //设置回调接口
    public void setFTwoBtnClickListener(FTwoBtnClickListener fTwoBtnClickListener){
    	this.fTwoBtnClickListener=fTwoBtnClickListener;
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_junp_two, container, false);
		mBtn = (Button) view.findViewById(R.id.id_fgtjump_btn_two);
		mBtn.setText("Second Fragment Button ");
		mBtn.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		if (null!=fTwoBtnClickListener) {
			fTwoBtnClickListener.onFTwoBtnClick();
		}
		
	}

	/**
	 跳转用的hide和将事物添加到回退栈，实例和视图都没被销毁 
	 */
//	@Override
//	public void onClick(View v) {
//		JumpThreeFragment fThree=new JumpThreeFragment();
//		FragmentManager fm=getFragmentManager();
//		FragmentTransaction ft=fm.beginTransaction();
//		ft.hide(this);
//		ft.add(R.id.id_content, fThree,"three");
//		ft.addToBackStack(null);
//		ft.commit();
//
//	}
}
