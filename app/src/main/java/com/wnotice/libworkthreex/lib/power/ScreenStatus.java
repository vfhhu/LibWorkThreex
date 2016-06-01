package com.wnotice.libworkthreex.lib.power;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class ScreenStatus {
	private static final String TAG = "ScreenStatus";

	@SuppressLint("NewApi")
	public static boolean getStatus(Context c){
		PowerManager pm = (PowerManager) c.getSystemService(Context.POWER_SERVICE);  
		KeyguardManager km = (KeyguardManager) c.getSystemService(Context.KEYGUARD_SERVICE);  
		int sdk=new Integer(Build.VERSION.SDK).intValue();
		
		//Log.d(TAG, "-**********************************");
		if (sdk<20) {			
			boolean b=pm.isScreenOn();			
			if(b){
				b=!km.inKeyguardRestrictedInputMode();
			}
			
			//Log.d(TAG, b+"");
			return b;
		}else {
			WindowManager mWindowManager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
			Display display = mWindowManager.getDefaultDisplay();
			//Display display = act.getWindowManager().getDefaultDisplay();
			int stat=display.getState();
//			if(stat==Display.STATE_DOZING){
//				//Log.d(TAG, "STATE_DOZING");
//			}
			if(stat==Display.STATE_OFF){
				//Log.d(TAG, "STATE_OFF");
			}
			if(stat==Display.STATE_ON){
				//Log.d(TAG, "STATE_ON");				
				return (!km.inKeyguardRestrictedInputMode());
			}
			if(stat==Display.STATE_UNKNOWN){
				//Log.d(TAG, "STATE_UNKNOWN");
			}
			return false;
			
		}
	}
}
