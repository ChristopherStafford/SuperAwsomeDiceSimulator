package com.HedronCo.dicemod;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

public class RollingDie
{
	private boolean mTouched;
	private int mX;
	private int mY;
	
	private double mXSpeed;
	private double mYSpeed;
	
	private int mNumSides;
	private int mNum;
	
	private double mWallDecay;
	private double mTimeDecay;
	
	private Random rand;
	
	private int mToNextRand;
	private boolean mRolling;
	
	private int mStartSpeed;
	
	private int DICE_SIZE_X = 50;
	private int DICE_SIZE_Y = 50;
	private int mOutlineSize = 5;
	private Paint mOutlineColor;
	private Paint mWhite;
	private Paint mBlack;

	
	public RollingDie(int sides)
	{
		this.mTouched = false;
		this.rand = new Random();

		
		mWhite = new Paint();
		mWhite.setColor(Color.WHITE); 
		mWhite.setStyle(Style.FILL);
		
		mBlack = new Paint();
		mBlack.setColor(Color.BLACK); 
		mBlack.setStyle(Style.FILL);
		mBlack.setTextSize(50);
		mBlack.setTextAlign(Align.CENTER);
		
		mOutlineColor = new Paint();
		mOutlineColor.setStyle(Style.FILL);
		mOutlineColor.setTextSize(50);
		switch(sides)
		{
		case 2:
			mOutlineColor.setColor(Color.LTGRAY);
			break;
		case 4:
			mOutlineColor.setColor(Color.RED);
			break;
		case 6:
			mOutlineColor.setColor(Color.BLUE);
			break;
		case 8:
			mOutlineColor.setColor(Color.GREEN);
			break;
		case 10:
			mOutlineColor.setColor(Color.MAGENTA);
			break;
		case 12:
			mOutlineColor.setColor(Color.YELLOW);
			break;
		case 20:
			mOutlineColor.setColor(Color.CYAN);
			break;
		default:
			mOutlineColor.setColor(Color.BLACK);
			break;
		}
		
		
		this.mX = 400;
		this.mY = 50;
		
		this.mXSpeed = 0;
		this.mYSpeed = 0;
		
		this.mNum = 1;
		this.mNumSides = sides;
		
		this.mWallDecay = .8;
		this.mTimeDecay = .99;
		
		
		this.mToNextRand = 0;
		this.mRolling = false;
		this.mStartSpeed = 100;
	}
	
	public void update(int height, int width)
	{
		
		
		if(mX + (DICE_SIZE_X/2) > width) // right wall
		{
			mX = width - (DICE_SIZE_X/2);
			this.mXSpeed = mXSpeed * -mWallDecay;
		}
		
		if( mX - DICE_SIZE_X / 2 < 200) // left wall
		{
			if( mRolling)
			{
				mX = 200 + DICE_SIZE_X/2;
				this.mXSpeed = mXSpeed * -mWallDecay;
			}
		}
		
		if( mY + (DICE_SIZE_Y/2) > height) // bot wall
		{
			mY = height - (DICE_SIZE_Y/2);
			this.mYSpeed = mYSpeed * -mWallDecay;
		}
		
		if(mY - (DICE_SIZE_Y / 2) < 0)// top wall
		{
			mY = DICE_SIZE_Y / 2;
			this.mYSpeed = mYSpeed * -mWallDecay;
		}
		
		mX += mXSpeed;
		mY += mYSpeed;
		
		mYSpeed *= mTimeDecay;
		mXSpeed *= mTimeDecay;
		
		if(mXSpeed < 1 && mXSpeed > -1 && mYSpeed < 1 && mYSpeed > -1)
		{
			mRolling = false;
			mXSpeed = 0;
			mYSpeed = 0;
		}
		
		if(mRolling)
		{
			mToNextRand--;
			if(mToNextRand <= 0)
			{
				mToNextRand = 10;
				this.genNum();
			}
		}
		
	}
	

	
	public int getX() { return mX; }
	public void setX(int x) { this.mX = x; }
	
	public int getY() { return mY; }
	public void setY(int y)	{ this.mY = y; }
	
	public boolean isTouched(){ return mTouched; }
	public void setTouched(boolean tf){ this.mTouched = tf; }
	
	public void draw(Canvas canvas)
	{

		
		
		canvas.drawRect( mX - (DICE_SIZE_X / 2), mY - (DICE_SIZE_Y / 2), mX + (DICE_SIZE_X / 2), mY + (DICE_SIZE_Y / 2), mOutlineColor);
		canvas.drawRect( mX - (DICE_SIZE_X / 2) + mOutlineSize, mY - (DICE_SIZE_Y / 2) + mOutlineSize, mX + (DICE_SIZE_X / 2) - mOutlineSize, mY + (DICE_SIZE_Y / 2) - mOutlineSize, mWhite);
		//canvas.drawRect( mX - (DICE_SIZE_X / 2) + mOutlineSize, mY - (DICE_SIZE_Y / 2) + mOutlineSize, DICE_SIZE_X - mOutlineSize, DICE_SIZE_Y - mOutlineSize, mWhite);
		//canvas.drawBitmap(mSprite, mX - (mSprite.getWidth() / 2), mY - (mSprite.getHeight() / 2), null);
		canvas.drawText(""+mNum, mX, mY + (DICE_SIZE_Y / 2) - mOutlineSize, mBlack);
	}
	
	public void handleActionDown(int eventX, int eventY)
	{
		if (eventX >= (mX - (DICE_SIZE_X / 2)) && (eventX <= (mX + (DICE_SIZE_X / 2))) )
		{
			if (eventY >= (mY - (DICE_SIZE_Y / 2)) && (eventY <= (mY + (DICE_SIZE_Y / 2))))
			{
				// droid touched
				setTouched(true);
			} else
			{
				setTouched(false);
			}
		} else {
			setTouched(false);
		}
	 
	}
	
	public void genNum()
	{
		mNum = rand.nextInt(mNumSides - 0) + 1;
	}
	
	public void shake()
	{
		if(!mRolling && !mTouched && mX > 200)
		{
			mRolling = true;
			mXSpeed = rand.nextInt(mStartSpeed) - (mStartSpeed / 2);
			mYSpeed = rand.nextInt(mStartSpeed) - (mStartSpeed / 2);
			mToNextRand = 10;
		}
	}
	
}
