package com.example.researchpoject;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AsynTaskAndProgressDialogActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asyntask_progressdialog);
	}
	public void openAsyncTaskAndSaveInstanceState(View view)
	{
		Intent intent = new Intent(this, SavedInstanceStateUsingActivity.class);
		startActivity(intent);
	}

	public void openFragmentRetainDataActivity(View view)
	{
		Intent intent = new Intent(this, FragmentRetainDataActivity.class);
		startActivity(intent);
	}

	public void openConfigChangesTestActivity(View view)
	{
		Intent intent = new Intent(this, ConfigChangesTestActivity.class);
		startActivity(intent);
	}

	public void openFixProblemsActivity(View view)
	{
		Intent intent = new Intent(this, FixProblemActivity.class);
		startActivity(intent);
	}
}
