package com.example.researchpoject.adapter;

import com.example.researchpoject.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
    private String[] data=null;
	public MainAdapter(Context context,String[] data) {
		this.inflater = LayoutInflater.from(context);
		this.data=data;
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int arg0) {
		return data[arg0];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.main_lv_item, null);
			holder.text=(TextView)convertView.findViewById(R.id.main_lv_text);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		holder.text.setText(data[position]);
		return convertView;
	}

	private class ViewHolder{
		TextView text;
	}
}
