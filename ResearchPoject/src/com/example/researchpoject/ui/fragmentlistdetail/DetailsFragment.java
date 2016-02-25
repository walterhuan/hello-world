package com.example.researchpoject.ui.fragmentlistdetail;

import com.example.researchpoject.utils.UtilsConstant;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

	public static DetailsFragment newInstance(int index){
		DetailsFragment f=new DetailsFragment();
		Bundle args=new Bundle();
		args.putInt("index", index);
		f.setArguments(args);
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("DF onCreateView"+ "      container="+container  );  
		if (container==null) {
			return null;
		}
		ScrollView scroller=new ScrollView(getActivity());
		TextView text=new TextView(getActivity());
		
		 int padding = (int) TypedValue.applyDimension(  
                TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources()  
                .getDisplayMetrics());  
		 text.setPadding(padding, padding, padding, padding);
		 
		 scroller.addView(text);
		 text.setText(UtilsConstant.details[getArguments().getInt("index",0)]);
		 
		return scroller;
	}
}
