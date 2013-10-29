package com.HedronCo.dicemod;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends ListActivity
{

	String classes[] = {	"Instructions",
							"SelectDiceActivity",
							"SelectDiceActivity",
							"SelectDiceActivity",
							"SelectDiceActivity"
							
							};
	String menuItems[] = {	"Instructions",
							"Roleplaying",
							"Yatzee",
							"Coin Flip",
							"Custom Set",
							
							
							};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(MainMenu.this, android.R.layout.simple_list_item_1, menuItems));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		// TODO Auto-generated method stub
		
		
		super.onListItemClick(l, v, position, id);
		String item = classes[position];
		Intent gameIntent = new Intent(getApplicationContext(), DiceGameActivity.class);
		switch (position)
		{
		case 1: //dnd
			
			gameIntent.putExtra("D2", 0);
			gameIntent.putExtra("D4", 1);
			gameIntent.putExtra("D6", 1);
			gameIntent.putExtra("D8", 1);
			gameIntent.putExtra("D10", 1);
			gameIntent.putExtra("D12", 1);
			gameIntent.putExtra("D20", 1);
			startActivity(gameIntent);
			break;
			
		case 2: //yatzee
			gameIntent.putExtra("D2", 0);
			gameIntent.putExtra("D4", 0);
			gameIntent.putExtra("D6", 5);
			gameIntent.putExtra("D8", 0);
			gameIntent.putExtra("D10", 0);
			gameIntent.putExtra("D12", 0);
			gameIntent.putExtra("D20", 0);
			startActivity(gameIntent);
			break;
			
		case 3://coin
			gameIntent.putExtra("D2", 1);
			gameIntent.putExtra("D4", 0);
			gameIntent.putExtra("D6", 0);
			gameIntent.putExtra("D8", 0);
			gameIntent.putExtra("D10", 0);
			gameIntent.putExtra("D12", 0);
			gameIntent.putExtra("D20", 0);
			startActivity(gameIntent);
			break;
			
		default://other
			try
			{
				Class theClass = Class.forName("com.HedronCo.dicemod." + item);
				Intent menuIntent = new Intent(MainMenu.this, theClass);
				startActivity(menuIntent);
				
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} 
				
			break;
		};
		
		
		
		
	}
	

}
