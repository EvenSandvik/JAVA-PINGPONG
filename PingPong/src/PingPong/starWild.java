package PingPong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class starWild {
	int x, y, size, n;
	Ball ball;
	Random rnd = new Random();
	
	public starWild(Ball b){
		size = 30;
		ball = b;
		x = rnd.nextInt(400) + 200;
		y = rnd.nextInt(500)+ 20;
		//y = 100;
		//x = 100;
	}
	
	public void draw(Graphics g){
		checkCollision();
		n = rnd.nextInt(4) + 1;
		rainbow(g);
		g.fillRect(x, y, size, size);
	}

	void rainbow(Graphics g) {
		if(n==0) g.setColor(Color.decode("#8bf4df"));
		else if(n==1)g.setColor(Color.decode("#f48bf4"));
		else if(n==2)g.setColor(Color.decode("#f4cc8b"));
		else if(n==3)g.setColor(Color.decode("#8bf4ae"));
		else if(n==4)g.setColor(Color.decode("#f9ff63"));
	}
	
	private void checkCollision(){
		if(ball.y >= y && ball.y<= y + size  || ball.y + ball.size >= y && ball.y+ball.size<= y + size){
     		if(ball.x >= x && ball.x <= x + size || ball.x + ball.size >= x && ball.x+ball.size<= x + size){
     			x = rnd.nextInt(400) + 200;
     			y = rnd.nextInt(500)+ 20;
     			ball.magicMove(true);
     		}
     		
     	}
	}
}
