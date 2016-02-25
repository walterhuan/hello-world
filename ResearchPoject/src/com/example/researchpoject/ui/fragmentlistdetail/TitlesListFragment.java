package com.example.researchpoject.ui.fragmentlistdetail;

import com.example.researchpoject.R;
import com.example.researchpoject.utils.UtilsConstant;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesListFragment extends ListFragment {

	int mCurCheckPosition=0;
	int mShownCheckPosition=-1;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_activated_1,UtilsConstant.titles));
		if (savedInstanceState != null) {
			mCurCheckPosition= savedInstanceState.getInt("curChoice", 0);
			mShownCheckPosition = savedInstanceState.getInt("shownChoice", -1); 
		}
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		showDetails(mCurCheckPosition);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
		outState.putInt("shownChoice", mShownCheckPosition);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	private void showDetails(int position) {
		mCurCheckPosition = position;
		getListView().setItemChecked(position, true);
		System.out.println("showDetails mCurCheckPosition="+position+"   mShownCheckPosition="+mShownCheckPosition);
		if (mShownCheckPosition !=mCurCheckPosition) {
			DetailsFragment df= DetailsFragment.newInstance(position);
			FragmentTransaction ft=getFragmentManager().beginTransaction();
			ft.replace(R.id.details, df);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			mShownCheckPosition=position;
		}
	}
}
