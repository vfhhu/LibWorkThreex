package com.wnotice.libworkthreex.lib.store_json;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;

public class JsonESA {
	private Context context;
	private String namespace;
	private JSONArray JsonArr;
	public JsonESA(Context context,String namespace){
		this.context = context;
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
	public void clear(){		
		try {
			JsonArr = new JSONArray("[]");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Write(namespace,JsonArr.toString());
	}
	public JSONArray getJSON(){					
		return JsonArr;
	}
	public String getString(){					
		return JsonArr.toString();
	}
	public void setJson(JSONArray v) {
		// TODO Auto-generated method stub
		JsonArr=v;
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
		Write(namespace,JsonArr.toString());	*/	
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