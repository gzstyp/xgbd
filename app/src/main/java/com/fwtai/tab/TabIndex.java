package com.fwtai.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.fwtai.activity.BaseFragment;
import com.fwtai.ui.UIEmployee;
import com.fwtai.ui.UIEnvironment;
import com.fwtai.ui.UIManage;
import com.fwtai.widget.TitleBar;
import com.gyf.immersionbar.ImmersionBar;
import com.yinlz.cdc.R;

/**
 * 首页
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2016年10月9日 18:08:16
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
public final class TabIndex extends BaseFragment {

	private TitleBar titleBar;
	private View mView;
	
	@Override
	public View onCreateView(final LayoutInflater inflater,final ViewGroup container,final Bundle bundle) {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.base_blue).navigationBarColor(R.color.base_blue).barColor(R.color.base_blue).statusBarDarkFont(true).keyboardEnable(true).init();//更改本主题默认的状态栏颜色
		if (mView != null) {
			ViewGroup parentView = (ViewGroup) mView.getParent();
			if (parentView != null) {
				parentView.removeViewAt(0);
			}
			return mView;
		}
		mView = inflater.inflate(R.layout.tab_layout_index,null,false);
		initView();
        initClick();
		return mView;
	}
	
	private void initView(){
		titleBar = mView.findViewById(R.id.titlebar_tab_index);
		titleBar.removeBorderView();
		titleBar.setTitle(getString(R.string.tab_tv_index));
	}

	void initClick(){
	    mView.findViewById(R.id.tab_index_tv_manage).setOnClickListener(this);
        mView.findViewById(R.id.tab_index_tv_employee).setOnClickListener(this::onClick);
        mView.findViewById(R.id.tab_index_tv_environment).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view){
	    switch (view.getId()){
	        case R.id.tab_index_tv_manage:
	            nextActivityAnim(UIManage.class);
	            break;
	        case R.id.tab_index_tv_environment:
                nextActivityAnim(UIEnvironment.class);
	            break;
	        case R.id.tab_index_tv_employee:
                nextActivityAnim(UIEmployee.class);
	            break;
	        default:
	            break;
	    }
    }
}