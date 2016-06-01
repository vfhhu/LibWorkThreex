package com.wnotice.libworkthreex.lib.store_json;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class JsonESO {
	private Context context;
	private String namespace;
	private JSONObject JsonObj;
	public JsonESO(Context context,String namespace){
		this.context = context;
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
		//Log.d("com.threex.callguard Write", "Write Start");
		try {
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			pw.println(data);
	        pw.flush();
	        pw.close();
	        f.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		return true;		
	}
	private String Read(String file){
		//Log.d("com.threex.callguard Read", "Read Start");
		try {
			FileReader fr = new FileReader(file);
			//將BufferedReader與FileReader做連結
			BufferedReader br = new BufferedReader(fr);
			String readData = "";
			String temp = br.readLine(); //readLine()讀取一整行
			while (temp!=null){
				readData+=temp;
				temp=br.readLine();
			}
			br.close();
			fr.close();
			return readData;
		}catch (Exception e){  
			e.printStackTrace();  
			return "";  
		}  		
	}
}