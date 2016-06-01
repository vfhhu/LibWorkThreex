package com.wnotice.libworkthreex.lib.store_json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class JsonSPA {
	private static Context context;
	private String namespace;
	private JSONArray JsonArr;
	private String SharePackage="com.threex.shared";
	public JsonSPA(Context context,String namespace){
		JsonSPA.context = context;
		/*
		try {
			this.context = context.createPackageContext(SharePackage, 0);
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		this.namespace = namespace;				

		String jsonStr=Read(namespace);
		boolean isNew=jsonStr.equals("");
		if(isNew)jsonStr="[]";
		try {
			JsonArr = new JSONArray(jsonStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isNew)Write(namespace,JsonArr.toString());
	}
	
	public JSONArray getJSON(){					
		return JsonArr;
	}
	public String getString(){					
		return JsonArr.toString();
	}

	public boolean getBoolean(int key){		
		return JsonArr.optBoolean(key);//false
	}
	public int getInt(int key){			
		return JsonArr.optInt(key);//0	
	}	
	public double getDouble(int key){			
		return JsonArr.optDouble(key);//NaN
	}
	public String getString(int key){		
		return JsonArr.optString(key);//""
	}
	public long getLong(int key){
		return JsonArr.optLong(key);//0
	}
	public JSONArray getJSONArray(int key){			
		return JsonArr.optJSONArray(key);//null
	}
	public JSONObject getJSONObject(int key){					
		return JsonArr.optJSONObject(key);//null
	}

	public void put(boolean value){		
		JsonArr.put(value);
		Write(namespace,JsonArr.toString());		
	}
	public void put(int value){		
		JsonArr.put(value);
		Write(namespace,JsonArr.toString());		
	}
	public void put(long value){		
		JsonArr.put(value);
		Write(namespace,JsonArr.toString());		
	}
	public void put(double value){		
		try {
			JsonArr.put(value);
			Write(namespace,JsonArr.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void put(Object value){		
		JsonArr.put(value);
		Write(namespace,JsonArr.toString());		
	}
	public void put(String value){		
		JsonArr.put(value);
		Write(namespace,JsonArr.toString());		
	}
	
	public void set(int Index,boolean value){		
		try {
			JsonArr.put(Index,value);
			Write(namespace,JsonArr.toString());	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	public void set(int Index,int value){		
		try {
			JsonArr.put(Index,value);
			Write(namespace,JsonArr.toString());	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void set(int Index,long value){		
		try {
			JsonArr.put(Index,value);
			Write(namespace,JsonArr.toString());	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void set(int Index,double value){		
		try {
			JsonArr.put(Index,value);
			Write(namespace,JsonArr.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void set(int Index,Object value){		
		try {
			JsonArr.put(Index,value);
			Write(namespace,JsonArr.toString());	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void set(int Index,String value){		
		try {
			JsonArr.put(Index,value);
			Write(namespace,JsonArr.toString());	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int length(){
		return JsonArr.length();
	}
	@SuppressLint("NewApi")
	public void remove(int k) throws JSONException{		
		/*
		JsonArr.remove(k);
		Write(namespace,JsonArr.toString());		*/
	}
	public void removeObject(int k) throws JSONException{		
		JSONArray j=new JSONArray();
		for(int i = 0; i < JsonArr.length(); i++){
			if(i==k)continue;
		    j.put(JsonArr.getJSONObject(i));
		}
		JsonArr=j;
		Write(namespace,JsonArr.toString());		
	}
	
	
	private synchronized static boolean Write(String file,String data){		
		SharedPreferences settings = context.getSharedPreferences(file, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("data", data);
		editor.commit();
		
		return true;		
	}
	private String Read(String file){
		SharedPreferences settings = context.getSharedPreferences(file, 0);
		String str = settings.getString("data","{}");
		return str;		
	}
	public void clear(){
		SharedPreferences preferences = context.getSharedPreferences(namespace, Context.MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear(); 
		editor.commit();

	}
	/*
	public void clear(){		
		try {
			JsonArr = new JSONArray("[]");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Write(namespace,JsonArr.toString());
	}
	private boolean Write(String file,String data){
		context.getDir(file, Context.MODE_PRIVATE);
		try {	
			OutputStreamWriter write = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_PRIVATE), "UTF-8");// 覆蓋文件
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(data);
			writer.close();
		} catch (Exception e) {	
			//e.printStackTrace();
		}
		return true;		
	}
	private String Read(String file){
		context.getDir(file, Context.MODE_PRIVATE);
		String content="";
		try {	
			InputStreamReader read = new InputStreamReader(context.openFileInput(file), "UTF-8");	
			BufferedReader reader = new BufferedReader(read);	
			String line;	
			while ((line = reader.readLine()) != null) {	
				content += line;	
			}
			read.close();
		} catch (Exception e) {	
			//e.printStackTrace();
		}
		return content;		
	}*/
}