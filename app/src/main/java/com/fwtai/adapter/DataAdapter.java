package com.fwtai.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * listView的数据适配器
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2016年10月14日 上午9:39:49
 * @QQ号码 444141300
 * @主页 http://www.fwtai.com
*/
public abstract class DataAdapter<T> extends BaseAdapter {

	private ArrayList<T> list ;
	
	/**
	 * 初始化数据
	 * @param list - Bean类型的
	 * @作者 田应平
	 * @创建时间 2016年10月14日 09:40:09
	 * @QQ号码 444141300
	 * @官网 http://www.fwtai.com
	*/
	public DataAdapter(final ArrayList<T> list) {
		this.list = list;
	}
	
	@Override
	public final int getCount() {
		return list == null ? 0 : list.size();
	}
	
	@Override
	public final Object getItem(final int position) {
		return null == list ? null : list.get(position);
	}
	
	@Override
	public final long getItemId(final int position) {
		return position;
	}
	
	/**
	 * 继承时实现即可
	*/
	@Override
	public abstract View getView(final int position,final View convertView,final ViewGroup parent);
}