package com.mycompany.training;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.*;
import java.lang.Runtime;
import android.util.AndroidException;
import android.util.AndroidRuntimeException;
import java.io.PrintWriter;
import java.io.*;

public class MainActivity extends Activity
{
	DrawView drawView;
	Timer timer;
	
	class Task extends TimerTask {
		@Override
		public void run() {
		    drawView.update();
		}
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		drawView = new DrawView(this);
		timer = new Timer();
		timer.schedule(new Task(), 0, 1000/60);
		setContentView(drawView);
		/*Logger logger = Logger.getLogger("MyLog");
		try {
			FileHandler fh = new FileHandler("mnt/sdcard/appprojects/training/log.txt");
			logger.addHandler(fh);
			logger.info("testing");
		}
		catch (IOException e) {
		//TODO	onError(e.getMessage());
		}*/
        //setContentView(R.layout.main);
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		drawView.onTouchEvent(event);
		
		return super.onTouchEvent(event);
	}
	
	/*@Override
	public void onWindowFocusChanged(false) {
		drawView.save();
		super.onWindowFocusChanged(false);
	}
	
	@Override
	public void onWindowFocusChanged(true) {
		drawView.load();
		super.onWindowFocusChanged(true);
	}*/
	
	public void onError(String error) {
		setContentView(new ErrorView(error, this));
	}
	
	@Override
	public void onLowMemory() {
		onError("MEMORY LOW");
		super.onLowMemory();
	}
}
