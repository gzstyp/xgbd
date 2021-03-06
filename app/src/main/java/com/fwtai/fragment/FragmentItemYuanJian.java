package com.fwtai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yinlz.cdc.R;

/**
 * 愿检尽检
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020/12/13 13:26
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.yinlz.com</url>
*/
public final class FragmentItemYuanJian extends Fragment{

	private View viewLoyout;
	int index = 0;
	
	public static FragmentItemYuanJian newInstance(){
		final FragmentItemYuanJian fragment = new FragmentItemYuanJian();
		final Bundle bundle = new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater,final ViewGroup container,final Bundle savedInstanceState) {
		viewLoyout = inflater.inflate(R.layout.fragment_item_yuanjian, null);
		initView();
		return viewLoyout;
	}
	
	@Override
	public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		super.onActivityResult(requestCode,resultCode,data);
	}
	
	void initView(){
        viewLoyout.findViewById(R.id.tv_jinengrenzheng).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            	index++;
                ((TextView)viewLoyout.findViewById(R.id.tv_jinengrenzheng)).setText("愿检尽检"+index);
            }
        });
    }
}