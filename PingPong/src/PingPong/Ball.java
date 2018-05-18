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
		xVel = -2;
		yVel = 4;
	}
	public int randomDirection(){
		int direction = rand.nextInt(2);
		if(direction == 1)
			return 1;
		return -1;
		
	}
	public int randomDestination(){
		int destination = rand.nextInt(3);
		return destination;
	}
	
	public void checkPlayerBallCollision(Player p1, Player p2){
		float center = p1.getY()+40; 
		if(x<50){
			if(y >= p1.getY() && y <= p1.getY()+80)
			{
					/*if(y<=center+10 && y>=center-10){
						yVel = yVel*0;
						xVel = (xVel/xVel)*5;
					}
					else if(y>center+10 && y<center+20 )
					{
						yVel = 3;
						xVel = (xVel/xVel)*2;
					}
					else if(y>=center+20)
					{
						yVel = 4;
						xVel = (xVel/xVel)*1;
					}
					else if(y>center-10 && y<center-20 )
					{
						yVel = -3;
						xVel = -(xVel/xVel)*2;
					}
					else if(y>=center-20)
					{
						yVel = -4;
						xVel = -(xVel/xVel)*1;
					}*/
				xVel = -xVel;
			}
				
			
		}
		if(x>750){
			if(y >= p2.getY() && y <= p2.getY()+80)
				xVel = -xVel;
		}
		
	}
	
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
