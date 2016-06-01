package com.wnotice.libworkthreex.lib.image;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class TouchScreenImg implements OnTouchListener {
	// These matrices will be used to move and zoom image  
		Matrix matrix = new Matrix();  
		Matrix savedMatrix = new Matrix();  
		//Bitmap tmpBp;  
		// We can be in one of these 3 states  
		static final int NONE = 0;  
		static final int DRAG = 1;  
		static final int ZOOM = 2;  
		int mode = NONE;  
		  
		// Remember some things for zooming  
		PointF start = new PointF();  
		PointF mid = new PointF();  
		float oldDist = 1f;  
		
		   
		@Override  
		public boolean onTouch(View v, MotionEvent event) {  
			//Log.d("onTouch", "onTouch");
			ImageView view = (ImageView) v;  
			//Bitmap bitmap = ((BitmapDrawable)view.getDrawable()).getBitmap();
			// Dump touch event to log  
			dumpEvent(event);  
		  
			// Handle touch events here...  
			switch (event.getAction() & MotionEvent.ACTION_MASK) {  
				case MotionEvent.ACTION_DOWN:  
					//Log.d("onTouch", "ACTION_DOWN");
					savedMatrix.set(matrix);  
					start.set(event.getX(), event.getY());  
					mode = DRAG;  
					break;  
				case MotionEvent.ACTION_POINTER_DOWN:  
					//Log.d("onTouch", "ACTION_POINTER_DOWN");
					oldDist = spacing(event);  
					if (oldDist > 10f) {  
						savedMatrix.set(matrix);  
						midPoint(mid, event);  
						mode = ZOOM;  
					}  
					break;  
				case MotionEvent.ACTION_UP:  
					//Log.d("onTouch", "ACTION_UP");
				case MotionEvent.ACTION_POINTER_UP:  
					//Log.d("onTouch", "ACTION_POINTER_UP");
					mode = NONE;  
					
					/*
					Matrix var_matrix =view.getImageMatrix();  
					float[] postion= new float[9];
					var_matrix.getValues(postion);
					Log.d("onTouch", var_matrix.toString());
					Log.d("onTouch", postion[0]+"");*/
					//Log.d("onTouch", view.getWidth()+"");
					break;  
				case MotionEvent.ACTION_MOVE:  
					//Log.d("onTouch", "ACTION_MOVE");
					if (mode == DRAG) {  
						// ...    
						matrix.set(savedMatrix); 
						matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);   
						//Log.d("onTouch DRAG", ((event.getX() - start.x)+","+ (event.getY() - start.y)));
						
					} else if (mode == ZOOM) {  
						float newDist = spacing(event);  
						if (newDist > 10f) {  
							matrix.set(savedMatrix);  
							float scale = newDist / oldDist;  
							matrix.postScale(scale, scale, mid.x, mid.y);  
						}  
					}  
					break;  
			}  
		  
			view.setImageMatrix(matrix);  
			return true; // indicate event was handled  
		}  
		
		public void setMatrix(Matrix matrix){
			this.matrix=matrix;
		}

		public Matrix getMatrix(){
			return matrix;
		}
		public float getBaseX(){
			float[] postion= new float[9];
			matrix.getValues(postion);
			float baseX=postion[2]*-1;				
			return baseX;
		}
		public float getBaseY(){
			float[] postion= new float[9];
			matrix.getValues(postion);
			float baseY=postion[5]*-1;
			return baseY;
		}
		public void checkPostion(ImageView view,Bitmap bitmap){
			float[] postion= new float[9];
			matrix.getValues(postion);
			float baseX=postion[2]*-1;
			float baseY=postion[5]*-1;
			float width=bitmap.getWidth();
			float heigh=bitmap.getHeight();
			
			float moveX=0;
			float moveY=0;
			if(moveX>baseX)moveX=baseX;
			if(moveY>baseY)moveY=baseY;
			
			if((baseX-moveX)>(width-480))moveX=(width-480-baseX)*-1;
			if((baseY-moveY)>(heigh-800))moveY=(heigh-800-baseY)*-1;
			matrix.postTranslate(moveX, moveY);   
			view.setImageMatrix(matrix);  
		}
		
		
		
		
		
		/** Show an event in the LogCat view, for debugging */  
		private void dumpEvent(MotionEvent event) {  
			String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",  
		    "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };  
			StringBuilder sb = new StringBuilder();  
			int action = event.getAction();  
			int actionCode = action & MotionEvent.ACTION_MASK;  
			sb.append("event ACTION_").append(names[actionCode]);  
			if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) {  
				sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);  
				sb.append(")");  
			}  
			sb.append("[");  
			for (int i = 0; i < event.getPointerCount(); i++) {  
				sb.append("#").append(i);  
				sb.append("(pid ").append(event.getPointerId(i));  
				sb.append(")=").append((int) event.getX(i));  
				sb.append(",").append((int) event.getY(i));  
				if (i + 1 < event.getPointerCount())sb.append(";");  
			}  
			sb.append("]");  
		}  
		  
		/** Determine the space between the first two fingers */  
		private float spacing(MotionEvent event) {  
			float x = event.getX(0) - event.getX(1);  
			float y = event.getY(0) - event.getY(1); 
			
			//Log.d("onTouch spacing", FloatMath.sqrt(x * x + y * y)+"");
			return FloatMath.sqrt(x * x + y * y);  
		}  
		  
		/** Calculate the mid point of the first two fingers */  
		private void midPoint(PointF point, MotionEvent event) {  
			float x = event.getX(0) + event.getX(1);  
			float y = event.getY(0) + event.getY(1);  
			//Log.d("onTouch midPoint", x / 2+","+ y / 2);
			point.set(x / 2, y / 2);  
		}  
		
		public int getStartX(){			
			return (int) start.x;		
		}
		public int getStartY(){
			return (int) start.y;			
		}
		
		
}
