package com.wnotice.libworkthreex.lib.image;

import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class btnOnTouchListener implements OnTouchListener{
	public final  float[] BT_SELECTED=new float[]  
            { 2, 0, 0, 0, 2,  
        0, 2, 0, 0, 2,  
        0, 0, 2, 0, 2,  
        0, 0, 0, 1, 0 };                  

    public final float[] BT_NOT_SELECTED=new float[]  
            { 1, 0, 0, 0, 0,  
        0, 1, 0, 0, 0,  
        0, 0, 1, 0, 0,  
        0, 0, 0, 1, 0 };  
    public boolean onTouch(View v, MotionEvent event) {  
        // TODO Auto-generated method stub  
        if(event.getAction() == MotionEvent.ACTION_DOWN){ 
        	ImageView img=(ImageView)v;
        	Drawable d=img.getDrawable();
        	d.setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));  
        	img.setImageDrawable(d); 
        	
        	/*
            v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));  
            v.setBackgroundDrawable(v.getBackground());  */
        }  
        else if(event.getAction() == MotionEvent.ACTION_UP){  
        	ImageView img=(ImageView)v;
        	Drawable d=img.getDrawable();
        	d.setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));  
        	img.setImageDrawable(d); 
        	/*
            v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));  
            v.setBackgroundDrawable(v.getBackground());  */
              
        }  
        return false;  
    }  
}
