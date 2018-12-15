package PingPong;

import java.awt.Color;
import java.awt.Graphics;
/**
 * 
 * @author Even Berge Sandvik
 * Class contains implementations useful for Player and AI's
 */
public class AbstractPlayer implements Bat {

	double y, yVel;
	int x, player;
	boolean up, down;
	int width = 20;
	int height = 80;
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, (int)y, width, height);
	}

public void move() {
		if(up)
			y = y - 4;
		else if(down)
			y = y + 4;
			
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
	
	public void moveUp(){
		setUp(true);
		setDown(false);
	}
	public void moveDown(){
		setUp(false);
		setDown(true);
	}

	public int getHeight(){
		return height;
	}
}
