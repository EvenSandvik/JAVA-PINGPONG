package PingPong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

/**Author @Even Berge Sandvik
 * 
 * PingPong main class
 * 
 */
public class PingPong extends Applet implements Runnable, KeyListener{
	final int WIDTH = 800, HEIGHT = 550; //TODO: make ball move and players placed by WIDHT/HEIGHT
	Thread thread;
	Player player1, player2;
	AI playerAI;
	Ball ball = new Ball();
	Graphics gfx;
	Image img;
	int score1 = 0, score2 = 0;
	boolean gameStarted = false;
	private static final long serialVersionUID = -4170574729049260633L;

	public void init(){
		this.addKeyListener(this);
		this.resize(WIDTH, HEIGHT);
		player1 = new Player(0);//0 is player1, 1 is player2
		player2 = new Player(1);
		playerAI = (AI) new AI(1,ball);
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
			countDown(g, 3);
			ball = new Ball();
			playerAI.setBall(ball);
		}
		else{
			player1.draw(gfx);
			//player2.draw(gfx);
			playerAI.draw(gfx);
			ball.draw(gfx);
		}
		g.drawImage(img, 0, 0, this);
	}

	private void countDown(Graphics g, int tall) {
		g.setColor(Color.red);
		try {
			for(int i = tall; tall<0; tall--){
			count(g, Integer.toString(tall));}//TODO: parse to string
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void count(Graphics g, String message) throws InterruptedException {
			g.drawString(message, 400, 275);
			TimeUnit.MILLISECONDS.sleep(200);
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
				playerAI.moveAI();
				//player2.move();
				
				//ball.checkPlayerBallCollision(player1, player2);
				ball.checkPlayerBallCollision(player1, playerAI);
			}
			try {
				Thread.sleep(7);
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
			gameStarted = !gameStarted;
		}
		player1.keyPressed(e, KeyEvent.VK_W, KeyEvent.VK_S);
		//player2.keyPressed(e, KeyEvent.VK_I, KeyEvent.VK_K);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player1.keyReleased(e, KeyEvent.VK_W, KeyEvent.VK_S);
		//player2.keyReleased(e, KeyEvent.VK_I, KeyEvent.VK_L);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	
}
