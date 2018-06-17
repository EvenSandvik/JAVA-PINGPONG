package PingPong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
	double x, y, xVel, yVel;
	Random rand = new Random();
	public Ball(){
		x = 400;
		y = 275;
		int initSpeed = 3;
		xVel = randomDirection(initSpeed);
		yVel = randomDirection(initSpeed);
	}
	private int randomDirection(int speed){
		int numb = rand.nextInt(2);
		if(numb == 1) return speed;
		else return -speed;
		
	}
	
	public void checkPlayerBallCollision(Player p1, Player p2){
		
		if(x<50 && x>45){
			if(y >= p1.getY()-5 && y <= p1.getY()+85)
			{
				bounceBack(p1);
			}	
		}
		else if(x<750 && x>745){
			if(y >= p2.getY()-5 && y <= p2.getY()+85)
				bounceBack(p2);
		}
		
	}
public void checkPlayerBallCollision(Player p1, AI p2){
		
		if(x<50 && x>45){
			if(y >= p1.getY()-5 && y <= p1.getY()+85)
			{
				bounceBack(p1);
			}	
		}
		else if(x<750 && x>745){
			if(y >= p2.getY()-5 && y <= p2.getY()+85)
				bounceBack(p2);
		}
		
	}
	
	//TODO: Ball bounces back in a direction according to where it hits the bat.
	//Needs improvement
	private void bounceBack(AbstractPlayer p) {
		float center = p.getY()+40;
		
		float scalar = (float) ((y-center)/1);
		if(((scalar*scalar)/scalar)<0.3f) scalar = 0.3f;
		System.out.println("#Ball.bounceback() " + scalar + " by:" + p);
		if(y>center)
		yVel = 2*scalar;
		else
			yVel = -2*scalar;
		if(scalar<0) scalar = - scalar;
		if(xVel<0)
			xVel = 2/scalar;
		else if(xVel>0)
			xVel = -2/scalar;
	}
	
	//Moves the ball by adding to the x and y
	public void move(){
		x+=xVel;
		y+=yVel;
		//Bounce of roof and floor.
		if(y<10)
			yVel = -yVel;
		else if(y>540)
			yVel = -yVel;
	}
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillOval((int)x-10, (int)y-10, 20, 20);//-10 for center of circle
	}
	public int getX(){
		return (int) x;
	}
	public int getY(){
		return (int) y;
	}
}
