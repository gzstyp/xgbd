package com.fwtai.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.fwtai.activity.BaseActivity;
import com.fwtai.widget.TitleBar;
import com.yinlz.cdc.R;

/**
 * 从业人员录入
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020年12月12日 17:12:45
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
public final class UIEmployee extends BaseActivity{

    private TitleBar titleBar;
	private final Activity activity = this;

	@Override
	protected void onCreate(final Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.layout_employee);
        TitleBar.initSystemBar(this);
        initView();
        initClick();
	}

    private void initView(){
        titleBar = findViewById(R.id.title);
        titleBar.setTitle("从业人员录入");
        titleBar.setLeftClickListener(view -> {fromRightToLeftFinish();});
        titleBar.setRightText("保存");
        titleBar.setRightClickListener(view -> {
            save();
        });
	}

	void initClick(){

    }

    @Override
    public void onClick(final View view){

    }

    void save(){
        ok("保存");
    }
}