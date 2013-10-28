package com.HedronCo.dicemod;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import android.util.Log;


public class DiceGamePanel extends SurfaceView implements SurfaceHolder.Callback
{
	//variables
	   private static final String TAG = DiceGamePanel.class.getSimpleName();
	   private GameThread game;
	   public ArrayList<RollingDie> mDice;
	   private int bgcolor;
	   
	   
	   
		
	   
	   //constructor
	   public DiceGamePanel(Context context, int d2, int d4, int d6, int d8, int d10, int d12, int d20)
	   {
	      super(context);
	   
	      // adding the callback (this) to the surface holder to intercept events
	      getHolder().addCallback(this);
	      
	     
	      

	      game = new GameThread(getHolder(), this);
	      bgcolor = Color.BLACK;
	      
mDice = new ArrayList<RollingDie>();
	      
	      int i = 0;
	      for (i=0; i<d2; i++){
	    	  addDie(2);
	      }
	      
	      for (i=0; i<d4; i++){
	    	  addDie(4);
	      }
	      
	      for (i=0; i<d6; i++){
	    	  addDie(6);
	      }
	      
	      for (i=0; i<d8; i++){
	    	  addDie(2);
	      }
	      
	      for (i=0; i<d10; i++){
	    	  addDie(10);
	      }
	      
	      for (i=0; i<d12; i++){
	    	  addDie(12);
	      }
	      
	      for (i=0; i<d20; i++){
	    	  addDie(20);
	      }
	      // make the GamePanel focusable so it can handle events
	      setFocusable(true);
	      
	        
	   }

	   @Override
	   public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	   {
	   }

	   @Override
	   public void surfaceCreated(SurfaceHolder holder)
	   {
		   game.setRunning(true);
		   game.start();
	   }

	   @Override
	   public void surfaceDestroyed(SurfaceHolder holder)
	   {
		   boolean retry = true;
		   while (retry)
		   {
		      try
		      {
		         game.join();
		         retry = false;
		      } catch (InterruptedException e){
		    	  
		         // try again shutting down the thread
		      }
		   }
	   }

	  
	   @Override
	   public boolean onTouchEvent(MotionEvent event)
	   {
		   if (event.getAction() == MotionEvent.ACTION_DOWN)
		   {
			   for(int i = 0; i < mDice.size(); i++)
			   {
				   RollingDie mDie = mDice.get(i);
				   mDie.handleActionDown((int)event.getX(), (int)event.getY());
	
				   if (event.getY() > getHeight() - 50)
				   {
		              game.setRunning(false);
				      ((Activity)getContext()).finish();
				   } else
				   {
				      Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
				   }
			   }
			}
		   if (event.getAction() == MotionEvent.ACTION_MOVE)
		   {
			   // the gestures
			   for(int i = 0; i < mDice.size(); i++)
			   {
				   RollingDie mDie = mDice.get(i);
				   if (mDie.isTouched())
				   {
					   // the droid was picked up and is being dragged
					   mDie.setX((int)event.getX());
					   mDie.setY((int)event.getY());
				   }
			   }
			   
			   
		   }
		   if (event.getAction() == MotionEvent.ACTION_UP)
		   {
			   for(int i = 0; i < mDice.size(); i++)
			   {
				   RollingDie mDie = mDice.get(i);
				   if (mDie.isTouched())
				   { 
					   // touch was released
					   mDie.setTouched(false);
				   }
			   }
		   }
		   
		   return true;
	   }

	   @Override
	   protected void onDraw(Canvas canvas)
	   {
		   canvas.drawColor(bgcolor);
		   
		   Paint holdAreaPaint = new Paint();
		   holdAreaPaint.setColor(Color.RED);
		   canvas.drawRect(0, 0, 200, this.getHeight(), holdAreaPaint);
		   
		   for(int i = 0; i < mDice.size(); i++)
		   {
			   mDice.get(i).draw(canvas);
		   }
	   }
	   
	   public void shake()
	   {
		   for(int i = 0; i < mDice.size(); i++)
		   {
			   mDice.get(i).shake();
		   }
	   }
	   
	   public void addDie(int numSides)
	   {
		   mDice.add(
				   new RollingDie(
						   BitmapFactory.decodeResource(getResources(), R.drawable.placeholderdie),
						   this.getWidth(),
						   this.getHeight(),
						   200,
						   0,
						   numSides) );
	   }
	   
	   

	   public void wrapup()
	   {
		   game.setRunning(false);
		   ((Activity)getContext()).finish();
	   }
	   
	    
	    
}
