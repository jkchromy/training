package com.mycompany.training;

import java.util.Random;

class Ball{
	float ballX;
	float ballY;
	float ballSpeedX;
	float ballSpeedY;
	float ballRadius;
	int width;
	int height;
	enum TYPE { NORMAL, EXPAND, CURLY }
	TYPE type;
	static Random random = new Random();

	Ball(float x, float y, float sX, float sY, float r, int w, int h, TYPE t) {
		ballX=x;
		ballY=y;
		ballSpeedX=sX;
		ballSpeedY=sY;
		ballRadius=r;
		width=w;
		height=h;
		type=t;
	}
	
	Ball(Ball b) {
		ballX=b.ballX;
		ballY=b.ballY;
		ballSpeedX=b.ballSpeedX;
		ballSpeedY=b.ballSpeedY;
		ballRadius=b.ballRadius;
	}

	public void checkBounds() {
		if (ballX < ballRadius) {
			ballX = ballRadius;
			ballSpeedX *= -1;
		}
		if (ballX > width - ballRadius) {
			ballX = width - ballRadius;
			ballSpeedX *= -1;
		}
		if (ballY < ballRadius) {
			ballY = ballRadius;
			ballSpeedY *= -1;
		}
		if (ballY > height - ballRadius) {
			ballY = height - ballRadius;
			ballSpeedY *= -1;
		}
	}

	public void update () {
		switch (type) {
		case NORMAL:
	    break;
		case EXPAND:
		    ballX += ballSpeedX;
		    ballY += ballSpeedY;
			break;
		} 
		checkBounds();
	}

}
