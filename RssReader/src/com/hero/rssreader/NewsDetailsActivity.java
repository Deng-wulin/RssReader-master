package com.hero.rssreader;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ShareActionProvider;

import com.hero.rssreader.comm.BaseActivity;
import com.hero.rssreader.utils.SmartBarUtils;

/**
 * 查看新闻详情
 * 
 * @author wulin
 * 
 */
public class NewsDetailsActivity extends BaseActivity {

	private String link;
	private WebView webView;
	private String title;
	private ShareActionProvider mShareActionProvider;

	@Override
	protected int getContainer() {
		return R.layout.activity_news_details;
	}

	@SuppressLint("NewApi")
	@Override
	protected void onActivityStart() {

		getActionBar().setDisplayUseLogoEnabled(false);
		webView = aq.id(R.id.web_view).getWebView();
		link = getIntent().getExtras().getString("link");
		title = getIntent().getExtras().getString("title");
		initWebView();
		loadData();

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.action_share:
			shareArticle();
			break;
		}
		return true;
	}


	// 分享文章
	private void shareArticle() {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT, title+"。详情:"+link);

		PackageManager pm = getPackageManager();
		// 检查手机上是否存在可以处理这个动作的应用
		List<ResolveInfo> infolist = pm.queryIntentActivities(shareIntent, 0);
		if (!infolist.isEmpty()) {
			//mShareActionProvider.setShareIntent(shareIntent);
			 startActivity(shareIntent);
		}

	}

	private void initWebView() {
		WebSettings setting = webView.getSettings();
		/*setting.setJavaScriptEnabled(true);
		setting.setUseWideViewPort(false); // 将图片调整到适合webview的大小
		setting.setSupportZoom(true); // 支持缩放
		setting.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); // 支持内容重新布局
		setting.supportMultipleWindows(); // 多窗口
		setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 关闭webview中缓存
		setting.setAllowFileAccess(true); // 设置可以访问文件
		setting.setNeedInitialFocus(true); // 当webview调用requestFocus时为webview设置节点
		setting.setBuiltInZoomControls(true); // 设置支持缩放
		setting.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过JS打开新窗口
		
*/		setting.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
		setting.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		setting.setLoadsImagesAutomatically(true); // 支持自动加载图片
		setting.setBuiltInZoomControls(false);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news_details, menu);
		//MenuItem item = menu.findItem(R.id.action_share);
		//mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		return super.onCreateOptionsMenu(menu);
	}

	// Call to update the share intent
	private void setShareIntent(Intent shareIntent) {
		if (mShareActionProvider != null) {
			/*
			 * Intent shareIntent = new Intent(Intent.ACTION_SEND);
			 * shareIntent.setType("text/plain");
			 * shareIntent.putExtra(Intent.EXTRA_TEXT, "这是要发送的文本");
			 */
			mShareActionProvider.setShareIntent(shareIntent);
		}
	}

	// Call to update the share intent
	private boolean setShareIntent() {
		if (mShareActionProvider != null) {
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "这是要发送的文本");

			PackageManager pm = getPackageManager();
			// 检查手机上是否存在可以处理这个动作的应用
			List<ResolveInfo> infolist = pm.queryIntentActivities(shareIntent,
					0);
			if (!infolist.isEmpty()) {
				mShareActionProvider.setShareIntent(shareIntent);
				return true;
			}
			return false;
		}
		return false;
	}

	public void loadData() {
		webView.loadUrl(link);
	}

	@Override
	protected String getAcitityTitle() {
		return title;
	}

	@Override
	protected boolean getDisplayBack() {
		return true;
	}

}
