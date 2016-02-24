package com.example.researchpoject.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * 如果重新启动你的Activity需要恢复大量的数据，重新建立网络连接，或者执行其他的密集型操作，这样因为配置发生变化而完全重新启动可能会是一个慢的用户体验
 * 。 并且，使用系统提供的onSaveIntanceState()的回调中，使用Bundle来完全恢复你Activity的状态是可能是不现实的（
 * Bundle不是设计用来携带大量数据的（例如bitmap），
 * 并且Bundle中的数据必须能够被序列化和反序列化），这样会消耗大量的内存和导致配置变化缓慢。
 * 在这样的情况下，当你的Activity因为配置发生改变而重启，
 * 你可以通过保持一个Fragment来缓解重新启动带来的负担。这个Fragment可以包含你想要保持的有状态的对象的引用。
 * 当Android系统因为配置变化关闭你的Activity的时候，你的Activity中被标识保持的fragments不会被销毁。
 * 你可以在你的Activity中添加这样的fragements来保存有状态的对象。 在运行时配置发生变化时，在Fragment中保存有状态的对象 a)
 * 继承Fragment，声明引用指向你的有状态的对象 b) 当Fragment创建时调用setRetainInstance(boolean) c)
 * 把Fragment实例添加到Activity中 d) 当Activity重新启动后，使用FragmentManager对Fragment进行恢复
 * 
 * @author Walter
 * 
 */
public class RetainedFragment extends Fragment {
	// data object we want to retain
	private Bitmap data;

	// this method is only called once for this fragment
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// retain this fragment
		setRetainInstance(true);
	}

	public void setData(Bitmap data) {
		this.data = data;
	}

	public Bitmap getData() {
		return data;
	}
}
