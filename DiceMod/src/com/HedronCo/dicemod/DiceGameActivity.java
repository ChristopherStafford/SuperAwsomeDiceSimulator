package com.HedronCo.dicemod;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class DiceGameActivity extends Activity
{

	/** Called when the activity is first created. */

	private static final String TAG = DiceGameActivity.class.getSimpleName();
	
	// sensor variables
	   private SensorManager mySensorManager;
	   /* Here we store the current values of acceleration, one for each axis */
		private float xAccel;
		private float yAccel;
		private float zAccel;

		/* And here the previous ones */
		private float xPreviousAccel;
		private float yPreviousAccel;
		private float zPreviousAccel;

		/* Used to suppress the first shaking */
		private boolean firstUpdate = true;

		/*What acceleration difference would we assume as a rapid movement? */
		private final float shakeThreshold = 1.5f;
		
		/* Has a shaking motion been started (one direction) */
		private boolean shakeInitiated = false;
		
		private final SensorEventListener mySensorEventListener = new SensorEventListener()
		{
			public void onSensorChanged(SensorEvent se)
			   {
				   updateAccelParameters(se.values[0], se.values[1], se.values[2]);   // (1)
				   if ((!shakeInitiated) && isAccelerationChanged())
				   {                                      // (2) 
					   shakeInitiated = true; 
				   } else if ((shakeInitiated) && isAccelerationChanged())
				   {                              // (3)
					   executeShakeAction();
				   } else if ((shakeInitiated) && (!isAccelerationChanged()))
				   {                           // (4)
					   shakeInitiated = false;
				   }
			    }
			
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			    /* can be ignored in this example */
			}
			
		};
	
	
	private DiceGamePanel panel;
	
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// requesting to turn the title OFF
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set our MainGamePanel as the View
		
		 mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE); // (1)
	      mySensorManager.registerListener(mySensorEventListener, mySensorManager
					.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_NORMAL); // (2)
	      
	      int D2 = 0;
	      int D4 = 0;
	      int D6 = 0;
	      int D8 = 0;
	      int D10 = 0;
	      int D12 = 0;
	      int D20 = 0;
	      
	      Bundle extras = getIntent().getExtras();
	      if (extras != null)
	      {
	    	  D2 = extras.getInt("D2");
	    	  D4 = extras.getInt("D4");
	    	  D6 = extras.getInt("D6");
	    	  D8 = extras.getInt("D8");
	    	  D10 = extras.getInt("D10");
	    	  D12 = extras.getInt("D12");
	    	  D20 = extras.getInt("D20");
	      }
		panel = new DiceGamePanel(this, D2, D4, D6, D8, D10, D12, D20);
		
		
		
		setContentView(panel);
		Log.d(TAG, "View added");
	}

	
	@Override
	protected void onPause() {
		
		super.onPause();
		panel.wrapup();
	}


	@Override
	protected void onDestroy()
	{
		Log.d(TAG, "Destroying...");
		super.onDestroy();
		panel.wrapup();
	}

	@Override
	protected void onStop()
	{
		Log.d(TAG, "Stopping...");
		super.onStop();
		panel.wrapup();
	}
	


	private void executeShakeAction()
	{
		panel.shake();
	}
	
	private void updateAccelParameters(float xNewAccel, float yNewAccel,
			float zNewAccel)
	{
		/* we have to suppress the first change of acceleration, it results from first values being initialized with 0 */
		if (firstUpdate) {  
			xPreviousAccel = xNewAccel;
			yPreviousAccel = yNewAccel;
			zPreviousAccel = zNewAccel;
			firstUpdate = false;
		} else {
			xPreviousAccel = xAccel;
			yPreviousAccel = yAccel;
			zPreviousAccel = zAccel;
		}
		xAccel = xNewAccel;
		yAccel = yNewAccel;
		zAccel = zNewAccel;
	}
   
   private boolean isAccelerationChanged()
   {
		float deltaX = Math.abs(xPreviousAccel - xAccel);
		float deltaY = Math.abs(yPreviousAccel - yAccel);
		float deltaZ = Math.abs(zPreviousAccel - zAccel);
		return (deltaX > shakeThreshold && deltaY > shakeThreshold)
				|| (deltaX > shakeThreshold && deltaZ > shakeThreshold)
				|| (deltaY > shakeThreshold && deltaZ > shakeThreshold);
	}
}
