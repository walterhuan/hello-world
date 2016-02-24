package com.example.researchpoject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.researchpoject.dialogfragment.LoadingDialogSavedFragment;
import com.example.researchpoject.fragment.RetainedFragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class FragmentRetainDataActivity extends Activity {
	private static final String TAG = "FragmentRetainDataActivity";
	private RetainedFragment dataFragment;
	private DialogFragment mLoadingDialog;
	private ImageView mImageView;
	private Bitmap mBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_retain_data);
		Log.e(TAG, "onCreat");
		// find the retained fragment on activity restarts
		FragmentManager fm = getFragmentManager();
		dataFragment = (RetainedFragment) fm.findFragmentByTag("data");
		// create the fragment and data the first time
		if (dataFragment == null) {
			// add the fragment
			dataFragment = new RetainedFragment();
			fm.beginTransaction().add(dataFragment, "data").commit();
		}
		mBitmap = collectMyLoadData();
		initData();
	}

	private void initData() {
		mImageView = (ImageView) findViewById(R.id.id_imageView);
		if (mBitmap == null) {
			mLoadingDialog = new LoadingDialogSavedFragment();
			mLoadingDialog.show(getFragmentManager(), "LOADING_DIALOG");
			RequestQueue newRequestQueue = Volley
					.newRequestQueue(FragmentRetainDataActivity.this);
			// http://imgnews.gmw.cn/attachement/jpg/site2/20160201/1688966784616542707.jpg
			ImageRequest imageRequest = new ImageRequest(
					"http://imgnews.gmw.cn/attachement/jpg/site2/20160201/1688966784616542707.jpg",
					new Response.Listener<Bitmap>() {

						@Override
						public void onResponse(Bitmap response) {
							mBitmap = response;
							mImageView.setImageBitmap(mBitmap);
							// load the data from the web
							dataFragment.setData(mBitmap);
							mLoadingDialog.dismiss();
						}

					}, 0, 0, Config.RGB_565, null);
			newRequestQueue.add(imageRequest);
		} else {
			mImageView.setImageBitmap(mBitmap);
		}

	}

	@Override
	protected void onDestroy() {
		Log.e(TAG, "onDestroy");
		super.onDestroy();
		// store the data in the fragment  
        dataFragment.setData(mBitmap);  
	}

	private Bitmap collectMyLoadData() {
		return dataFragment.getData();
	}

}
