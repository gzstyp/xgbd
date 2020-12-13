package com.fwtai.fragment;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Fragment适配器
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2015-12-25 下午3:18:15
 * @QQ号码 444141300
 * @官网 http://www.yinlz.com
*/
public final class FragmentAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> fragmentList;

	public FragmentAdapter(final FragmentManager fm) {
		super(fm);
	}

	public void updateData(final List<Fragment> list) {
		fragmentList = list;
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(final int arg0) {
		return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
	}

	@Override
	public CharSequence getPageTitle(final int position) {
		return "";
	}

	@Override
	public int getCount() {
		return fragmentList == null ? 0 : fragmentList.size();
	}
}