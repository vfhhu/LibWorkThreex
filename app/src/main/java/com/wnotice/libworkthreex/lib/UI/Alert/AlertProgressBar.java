package com.wnotice.libworkthreex.lib.UI.Alert;


import android.app.Activity;
import android.app.Dialog;
import android.widget.TextView;

import com.wnotice.libworkthreex.R;


public class AlertProgressBar {
	Activity act;
	String title;
	Dialog dialog;
	public AlertProgressBar(Activity act,String title){
		run(act,title,false,3);
	}
	public AlertProgressBar(Activity act,String title,boolean isWait,final int secend){
		run(act,title,isWait,secend);
	}
	public void run(Activity act,String title,boolean isWait,final int secend){
		this.act=act;
		this.title=title;
		
		if(dialog!=null && dialog.isShowing())dialog.dismiss();
		dialog = new Dialog (act, R.style.dialog);
		dialog.setContentView(R.layout.alert_progress_bar);
		if(title!=null && !title.equals("")){
			//mDialog.setTitle(title);
			TextView textView =(TextView)dialog.findViewById(R.id.textViewTitle);			
			textView.setText(title);
		}
		try{
			dialog.show();
		}catch(Exception e){
			
		}
		
		if(!isWait){
			Thread t=new Thread(){
				 @Override
				 public void run() {
			        try {
						Thread.sleep(secend*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        dismiss();
				 }
			};
			t.start();
		}
		
		
	}
	
	public AlertProgressBar(Activity act,String title,int LoyoutRes){
		this.act=act;
		this.title=title;
		
		
		dialog = new Dialog (act, R.style.dialog);
		dialog.setContentView(LoyoutRes);
		if(title!=null && !title.equals("")){
			//mDialog.setTitle(title);
			TextView textView =(TextView)dialog.findViewById(R.id.textViewTitle);			
			textView.setText(title);
		}
		dialog.show();
		
		Thread t=new Thread(){
			 @Override
			 public void run() {
		        try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        dismiss();
			 }
		};
		t.start();
	}
	public void dismiss(){
		if(dialog.isShowing()){
			dialog.dismiss();
		}
	}
	public void setCancelable(boolean flag){
		dialog.setCancelable(flag);
	}
	

	public Activity getAct() {
		return act;
	}

	public void setAct(Activity act) {
		this.act = act;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}

	
	
}
