package com.wnotice.libworkthreex.lib.power;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;

public class ScreenLock {
	private static KeyguardManager.KeyguardLock mKeyguardLock;
	private static boolean isScreenLock;
	private static PowerManager.WakeLock mWakelock;

	@SuppressWarnings("deprecation")
	public static void getUnlock(Activity act) {
		// acquire wake lock

		// if(mKeyguardLock!=null)releaseUnlock();

		PowerManager pm = (PowerManager) act.getSystemService(Context.POWER_SERVICE);
		mWakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "SimpleTimer");
		mWakelock.acquire();
		// unlock screen
		KeyguardManager km = (KeyguardManager) act.getSystemService(Context.KEYGUARD_SERVICE);

		mKeyguardLock = km.newKeyguardLock(act.getPackageName());
		if (km.inKeyguardRestrictedInputMode()) {
			mKeyguardLock.disableKeyguard();
			isScreenLock = true;
		} else {
			isScreenLock = false;
		}

		// pm.userActivity(when, noChangeLights)
	}

	public static void releaseUnlock() {
		// release screen
		if (isScreenLock) {
			mKeyguardLock.reenableKeyguard();
			isScreenLock = false;
		}
		// release wake lock
		if (mWakelock.isHeld()) {
			mWakelock.release();
		}
	}
	public static void releaseWakelock() {
		// release screen
		if (mWakelock.isHeld()) {
			mWakelock.release();
		}
		
	}
	
	
}
