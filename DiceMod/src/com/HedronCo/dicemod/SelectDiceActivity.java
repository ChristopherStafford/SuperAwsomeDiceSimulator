package com.HedronCo.dicemod;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectDiceActivity extends Activity {
	
	int mNumD2;
	int mNumD4;
	int mNumD6;
	int mNumD8;
	int mNumD10;
	int mNumD12;
	int mNumD20;
	
	Button addD2;
	Button subtractD2;
	TextView D2Text;
	TextView numD2Text;
	
	Button addD4;
	Button subtractD4;
	TextView D4Text;
	TextView numD4Text;
	
	Button addD6;
	Button subtractD6;
	TextView D6Text;
	TextView numD6Text;
	
	Button addD8;
	Button subtractD8;
	TextView D8Text;
	TextView numD8Text;
	
	Button addD10;
	Button subtractD10;
	TextView D10Text;
	TextView numD10Text;
	
	Button addD12;
	Button subtractD12;
	TextView D12Text;
	TextView numD12Text;
	
	Button addD20;
	Button subtractD20;
	TextView D20Text;
	TextView numD20Text;
	
	Button startButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_dice);
		
		mNumD2 = 0;
		addD2 = (Button) findViewById(R.id.bAddD2);
		subtractD2 = (Button) findViewById(R.id.bSubtractD2);
		numD2Text = (TextView) findViewById(R.id.numD2);
		D2Text = (TextView) findViewById(R.id.D2Text);
		
		addD2.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD2 < 10)
				{
					mNumD2++;
					numD2Text.setText("" + mNumD2);
				}
				
			}
		});
		subtractD2.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD2 > 0)
				{
					mNumD2--;
					numD2Text.setText("" + mNumD2);
				}
			}
		});
		
		
		
		mNumD4 = 0;
		addD4 = (Button) findViewById(R.id.bAddD4);
		subtractD4 = (Button) findViewById(R.id.bSubtractD4);
		numD4Text = (TextView) findViewById(R.id.numD4);
		D4Text = (TextView) findViewById(R.id.D4Text);
		
		addD4.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD4 < 10)
				{
					mNumD4++;
					numD4Text.setText("" + mNumD4);
				}
				
			}
		});
		
		subtractD4.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD4 > 0)
				{
					mNumD4--;
					numD4Text.setText("" + mNumD4);
				}
			}
		});
		
		
		mNumD6 = 0;
		addD6 = (Button) findViewById(R.id.bAddD6);
		subtractD6 = (Button) findViewById(R.id.bSubtractD6);
		numD6Text = (TextView) findViewById(R.id.numD6);
		D6Text = (TextView) findViewById(R.id.D6Text);
		
		addD6.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD6 < 10)
				{
					mNumD6++;
					numD6Text.setText("" + mNumD6);
				}
				
			}
		});
		
		subtractD6.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD6 > 0)
				{
					mNumD6--;
					numD6Text.setText("" + mNumD6);
				}
			}
		});
	
	
		mNumD8 = 0;
		addD8 = (Button) findViewById(R.id.bAddD8);
		subtractD8 = (Button) findViewById(R.id.bSubtractD8);
		numD8Text = (TextView) findViewById(R.id.numD8);
		D8Text = (TextView) findViewById(R.id.D8Text);
		
		addD8.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD8 < 10)
				{
					mNumD8++;
					numD8Text.setText("" + mNumD8);
				}
				
			}
		});
		
		subtractD8.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD8 > 0)
				{
					mNumD8--;
					numD8Text.setText("" + mNumD8);
				}
			}
		});
		
		
		mNumD10 = 0;
		addD10 = (Button) findViewById(R.id.bAddD10);
		subtractD10 = (Button) findViewById(R.id.bSubtractD10);
		numD10Text = (TextView) findViewById(R.id.numD10);
		D10Text = (TextView) findViewById(R.id.D10Text);
		
		addD10.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD10 < 10)
				{
					mNumD10++;
					numD10Text.setText("" + mNumD10);
				}
				
			}
		});
		
		subtractD10.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD10 > 0)
				{
					mNumD10--;
					numD10Text.setText("" + mNumD10);
				}
			}
		});
		

		mNumD12 = 0;
		addD12 = (Button) findViewById(R.id.bAddD12);
		subtractD12 = (Button) findViewById(R.id.bSubtractD12);
		numD12Text = (TextView) findViewById(R.id.numD12);
		D12Text = (TextView) findViewById(R.id.D12Text);
		
		addD12.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD12 < 10)
				{
					mNumD12++;
					numD12Text.setText("" + mNumD12);
				}
				
			}
		});
		
		subtractD12.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD12 > 0)
				{
					mNumD12--;
					numD12Text.setText("" + mNumD12);
				}
			}
		});
		
	
		
		mNumD20 = 0;
		addD20 = (Button) findViewById(R.id.bAddD20);
		subtractD20 = (Button) findViewById(R.id.bSubtractD20);
		numD20Text = (TextView) findViewById(R.id.numD20);
		D20Text = (TextView) findViewById(R.id.D20Text);
		
		addD20.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD20 < 10)
				{
					mNumD20++;
					numD20Text.setText("" + mNumD20);
				}
				
			}
		});
		
		subtractD20.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				if(mNumD20 > 0)
				{
					mNumD20--;
					numD20Text.setText("" + mNumD20);
				}
			}
		});
		
		startButton = (Button) findViewById(R.id.StartGame);
		
		startButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				Intent gameIntent = new Intent(getApplicationContext(), DiceGameActivity.class);
				//gameIntent.putExtra("new_variable_name", "value");
				gameIntent.putExtra("D2", mNumD2);
				gameIntent.putExtra("D4", mNumD4);
				gameIntent.putExtra("D6", mNumD6);
				gameIntent.putExtra("D8", mNumD8);
				gameIntent.putExtra("D10", mNumD10);
				gameIntent.putExtra("D12", mNumD12);
				gameIntent.putExtra("D20", mNumD20);
				startActivity(gameIntent);
			}
		});
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_dice, menu);
		return true;
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    // Read values from the "savedInstanceState"-object and put them in your textview
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    // Save the values you need from your textview into "outState"-object
	    super.onSaveInstanceState(outState);
	}

}
