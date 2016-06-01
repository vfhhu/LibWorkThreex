package com.wnotice.libworkthreex.lib.store_json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public class JsonISO {
	private static Context context;
	private String namespace;
	private JSONObject JsonObj;
	public JsonISO(Context context,String namespace){
		JsonISO.context = context;
		this.namespace = namespace;				

		String jsonStr=Read(namespace);
		boolean isNew=jsonStr.equals("");
		if(isNew)jsonStr="{}";
		try {
			JsonObj = new JSONObject(jsonStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isNew)Write(namespace,JsonObj.toString());
	}
	public void clear(){		
		try {
			JsonObj = new JSONObject("{}");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Write(namespace,JsonObj.toString());
	}
	public JSONObject getJSON(){					
		return JsonObj;
	}
	public String getString(){					
		return JsonObj.toString();
	}
	public int length(){		
		return JsonObj.length();
	}

	public boolean getBoolean(String key){		
		return JsonObj.optBoolean(key);//false
	}
	public int getInt(String key){			
		return JsonObj.optInt(key);//0	
	}	
	public double getDouble(String key){			
		return JsonObj.optDouble(key);//NaN
	}
	public String getString(String key){		
		return JsonObj.optString(key);//""
	}
	public long getLong(String key){
		return JsonObj.optLong(key);//0
	}
	public JSONArray getJSONArray(String key){			
		return JsonObj.optJSONArray(key);//null
	}
	public JSONObject getJSONObject(String key){					
		return JsonObj.optJSONObject(key);//null
	}
	
	public void setJSON(String v){		
		try {
			JsonObj = new JSONObject(v);
			Write(namespace,JsonObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setJSON(JSONObject v){				
		JsonObj = v;		
		Write(namespace,JsonObj.toString());
	}
	public void remove(String k) throws JSONException{		
		JsonObj.remove(k);			
		Write(namespace,JsonObj.toString());
	}
	public void set(String key,boolean value){		
		try {
			JsonObj.put(key, value);
			Write(namespace,JsonObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void set(String key,int value){		
		try {
			JsonObj.put(key, value);
			Write(namespace,JsonObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void set(String key,long value){		
		try {
			JsonObj.put(key, value);
			Write(namespace,JsonObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void set(String key,double value){		
		try {
			JsonObj.put(key, value);
			Write(namespace,JsonObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void set(String key,Object value){		
		try {
			JsonObj.put(key, value);
			Write(namespace,JsonObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void set(String key,String value){		
		try {
			JsonObj.put(key, value);
			Write(namespace,JsonObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void removeObject(String k) throws JSONException{
		JsonObj.remove(k);
		Write(namespace,JsonObj.toString());
		/*
		JSONArray j=new JSONArray();
		for(int i = 0; i < JsonArr.length(); i++){
			if(i==k)continue;
		    j.put(JsonArr.getJSONObject(i));
		}
		JsonArr=j;
		Write(namespace,JsonArr.toString());	*/	
	}

	private synchronized static boolean Write(String file,String data){
		context.getDir(file, Context.MODE_PRIVATE);
		try {	
			OutputStreamWriter write = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_PRIVATE), "UTF-8");// 覆蓋文件
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(data);
			writer.flush();
			writer.close();
			write.close();
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
			reader.close();
			read.close();
		} catch (Exception e) {	
			//e.printStackTrace();
		}
		return content;		
	}
}