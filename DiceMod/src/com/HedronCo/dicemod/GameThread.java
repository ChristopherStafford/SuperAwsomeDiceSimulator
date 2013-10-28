package com.HedronCo.dicemod;

import android.view.SurfaceHolder;
import android.graphics.Canvas;
import android.util.Log;

public class GameThread extends Thread
{
	//variables
	private static final String TAG = GameThread.class.getSimpleName();
	
   private boolean mRunning;
   private SurfaceHolder mSurfaceHolder;
   private DiceGamePanel mGamePanel;
   
   //constructor
   public GameThread(SurfaceHolder surfaceHolder, DiceGamePanel gamePanel)
   {
	   super();
	   this.mSurfaceHolder = surfaceHolder;
	   this.mGamePanel = gamePanel;
   }
   
   //functions
   public void setRunning(boolean running)
   {
      this.mRunning = running;
   }

   @Override
   public void run()
   {
	   Canvas canvas;
	   

	   long tickCount = 0L;
       Log.d(TAG, "Starting game loop");
          
       while (mRunning)
       {
          tickCount++;
          canvas = null;
          
          try
          {
        	  canvas = this.mSurfaceHolder.lockCanvas();
        	  synchronized (mSurfaceHolder)
        	  {
        		  
        		  // update game state
        		  for(int i = 0; i < this.mGamePanel.mDice.size(); i++)
        		  {
   				   		RollingDie mDie =  this.mGamePanel.mDice.get(i);
   				   		mDie.update(this.mGamePanel.getHeight(),  this.mGamePanel.getWidth());
        		  // render state to the screen
        		  }
        		  
        		  this.mGamePanel.onDraw(canvas);
        	  }
          } finally
          {
        	  if(canvas != null)
        	  {
        		  mSurfaceHolder.unlockCanvasAndPost(canvas);
        	  }
          }
          
          
       }
          
       Log.d(TAG, "Game loop executed " + tickCount + " times");
   }
}
