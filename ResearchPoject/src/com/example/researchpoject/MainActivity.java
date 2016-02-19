package com.example.researchpoject;

import com.example.researchpoject.adapter.MainAdapter;
import com.example.researchpoject.utils.UtilsConstant;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView mainListView;
	private Context mctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mctx=this;
		mainListView = (ListView) findViewById(R.id.listview_main);
		MainAdapter adp_main = new MainAdapter(this,UtilsConstant.sListsStrings);
		mainListView.setAdapter(adp_main);
		mainListView.setOnItemClickListener(myItemClick);
	}

	public OnItemClickListener myItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0://用浏览器打开网页
				 final Uri uri = Uri.parse("http://www.baidu.com");
				 final Intent it = new Intent(Intent.ACTION_VIEW, uri);
				 startActivity(it);
				break;

			case 1://Fragment 静态应用
				Intent it2=new Intent(mctx,FragmentStaticUseActivity.class);
				startActivity(it2);
				break;
				
			case 2://Fragment Jump使用
				Intent it3=new Intent(mctx,FragmentJumpActivity.class);
				startActivity(it3);
				break;
				
			case 3://Fragment Dialog使用
				Intent it4=new Intent(mctx,FragmentDialogActivity.class);
				startActivity(it4);
				break;
				
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}