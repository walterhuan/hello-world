package com.example.researchpoject.volley;

import java.io.IOException;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.researchpoject.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class VolleyTestOne extends Activity {
	private RequestQueue mRQ;
	private ImageView mImageV;
	private NetworkImageView mNetworkImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volley_main);
		mImageV = (ImageView) findViewById(R.id.id_imageView1);
		mNetworkImageView = (NetworkImageView) findViewById(R.id.network_image_view);

		mRQ = Volley.newRequestQueue(this);
		// useStringRequestGet();
		// useJSONRequest();
		// useImageRequest();
	}

	public void myOnClick(View view) {
		// useImageRequest();
		// useImageLoader();
		//uesNetworkImageView();
		//useXMLRequest();
		useGsonRequest();
	}

	private void useGsonRequest() {
		GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(
				"http://www.weather.com.cn/adat/sk/101010100.html", Weather.class,
				new Response.Listener<Weather>() {
					@Override
					public void onResponse(Weather weather) {
						WeatherInfo weatherInfo = weather.getWeatherinfo();
						Log.d("TAG", "city is " + weatherInfo.getCity());
						Log.d("TAG", "temp is " + weatherInfo.getTemp());
						Log.d("TAG", "time is " + weatherInfo.getTime());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
	    mRQ.add(gsonRequest);  
		
	}

	private void useXMLRequest() {
		XMLRequest xmlRequest=new XMLRequest("http://flash.weather.com.cn/wmaps/xml/china.xml", 
				new Response.Listener<XmlPullParser>() {

					@Override
					public void onResponse(XmlPullParser response) {
						Log.d("onResponse", "response is "+response);
						try {
							int eventType=response.getEventType();
							while (eventType!=XmlPullParser.END_DOCUMENT) {
								switch (eventType) {
								case XmlPullParser.START_TAG:
									String nodeName=response.getName();
									if ("city".equals(nodeName)) {
										String pName=response.getAttributeValue(0);
										Log.d("TAG", "pName is "+pName);
									}
									break;
								}
								eventType=response.next();
								 
								
							}
						} catch (XmlPullParserException e) {  
		                    e.printStackTrace();  
		                } catch (IOException e) {  
		                    e.printStackTrace();  
		                }  
						
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);  
						
					}
				});
		mRQ.add(xmlRequest);
		
	}

	/**
	 * NetworkImageView是一个自定义控制，它是继承自ImageView的，具备ImageView控件的所有功能，
	 * 并且在原生的基础之上加入了加载网络图片的功能。NetworkImageView控件的用法要比前两种方式更加简单，大致可以分为以下五步：
	 * 
	 * 1. 创建一个RequestQueue对象。
	 * 
	 * 2. 创建一个ImageLoader对象。
	 * 
	 * 3. 在布局文件中添加一个NetworkImageView控件。
	 * 
	 * 4. 在代码中获取该控件的实例。
	 * 
	 * 5. 设置要加载的图片地址。 NetworkImageView会始终呈现给我们一张大小刚刚好的网络图片，不会多占用任何一点内存，
	 * 这也是NetworkImageView最简单好用的一点吧。
	 * 
	 * 当然了，如果你不想对图片进行压缩的话，其实也很简单，
	 * 只需要在布局文件中把NetworkImageView的layout_width和layout_height都设置成wrap_content就可以了
	 * ，这样NetworkImageView就会将该图片的原始大小展示出来，不会进行任何压缩。
	 */
	private void uesNetworkImageView() {
		ImageLoader mImageLoader = new ImageLoader(mRQ, new BitmapCache());
		mNetworkImageView.setDefaultImageResId(R.drawable.hoj);
		mNetworkImageView.setErrorImageResId(R.drawable.hoi);
		mNetworkImageView
				.setImageUrl(
						"http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
						mImageLoader);

	}

	/**
	 * ImageLoader明显要比ImageRequest更加高效，因为它不仅可以帮我们对图片进行缓存，还可以过滤掉重复的链接，避免重复发送请求 1.
	 * 创建一个RequestQueue对象。
	 * 
	 * 2. 创建一个ImageLoader对象。
	 * 
	 * 3. 获取一个ImageListener对象。
	 *   
	 * 4. 调用ImageLoader的get()方法加载网络上的图片。
	 */
	private void useImageLoader() {
		// 但是如果想要写一个性能非常好的ImageCache，最好就要借助Android提供的LruCache功能了
		// Android高效加载大图、多图解决方案，有效避免程序OOM。
		// ImageCache imageCache = new ImageCache() {
		//
		// @Override
		// public void putBitmap(String url, Bitmap bitmap) {
		//
		// }
		//
		// @Override
		// public Bitmap getBitmap(String url) {
		// return null;
		// }
		// };
		ImageLoader imageLoader = new ImageLoader(mRQ, new BitmapCache());
		// 第一个参数指定用于显示图片的ImageView控件，第二个参数指定加载图片的过程中显示的图片，第三个参数指定加载图片失败的情况下显示的图片。
		ImageListener listener = ImageLoader.getImageListener(mImageV,
				R.drawable.hoj, R.drawable.hoi);
		imageLoader
				.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
						listener, 200, 200);

	}

	/**
	 * 这里我们将缓存图片的大小设置为10M 这样我们就把ImageLoader的功能优势充分利用起来了。
	 * 
	 */
	public class BitmapCache implements ImageCache {
		private LruCache<String, Bitmap> mCache;

		public BitmapCache() {
			int maxSize = 10 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				protected int sizeOf(String key, Bitmap bitmap) {
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};

		}

		@Override
		public Bitmap getBitmap(String url) {

			return mCache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			mCache.put(url, bitmap);
		}
	}

	/**
	 * 第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中。第三第四个参数分别用于指定允许图片最大的宽度和高度，
	 * 如果指定的网络图片的宽度或高度大于这里的最大值
	 * ，则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。第五个参数用于指定图片的颜色属性
	 * ，Bitmap.Config下的几个常量都可以在这里使用
	 * ，其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小
	 * 。第六个参数是图片请求失败的回调
	 */
	private void useImageRequest() {
		ImageRequest imageRequest = new ImageRequest(
				"http://img3.imgtn.bdimg.com/it/u=3841157212,2135341815&fm=21&gp=0.jpg",
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						mImageV.setImageBitmap(response);
					}
				}, 0, 0, Config.RGB_565, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);

					}
				});
		mRQ.add(imageRequest);
	}

	private void useJSONRequest() {

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				"http://182.151.210.179/loadPassengerInfo_m.action?loadPassengers={%22segments%22:%223U8888%22,%22flag%22:%22flightNo%22,%22flightDate%22:%222015-9-7%22}&userName=gujiao&userPwd=aaaa1234!",
				null, new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("TAG", response.toString());

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);

					}
				});
		mRQ.add(jsonObjectRequest);
	}

	/**
	 * 1. 创建一个RequestQueue对象。
	 * 
	 * 2. 创建一个StringRequest对象。
	 * 
	 * 3. 将StringRequest对象添加到RequestQueue里面。
	 * 
	 * 不过大家都知道，HTTP的请求类型通常有两种，GET和POST，刚才我们使用的明显是一个GET请求
	 */
	private void useStringRequestGet() {
		StringRequest stringRequest = new StringRequest("http://www.baidu.com",
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						Log.i("TAG", response);

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);

					}
				});
		mRQ.add(stringRequest);
	}
}
