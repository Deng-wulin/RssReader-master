package com.hero.rssreader;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;
import com.hero.rssreader.comm.BaseActivity;
import com.hero.rssreader.comm.MyApplication;
import com.hero.rssreader.entity.ChannelEntity;

/** 添加Rss订阅
 * @author wulin
 *
 */
public class AddChanneActivity extends BaseActivity {

	private boolean isUpdate = false;
	
	@Override
	protected int getContainer() {
		// TODO Auto-generated method stub
		return R.layout.activity_add_channe;
	}

	@Override
	protected void onActivityStart() {
		aq.id(R.id.submit_btn).clicked(this,"onAddChanne");
	}

	/** 添加Rss
	 * @param v
	 */
	public void onAddChanne(View v){
		String rssLink = aq.id(R.id.rss_link_et).getEditText().getText().toString();
		if(TextUtils.isEmpty(rssLink)){
			showToater("Rss地址不能为空");
			return;
		}
		aq.id(R.id.submit_btn).getButton().setClickable(false);
		aq.progress(new AlertDialog.Builder(this).setMessage("正在查询数据")).ajax(rssLink, XmlDom.class, this,"callBack");
	}
	
	public void showToater(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	public void callBack(String url, XmlDom xml, AjaxStatus status) {
		if (xml == null){
			showToater("没有找到该Rss地址，请确认后在输入");
			return;
		}
			
		ChannelEntity channe = new ChannelEntity();
		XmlDom xmlDom = xml.tag("channel");
		channe.setName(xmlDom.text("title"));
		channe.setIsAdd(1);
		channe.setFeedUrl(aq.id(R.id.rss_link_et).getEditText().getText().toString());
		channe.setDescription(xmlDom.text("description"));
		MyApplication.db.save(channe);
		showToater("addSuccess");
		aq.id(R.id.submit_btn).getButton().setClickable(true);
		isUpdate = true;
	}

	@Override
	public void onBackPressed() {
		if(isUpdate){
			setResult(RESULT_OK);
		}
		super.onBackPressed();
	}

	@Override
	protected String getAcitityTitle() {
		// TODO Auto-generated method stub
		return "添加订阅";
	}

	@Override
	protected boolean getDisplayBack() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
