package com.example.researchpoject;

import com.example.researchpoject.fragment.JumpOneFragment;
import com.example.researchpoject.fragment.JumpThreeFragment;
import com.example.researchpoject.fragment.JumpOneFragment.FOneBtnClickListener;
import com.example.researchpoject.fragment.JumpTwoFragment.FTwoBtnClickListener;
import com.example.researchpoject.fragment.JumpTwoFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
/**
 * 
a、如果你Activity中包含自己管理的Fragment的引用，可以通过引用直接访问所有的Fragment的public方法
b、如果Activity中未保存任何Fragment的引用，那么没关系，每个Fragment都有一个唯一的TAG或者ID,
     可以通过getFragmentManager.findFragmentByTag()或者findFragmentById()获得任何Fragment实例，
     然后进行操作。
c、在Fragment中可以通过getActivity得到当前绑定的Activity的实例，然后进行操作。
注：如果在Fragment中需要Context，可以通过调用getActivity(),如果该Context需要在Activity被销毁后还存在，
      则使用getActivity().getApplicationContext()。
 * 
 * */
public class FragmentJumpActivity extends Activity implements FOneBtnClickListener,FTwoBtnClickListener{
	private JumpOneFragment mFOne;
	private JumpTwoFragment mFTwo;
	private JumpThreeFragment mFThree;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_jump_fragment_use);
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		ft.add(R.id.id_content, new JumpOneFragment(), "one");
//		ft.addToBackStack(null);   没有加入此行会销毁实例直接finish
		ft.commit();
	}

	/**
	 * JumpOneFragment 按钮点击时的回调
	 */
	@Override
	public void onFOneBtnClick() {
		if (mFTwo==null) {
			mFTwo=new JumpTwoFragment();
			mFTwo.setFTwoBtnClickListener(this);
		}
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		ft.replace(R.id.id_content, mFTwo, "Two");
		ft.addToBackStack(null);
		ft.commit();
		
	}

	/**
	 * JumpTwoFragment 按钮点击时的回调
	 */
	@Override
	public void onFTwoBtnClick() {
		if(mFThree==null){
			mFThree=new JumpThreeFragment();
		}
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		ft.hide(mFTwo);
		ft.add(R.id.id_content, mFThree, "Three");
		ft.addToBackStack(null);
		ft.commit();
	}
}
