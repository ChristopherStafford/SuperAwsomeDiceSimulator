package com.HedronCo.dicemod;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity
{

	MediaPlayer bgm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		//play some bgm
		bgm = MediaPlayer.create(Splash.this, R.raw.duel_event);
		bgm.start();
		
		
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(4000); // 1000 = 1 second
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				} finally
				{
					Intent openMainMenu = new Intent("com.HedronCo.dicemod.MAINMENU");
					startActivity(openMainMenu);
				}
			}
			
		};
		
		timer.start();
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		bgm.release();
		finish();
	}
	
	@Override
	public void onBackPressed() {
	}
	
	
	
	
}
