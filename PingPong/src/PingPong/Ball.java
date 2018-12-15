package PingPong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
	double x, y, xVel, yVel;
	int size, initSpeed;
	Random rand = new Random();
	public float speed = 6;
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
	
	public void checkPlayerBallCollision(Player p1, Player p2){
		
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
public void checkPlayerBallCollision(Player p1, AI p2){
		
		if(x<50 && x>30){
			if(y >= p1.getY()-5 && y <= p1.getY()+85)
			{
				bounceBack(p1);
			}	
		}
		else if(x<750 && x>730){
			if(y >= p2.getY()-5 && y <= p2.getY()+85)
				bounceBack(p2);
		}
		
	}
	
	//TODO: Ball bounces back in a direction according to where it hits the bat.
	//Needs improvement
	private void bounceBack(AbstractPlayer p) {
		/*float center = p.getY()+(p.getHeight()*0.5f);
		yVel = Math.sin(center)*speed;
		if(y<100){
			if(yVel<0){
				yVel = -yVel;
			}
		}
		else{
			if(yVel>0){
				yVel = -yVel;
			}
		}
			
		xVel = Math.cos(center)*speed;
		System.out.println("BounceBack# " + xVel + " : " + yVel);		*/
		xVel=-xVel;
	}
	
	//calculates how the ball bounces off Player
			private void bounceBack(Player p, int side){
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
			x+=1;
		}
		else if(y>540){
			yVel = -yVel;
			x-=1;
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
}
