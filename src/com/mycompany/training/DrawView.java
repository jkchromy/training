package com.mycompany.training;

import android.view.View;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;
//import java.lang.Math;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
//import android.os.Parcelable;

public class DrawView extends View {
    Paint paint;
	static Random random = new Random();
	MainActivity mA;
	List<Ball> balls;
	List<Ball> sparkles;
	List<Ball> addedBalls;
	int updateCount;
	float maxMem;
	float totalMem;
	float freeMem;
	int procs;
	
    DrawView (Context context) {
		super(context);
		mA = (MainActivity)context;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setTextSize(50);
		balls = new ArrayList<Ball>();
		sparkles = new ArrayList<Ball>();
		addedBalls = new ArrayList<Ball>();
	    //balls.add(new Ball(100, 100, 1, 1, 10, getWidth(), getHeight(), Ball.TYPE.NORMAL));
		updateCount = 0;
		//java.lang.System.console().printf("test");
	}
	
    @Override
	public void onDraw(Canvas canvas) {
//		canvas.drawText("DrawView", 0, 100, paint);
		//if (updateCount%20==0) {
		//canvas.drawText("ballSpeedX: " + ballSpeedX, 0, 200, paint);
		//canvas.drawText("ballSpeedY: " + ballSpeedY, 0, 300, paint);
		//canvas.drawText("ballX: " + ballX, 0, 400, paint);
		//canvas.drawText("ballY: " + ballY, 0, 500, paint);
		canvas.drawText("#balls: " + balls.size(), 0, 600, paint);
//		canvas.drawText("updateCount: " + updateCount, 0, 700, paint);
		for (Ball b:balls)
			canvas.drawCircle(b.ballX, b.ballY, b.ballRadius, paint);
		
	/*	float textSize = paint.getTextSize();
		paint.setTextSize(30);
		drawClass(canvas, paint, paint.getTextSize());
		//canvas.drawText(toString(), 0, 800, paint);
		paint.setTextSize(textSize);*/
		
//		canvas.drawText("totalMem: " + totalMem, 0, 900, paint);
//		canvas.drawText("freeMem: " + freeMem, 0, 1000, paint);
//		canvas.drawText("procs: " + procs, 0, 1100, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: break;
		case MotionEvent.ACTION_UP: break;
		default:
		switch (random.nextInt(3)) {
		case 0:
		addedBalls.add(new Ball(event.getX(), event.getY(),
				random.nextFloat()*2-1,
				random.nextFloat()*2-1, 10,
				getWidth(), getHeight(), Ball.TYPE.EXPAND));
		break;
		case 1:
		case 2:
		    addedBalls.add(new Ball(event.getX(), event.getY(),0 , 0, 10,
				getWidth(), getHeight(), Ball.TYPE.NORMAL));
		    } break;
		}
		return super.onTouchEvent(event);
	}
	
	public void update() {
		updateCount++;
		for (Ball b: balls) {
		    b.update();
			if (b.type == Ball.TYPE.EXPAND && b.ballRadius > 1 && balls.size() < 100) {
				if (updateCount % 10 == 0) {
					try {
					    sparkles.add(sparkle(b));
					}
					catch (ConcurrentModificationException e) {} // TODO: This is the fatal error being thrown.
				}		
			}
		}
		balls.addAll(sparkles);
		sparkles.clear();
		balls.addAll(addedBalls);
		addedBalls.clear();
		this.postInvalidate();
		
		/*this.postInvalidate((int)Math.ceil(ballX-ballRadius),
		                    (int)Math.ceil(ballY-ballRadius),
							(int)Math.ceil(ballX+ballRadius),
							(int)Math.ceil(ballY+ballRadius));*/
	}
	
	/*public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("balls.sav");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    for (Ball b:balls) {
				oos.writeObject(b);
			}
			oos.close();
			fos.close();
		}
		catch (IOException e) {
			mA.onError(e.getMessage());
		}
	}*/
	
	/*public void load() {
		try {
		    FileInputStream fis = new FileInputStream("balls.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (ois.available()>0) {
				balls.add(new Ball((Ball)ois.readObject()));
			}
		}
		catch (IOException e) {
			mA.onError(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			mA.onError(e.getMessage());
		}
	}*/
	
	/*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getGroupId()) {
        case R.layout
        default:
        return super.onOptionsItemSelected(item);
       }
    }*/
	
	private Ball sparkle(Ball b) {
		return new Ball(b.ballX, b.ballY,
		                   random.nextFloat()*6-3,
						   random.nextFloat()*6-3, 1,
						   getWidth(), getHeight(), Ball.TYPE.EXPAND);
	}
	
	private void drawClass(Canvas canvas, Paint paint, float textHeight) {
		int rowCount = 1;
        int colCount = 0;
        float textWidth = 15;
		canvas.drawText(getContentDescription(), 0, getContentDescription().length(),
                        0, textHeight, paint);
        
		/*for (Field f:getClass().getFields()) {
			canvas.drawText(f.toString(), 0, textHeight*rowCount, paint);
			rowCount++;
		}
		rowCount = 1;
		float colPos = getWidth()/4;
		for (Method m:getClass().getMethods()) {
			canvas.drawText(m.toString(), colPos, textHeight*rowCount, paint);
			rowCount++;
			if (textHeight*rowCount>getHeight()) {
				colPos+=getWidth()/4;
				rowCount=1;
			}
		}*/
	}
}
