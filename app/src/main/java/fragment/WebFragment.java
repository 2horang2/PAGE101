package fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import page.page.page101.R;

public class WebFragment extends Fragment{

	private WebView wv;

	// TODO: Rename and change types and number of parameters
	public static WebFragment newInstance() {
		WebFragment fragment = new WebFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_web, container, false);

		wv = (WebView)v.findViewById(R.id.webView1);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("http://naver.com");
		wv.setWebViewClient(new WebViewClientClass());

		wv.setVerticalScrollBarEnabled(true);
		wv.setHorizontalScrollBarEnabled(true);

		return v;
	}
	private class WebViewClientClass extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view,String url){

			view.loadUrl(url);
			return true;
		}
	}

	public boolean onKeyDown(int keycode,KeyEvent ev){

		if((keycode==KeyEvent.KEYCODE_BACK)&& wv.canGoBack()){
			wv.goBack();
			return true;
		}
		return getActivity().onKeyDown(keycode, ev);
	}

}

