package com.wnotice.libworkthreex.lib.NetworkUtil;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkUtil {
	public static int TYPE_WIFI = 1;
	public static int TYPE_MOBILE = 2;
	public static int TYPE_NOT_CONNECTED = 0;
	
	
	public static int getConnectivityStatus(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (null != activeNetwork) {
			if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
				return TYPE_WIFI;
			
			if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
				return TYPE_MOBILE;
		} 
		return TYPE_NOT_CONNECTED;
	}
	
	public static String getConnectivityStatusString(Context context) {
		int conn = NetworkUtil.getConnectivityStatus(context);
		String status = null;
		if (conn == NetworkUtil.TYPE_WIFI) {
			status = "Wifi enabled";
		} else if (conn == NetworkUtil.TYPE_MOBILE) {
			status = "Mobile data enabled";
		} else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
			status = "Not connected to Internet";
		}
		return status;
	}
	public static boolean checkInternetConnection(Context c,boolean isToast,int resid){		
		if(isToast==false){
			return checkInternetConnection(c,isToast,null);
		}
		return checkInternetConnection(c,isToast,c.getResources().getString(resid));
	}
	public static boolean checkInternetConnection(Context c,boolean isToast,String msg){
		//Context c= ThreeX.getAppContext();
		ConnectivityManager cm=(ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni=cm.getActiveNetworkInfo();
		if(ni!=null && ni.isConnected()){
		// System.out.println("ni.isConnected() = "+ni.isConnected());
			if(isToast && !ni.isConnected()){
				if(c!=null || msg!=null)Toast.makeText(c, msg, Toast.LENGTH_LONG).show();
			}
			return ni.isConnected();
		}else{
		// System.out.println("ni.isConnected() = "+ni.isConnected());
			if(isToast){
				if(c!=null || msg!=null)Toast.makeText(c, msg, Toast.LENGTH_LONG).show();
			}
			return false;
		}
	}

}
