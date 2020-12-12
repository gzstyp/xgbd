package com.fwtai.widget;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.fwtai.tool.ScreenDisplay;
import com.yinlz.cdc.R;

import java.util.ArrayList;
import java.util.List;

public final class SheetDialog {
	
	private Activity activity;
	private Dialog dialog;
	private TextView txt_title;
	private TextView txt_cancel;
	private LinearLayout lLayout_content;
	private ScrollView sLayout_content;
	private boolean showTitle = false;
	private List<SheetItem> sheetItemList;

	public SheetDialog(Activity activity){
		this.activity = activity;
	}

	public SheetDialog builder(){
		// 获取Dialog布局
		View view = LayoutInflater.from(activity).inflate(R.layout.dialog_sheet,null,false);
		// 设置Dialog最小宽度为屏幕宽度
		view.setMinimumWidth(ScreenDisplay.getInstance().getScreenWidth(activity));
		// 获取自定义Dialog布局中的控件
		sLayout_content = (ScrollView) view.findViewById(R.id.sLayout_content);
		lLayout_content = (LinearLayout) view.findViewById(R.id.lLayout_content);
		txt_title = (TextView) view.findViewById(R.id.txt_title);
		txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
		txt_cancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				dialog.dismiss();
			}
		});

		// 自定义仿IOS的Dialog布局和参数
		dialog = new Dialog(activity,R.style.ActionSheetDialogStyle);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);
		return this;
	}

	public SheetDialog setTitle(String title){
		showTitle = true;
		txt_title.setVisibility(View.VISIBLE);
		txt_title.setText(title);
		return this;
	}

	public SheetDialog setCancelable(boolean cancel){
		dialog.setCancelable(cancel);
		return this;
	}

	public SheetDialog setCanceledOnTouchOutside(boolean cancel){
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	/**
	 * 
	 * @param strItem 条目名称
	 * @param color 条目字体颜色，设置null则默认蓝色
	 * @param listener
	 * @return
	 */
	public SheetDialog addSheetItem(String strItem, SheetItemColor color, OnSheetItemClickListener listener){
		if (sheetItemList == null){
			sheetItemList = new ArrayList<SheetItem>();
		}
		sheetItemList.add(new SheetItem(strItem, color, listener));
		return this;
	}

	/** 设置条目布局 */
	private void setSheetItems(){
		if (sheetItemList == null || sheetItemList.size() <= 0){
			return;
		}

		int size = sheetItemList.size();

		// 高度控制，非最佳解决办法
		// 添加条目过多的时候控制高度
		if (size >= 7){
			LinearLayout.LayoutParams params = (LayoutParams) sLayout_content.getLayoutParams();
			params.height = ScreenDisplay.getInstance().getScreenHeight(activity) / 2;
			sLayout_content.setLayoutParams(params);
		}

		// 循环添加条目
		for (int i = 1; i <= size; i++){
			final int index = i;
			SheetItem sheetItem = sheetItemList.get(i - 1);
			String strItem = sheetItem.name;
			SheetItemColor color = sheetItem.color;
			final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem.itemClickListener;

			TextView textView = new TextView(activity);
			textView.setText(strItem);
			textView.setTextSize(16);//设置字体大小
			textView.setGravity(Gravity.CENTER);

			// 背景图片
			if (size == 1){
				if (showTitle){
					textView.setBackgroundResource(R.drawable.sheet_bottom_selector);
				} else {
					textView.setBackgroundResource(R.drawable.sheet_single_selector);
				}
			} else {
				if (showTitle){
					if (i >= 1 && i < size){
						textView.setBackgroundResource(R.drawable.sheet_middle_selector);
					} else {
						textView.setBackgroundResource(R.drawable.sheet_bottom_selector);
					}
				} else {
					if (i == 1){
						textView.setBackgroundResource(R.drawable.sheet_top_selector);
					} else if (i < size){
						textView.setBackgroundResource(R.drawable.sheet_middle_selector);
					} else {
						textView.setBackgroundResource(R.drawable.sheet_bottom_selector);
					}
				}
			}

			// 字体颜色
			if (color == null){
				textView.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));
			} else {
				textView.setTextColor(Color.parseColor(color.getName()));
			}
			// 高度
			float scale = activity.getResources().getDisplayMetrics().density;
			int height = (int) (45 * scale + 0.5f);
			textView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, height));

			// 点击事件
			textView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					listener.onClick(index);
					dialog.dismiss();
				}
			});
			lLayout_content.addView(textView);
		}
	}

	public void show(){
		setSheetItems();
		dialog.show();
	}

	public interface OnSheetItemClickListener {
		void onClick(int which);
	}

	public class SheetItem {
		String name;
		OnSheetItemClickListener itemClickListener;
		SheetItemColor color;
		public SheetItem(String name, SheetItemColor color, OnSheetItemClickListener itemClickListener){
			this.name = name;
			this.color = color;
			this.itemClickListener = itemClickListener;
		}
	}

	public enum SheetItemColor{
		Blue("#037BFF"), Red("#FD4A2E");
		private String name;
		private SheetItemColor(String name){
			this.name = name;
		}
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name = name;
		}
	}
}