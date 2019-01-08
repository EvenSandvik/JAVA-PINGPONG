package PingPong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
	double x, y, xVel, yVel;
	int size, initSpeed;
	Random rand = new Random();
	public float speed = 6;
	boolean magic = false;
	public Ball(){
		x = 400;
		y = 275;
		size = 20;
		initSpeed = 3;
		xVel = randomDirection(initSpeed);
		yVel = randomDirection(initSpeed);
	}
	private int randomDirection(int speed){
		int numb = rand.nextInt(2);
		if(numb == 1) return speed;
		else return -speed;
		
	}
	
	public void checkPlayerBallCollision(AbstractPlayer p1, AbstractPlayer p2){
		
		if(x<50 && x>30){
			if(y >= p1.getY()-5 && y <= p1.getY()+85)
			{
				bounceBack(p1, 0);
			}
		}
		else if(x<765 && x>745){
			if(y >= p2.getY()-5 && y <= p2.getY()+85)
				bounceBack(p2, 1);
		}
		
		
	}

	//calculates how the ball bounces off Player
			private void bounceBack(AbstractPlayer p, int side){
					int center = (int) (p.getY() + (p.getHeight()*0.5f));
					int ballHitPlayer = (int) (y-center);
					
					//prevents ball from having a huge y vector
					if(!(ballHitPlayer < 75 && ballHitPlayer > -75)){
						if(ballHitPlayer<0)
							ballHitPlayer = -75;
						else ballHitPlayer = 75;
					}
						
					xVel = (float) Math.cos(Math.toRadians(ballHitPlayer)) * speed;
		            yVel = (float) Math.sin(Math.toRadians(ballHitPlayer)) * speed;
				if(side==0 && xVel<=0) xVel= -xVel;
				if(side==1 && xVel>=0) xVel= -xVel;
			}
	
	//Moves the ball by adding to the x and y
	public void move(){
		x+=xVel;
		y+=yVel;
		//Bounce of roof and floor.
		if(y<10){
			yVel = -yVel;
			y+=1;
		}
		else if(y>540){
			yVel = -yVel;
			y-=1;
		}
		if(magic){
			speed=3;
			if(rand.nextInt(2)==1){
				yVel+=0.2;
			}
			else{
				yVel-=0.2;
			}
		}
	}
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.drawRect((int)x-10, (int)y-10, size, size);
	}
	public int getX(){
		return (int) x;
	}
	public int getY(){
		return (int) y;
	}
	public void magicMove(boolean t) {
		magic = t;
	}
}
