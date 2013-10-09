package com.mycompany.training;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;

public class ErrorView extends View {
    String errorMsg;
	Paint paint;
	
    public ErrorView(String error, Context context) {
		super(context);
		
		errorMsg = new String(error);
		if (errorMsg == null)
		    errorMsg = new String("DEFAULT_ERROR_MESSAGE");
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(30);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawText(errorMsg, 0, 100, paint);
	}
}
