package com.wnotice.libworkthreex.lib.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;


public class ImageHelper {
	private static final String TAG = "ImageHelper";
	public static int readPictureDegree(String path) {  
        int degree  = 0;  
        try {  
                ExifInterface exifInterface = new ExifInterface(path);  
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);  
                switch (orientation) {  
                case ExifInterface.ORIENTATION_ROTATE_90:  
                        degree = 90;  
                        break;  
                case ExifInterface.ORIENTATION_ROTATE_180:  
                        degree = 180;  
                        break;  
                case ExifInterface.ORIENTATION_ROTATE_270:  
                        degree = 270;  
                        break;  
                }  
        } catch (Exception e) {  
                e.printStackTrace();  
        }  
        return degree;  
    }  
	public static Bitmap rotaingImageView(int angle , Bitmap bitmap,float scale) {    
		//旋转图片 动作     
		Matrix matrix = new Matrix();;    
		matrix.postRotate(angle);  
		if(scale!=1 && scale>0)matrix.postScale(1/scale, 1/scale);
		//System.out.println("angle2=" + angle);    
		// 创建新的图片     
		//Log.d(TAG, bitmap.getWidth()+","+bitmap.getHeight()+","+scale);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), matrix, true);    
		return resizedBitmap;    
	} 
//    public static Bitmap getRoundedCornerBitmapHexagon(Bitmap bitmap) {
//    	Bitmap result = null;
//        try {
//        	
//            result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(result);
//
//            int color = 0xff424242;
//            Paint paint = new Paint();
//            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//
//            paint.setAntiAlias(true);
//            canvas.drawARGB(0, 0, 0, 0);
//            paint.setColor(color);
//            paint.setStyle(Style.FILL);
//            
//            
//            float minside=Hexagon.getFlatSide(Math.min(bitmap.getWidth(), bitmap.getHeight()));
//            Path mPath =  Hexagon.getPathFlat(minside,((bitmap.getWidth()-minside)/2) ,((bitmap.getHeight()-Hexagon.getFlatHeight(minside))/2));
//            canvas.drawPath(mPath, paint);
//            
//            
//            
//            
//            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//            
//            
//            canvas.drawBitmap(bitmap, rect, rect, paint);
//
//        } catch (NullPointerException e) {
//        } catch (OutOfMemoryError o) {
//        }
//        return result;
//    }
    public static Bitmap getBitmap(Context c,int Resource) {
    	Bitmap vBitmap = BitmapFactory.decodeResource( c.getResources(), Resource);
    	return vBitmap;
    }
    public static Bitmap getRoundedCornerBitmapRect(Bitmap bitmap, int pixels,int Width,int Heigh) {
    	Bitmap result = null;
        try {
        	/*
            result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);

            int color = 0xff424242;
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            paint.setStyle(Style.FILL);
            
            
            Rect rectF = new Rect(0, 0, Width, Heigh);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);     
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            
            
            canvas.drawBitmap(bitmap, rect, rect, paint);*/

        } catch (NullPointerException e) {
        } catch (OutOfMemoryError o) {
        }
        return result;
    }
    
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx){    
    	
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), 
	                                        bitmap.getHeight(), 
	                                        Config.ARGB_8888);  
	    Canvas canvas = new Canvas(output);  
	    final int color = 0xff424242;  
	    final Paint paint = new Paint();  
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(),
	                                     bitmap.getHeight());  
	    final RectF rectF = new RectF(rect);  
	    paint.setAntiAlias(true);  
	    canvas.drawARGB(0, 0, 0, 0);  
	    paint.setColor(color);  
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);  
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
	    canvas.drawBitmap(bitmap, rect, rect, paint);  
	    return output;  
    }  
}

