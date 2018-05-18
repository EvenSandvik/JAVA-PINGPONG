package PingPong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Player implements Bat{
	double y, yVel;
	int x, player;
	boolean up, down;
	private Image batImage = null;
	
	public Player(int player){
		up = false; down = false;
		y = 210; yVel = 0;
		
		if(player == 0)
			x = 20;
		else
			x=760;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, (int)y, 20, 80);
		/*try{
			URL imageURL = Player.class.getResource(path);
		}
		g.drawImage(batImage, x, (int)y, 20, 80, null);*/
	}

	public void move() {
		
		if(up)
			yVel -= 0.2;
		else if(down)
			yVel += 0.2;
		else
			yVel = 0;
		/*if(yVel>4)
			y = 4;
		else if(yVel<=-4)
			y = -4;*/
		
		y += yVel;
			
		if(y<0) 
			y=0;
		if(y>470)
			y=470;
	}

	public int getY() {
		return (int) y;
	}
	
	public void setDown(boolean bool){
		down = bool;
	}
	
	public void setUp(boolean bool){
		up = bool;
	}
	
}
