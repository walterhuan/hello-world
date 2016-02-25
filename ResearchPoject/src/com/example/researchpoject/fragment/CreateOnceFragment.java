package com.example.researchpoject.fragment;

import com.example.researchpoject.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Fragment可以添加自己的MenuItem到Activity的ActionBar或者可选菜单中。
 * 
 * a、在Fragment的onCreate中调用 setHasOptionsMenu(true);
 * 
 * b、然后在Fragment子类中实现onCreateOptionsMenu
 * 
 * c、如果希望在Fragment中处理MenuItem的点击，也可以实现onOptionsItemSelected；
 * 当然了Activity也可以直接处理该MenuItem的点击事件。
 * 
 * 
 */
public class CreateOnceFragment extends Fragment {

	private static final String TAG = "CreateOnceFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_junp_one, container,
				false);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "onCreate");
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.id_menu_fra_test:
			 Toast.makeText(getActivity(), "FragmentMenuItem1", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDestroyView() {
		Log.e(TAG, "onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}

}
