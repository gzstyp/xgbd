package com.fwtai.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioGroup;
import com.fwtai.activity.BaseFragment;
import com.fwtai.fragment.FragmentAdapter;
import com.fwtai.fragment.FragmentItemYingJian;
import com.fwtai.fragment.FragmentItemYuanJian;
import com.fwtai.widget.TitleBar;
import com.gyf.immersionbar.ImmersionBar;
import com.yinlz.cdc.R;

import java.util.ArrayList;

/**
 * 统计
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2016年10月9日 18:06:58
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
public final class TabStatistics extends BaseFragment implements OnClickListener{

    private FragmentItemYuanJian skill;//应检尽检
    private FragmentItemYingJian customization;//定制发布
    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    private RadioGroup mRadioGroup;


	
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
		mView = inflater.inflate(R.layout.tab_layout_statistics,null,false);
		initView();
		return mView;
	}
	
	private void initView() {
		titleBar = mView.findViewById(R.id.title_bar_order);
		titleBar.removeBorderView();
		titleBar.setTitle(getString(R.string.tab_tv_statistics));

        mViewPager = mView.findViewById(R.id.tab_viewpager);
        adapter = new FragmentAdapter(getFragmentManager());//adapter = new FragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        final ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        skill = FragmentItemYuanJian.newInstance();// 实例化-应检尽检
        customization = FragmentItemYingJian.newInstance();// 实例化-定制发布
        fragmentList.add(customization);
        fragmentList.add(skill);
        adapter.updateData(fragmentList);

        mRadioGroup = (RadioGroup) mView.findViewById(R.id.rg_radioGroup);
        //点击事件
        mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_jinengrenzheng://应检尽检
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_quanzhifabu://愿检尽检
                        mViewPager.setCurrentItem(1);
                        break;
                }
            }
        });
        //滑动事件
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(final int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.rb_jinengrenzheng);//应检尽检
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_quanzhifabu);//愿检尽检
                        break;
                }
            }
            @Override
            public void onPageScrolled(final int arg0,final float arg1,final int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(final int arg0) {
            }
        });
	}
	
	@Override
	public void onClick(final View view){
		switch (view.getId()){
		default:
			break;
		}
	}
}