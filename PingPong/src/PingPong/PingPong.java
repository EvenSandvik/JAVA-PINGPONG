package PingPong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class PingPong extends Applet implements Runnable, KeyListener{
	final int WIDTH = 800, HEIGHT = 550;
	Thread thread;
	Player player1, player2;
	Ball ball;
	Graphics gfx;
	Image img;
	int score1 = 0, score2 = 0;
	boolean gameStarted = false;
	/**Author @Even Berge Sandvik
	 * 
	 * PingPong main class
	 * 
	 */
	private static final long serialVersionUID = -4170574729049260633L;

	public void init(){
		this.addKeyListener(this);
		this.resize(WIDTH, HEIGHT);
		player1 = new Player(0);
		player2 = new Player(1);
		ball = new Ball();
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		gfx.setColor(Color.white);//Senere sett til bakgrunnbilde?
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		gfx.setColor(Color.black);
		gfx.drawString("Controls: W/S and I/K", 340, 30);
		gfx.drawString(Integer.toString(score1), 40, 30);
		gfx.drawString(Integer.toString(score2), 760, 30);
		if(!gameStarted){
			gfx.drawString("Press Enter to start", 345, 340);	
		}
		if(ball.getX()<-10 || ball.getX()>810){
			if(ball.getX()<-10)
			score2++;
			else if(ball.getX()>810)
				score1++;
			ball = new Ball();
			g.setColor(Color.red);
			g.drawString("Game over maaaaan", 325, 275);
		}
		else{
			player1.draw(gfx);
			player2.draw(gfx);
			ball.draw(gfx);
		}
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics gfx){
		paint(gfx);
		
	}

	public void run() {
		while(true){
			repaint();
			if(gameStarted){
				ball.move();
				player1.move();
				player2.move();
				ball.checkPlayerBallCollision(player1, player2);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//Controls
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			gameStarted = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			player1.setUp(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			player1.setDown(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_I){
			player2.setUp(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_K){
			player2.setDown(true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			player1.setUp(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			player1.setDown(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_I){
			player2.setUp(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_K){
			player2.setDown(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
